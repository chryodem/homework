#ifndef DICE_H_
#define DICE_H_

#endif /*DICE_H_*/

#define myCLOCK  CALDCO_8MHZ
#define CLOCK    8000000      		// SMCLK = ~8 mhz

#define WDT_CTL  WDT_MDLY_32 // WDT SMCLK, 8 ms (@1 Mhz)
#define WDT_CPS  CLOCK/32000         // WD clocks / second count

#define Freq_A2  8000000/440/2/2     //the Freqeuncy of A - 440 hz
#define Freq_D	 8000000/587/2/2	 //the frequency of D - 293.66 hz
#define Freq_A   8000000/880/2/2 	 //the frequency of HiA-880.0
#define Freq_G	 8000000/784/2/2     //the frequency of G - 783.99
#define TB_CTL   TBSSEL_2+ID_0+MC_1  //set timer b register 
#define note_A	 CLOCK/Freq_A        // WD clocks / second count
#define note_D	 CLOCK/Freq_D
#define note_G	 CLOCK/Freq_G
#define note_A2	 CLOCK/Freq_A2

int main(void);
void port2_init(void);
void boop(void);
__interrupt void Port_2(void);
__interrupt void WDT_ISR(void);
void congradulations(void);
