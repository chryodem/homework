;*******************************************************************************
;   CS/ECEn 124 Lab 1 - blinky.asm: Software Toggle P1.0
;
;   Description: Toggle P1.0 by xor'ing P1.0 inside of a software loop.
;
;             MSP430F2013/F2274
;             -----------------
;         /|\|              XIN|-
;          | |                 |
;          --|RST          XOUT|-
;            |                 |
;            |             P1.0|-->GREEN LED
;            |             P1.1|-->RED LED (F2274)
;
;*******************************************************************************
           .cdecls C,LIST, "msp430x20x3.h"  ; MSP430F2013
;          .cdecls C,LIST, "msp430x22x4.h"  ; MSP430F2274
SMALL_DELY	.equ	1000
;------------------------------------------------------------------------------
            .text                           ; beginning of executable code
;------------------------------------------------------------------------------
RESET:      mov.w   #0x0280,SP              ; init stack pointer
            mov.w   #WDTPW+WDTHOLD,&WDTCTL  ; stop WDT
            bis.b   #0x01,&P1DIR            ; set P1.0 as output

mainloop:   bis.b   #0x01,&P1OUT            ; toggle P1.0
			mov.w	#SMALL_DELAY, r15
            
lp1:		dec.w	r15
			jnz		lp1
            bic.b	#0x01,&P1OUT            ; toggle P1.0
            
            mov.w   #50,r15                  ; use R15 as delay counter
            mov.w 	#0,r14

delayloop:  dec.w	r14
			jne		delayloop
			dec.w   r15                     ; delay over?
              jnz   delayloop               ; n
            jmp     mainloop                ; y, toggle led

;------------------------------------------------------------------------------
;           Interrupt Vectors
;------------------------------------------------------------------------------
            .sect   ".reset"                ; MSP430 RESET Vector
            .short  RESET                   ; start address
            .end