//******************************************************************************
//  Lab 11 - Pong (Event Driven Model)
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
//*******************************************************************************
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
#define myCLOCK	12000000				// clock speed
#define WDT_CLK	32000					// 32 Khz WD clock (@1 Mhz)
#define	WDT_CTL	WDT_MDLY_32				// WDT SMCLK, ~32ms
#define	WDT_CPS	myCLOCK/WDT_CLK			// WD clocks / second count

#define WDT_ADC		WDT_CPS/10			// 100 ms

//------------------------------------------------------------------------------
//
#define BEEP_COUNT		myCLOCK/4000
#define BALL_SPEED		32000
#define POT_THRESHHOLD	3

#define BEEP			myCLOCK/4000	// beep frequency
#define BEEP_CNT		myCLOCK/2000000	// beep duration

extern const unsigned char byu_image[];	// BYU logo

//------------------------------------------------------------------------------
// events & event service routines:
#define MOVE_BALL		0x0010
#define ADC_READ		0x0020
//#define SWITCH_1		0x000 //copy from servo
//port 2 interrupt service routine down by the WD

//port initi
// switch_1 event

void MOVE_BALL_event(BALL* ball);
void ADC_READ_event(PADDLE* paddle);

void init_ball(BALL* ball);
void init_paddle(PADDLE* paddle);
void doTone(int tone, int time);		// output tone
void WDT_sleep(int sleep);				// sleep

//------------------------------------------------------------------------------
// global variables
volatile uint16 WDT_cps_cnt;
volatile uint16 WDT_adc_cnt;
volatile uint16 WDT_delay;
volatile uint16 WDT_Tone_Cnt;			// WDT tone counter
volatile uint16 sys_event;				// pending events

int dx, dy;

//------------------------------------------------------------------------------
//
void main(void)
{
	// 	MSP430 Clock - Set DCO to 12 MHz:
	eZ430X_init(_12MHZ);				// init board
	lcd_init();							// init LCD
	ADC_init();							// init ADC

	// configure watchdog
	WDTCTL = WDT_CTL;					// set Watchdog interval
	IE1 |= WDTIE;						// Enable WDT interrupt
	WDT_adc_cnt = 0;
	WDT_delay = 0;

	// configure TimerA to move the ball
	TAR = 0;							// reset timer
	TACCR0 = 0;							// interrupt rate
	TACTL = TASSEL_2 | ID_3 | MC_1 | TAIE;	// SMCLK/8, up mode, interrupt enable

	// configure TimerB to PWM speaker
	P4SEL |= 0x20;						// P4.5 TB2 output
	TBR = 0;							// reset timer B
	TBCTL = TBSSEL_2 | ID_0 | MC_1;		// SMCLK, /1, UP (no interrupts)
	TBCCTL2 = OUTMOD_3;					// TB2 = set/reset

	__bis_SR_register(GIE);				// enable interrupts

	//-----------------------------------------------------------
	//	play forever
	while (1)
	{
		BALL myBall;						// instantiate ball
		PADDLE myPaddle;					// instantiate paddle

		// clear LCD
		lcd_clear(0);						// clear LCD
		lcd_backlight(ON);					// turn on LCD
		lcd_image(byu_image, 40, 11);		// display BYU
		lcd_display(LCD_2X_FONT);			// turn 2x font on
		lcd_cursor(42, 6);					// display coordinates
		lcd_printf("P O N G");				// splash screen
		lcd_display(~LCD_2X_FONT);			// turn 2x font off

		// count down
		WDT_sleep(WDT_CPS*2);				// pause 2 seconds

		lcd_clear(0);						// clear LCD
		init_ball(&myBall);					// initialize ball
		init_paddle(&myPaddle);				// initialize paddle
		dx = rand() % 2 ? -1 : 1;			// delta x
		dy = rand() % 2 ? -1 : 1;			// delta y
		sys_event = 0;						// no events pending
		WDT_adc_cnt = 1;					// start ADC sampling
		lcd_volume(40);						// turn up the volume
		TACCR0 = BALL_SPEED;				// interrupt rate

		//-----------------------------------------------------------
		// start new game
		//-----------------------------------------------------------
		//	event service routine loop
		//
		while (1)
		{
			
			_disable_interrupts(); // disable interrupts before check sys_event

			if (sys_event) _enable_interrupt(); // if there's something pending, enable interrupts before servicing
			
			else __bis_SR_register(LPM0_bits + GIE); // otherwise, enable interrupts and goto sleep (LPM0)
			

			//-------------------------------------------------------
			//	I'm AWAKE!!!  There must be something to do
			//-------------------------------------------------------
			if (sys_event & MOVE_BALL)				// timer A event
			{
				sys_event &= ~MOVE_BALL;			// clear TimerA event
				MOVE_BALL_event(&myBall);			// update ball position
			}
			else if (sys_event & ADC_READ)			// read ADC event
			{
				sys_event &= ~ADC_READ;				// clear ADC event
				ADC_READ_event(&myPaddle);			// read ADCs
			}
			else									// ????
			{
				ERROR2(10);							// unrecognized event
			}
		}
	}
} // end main


void init_ball(BALL* ball)
{
	// initialize ball values
	ball->x = HD_X_MAX/2;				// put ball in middle of screen
	ball->y = HD_Y_MAX/2;
	ball->oldx = 0;						// init some housekeeping
	ball->oldpage1 = 0;
	ball->oldpage2 = 0;
	return;
} // end init_ball


void init_paddle(PADDLE* paddle)
{
	// initialize paddle values
	paddle->oldpage = 2;
	return;
} // end init_ball


//------------------------------------------------------------------------------
//
void MOVE_BALL_event(BALL* ball)
{
	ball->x += dx;						// increment x coordinate
	ball->y += dy;						// update y coordinate
	if ((ball->y > HD_Y_MAX-6) || (ball->y < 2))
	{
		dy = -dy;						// bounce ball off ceiling/floor
		doTone(BEEP, BEEP_CNT);			// output BEEP
	}
	drawBall(ball);						// ok, draw ball in new position

	if ((ball->x < 5) || (ball->x > HD_X_MAX-11))
	{
		dx = -dx;						// bounce ball off walls
		doTone(BEEP, BEEP_CNT);			// output BEEP
	}
	return;
} // end MOVE_BALL_event


//------------------------------------------------------------------------------
//
void ADC_READ_event(PADDLE* paddle)
{
	static int oldRPot;
	int RPot = ADC_read(RIGHT_POT);		// sample right potentiometer

	// check for paddle position change
	if ((abs(RPot - oldRPot) > POT_THRESHHOLD))
	{
		oldRPot = RPot;					// save old values

		// move right
		paddle->x = 155;				// update paddle position
		paddle->y = RPot >> 3;
		drawPaddle(paddle);				// draw paddle
	}
	return;
} // end ADC_READ_event


//------------------------------------------------------------------------------
//   output tone subroutine
void doTone(int tone, int time)
{
	TBCCR0 = tone;						// set beep frequency/duty cycle
//	TBCCR2 = tone >> 1;					// 50% duty cycle
	TBCCR2 = tone >> 3;					// 12% duty cycle
	WDT_Tone_Cnt = time;				// turn on speaker
	return;
} // end doTone


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
//------------------------------------------------------------------------------
// Timer A1 interrupt service routine
//
#pragma vector = TIMERA1_VECTOR
__interrupt void TIMERA1_ISR(void)
{
	TACTL &= ~TAIFG;					// acknowledge interrupt
	TACCR0 = BALL_SPEED;
	sys_event |= MOVE_BALL;				// generate move ball event
	__bic_SR_register_on_exit(LPM0_bits);
	return;
} // end TIMERA1_VECTOR


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

	// decrement tone counter - turn off tone when 0
	if (WDT_Tone_Cnt && (--WDT_Tone_Cnt == 0))
	{
		TBCCR0 = 0;
	}

	if (WDT_adc_cnt && (--WDT_adc_cnt == 0))
	{
		WDT_adc_cnt = WDT_ADC;			// update adc counter
		sys_event |= ADC_READ;			// generate ADC event
	}
	if (WDT_delay) WDT_delay--;			// process any delays

	if (sys_event) __bic_SR_register_on_exit(LPM0_bits);
	return;
} // end WDT_ISR

/*
 * typedef struct
 * {int x;
 * int y;
 * int oldx;
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 */

