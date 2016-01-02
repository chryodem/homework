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
//				March 2010
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
//
#include "msp430x22x4.h"
#include <stdlib.h>
#include "eZ430X.h"
#include "lcd.h"
#include "dice.h"

//------------------------------------------------------------------------------
// defined constants
//
#define myCLOCK	8000000					// clock speed
#define WD_CLK	32						// 32 Khz WD clock (@1 Mhz)
#define	WD_1SEC_CNT	myCLOCK/32000	// WD counts/second (32 ms)
#define	WDT_CTL	WDT_MDLY_32				// WDT SMCLK, ~32ms
#define	WDT_CPS	myCLOCK/WDT_CLK			// WD clocks / second count
#define WDT_CPI			32000		
#define	WDT_1SEC_CNT myCLOCK/WDT_CPI	// WDT counts/second (32 ms)

//-----------------------------------------------------------
//	external/internal prototypes
extern void drawDieFrame(int column);	// draw die frame
extern void drawDie(int die, int x);	// draw die

extern void initRand16(void);			// init random # generator
extern int rand6(void);					// get 0-5 random #

void WD_sleep(int delay);				// WD sleep routine

//-----------------------------------------------------------
//	global variables
volatile int WDT_Delay;		
volatile int WD_Sec_Cnt;				// WD second counter
volatile int WD_Delay;					// WD delay counter
volatile int WD_5_sec;					//5 second counter
volatile int WDT_Tone_Cnt;
volatile int WDT_Sec_Cnt;

int get_switch(void);

//-----------------------------------------------------------
//	main
void main(void)
{
	int i = 0;
	int die1;
	int die2;

	eZ430X_init(_8MHZ);					// init board to 8Mhz
	lcd_init();							// init LCD
	initRand16();
//	initRand6();

	// configure Watchdog
	WD_Sec_Cnt = WD_1SEC_CNT;			// set WD 1 second counter
	WDTCTL = WDT_CTL;					// Set Watchdog interval
	WD_Delay = 0;						// reset delay counter
	IE1 |= WDTIE;						// enable WDT interrupt
	__bis_SR_register(GIE);				// enable interrupts
	WDT_Tone_Cnt = 0;					// turn off tone

	drawDieFrame(10);					// draw die frame
	drawDieFrame(90);
	lcd_display(LCD_2X_FONT | LCD_PROPORTIONAL);
	lcd_cursor(25, 2);					// position message
	lcd_printf("Hello World!");

	while (1)							// repeat forever
	{
		
		int myRandNumber;// = rand6;// = rand16();
		int i;
		
		for (i=0; i<100; i+=5) //
		
		{
			myRandNumber = rand6();
			
			drawDie((myRandNumber)+1,58); //* %6) + 1,58
			//drawDie((myRandNumber)+2,58);
			lcd_backlight(ON);				// turn on LCD
			WD_5_sec = 5;
			
			if((P2IN &0x0F) !=0x0f) i = 0; //waits for the switch to be pressed, when pressed speeds it up again
			
			WD_sleep(i);					//delay again
			
			lcd_backlight(OFF);
								
		
			
		}
			lcd_backlight(ON);				// turn on LCD
			WD_5_sec = 5;
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
	return sw ^ 0x000f;
	
}

//------------------------------------------------------------------------------
//   Watchdog sleep subroutine
//
void WD_sleep(int sleep)
{
	WD_Delay = sleep;
	while(WD_Delay);					// set WD decrementer
	__bis_SR_register(CPUOFF);			// goto sleep
	return;
} // end WD_delay()


//------------------------------------------------------------------------------
//	Watchdog Timer ISR
//
//*
#pragma vector = WDT_VECTOR
__interrupt void WDT_ISR(void)
{
	if (WD_Delay && (--WD_Delay == 0))	// decrement delay (if non-zero)
	{
		// wakeup processor
		__bic_SR_register_on_exit(CPUOFF);
	}
	
	// decrement tone counter - turn off tone when 0
	if (WDT_Tone_Cnt && (--WDT_Tone_Cnt == 0))
	{
		TBCCR0 = 0;
	}

	if (--WD_Sec_Cnt == 0)
	{
		WD_Sec_Cnt = WD_1SEC_CNT;		// reset counter
		P1OUT ^= 0x02;					// toggle P1.1
		if(WD_5_sec && (--WD_5_sec ==0))
		{
			lcd_backlight(OFF); //turns off the LCD
		}	
	}
} // end WDT_ISR(void)

//*/
/*
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
*/
