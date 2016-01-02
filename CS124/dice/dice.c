//******************************************************************************
//  Lab 8 - Dice
//
//  Description:
//
//	"Write a dice roller C program that waits for a switch to be pressed and then
//	 displays two dice which randomly change values. The dice roll begins rapidly
//	 and progressively slows down until it stops (after approximately 3-5
//	 seconds). A congratulatory ditty is played when doubles are rolled. If
//	 doubles are rolled twice in a row, output a raspberry tone. Write an
//	 assembly language function which returns a random number from 1 to 6 and
//	 call this function from your C program." 
//
//   Author:	Paul Roper, Brigham Young University
//				July 2011
//
//   Built with Code Composer Essentials Version: 3.2
//*******************************************************************************
//
//      A  P4.4     Left Pot  O--O  O  Servo #1
//      B  P4.5      Speaker  O--O  O  Servo #2
//      C  P4.6   Thermistor  O  O--O  LED_4
//      D  P2.2  ADXL345 INT  O  O--O  SW_3
//      E  P2.3         SW_4  O--O  O  Servo #3 
//      F  P2.4    Backlight  O--O  O  Servo #4
//      G  P4.3    Right Pot  O--O  O  EZ0_AN
//      H  P2.1         SW_2  O--O  O  EZ0_TX
//
//	                     MSP430F2274
//                   .-----------------.
//         RED LED-->|P1.0         P2.0|<--SW1 (pull-up)
//       GREEN LED-->|P1.1         P2.1|<--SW2 (pull-up)
//    eZ430 BUTTON-->|P1.2         P2.2|<--SW3 (pull-up)
//                   |             P2.3|<--SW4 (pull-up)
//                   |             P2.4|-->LCD BACKLIGHT
//                   |         XIN P2.6|-->LED_1
//                   |        XOUT P2.7|-->LED_2
//                   |     UCB0STE P3.0|-->i2c_RST
//                   |     UCB0SDA P3.1|<-|---+->i2c SDA
//                   |     UCB0SCL P3.2|<-+----->i2c SCL     |
//                   |     UCB0CLK P3.3|-->LED_3
//                   |             P4.3|-->LEFT POTENTIOMETER
//                   |             P4.5|-->BUZZER
//                   |             P4.6|-->LED_4
//                   |                 |
//
//******************************************************************************
// includes
#include "msp430x22x4.h"
#include <stdlib.h>
#include "eZ430X.h"
#include "lcd.h"
#include "dice.h"


//------------------------------------------------------------------------------
// defined constants
#define myCLOCK			8000000			// 1.2 Mhz clock
#define	WDT_CTL			WDT_MDLY_32		// WD configuration (Timer, SMCLK, ~32 ms)
#define WDT_CPI			32000			// WDT Clocks Per Interrupt (@1 Mhz)
#define	WDT_1SEC_CNT	myCLOCK/WDT_CPI	// WDT counts/second (32 ms)

#define BEEP			1000
#define BEEP_CNT		5

#define Freq_A2  8000000/440/2/2     //the Freqeuncy of A - 440 hz
#define Freq_D	 8000000/587/2/2	 //the frequency of D - 293.66 hz
#define Freq_A   8000000/880/2/2 	 //the frequency of HiA-880.0
#define Freq_G	 8000000/784/2/2     //the frequency of G - 783.99
#define TB_CTL   TBSSEL_2+ID_0+MC_1  //set timer b register 
#define note_A	 8000000/Freq_A        // WD clocks / second count
#define note_D	 8000000/Freq_D
#define note_G	 8000000/Freq_G
#define note_A2	 8000000/Freq_A2

//-----------------------------------------------------------
//	external/internal prototypes
extern void drawDieFrame(int column);	// draw die frame
extern void drawDie(int die, int x);	// draw die
extern int rand16(void);				// get random #

void WDT_sleep(int delay);				// WDT sleep routine
int myDelayCount(void);

//-----------------------------------------------------------
//	global variables
volatile int WDT_Sec_Cnt;				// WDT second counter
volatile int WDT_Delay;					// WDT delay counter
volatile int WDT_Tone_Cnt;				// Tone on flag
volatile int WD_Sec_Cnt;				// WD second counter
volatile int WD_Delay;					// WD delay counter
volatile int WD_5_sec;					//5 second counter
volatile int WDT_Tone_Cnt;
volatile int WDT_Sec_Cnt;
volatile int lightOff; //counts the light

int get_switch(void);
void congradulations(void);
//-----------------------------------------------------------
//	main
void main(void)
{
	int i;
	int doubles = 0;
	eZ430X_init(_8MHZ);					// init board
	lcd_init();							// init LCD

	// configure Watchdog
	WDTCTL = WDT_CTL;					// Set Watchdog interval
	WDT_Sec_Cnt = WDT_1SEC_CNT;			// set WD 1 second counter
	WDT_Delay = 0;						// reset delay counter
	WDT_Tone_Cnt = 0;					// turn off tone
	IE1 |= WDTIE;						// enable WDT interrupt

	// configure h/w PWM for speaker
	P4DIR |= 0x20;						// P4.5 speaker output
	P4SEL |= 0x20;						// P4.5 TB2 output
	TBR = 0;							// reset timer B
	TBCTL = TBSSEL_2 | ID_0 | MC_1;		// SMCLK, /1, UP (no interrupts)
	TBCCTL2 = OUTMOD_3;					// TB2 = set/reset

	__bis_SR_register(GIE);				// enable interrupts

	drawDieFrame(10);					// draw die frame
	drawDieFrame(90);					// draw second die frame
	lcd_display(LCD_2X_FONT | LCD_PROPORTIONAL);
	lcd_cursor(25, 2);					// position message
	lcd_printf("Hello World!");
	doubles=0;

	while (1)							// repeat forever
	{
		int i;
		lightOff = 140;
		for (i=0; i<20; i++) //
		
		{
			int die = rand6();
			int die2 = rand6();         	// get a random die (1-6)
			lcd_backlight(ON);				// turn on LCD
			TBCCR0 = BEEP;					// set beep frequency/duty cycle
			TBCCR2 = BEEP >> 1;
			WDT_Tone_Cnt = BEEP_CNT;		// turn on speaker
			
			drawDie(die, 18);				// draw a die
			drawDie(die2,98);				// draw a second die
			//just added
			WD_5_sec = 5;
			
			if((P2IN &0x0F) !=0x0f) i = 0; //waits for the switch to be pressed, when pressed speeds it up again
			
			if(die==die2)
			{
					doubles++;			
			}
			else
			{
				doubles=0;				
			}
				
			
			WDT_sleep(i);					//delay again
			lcd_backlight(OFF);
			
		}
			WD_5_sec = 5;
			lcd_cursor(25, 2);		
			lcd_printf("             ");
			lcd_cursor(15, 2);		
			lcd_printf("Press a button: ");
			
			
			get_switch();
			lcd_backlight(OFF);
			
		if(doubles>0)
		{
				
				congradulations();	
				if(doubles==2)
				{
					TBCCR0 = note_G;  // set interval (frequency)
    				TBCCR2 = note_G/2;// load volume (duty cycle)
           		    TBCCR0 = note_G*2;  // set interval (frequency)       
			        doubles=0;
				}
		}
			
			TBCCR0 = (BEEP*2);					// set beep frequency/duty cycle
			TBCCR2 = (BEEP*2) >> 1;
			WDT_Tone_Cnt = BEEP_CNT;		// turn on speaker
	}//end of while
} // end main()

int get_switch()
{
	int sw;
	while ((sw = P2IN & 0x0f)==0x0f) //end it to get the low 4 bits, asking to switch, waiting for the user input
	WD_5_sec = 5;
	return sw ^ 0x0f;
	
}

//------------------------------------------------------------------------------
//   Watchdog sleep subroutine
void WDT_sleep(int sleep)
{
	if (sleep <= 0) return;
	WDT_Delay = sleep;					// set WD decrementer
	while (WDT_Delay);					// wait for time to expire
	//__bis_SR_register(CPUOFF);
	return;
} // end WD_delay()


//------------------------------------------------------------------------------
//	Watchdog Timer ISR
#pragma vector = WDT_VECTOR
__interrupt void WDT_ISR(void)
{
	// decrement delay (if non-zero)
	if (WDT_Delay && (--WDT_Delay == 0));

	// decrement tone counter - turn off tone when 0
	if (WDT_Tone_Cnt && (--WDT_Tone_Cnt == 0))
	{
		TBCCR0 = 0;
	}

	if (--WDT_Sec_Cnt == 0)
	{
		WDT_Sec_Cnt = WDT_1SEC_CNT;		// reset counter
		P1OUT ^= 0x02;					// toggle P1.1
		if (--lightOff == 0)//(WD_5_sec && (--WD_5_sec ==0) )
		{
			
			lcd_backlight(OFF); //turns off the LCD
		}	
	}
} // end WDT_ISR(void)
void doubles_method(void)
{
	
	
}

void congradulations(void)
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
