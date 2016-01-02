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

//------------------------------------------------------------------------------
// defined constants
//#define myCLOCK			8000000			// 1.2 Mhz clock
//#define	WDT_CTL			WDT_MDLY_32		// WD configuration (Timer, SMCLK, ~32 ms)
#define WDT_CPI			32000			// WDT Clocks Per Interrupt (@1 Mhz)
#define	WDT_1SEC_CNT	myCLOCK/WDT_CPI	// WDT counts/second (32 ms)

#define myCLOCK	8000000					// clock speed
#define WD_CLK	32						// 32 Khz WD clock (@1 Mhz)
#define	WD_1_SEC_CNT	myCLOCK/32000	// WD counts/second (32 ms)
#define	WDT_CTL	WDT_MDLY_32				// WDT SMCLK, ~32ms
#define	WDT_CPS	myCLOCK/WDT_CLK			// WD clocks / second count

#define BEEP			1000
#define BEEP_CNT		5

//-----------------------------------------------------------
//	external/internal prototypes
extern void drawDieFrame(int column);	// draw die frame
extern void drawDie(int die, int x);	// draw die
extern int rand16(void);				// get random #
extern int rand6(void);					// get 0-5 random #

void WDT_sleep(int delay);				// WDT sleep routine
int myDelayCount(void);

//-----------------------------------------------------------
//	global variables
volatile int WDT_Sec_Cnt;				// WDT second counter
volatile int WDT_Delay;					// WDT delay counter
volatile int WDT_Tone_Cnt;				// Tone on flag

int get_switch(void);

//-----------------------------------------------------------
//	main
void main(void)
{
	int i = 0;
	eZ430X_init(_8MHZ);					// init board
	lcd_init();
								// init LCD

	// configure Watchdog
	WDTCTL = WDT_CTL;					// Set Watchdog interval
	WD_Sec_Cnt = WD_1SEC_CNT;			// set WD 1 second counter
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
	
	
	while (1)							// repeat forever
	{
		
		int myRandNumber;// = rand16();
		int i;
		
		for (i=0; i<100; i+=5) //
		
		{
			myRandNumber = rand6();
			
			drawDie((myRandNumber %6) + 1,58);
			lcd_backlight(ON);				// turn on LCD
			WD_5_sec = 5;
			
			if((P2IN &0x0F) !=0x0f) i = 0; //waits for the switch to be pressed, when pressed speeds it up again
			
			WD_sleep(i);					//delay again
			
			lcd_backlight(OFF);
								
		
			
		}
			lcd_backlight(ON);				// turn on LCD
		//int	WD_5_sec = 5;
			get_switch();
		
		//drawDie((++i % 7), 58);			// draw a die
		//WD_sleep(100);					// delay
		
	}
} // end main()


int get_switch()
{
	int sw;
	while ((sw = P2IN & 0x0f)==0x0f) //end it to get the low 4 bits, assing to switch, waiting for the user input
	WD_5_sec = 5;
	return sw ^ 0x0f;
	
}
	
//
//	drawDieFrame(50);					// draw die frame
//	lcd_display(LCD_2X_FONT | LCD_PROPORTIONAL);
//	lcd_cursor(25, 2);					// position message
//	lcd_printf("Hello World!");
//
//	while (1)							// repeat forever
//	{
//		int die = (rand16() % 6) + 1;	// get a random die (1-6)
//		lcd_backlight(ON);				// turn on LCD
//		TBCCR0 = BEEP;					// set beep frequency/duty cycle
//		TBCCR2 = BEEP >> 1;
//		WDT_Tone_Cnt = BEEP_CNT;		// turn on speaker
//		drawDie(die, 58);				// draw a die
//		WDT_sleep(25);					// delay
//		lcd_backlight(OFF);				// turn off LCD
//		WDT_sleep(2);					// short delay
//	}
//} // end main()


//------------------------------------------------------------------------------
//   Watchdog sleep subroutine
void WDT_sleep(int sleep)
{
	if (sleep <= 0) return;
	WDT_Delay = sleep;					// set WD decrementer
	while (WDT_Delay);					// wait for time to expire
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
	}
} // end WDT_ISR(void)
