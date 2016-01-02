//******************************************************************************
//  Lab 10 - Servo
//
//  Description:
//
//	"Your employer is bringing to market the new Tin-Lizzy automobile.  You
//	have been asked to design and program the windshield wiper module.
//	Initial specifications are to use two servos (windshield wipers) in various
//	modes of operation (ie. slow, fast, intermittent, opposed, etc.)
//	Use the switches to turn the wipers on/off and to select the mode of operation.
//	Use a potentiometer to control the wiper speed.  Display the mode, speed, and
//	direction of each wiper on the LCD." 
//
//
//  Author:		Paul Roper, Brigham Young University
//  Revisions:	03/2010		Original code
//				11/2010		moved servo updates to TimerB ISR
//				07/28/2011	Added outTone, WDT_sleep
//
//  Built with Code Composer Studio Version: 4.2.3.00004 

//*******************************************************************************
//   Set eZ430X jumpers as follows:
//
//      A  P4.4     Left Pot  O  O--O  Servo #1
//      B  P4.5       Buzzer  O  O--O  Servo #2
//      C  P4.6   Thermistor  O  O--O  LED_4
//      D  P2.2  ADXL345 INT  O  O--O  SW_3
//      E  P2.3         SW_4  O--O  O  Servo #3 
//      F  P2.4    Backlight  O--O  O  Servo #4
//      G  P4.3    Right Pot  O--O  O  EZ0_AN
//      H  P2.1         SW_2  O--O  O  EZ0_TX
//
//     GND    O    O--O  O
//                 T     C
//            O    A     C
//            |    B     V
//     4.5VDC O
//******************************************************************************
//
#include "msp430x22x4.h"
#include <stdlib.h>
#include "eZ430X.h"
#include "lcd.h"
#include "adc.h"
#include "servo.h"

//------------------------------------------------------------------------------
// INITIALIZE SYSTEM CONSTANTS
#define myCLOCK		12000000			// clock speed (12 mhz)
#define WDT_CLK		32000				// 32 Khz WD clock (@1 Mhz)
#define	WDT_CTL		WDT_MDLY_32			// WDT SMCLK, ~32ms
#define	WDT_CPS		myCLOCK/WDT_CLK		// WD clocks / second count
#define WDT_LCD		WDT_CPS/2			// 500 ms

//------------------------------------------------------------------------------
// function prototypes
void port2_init(void);
void display_init(void);
void timerB_init(void);
void watchdogtimer_init(int);

//------------------------------------------------------------------------------
// service routine events:
#define SWITCH_1	0x0001
#define SWITCH_2	0x0002
#define SWITCH_3	0x0004
#define SWITCH_4	0x0008
#define ADC_READ	0x0010
#define LCD_UPDATE	0x0020
#define TIMERB_INT	0x0040

void SWITCH_1_event(void);
void LCD_UPDATE_event(void);
void TIMERB_INT_event(void);

//------------------------------------------------------------------------------
// global variables
volatile uint16 sys_event;				// pending events
volatile uint16 WDT_cps_cnt;			// WDT count per second
volatile uint16 WDT_adc_cnt;			// counter to adc read event
volatile uint16 WDT_lcd_cnt;			// counter for lcd update event

volatile uint16 servo_pulse_width;
volatile uint8 wiper_on;

//------------------------------------------------------------------------------
// servo equates
#define FREQUENCY(freq)	((freq*(myCLOCK/1000))/(1000*8))
#define	TIMERB_FREQ		FREQUENCY(40000)	// 40 ms
#define SERVO_RIGHT		FREQUENCY(800)		// 0.8 ms
#define SERVO_MIDDLE	FREQUENCY(1500)		// 1.5 ms
#define SERVO_LEFT		FREQUENCY(2200)		// 2.2 ms

extern const unsigned char byu_image[];		// BYU logo

//------------------------------------------------------------------------------
//
void main(void)
{
	eZ430X_init(_12MHZ);				// init board (12 mhz)
	watchdogtimer_init(WDT_CTL);		// init watchdog timer
	ADC_init();							// init ADC
	lcd_init();							// init LCD
	display_init();						// init display
	port2_init();						// init port 2 (switches)
	timerB_init();						// init timer B (servos)
	sys_event = 0;						// clear any pending events

	//--------------------------------------------------------------------------
	//	event service routine loop
	//
	while (1)
	{
		_disable_interrupts();					// disable interrupts before check sys_event //no events, no one can put on the table
		if (sys_event) _enable_interrupt();		// enable interrupts during event servicing
		else __bis_SR_register(LPM0_bits|GIE);	// otherwise, enable interrupts / goto sleep

		//----------------------------------------------------------------------
		//	I'm AWAKE!!!  There must be something to do
		//----------------------------------------------------------------------
		if (sys_event & TIMERB_INT)			// timer B event
		{
			sys_event &= ~TIMERB_INT;		// clear TimerB event
			TIMERB_INT_event();				// update servo movement
		}
		else if (sys_event & SWITCH_1)		// switch #1 event
		{
			sys_event &= ~SWITCH_1;			// clear switch #1 event
			SWITCH_1_event();				// turn servos on/off
		}
		else if (sys_event & LCD_UPDATE)	// update LCD event
		{
			sys_event &= ~LCD_UPDATE;		// clear LCD update event
			LCD_UPDATE_event();				// update LCD
		}
		else								// ????
		{
			ERROR2(12);						// unrecognized event
		}
	}
} // end main


//------------------------------------------------------------------------------
//	switch #1 service event
//
void SWITCH_1_event(void)
{
	P2IFG &= ~0x01;						// acknowledge interrupt
	P2IE |= 0x01;						// enable interrupts again
	wiper_on = (wiper_on) ? OFF : ON;	// toggle on/off
	return;
} // end SWITCH_1_event


//------------------------------------------------------------------------------
//	TimerB frame service event
// 
void TIMERB_INT_event(void)
{
	// update servos direction of movement
	return;
} // end TIMERB_INT_event


//------------------------------------------------------------------------------
//	LCD update service event
//
void LCD_UPDATE_event(void)
{
	lcd_display(LCD_2X_FONT);
	lcd_cursor(100, 6);
	lcd_printf("%s", (wiper_on)?"ON ":"OFF");
	lcd_cursor(100, 4);
	lcd_printf("%d  ", ADC_read(RIGHT_POT));
	lcd_display(~LCD_2X_FONT);
	return;
} // end LCD_UPDATE_event


//------------------------------------------------------------------------------
//	initialization
//
void timerB_init(void)
{
	// configure servo port 4 pins
	P4DIR |= 0x30;						// P4.4 (TB1/A13)
	P4SEL |= 0x30;						// P4.5 (TB2/A14)

	// configure TimerB
	TBCTL = TBSSEL_2+ID_3+MC_1+TBIE;	// SMCLK/8, up mode, interrupt enable
	TBCCR0 = TIMERB_FREQ;				// frame rate
	TBCCR1 = SERVO_RIGHT;				// right
	TBCCTL1 = OUTMOD_3;					// set/reset
	TBCCR2 = SERVO_RIGHT;				// right
	TBCCTL2 = OUTMOD_3;					// set/reset

	servo_pulse_width = SERVO_RIGHT;	// default right
	wiper_on = OFF;						// turn off servos
	return;
} // end timerB_init


//------------------------------------------------------------------------------
//
void port2_init(void)
{
	// configure P2 switches for interrupt
	P2SEL &= ~0x0f;						// select GPIO
	P2DIR &= ~0x0f;						// Configure P2.0-3 as Inputs
	P2OUT |= 0x0f;						// use pull-ups
	P2IES |= 0x0f;						// high to low transition
	P2REN |= 0x0f;						// Enable pull-ups
	P2IE |= 0x0f;						// P2.0-3 interrupt enabled
	P2IFG &= ~0x0f;						// P2.0-3 IFG cleared
	return;
} // end port2_init


//------------------------------------------------------------------------------
//
void watchdogtimer_init(int wd_ctl)
{
	// configure watchdog
	WDTCTL = wd_ctl;					// set watchdog interval
	IE1 |= WDTIE;						// enable WDT interrupt
	WDT_cps_cnt = WDT_CPS;				// set WD 1 second counter
	WDT_lcd_cnt = WDT_LCD;				// lcd update rate
	return;
} // end watchdogtimer_init


//------------------------------------------------------------------------------
//
void display_init(void)
{
	lcd_image(byu_image, 36, 11);		// display BYU
	lcd_display(LCD_2X_FONT);
	lcd_cursor(14, 6);
	lcd_printf("On/Off:");
	lcd_cursor(28, 4);
	lcd_printf("Speed:");
	lcd_display(~LCD_2X_FONT);
	return;
} // end servos_init


//------------------------------------------------------------------------------
// Port 2 interrupt service routine
//
#pragma vector=PORT2_VECTOR
__interrupt void Port_2_ISR(void)
{
	P2IE &= ~(sys_event & 0x0f);			// acknowledge interrupt
	sys_event |= (P2IN ^ 0x0f) & 0x0f;		// generate switch event
	if (sys_event)							// wake-up if event generated
		__bic_SR_register_on_exit(LPM0_bits);
	return;
} // end Port_2_ISR


//------------------------------------------------------------------------------
// Timer B1 interrupt service routine
//
#pragma vector = TIMERB1_VECTOR
__interrupt void TIMERB1_ISR(void)
{
	static uint16 servo_toggle;
	static uint16 servo_cnt = 50;

	if (TBIV != 0x000e) return;				// acknowledge interrupt

	if (wiper_on)
	{
		if (--servo_cnt == 0)
		{
			servo_cnt = 50;
			servo_toggle = (servo_toggle) ? 0 : 1;
			servo_pulse_width = (servo_toggle) ? SERVO_RIGHT : SERVO_LEFT;
		}
	}
	TBCCR1 = TIMERB_FREQ - servo_pulse_width;
	TBCCR2 = TIMERB_FREQ - servo_pulse_width;

	sys_event |= TIMERB_INT;				// generate TIMERB_INT evet
	__bic_SR_register_on_exit(LPM0_bits);	// Clear CPUOFF bit from 0(SR)
	return;
} // end TIMERB1_ISR


//------------------------------------------------------------------------------
//	Watchdog Timer ISR
//
#pragma vector = WDT_VECTOR
__interrupt void WDT_ISR(void)
{
	if (--WDT_cps_cnt == 0)				// 1 second counter
	{
		LED_GREEN_TOGGLE;				// toggle GREEN led
		WDT_cps_cnt = WDT_CPS;
	}
	if (--WDT_lcd_cnt == 0)				// update lcd counter
	{
		sys_event |= LCD_UPDATE;		// generate LCD_UPDATE event
		WDT_lcd_cnt = WDT_LCD;
	}
	if (sys_event)						// wake-up if event generated
		__bic_SR_register_on_exit(LPM0_bits);
	
	return;
} // end WDT_ISR
