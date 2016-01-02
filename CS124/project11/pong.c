	//******************************************************************************
	//  Lab 11 - Pong
	//
	//  Description:
	//
	//	"Write a C program to play a 2-player game of Pong, a two-dimensional sports
	//	game which simulates table tennis. Use the potentiometers to control the
	//	paddles moving vertically on each side of the screen. The paddles and walls
	//	reflect a ball back and forth at the correct speed and reflection angle.
	//	A point is scored when one side fails to return the ball to the other side.
	//	Show the score in real time on the LCD display.
	//
	//	"Divide the paddle into segments to change the ball's angle of return.
	//	For example, the center segments return the ball a 90° angle in relation to
	//	the paddle, while the outer segments return the ball at smaller angles.
	//	Make the ball accelerate the more it is returned back and forth between
	//	paddles; missing the ball resets the speed." 
	//
	//  Author:		Paul Roper, Brigham Young University
	//  Revisions:	March 2010	Original code
	//				11/2010		Use TIMERB1_VECTOR
	//				07/28/2011	Added outTone, WDT_sleep
	//
	//  Built with Code Composer Studio Version: 4.2.3.00004 
	//*******************************************************************************
	//   Set eZ430X jumpers as follows:
	//
	//      A  P4.4     Left Pot  O--O  O  Servo #1
	//      B  P4.5       Buzzer  O--O  O  Servo #2
	//      C  P4.6   Thermistor  O  O--O  LED_4
	//      D  P2.2  ADXL345 INT  O  O--O  SW_3
	//      E  P2.3         SW_4  O--O  O  Servo #3 
	//      F  P2.4    Backlight  O--O  O  Servo #4
	//      G  P4.3    Right Pot  O--O  O  EZ0_AN
	//      H  P2.1         SW_2  O--O  O  EZ0_TX
	//
	//******************************************************************************
	//
	#include "msp430x22x4.h"
	#include <stdlib.h>
	#include "eZ430X.h"
	#include "lcd.h"
	#include "adc.h"
	#include "pong.h"
	
	//------------------------------------------------------------------------------
	// INITIALIZE SYSTEM CONSTANTS
	//
	#define myCLOCK		12000000			// clock speed
	#define WDT_CLK		32000				// 32 Khz WD clock (@1 Mhz)
	#define	WDT_CTL		WDT_MDLY_32			// WDT SMCLK, ~32ms
	#define	WDT_CPS		myCLOCK/WDT_CLK		// WD clocks / second count
	#define Freq_A2  myCLOCK/440/2/2     //the Freqeuncy of A - 440 hz
	#define Freq_D	 myCLOCK/587/2/2	 //the frequency of D - 293.66 hz
	#define Freq_A   myCLOCK/880/2/2 	 //the frequency of HiA-880.0
	#define Freq_G	 myCLOCK/784/2/2     //the frequency of G - 783.99
	#define note_A	 CLOCK/Freq_A        
	#define note_D	 CLOCK/Freq_D
	#define note_G	 CLOCK/Freq_G
	#define note_A2	 CLOCK/Freq_A2
	
	//------------------------------------------------------------------------------
	// Pong defines and constants
	//
	#define BALL_SPEED		10
	#define POT_THRESHHOLD	3
	
	#define BEEP			myCLOCK/4000	// beep frequency
	#define BEEP_CNT		myCLOCK/2000000	// beep duration
	
	//------------------------------------------------------------------------------
	// interrupt variables
	volatile uint16 WDT_cps_cnt;			// WDT count per second
	volatile uint16 WDT_delay;				// WDT delay counter
	volatile uint16 WDT_Tone_Cnt;			// WDT tone counter
	volatile float ballSpeed;
	
	extern const unsigned char byu_image[];	// BYU logo
	void doTone(int tone, int time);		// output tone
	void startOver(void); 					//stars the game over when someone looses
	void resetScore();
	void BallSpeed();
	void WDT_sleep(int sleep);				// sleep
	volatile int scoreRight;
	volatile int scoreLeft;
	void leftPlayer();
	void rightPlayer();
	void congratulations(void);
	
	//------------------------------------------------------------------------------
	//
	void main(void)
	{
		// 	MSP430 Clock - Set DCO to 12 MHz:
		eZ430X_init(_12MHZ);				// init board
	
		// configure Watchdog
		WDT_cps_cnt = WDT_CPS;				// set WD 1 second counter
		WDT_delay = 0;						// no delay
		WDT_Tone_Cnt = 0;					// no tone
		WDTCTL = WDT_CTL;					// Set Watchdog interval to ~32ms
		IE1 |= WDTIE;						// Enable WDT interrupt
	
		// configure other peripherals
		lcd_init();							// init LCD
		ADC_init();							// init ADC
	
		// configure TimerB to PWM speaker
		P4SEL |= 0x20;						// P4.5 TB2 output
		TBR = 0;							// reset timer B
		TBCTL = TBSSEL_2 | ID_0 | MC_1;		// SMCLK, /1, UP (no interrupts)
		TBCCTL2 = OUTMOD_3;					// TB2 = set/reset
	
		__bis_SR_register(GIE);				// enable interrupts
	
		//-----------------------------------------------------------
		//	startup
		lcd_image(byu_image, 40, 11);		// display BYU
		lcd_backlight(ON);					// turn on LCD
		lcd_cursor(40, 6);					// set display coordinates
		lcd_display(LCD_2X_FONT);			// turn 2x font on
		lcd_printf("P O N G");
		lcd_display(~LCD_2X_FONT);			// turn 2x font off
		WDT_sleep(WDT_CPS*2);				// pause 2 seconds
		lcd_cursor(40, 6);					// set display coordinates
		lcd_display(LCD_2X_FONT);			// turn 2x font on
		lcd_printf("            ");
		lcd_display(~LCD_2X_FONT);			// turn 2x font off
		lcd_cursor(30, 6);					// set display coordinates
		lcd_printf("PRESS 1 TO START");
		while(!((P2IN & 0x01)^0x01)) 
		lcd_cursor(30, 6);					// set display coordinates
		lcd_printf("                 ");
		lcd_display(LCD_2X_FONT);			// turn 2x font on
		lcd_cursor(79, 6);					// set display coordinates
		lcd_printf("3");
		WDT_sleep(WDT_CPS);				// pause 1/2 seconds
		lcd_cursor(79, 6);					// set display coordinates
		lcd_printf("  ");
		lcd_cursor(79, 6);					// set display coordinates
		lcd_printf("2");
		WDT_sleep(WDT_CPS);				// pause 1/2 seconds
		lcd_cursor(79, 6);					// set display coordinates
		lcd_printf("  ");
		lcd_cursor(79, 6);					// set display coordinates
		lcd_printf("1");
		WDT_sleep(WDT_CPS/2);				// pause 1/2 seconds
		lcd_cursor(79, 6);					// set display coordinates
		lcd_printf("  ");
		lcd_cursor(79, 6);					// set display coordinates
		lcd_printf("GO");
		WDT_sleep(WDT_CPS/2);				// pause 1/2 seconds
		lcd_display(~LCD_2X_FONT);			// turn 2x font off
	
		// count down
		
	
		//-----------------------------------------------------------
		// start game
		while(1)
		{
			int16 RPot, LPot;
			int16 oldLPot, oldRPot;
			int16 dx, dy;
	
			BALL myBall;						// instantiate a ball
			PADDLE myPaddle1;					// instantiate left paddle
			PADDLE myPaddle2;					// instantiate right paddle
			ballSpeed = 1;
			scoreRight = 0;
			scoreLeft = 0;
	
			// clear LCD
			lcd_clear(0);						// clear LCD
			lcd_volume(30);						// turn up the volume
			
			lcd_cursor(60, 6);					// set display coordinates
			lcd_display(LCD_2X_FONT);			// turn 2x font on
			lcd_printf("%d",scoreLeft);
			lcd_display(~LCD_2X_FONT);			// turn 2x font off
			
			lcd_display(LCD_2X_FONT);			// turn 2x font on
			lcd_cursor(90, 6);					// set display coordinates
			lcd_printf("%d",scoreRight);
			lcd_display(~LCD_2X_FONT);			// turn 2x font off
	
			// initialize ball/paddle values
			myBall.x = HD_X_MAX/2;				// start ball in middle
			myBall.y = HD_Y_MAX/2;
			myBall.oldx = 0;
			myBall.oldpage1 = 0;
			myBall.oldpage2 = 0;
			myPaddle1.oldpage = 2;
			myPaddle2.oldpage = 2;
	
			dx = 1;								// delta x
			dy = 1;								// delta y
	
			oldRPot = ADC_read(RIGHT_POT);		// make initial read of pots
			oldLPot = ADC_read(LEFT_POT);
	
			while (1)
			{
				LPot = ADC_read(LEFT_POT);		// sample left potentiometer
				RPot = ADC_read(RIGHT_POT);		// sample right potentiometer
	
				if (WDT_delay == 0)				// update ball only when WD_delay == 0
				{
					WDT_delay = BALL_SPEED;		// reset WD delay
					myBall.x += dx * ballSpeed ;				// increment x coordinate
	
					if (myBall.x > HD_X_MAX-11)//right side ------------------------------------- || (myBall.x < 5))
					{
						//dx = -dx;				// bounce off walls
						//doTone(BEEP, BEEP_CNT);	// output BEEP
						
						if(myBall.y >= myPaddle2.y && myBall.y < myPaddle2.y+3)
						{
							dx = -dx;
								if(dy<0)
								{
									dy = -2;
								}
								else
								{
									dy = 2;
								}
								doTone(BEEP/2, BEEP_CNT);	// output BEEP
								BallSpeed();
						}
						else if (myBall.y >= myPaddle2.y+3 && myBall.y < myPaddle2.y+7)
						{
							dx = -dx;
								if(dy<0)
								{
									dy = -1;
								}
								else
								{
									dy = 1;
								}
								doTone(BEEP/2, BEEP_CNT);	// output BEEP
								BallSpeed();
						}
						else if (myBall.y >= myPaddle2.y+7 && myBall.y < myPaddle2.y+9)
						{
							dx = -dx;
							dy = 0;
								doTone(BEEP/2, BEEP_CNT);	// output BEEP
								BallSpeed();
						}
						else if (myBall.y >= myPaddle2.y+9 && myBall.y < myPaddle2.y+13)
						{
							dx = -dx;
								if(dy<0)
								{
									dy = -1;
								}
								else
								{
									dy = 1;
								}
								doTone(BEEP/2, BEEP_CNT);	// output BEEP
								BallSpeed();
						}
						else if(myBall.y >= myPaddle2.y+13 && myBall.y < myPaddle2.y+16)
						{
							dx = -dx;
								if(dy<0)
								{
									dy = -2;
								}
								else
								{
									dy = 2;
								}
								doTone(BEEP/2, BEEP_CNT);	// output BEEP
								BallSpeed();
						}
						
						
						else
						{
							congratulations();
							startOver();
							myBall.x = HD_X_MAX/2;				// start ball in middle
							myBall.y = HD_Y_MAX/2;
							leftPlayer();
						}
						
						
						
					}
					else if(myBall.x < 5) //left side ----------------------------------------------------------------------------------
					{
						
						if(myBall.y >= myPaddle1.y && myBall.y < myPaddle1.y+3)
						{
							dx = -dx;
								if(dy<0)
								{
									dy = -2;
								}
								else
								{
									dy = 2;
								}
								doTone(BEEP/2, BEEP_CNT);	// output BEEP
						}
						else if (myBall.y >= myPaddle1.y+3 && myBall.y < myPaddle1.y+7)
						{
							dx = -dx;
								if(dy<0)
								{
									dy = -1;
								}
								else
								{
									dy = 1;
								}
								doTone(BEEP/2, BEEP_CNT);	// output BEEP
						}
						else if (myBall.y >= myPaddle1.y+7 && myBall.y < myPaddle1.y+9)
						{
							dx = -dx;
							dy = 0;
								doTone(BEEP/2, BEEP_CNT);	// output BEEP
						}
						else if (myBall.y >= myPaddle1.y+9 && myBall.y < myPaddle1.y+13)
						{
							dx = -dx;
								if(dy<0)
								{
									dy = -1;
								}
								else
								{
									dy = 1;
								}
								doTone(BEEP/2, BEEP_CNT);	// output BEEP
						}
						else if(myBall.y >= myPaddle1.y+13 && myBall.y < myPaddle1.y+16)
						{
							dx = -dx;
								if(dy<0)
								{
									dy = -2;
								}
								else
								{
									dy = 2;
								}
								doTone(BEEP/2, BEEP_CNT);	// output BEEP
						}
						
						
						else
						{
							congratulations();
							startOver();
							myBall.x = HD_X_MAX/2;				// start ball in middle
							myBall.y = HD_Y_MAX/2;
							rightPlayer();
						}
						
						
					} //end of else if
					
					
					
					myBall.y += dy;				// update y coordinate
					if ((myBall.y > HD_Y_MAX-6) || (myBall.y < 2))
					{
						dy = -dy;				// bounce off ceiling/floor
						doTone(BEEP, BEEP_CNT);	// output BEEP
						
						
					}
					// draw ball
					drawBall(&myBall);			// ok, draw ball in new position
				}
	
				// check for paddle position change
				if ((abs(RPot - oldRPot) > POT_THRESHHOLD) ||
				    (abs(LPot - oldLPot) > POT_THRESHHOLD))
				{
					oldRPot = RPot;				// save old values
					oldLPot = LPot;
	
					// move paddles
					myPaddle1.x = 1;			// update paddle position
					myPaddle1.y = LPot >> 3;
					
					myPaddle2.x = 155;
					myPaddle2.y = RPot >>3;
					drawPaddle(&myPaddle1);		// draw paddle
					drawPaddle(&myPaddle2);
				}
			} // end while (1) innerloop - moving ball
		} // end while (1) outerloop - repeat game
	} // end main
	
	
	//------------------------------------------------------------------------------
	//   output tone subroutine
	void doTone(int tone, int time)
	{
		TBCCR0 = tone;						// set beep frequency/duty cycle
	//	TBCCR2 = tone >> 1;					// 50% duty cycle
		//WDT_sleep(WDT_CPS/2);
		TBCCR2 = tone >> 3;					// 12% duty cycle
		WDT_Tone_Cnt = time;				// turn on speaker
		return;
	} // end doTone
	
	void resetScore()
	{
		scoreRight = 0;
		scoreLeft = 0;	
	}
	
	void BallSpeed()
	{
		ballSpeed += 0.5;
		return;	
	}
	
	void leftPlayer()
	{
			doTone(BEEP/2, BEEP_CNT);
			doTone(BEEP/2, BEEP_CNT);
			doTone(BEEP/2, BEEP_CNT);
			if(scoreLeft<10)
			{
			scoreLeft++;
			lcd_display(LCD_2X_FONT);			// turn 2x font on
			lcd_cursor(60, 6);					// set display coordinates
			lcd_printf("  ");
			lcd_cursor(60, 6);
			lcd_printf("%d",scoreLeft);
			lcd_cursor(90, 6);					// set display coordinates
			lcd_printf("  ");
			lcd_cursor(90, 6);
			lcd_printf("%d",scoreRight);
			lcd_display(~LCD_2X_FONT);			// turn 2x font off
			}
			else
			{
				resetScore();
				startOver();
			}
	}
	void rightPlayer()
	{
			doTone(BEEP/2, BEEP_CNT);
			doTone(BEEP/2, BEEP_CNT);
			doTone(BEEP/2, BEEP_CNT);
		if(scoreRight<10)
			{
				scoreRight++;
			lcd_display(LCD_2X_FONT);			// turn 2x font on
			lcd_cursor(60, 6);					// set display coordinates
			lcd_printf("  ");
			lcd_cursor(60, 6);
			lcd_printf("%d",scoreLeft);
			lcd_cursor(90, 6);					// set display coordinates
			lcd_printf("  ");
			lcd_cursor(90, 6);
			lcd_printf("%d",scoreRight);
			lcd_display(~LCD_2X_FONT);	
			}
			else
			{
				resetScore();
				startOver();
			}
		
	}
	void congratulations(void)
	{
	int i,tone;
	tone=3000;
	for (i=0;i<1;i++)
	{
		TBCCR0 = tone;  // set interval (frequency)
    	TBCCR2 = tone/5;// load volume (duty cycle)
    	tone/=1.2;        
		LED_1_TOGGLE;
		LED_2_TOGGLE;
		LED_3_TOGGLE;
		LED_4_TOGGLE;
		wait(200);
	}		
	TBCCR0 = 0;  // set interval (frequency)
	}
	
	
	void startOver(void)
	{
		lcd_clear(0);
		lcd_cursor(40, 6);					// set display coordinates
		lcd_printf("PRESS 1 TO START");
		while(!((P2IN & 0x01)^0x01)) 
		lcd_cursor(40, 6);					// set display coordinates
		lcd_printf("                 ");
		lcd_display(LCD_2X_FONT);			// turn 2x font on
		lcd_cursor(79, 6);					// set display coordinates
		lcd_printf("3");
		WDT_sleep(WDT_CPS);				// pause 1/2 seconds
		lcd_cursor(79, 6);					// set display coordinates
		lcd_printf("  ");
		lcd_cursor(79, 6);					// set display coordinates
		lcd_printf("2");
		WDT_sleep(WDT_CPS);				// pause 1/2 seconds
		lcd_cursor(79, 6);					// set display coordinates
		lcd_printf("  ");
		lcd_cursor(79, 6);					// set display coordinates
		lcd_printf("1");
		WDT_sleep(WDT_CPS/2);				// pause 1/2 seconds
		lcd_cursor(79, 6);					// set display coordinates
		lcd_printf("  ");
		lcd_cursor(79, 6);					// set display coordinates
		lcd_printf("GO");
		WDT_sleep(WDT_CPS/2);				// pause 1/2 seconds
		lcd_display(~LCD_2X_FONT);			// turn 2x font off
		lcd_clear(0);
		ballSpeed = 1;
		return;
		
	}
	
	//------------------------------------------------------------------------------
	//   Watchdog sleep subroutine
	void WDT_sleep(int sleep)
	{
		if (sleep <= 0) return;
		WDT_delay = sleep;					// set WD decrementer
		while (WDT_delay);					// wait for time to expire
		return;
	} // end WD_delay()
	
	
	//------------------------------------------------------------------------------
	//	Watchdog Timer ISR
	//
	#pragma vector = WDT_VECTOR
	__interrupt void WDT_ISR(void)
	{
		if (--WDT_cps_cnt == 0)				// 1 second?
		{
			LED_GREEN_TOGGLE;				// toggle green LED
			WDT_cps_cnt = WDT_CPS;			// reset counter
		}
	
		if (WDT_delay) WDT_delay--;			// decrement delay counter
	
		// decrement tone counter - turn off tone when 0
		if (WDT_Tone_Cnt && (--WDT_Tone_Cnt == 0))
		{
			TBCCR0 = 0;
		}
		return;
	} // end WDT_ISR
