#include "msp430x22x4.h"
#include "eZ430X.h"
#include "lcd.h"
#include "Dice.h"
#include "dicePics.c"
int WDTSecCnt=0;
int seconds=0;
int main (void)
{
	int doubles=0; //0 =false, 1 = true
	int backlight=0;
	
	eZ430X_init(myCLOCK);       // init board
	
    lcd_init();   
	lcd_backlight(1);
	__bis_SR_register(GIE);				// enable interrupts
	
    P4DIR |= 0x20; // P4.5 transducer output
    P4SEL |= 0x20; // P4.5 timerB output
    SPEAKER_ON;
        
    TBR = 0;			     // Timer B SMCLK, /1, up mode
	TBCTL = TBSSEL_2 | ID_0 | MC_1;
	TBCCTL2 = OUTMOD_3;		// output mode = set/reset
	TBCTL |= TBIE;			// enable timer B interrupts
	TBCTL &= ~TBIFG;
	
	//P4SEL |= 0x20;
	
	TBCCR0 = 000;  // set speaker to off
    TBCCR2 = 000;
    
    port2_init();
    
    IE1 |= WDTIE;
    WDTCTL = WDT_CTL; //revive the dog      
    WDTSecCnt = WDT_CPS;          
    //bis.w  #LPM0+GIE,SR;//enter LPM0 w/interrupts
	
	lcd_cursor(0, 3);	
	lcd_printf("Dice by Andrei Rybin");
	lcd_cursor(0, 0);
	lcd_printf("Press any button to continue");	
	
	P2IE |= 0x0f;                        // P2.0-3 interrupt enabled
	_bis_SR_register(CPUOFF+GIE);
	P2IE &= 0xF0;
	
	lcd_clear(0); 
	doubles=0;
	
	while(1)
	{				
		
		doubles=rollDice(&backlight,doubles);						
		if (doubles>0) congradulations(); //congradulations
		if (doubles==2)
		{
			TBCCR0 = note_G;  // set interval (frequency)
    		TBCCR2 = note_G/5;// load volume (duty cycle)
            TBCCR0 = note_G;  // set interval (frequency)       
			doubles=0;
		}
		wait(400);	
		LED_4_ON;
		BACKLIGHT_ON;
		//wait for button press
		P2IE |= 0x0f;                        // P2.0-3 interrupt enabled
		_bis_SR_register(CPUOFF+GIE);
		seconds=0;
		BACKLIGHT_ON;
		P2IE &= 0xF0;                       // P2.0-3 interrupt disabled
		LED_4_OFF;
			
	}	
	return 0;	
}
int rollDice(int* backlight, int doubles)
{
	int counterLimit;
	int counter,dice1,dice2;
	drawDieFrame(10);
	drawDieFrame(90);
	
	counterLimit=2;	
	counter=counterLimit;
	while(counterLimit<900)
	{
		seconds=0;
		boop();
		*backlight^=1;
		lcd_backlight(*backlight);
		counter=counterLimit;
		dice1=rand();
		dice1+=1;	
		if (dice1<1||dice1>6) dice1=0;
		drawDie(dice1, 18);
		dice2=rand();		
		dice2+=1;		
		if (dice2<1||dice2>6) dice2=0;
		drawDie(dice2, 98);	
		TBCCR0 = 0;  // set interval (frequency)
		wait(counter);
		counterLimit*=1.5;		
		//flash the lights		
	}
	lcd_backlight(1);
	dice1=rand();
	dice1+=1;	
	if (dice1<1||dice1>6) dice1=3;
	drawDie(dice1, 18);
	dice2=rand();		
	dice2+=1;		
	if (dice2<1||dice2>6) dice2=4;
	drawDie(dice2, 98);	
	TBCCR0 = 9000;  // set interval (frequency)
    TBCCR2 = 5000;// load volume (duty cycle)     
    wait(300);
    TBCCR0 = 000;  // set interval (frequency)
	return doubles;
}
void congradulations(void)
{
	int i,tone;
	tone=3000;
	for (i=0;i<4;i++)
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

void boop(void)
{
	TBCCR0 = 24000;  // set interval (frequency)
    TBCCR2 = 20000;// load volume (duty cycle)        
}

void port2_init(void)
{
    // configure P2 switches for interrupt
    P2SEL &= 0xf0;                           // select GPIO
    P2DIR &= 0xf0;                        // Configure P2.0-3 as Inputs
    P2OUT |= 0x0f;                        // use pull-ups
    P2IES |= 0x0f;                        // high to low transition
    P2REN |= 0x0f;                        // Enable pull-ups
    P2IE  |= 0x0f;                        // P2.0-3 interrupt enabled
    P2IFG &= ~0x0f;                      // P2.0-3 IFG cleared
    return;
}
// Watchdog Timer interrupt service routine
#pragma vector=WDT_VECTOR
__interrupt void WDT_ISR(void)
{
  WDTSecCnt-=1;
  if (WDTSecCnt==0) 
  {
  	P1OUT ^= 0x02;                            // Toggle P1.0 using exclusive-OR
  	WDTSecCnt=WDT_CPS;
  	seconds+=1;
  	if (seconds>6) BACKLIGHT_OFF;  	
  }
  //return;
}
//------------------------------------------------------------------------------
// Port 2 interrupt service routine
#pragma vector=PORT2_VECTOR
__interrupt void Port_2(void)
{
   //_disable_interrupts();
   P2IFG &= ~0x0f;
   __bic_SR_register_on_exit(LPM0_bits); // Clear CPUOFF bit from 0(SR)
   return;
}
__interrupt void TMB_ISR(void);
#pragma vector = TIMERB1_VECTOR
__interrupt void TMB_ISR(void)
{
	TBCTL &= ~TBIFG;            	// acknowledge interrupt
	P4OUT ^= 0x20;		// Toggle Buzzer
}


