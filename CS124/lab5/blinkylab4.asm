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
LOOP_FOR15  .equ	4000 ; 10 seconds delay 2000=5 seconds 400 = 1 second 40 = 1/10 second
LOOP_FOR6	.equ	6
LOOP_FOR50	.equ	50
LOOP_FOR10	.equ	10
LOOP_FOR20	.equ	20
LOOP_FOR2	.equ	2
ONETENTH	.equ	40000
;------------------------------------------------------------------------------
            .text                           ; beginning of executable code
;------------------------------------------------------------------------------
RESET:      mov.w   #0x0280,SP              ; init stack pointer 				;2 cycles
            mov.w   #WDTPW+WDTHOLD,&WDTCTL  ; stop WDT							;5 cycles
            bis.b   #0x01,&P1DIR            ; set P1.0 as output				;4 cycles = total of 11

start:		bis.b   #0x01,&P1OUT
			mov.w   #LOOP_FOR50, r14
			
fivesec:  	call    #subtenth
			dec.w   r14
			jne		fivesec
			bic.b	#0x01,&P1OUT

			mov.w 	#LOOP_FOR6, r12
sixsec:		xor.b   #0x01,&P1OUT
			mov.w	#LOOP_FOR10,r9
			
onesec:		call	#subtenth
			dec.w	r9
			jne		onesec ;becomes one sec
			dec.w	r12
			jne		sixsec ; toggles it
			
			mov.w	#LOOP_FOR20, r11
foursec:	xor.b	#0x01,P1OUT

			mov.w	#LOOP_FOR2,r10
onefifth:	call	#subtenth			
			dec.w	r10
			jne		onefifth
			dec.w	r11
			jne		foursec
			
			mov.w	#LOOP_FOR10,r7
			
end:		bic.b	#0x01,&P1OUT
			mov.w	#LOOP_FOR10,r6
			
anothersec:	call	#subtenth
			dec.w	r6
			jne		anothersec
			dec.w	r7
			jne		end
			jmp		start
			
			
			
			;jmp		start
			
									

subtenth:	mov.w	#ONETENTH, r8
subtenth2:  dec.w	r8
 			jne		subtenth2
 			ret           
           

;------------------------------------------------------------------------------
;           Interrupt Vectors
;------------------------------------------------------------------------------
            .sect   ".reset"                ; MSP430 RESET Vector
            .short  RESET                   ; start address
            .end