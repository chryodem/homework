;*******************************************************************************
;   CS/ECEn 124 Lab 1 - blinky.asm: Software Toggle P1.0
;*******************************************************************************
;cycles = 141557760
;MCLK =  1.41Mhz
;CPI = MCLK/ number of instructions (1335610)= 1.48
;MIPS = MCLK/CPI/1M = 9.52 MIPS
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
SMALL_DELAY	.equ	10000 ;used to turn on the light
LOOP_FOR14	.equ	1000 ;used in the loop
LOOP_FOR15  .equ	4000 ; 10 seconds delay 400 = 1 second 40 = 1/10 second
LOOP_FOR6	.equ	6
LOOP_FOR5	.equ	5
LOOP_FOR20	.equ	20
;------------------------------------------------------------------------------
            .text                           ; beginning of executable code
;------------------------------------------------------------------------------
RESET:      mov.w   #0x0280,SP              ; init stack pointer 				;2 cycles
            mov.w   #WDTPW+WDTHOLD,&WDTCTL  ; stop WDT							;5 cycles
            bis.b   #0x01,&P1DIR            ; set P1.0 as output				;4 cycles = total of 11

mainloop:   bis.b   #0x01,&P1OUT            ; Turn LED on						;4 cycles
			mov.w	#SMALL_DELAY, r15											;2 cycle = total 17
            
lp1:		dec.w	r15						;decrements r15 by 1				;1 cycle (1+2)*SMALL_DELAY=3000
			jnz		lp1															;2 cycles
            bic.b	#0x01,&P1OUT       		; turn  P1.0 off					;4 cycles total = 3021
            
            mov.w   #LOOP_FOR15,r15         ; use R15 as delay counter			;2 cycle 
delayloop1: mov.w 	#LOOP_FOR14,r14														;1 cycle total = 3023

delayloop:	dec.w	r14						; decrement r14 by 2				;1 cycle 3*65536= 196608 * 240 + 720 +2			
			jnz		delayloop				; while r14!=0 go back				;2 cycles							
			dec.w   r15                     ; delay over?						;1 cycle 3*240= 720					
              jnz   delayloop1              ; jump to delay loop !=0			;2 cycles							
            jmp     mainloop                ; when r15==0, jump to mainloop		;2 cycles
           
           

;------------------------------------------------------------------------------
;           Interrupt Vectors
;------------------------------------------------------------------------------
            .sect   ".reset"                ; MSP430 RESET Vector
            .short  RESET                   ; start address
            .end