///******************************************************************************
//  Lab 9 - Etch-a-Sketch
//
//  Description:
//
//	"Write a C language program that uses the sampled values from two
//	potentiometers to draw (etch) lines on the LCD. Use a low-pass filter
//	to smooth out the sampled values. Program one push button to clear
//	the LCD and another to toggle the size of the drawing pen. Display
//	the pen coordinates in the lower right corner of the display."
//
//   Author:	Paul Roper, Brigham Young University
//				March 2010
//				11/09/2010	simplfied routine
//
//   Built with Code Composer Studio Version: 4.0.1
//*******************************************************************************
//
//	                  MSP430F2274 (REV C)
//	               .----------------------.          LEFT         RIGHT
//	     RED LED-->|P1.0    (ACLK/A0) P2.0|<--SW_1
//	   GREEN LED-->|P1.1   (SMCLK/A1) P2.1|<--(H)    SW_2         EZ0_TX
//	eZ430 BUTTON-->|P1.2     (TA0/A2) P2.2|<--(D)    ADXL_INT     SW_3
//	           N.C.|P1.3     (TA1/A3) P2.3|<--(E)    SW_4         SERVO_3
//	           N.C.|P1.4     (TA2/A4) P2.4|-->(F)    LCD_BLT      SERVO_4
//	           N.C.|P1.5              P2.5| N.C.
//	           N.C.|P1.6          XIN P2.6|-->LED_1
//	           N.C.|P1.7         XOUT P2.7|-->LED_2
//                 |     (UCB0STE/A5) P3.0|-->i2c_RST
//                 |        (UCB0SDA) P3.1|<->i2c_SDA
//                 |        (UCB0SCL) P3.2|-->i2c_SCL
//                 |        (UCB0CLK) P3.3|-->LED_3/EZ0_RX
//	               |                  P3.4| N.C.
//	               |                  P3.5| N.C.
//	               |                  P3.6| N.C.
//	               |                  P3.7| N.C.
//	               |                  P4.0| N.C.
//	               |                  P4.1| N.C.
//	               |                  P4.2| N.C.
//	               |        (TB0/A12) P4.3|-->(G)    RPOT         EZ0_AN
//	               |        (TB1/A13) P4.4|-->(A)    LPOT         SERVO_1
//	               |        (TB2/A14) P4.5|-->(B)    TRANSDUCER   SERVO_2
//	               |     (TBOUTH/A15) P4.6|-->(C)    THERMISTOR   LED_4
//	               |                  P4.7| N.C.
//	               '----------------------'
//
//******************************************************************************
//
#include "msp430x22x4.h"
#include "eZ430X.h"
#include "lcd.h"
#include "adc.h"
#include "graphics.h"
#include "etch-a-sketch.h"

//------------------------------------------------------------------------------
// INITIALIZE SYSTEM CONSTANTS/VARIABLES
//
#define myCLOCK	8000000					// clock speed
#define WDT_CLK	32000					// 32 Khz WD clock (@1 Mhz)
#define	WDT_CTL	WDT_MDLY_32				// WDT SMCLK, ~32ms
#define	WDT_CPS	myCLOCK/WDT_CLK			// WD clocks / second count
#define FIVE_SEC WDT_CPS*5;
volatile int WDT_cps_cnt;				// WD counts/second
extern const uint8 byu_image[];			// BYU logo
//void lineTo(int, int, int, int);

volatile int oldx;
volatile int oldy;
volatile int newx;
volatile int newy;
volatile int WDT_Delay;	
int get_switch(void);
int line_drawer;
volatile int lightOff;
volatile int switchPressed;
void WDT_sleep(int delay);
volatile int counter_5sec;
//------------------------------------------------------------------------------
//
void main(void)
{
	// init eZ430X development board
	
	eZ430X_init(_8MHZ);					// init board to 8 Mhz
	ADC_init();							// init ADC
	lcd_init();							// init LCD
	lcd_FRAM_init();					// clear lcd FRAM memory

	// configure Watchdog
	WDT_cps_cnt = WDT_CPS;				// set WD 1 second counter
	WDTCTL = WDT_CTL;					// set WC interval to ~32ms
	IE1 |= WDTIE;						// enable WDT interrupt

	__bis_SR_register(GIE);				// enable interrupts

	// update display (interrupts enabled)
	lcd_image(byu_image, 40, 11);		// display BYU
	lcd_backlight(ON);					// turn on LCD backlight
	lcd_display(LCD_2X_FONT | LCD_PROPORTIONAL);
	lcd_cursor(4, 2);					// set display coordinates
	lcd_printf("Etch-a-Sketch");
	lcd_clear(0);
	lcd_display(~(LCD_2X_FONT | LCD_PROPORTIONAL));
	WDT_Delay = 0;
	counter_5sec=0;
	lcd_backlight(ON);
	counter_5sec = FIVE_SEC;

/*	
 Filter
	oldx = 1023 - ADC_read(RIGHT_POT);
	oldy = 1023 - ADC_read(LEFT_POT);
	
	draw_moveTo(SCALE(oldx
*/
	line_drawer=1;
	lightOff = 34;
	while (1)
	{
		//7 switch 1
		// 11 switch 2
		/*
		if(switchPressed ==7)
		{
			lcd.clear(0);
		}
		else if(switchPressed = 11 || switchPressed == 15)
		{
			
		}
		*/
		oldy = ADC_read(RIGHT_POT);	// read potentiometrs
		oldx = ADC_read(LEFT_POT);

#define X_THRESHOLD 4
#define Y_THRESHOLD 4
#define THRESHOLD 4
	//	oldx = ADC_read(LEFT_POT);
		while (1)
		{
		int i = 0;
		switchPressed = P2IN&0x0f;
		if(switchPressed == 14)
		{
			lcd_clear(0);
			lcd_FRAM_init();					// clear lcd FRAM memory
			lcd_backlight(ON);
			counter_5sec = FIVE_SEC;
		}
		else if(switchPressed==13)
		{
			if(line_drawer==1)
			{
				line_drawer=3;
			}
			else
			{
				line_drawer=1;
			}
			counter_5sec = FIVE_SEC;
			lcd_backlight(ON);
			WDT_sleep(40);
		}
			
		#define OVER_SAMPLE 16
		#define OS_SHIFT 4
		newx=0;
		newy = 0;
	
		for (i =0; i<OVER_SAMPLE-1; i++) newx+=1023-ADC_read(RIGHT_POT);
		for (i= 0; i<OVER_SAMPLE-1; i++) newy+=1023-ADC_read(LEFT_POT);
//	newx+=1(<<(OS_SHIFT-1));
//	newy+=1(<<(OS_SHIFT-1));

		newx /= 160;
		newy /= 100;

  		 //newx = ADC_read(LEFT_POT);
  		 //newy = ADC_read(RIGHT_POT);
   		if (abs(newx - oldx) > THRESHOLD || (abs(newy-oldy))>THRESHOLD)
   {
   	  lineTo(oldx,oldy,newx,newy);
   	  lcd_backlight(ON);
	  counter_5sec = FIVE_SEC;
      oldx = newx;
      oldy = newy;

      // process new sample
   }

		
		lcd_cursor(120,1);
		lcd_printf("%d,%d   ", oldx, oldy);
		
		lcd_cursor(1,1);
		lcd_printf("%d ", switchPressed);
		
		
		}//end of while
	}//end of upper while
} // end main
/*
void lineTo(int x0, int y0, int x1, int y1)	//function to draw the line

{
//	long int dx = x1 - x0;
//	long int dy = y1 - y0;
//	float xMover = (float)x0; //floating number 
//	float yMover = (float)y0; //old y
	float oldX = (float)x0;
	float oldY = (float)y0;
	float newX = (float)x1;
	float newY = (float)y1;
	
	float xStep =(newX-oldX)/160;
	float yStep =(newY-oldY)/160;
	int i;
	for( i =0; i<160;i++)
	{
		lcd_point(oldX,oldY,line_drawer);
		oldX+=xStep;
		oldY+=yStep;
	}

}
*/
//
//int get_switch()
//{
//	int sw;
//	while ((sw = P2IN & 0x0f)==0x0f) //end it to get the low 4 bits, assing to switch, waiting for the user input
////	WD_5_sec = 5;
//	return sw ^ 0x0f;
//	
//}

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
//
#pragma vector = WDT_VECTOR
__interrupt void WDT_ISR(void)
{
	if (--counter_5sec==0)
	{
		lcd_backlight(OFF);
	}
	if (WDT_Delay && (--WDT_Delay == 0));
	if (--WDT_cps_cnt == 0)				// 1 second?
	{
		LED_GREEN_TOGGLE;				// toggle green LED
		WDT_cps_cnt = WDT_CPS;			// reset counter
		if (--lightOff == 0)//(WD_5_sec && (--WD_5_sec ==0) )
		{
			
			lcd_backlight(OFF); //turns off the LCD
		}	
	}
	return;
} // end WDT_ISR

//smoothing
//scaling the potentiometer, width (160), potentiometer(0 to 1023) may be 1010
//smoothing_filter is a function, needs to be initilized, is in the how-to's
//do scaling last
