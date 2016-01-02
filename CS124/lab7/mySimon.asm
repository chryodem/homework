        .title  "simon.asm"
;*******************************************************************************
;  Project:  simon.asm
;  Author:  Andrei Rybin, S2011
;
;  Description: A MSP430 assembly language program that plays the game of Simon.
;
;  1. Begin your game of Simon with a four LED/tone sequence. 
;  2. Start each round by rapidly flashing all four LEDs several times. 
;  3. Save the random seed. 
;  4. After a short pause, individually flash the LEDs (and associated tones)
;     one at a time in a random sequence. 
;  5. At the completion of the above sequence, restore the random seed. 
;  6. Now sample the push bottom switches and output the corresponding LED/tone
;     as each button is pressed. 
;  7. Continue sampling and comparing the push button sequence with the computer
;     generated sequence until either the end of the sequence is reached or a
;     mistake is made. 
;  8. If all the LEDs/tones are correctly reproduced in the correct order,
;     output some congratulatory tune (such as "charge!") and restart the game,
;     but with one more LED/tone added to the same sequence. 
;  9. Otherwise, immediately output a raspberry sound and restart the game with
;     a new four LED/tone sequence.
;
;  Timer_B output (TB2) is used for hardware PWM of the transducer (buzzer). 
;
;  Bonus:
;
;      -Pressing the eZ430-RF2500 board (P1.2) button restarts your program.
;      -Port 2 interrupts are used to detect a pressed switch.
;
;  **NOTE: ALL subroutines must be correctly implemented using Callee-Save protocol!
;
;                       MSP430F2274
;                    -----------------
;         RED LED<--|P1.0         P2.0|<--SW_1 (pull-up)
;       GREEN LED<--|P1.1         P2.1|<--SW_2 (pull-up)
;    eZ430 BUTTON-->|P1.2         P2.2|<--SW_3 (pull-up)
;                   |             P2.3|<--SW_4 (pull-up)
;                   |             P2.4|-->LCD BACKLIGHT
;                   |                 |
;                   |             P2.6|-->LED_1
;                   |             P2.7|-->LED_2
;                   |             P3.3|-->LED_3
;                   |             P4.6|-->LED_4
;                   |                 |
;                   |             P4.5|-->BUZZER (P4SEL.5=1)
;
;*******************************************************************************
;       external linkage
;
      ;  .ref    rand16                 ; get random #
        
;------------------------------------------------------------------------------
;	external references
;
		.ref	initRand16			; init random # generator
		.ref	rand16				; get 16-bit random #
		.ref	setRandSeed			; set random # seed
		.ref	getRandSeed			; get current random # seed


;------------------------------------------------------------------------------
  
;*******************************************************************************
;       constants and equates
;
        .cdecls C,LIST,"msp430x22x4.h"

myCLOCK	.set	80000000				; 8 Mhz clock speed
 		mov.b   &CALBC1_8MHZ,&BCSCTL1   ; Set range
    	mov.b   &CALDCO_8MHZ,&DCOCTL    ; Set DCO step + modulation
WDT_CTL .equ    WDT_MDLY_32             ; WD configuration (Timer, SMCLK, 32 ms)
WDT_CPI .equ    32000                   ; WDT Clocks Per Interrupt (@1 Mhz)
WDT_IPS .equ    myCLOCK/WDT_CPI         ; WDT Interrupts Per Second
STACK   .equ    0x0600                  ; top of stack
CYCLE	.equ	41000
TONE    .equ    2000                    ; buzzer tone
DELAY   .equ    20                      ; delay count
DO		.equ	4000
RE		.equ	DO*8/9
MI		.equ	DO*4/5
FA		.equ	DO*3/4
SOL		.equ	DO*2/3
LA		.equ	DO*3/5
TI		.equ	DO*8/15
DO1		.equ	DO*1/2



;*******************************************************************************
;       RAM section
;
        .bss    WDTSecCnt,2             ; watchdog counts/second
        .bss    WDT_delay,2             ; watchdog delay counter
        .asg "bis.b    #0x40,&P4OUT",LED4_ON	;red
   	    .asg "bic.b    #0x40,&P4OUT",LED4_OFF

        .asg "bis.b    #0x08,&P3OUT",LED3_ON	;yellow
        .asg "bic.b    #0x08,&P3OUT",LED3_OFF

        .asg "bis.b    #0x80,&P2OUT",LED2_ON	;orange
        .asg "bic.b    #0x80,&P2OUT",LED2_OFF

        .asg "bis.b    #0x40,&P2OUT",LED1_ON	;green
        .asg "bic.b    #0x40,&P2OUT",LED1_OFF

;*******************************************************************************
;       ROM section
;
        .text                           ; code Section
reset:  mov.w   #STACK,SP               ; Initialize stack pointer
		
;       Set Watchdog interval
        mov.w   #WDT_CTL,&WDTCTL        ; Set Watchdog interrupt interval
        mov.w   #WDT_IPS,&WDTSecCnt
        mov.b   #WDTIE,&IE1             ; Enable WDT interrupt

;       initialize eZ430X board
        call    #eZ430X_init            ; initialize ports

;       enable eZ430X buzzer to use Timer B PWM
        clr.w   &TBR                    ; Timer B SMCLK, /1, up mode
        mov.w   #TBSSEL_2|ID_0|MC_1,&TBCTL
        mov.w   #OUTMOD_3,&TBCCTL2      ; output mode = set/reset

;       enable interrupts
        bis.w   #GIE,SR                 ; enable interrupts
        call	#initRand16
;****************START PROGRAM************************************************
;*****************************************************************************
			call	#blinkred
			call	#blinkyellow	
			call	#blinkorange
			call	#blinkgreen
			call	#blinkgreen
			call	#blinkyellow
			call	#blinkred
			call	#getRandSeed
			
start:		call	#delay
			;call	#getRandSeed
			;call	#
			mov.w	r12,r10 ;save the random seed
			

			
;-----------------------------------------------------------------------------


;fail:		call	#setRandSeed
;			mov.w	#4,r11	;start the sequence over
;			jmp		randNumRec

			mov.w	#2,r11 ;number of tries originally

numLight:	mov.w	r11,r6 ; save the number of tries

nextLight:
;-----------------------------------------------------------------------------   
;                 Blink random lights
;
;-----------------------------------------------------------------------------   
        
randNumRec: call	 #rand16		;get the rand number into r12
			and.w	 #3,r12
			
sw_case:	cmp		#0,r12
			jne 	sw_case1
			;call	#blinkred
			call	#blinkgreen
			;call	#blinkorange
			;call	#blinkyellow
			jmp		sw_end ; go to the end of switch
			
sw_case1:	cmp		#1, r12
			jne		sw_case2
			call	#blinkorange
			;call	#blinkyellow
			;call	#blinkgreen
			jmp		sw_end
			
sw_case2:	cmp		#2, r12

			jne 	sw_case3
			;call	#blinkorange
			call	#blinkyellow
			;call	#blinkgreen
			jmp		sw_end

sw_case3:	cmp		#3, r12
			;call	#blinkgreen
			call	#blinkred
			

			
sw_end:

			dec.w	r6	;decrement that register by 1
			  jne	nextLight



;------------------------------------------------------------------------------
;				Compare the lights
;------------------------------------------------------------------------------

comparing:			mov.w 	r10,r12 ;restore the seed
					call 	#setRandSeed ;to reuse the same seed
					mov.w	r11,r6
		
keepPlaying:		call	#rand16 ;get the next value
					and.w	#3,r12 ;and it
					call	#getSwitch ;gets the value of the pressed button

		
sw_button: 			cmp.w 	#8,r4
					jne		sw_button1
					call	#blinkred
					cmp.w	#3,r12
					
					jne		RASPERRY
					jmp		nextLight1

sw_button1: 		cmp.w 	#4,r4
					jne		sw_button2
					call	#blinkyellow
					cmp.w	#2,r12
					jne		RASPERRY
					jmp		nextLight1

sw_button2:			cmp.w 	#2,r4
					jne		sw_button3
					call	#blinkorange
					cmp.w	#1,r12
					jne		RASPERRY
					jmp		nextLight1

sw_button3:			cmp.w 	#1,r4
					call	#blinkgreen
					cmp.w	#0,r12
					jne 	RASPERRY
					jmp		nextLight1

nextLight1:			dec.w 	r6
					jne		keepPlaying
					call	#success			

sw_buttonEnd:

;*******************************************************************************
success:	add.w	#1,r11 	;in case of success add one to the sequence
			mov.w	r11,r6
			mov.w	r10,r12
			call	#setRandSeed
			call	#blinkred
			call	#blinkgreen
			call	#blinkorange
			call	#blinkyellow
			call	#delay
			
			jmp		numLight;
;**************************RASPERRY*********************************************

RASPERRY:	    mov.w 	#DO1, &TBCCR0
				mov.w	#DO1/2, &TBCCR2
				mov.w	#10,r15;<===================================================
supersmall:		dec.w	r15	
				jne		supersmall
							
				jmp		start	

			



;*******************************************************************************

blinkred:	LED4_ON

			mov.w #RE, &TBCCR0
			mov.w #RE/2, &TBCCR2
			mov.w #RE, r7
			call	#delay
;			mov.w	#CYCLE,r5
;onestep4:	dec.w	r5
;			jne		onestep4
			mov.w #0, &TBCCR0
			LED4_OFF
			ret
			
;*******************************************************************************
blinkyellow:

			LED3_ON
			mov.w #MI, &TBCCR0
			mov.w #MI/2, &TBCCR2
			call	#delay
;			mov.w	#CYCLE,r5
;onestep1:	dec.w	r5
;			jne		onestep1
			mov.w #0, &TBCCR0
			LED3_OFF
			ret

;*******************************************************************************
blinkorange:

			LED2_ON
			mov.w #TI, &TBCCR0
			mov.w #TI/2, &TBCCR2
			call	#delay
;			mov.w	#CYCLE,r5
;onestep2:	dec.w	r5
;			jne		onestep2
			
			

			mov.w #0, &TBCCR0
			LED2_OFF
			ret
			
			
;*******************************************************************************
blinkgreen:

			LED1_ON
			mov.w #FA, &TBCCR0
			mov.w #FA/2, &TBCCR2
			mov.w #FA, r7
			call	#delay

;			mov.w	#CYCLE,r5
;onestep:	dec.w	r5
;			jne		onestep
		
			mov.w #0, &TBCCR0
		
			LED1_OFF
			ret

;******************************************************************


;*******************************************************************************
;       turn on an LED
;
LEDs:
        bic.b   #0xc0,&P2OUT            ; turn off LED's
        bic.b   #0x40,&P4OUT 
        bic.b   #0x08,&P3OUT

        bit.b   #0x01,r12
          jeq   LEDs02
        bis.b   #0x40,&P2OUT
        ret

LEDs02: bit.b   #0x02,r12
          jeq   LEDs04
        bis.b   #0x80,&P2OUT 
        ret

LEDs04: bit.b   #0x04,r12
          jeq   LEDs06
        bis.b   #0x08,&P3OUT
        ret

LEDs06: bis.b   #0x40,&P4OUT 
        ret

;*******************************************************************************
;       delay
;
delay:  mov.w   #DELAY,&WDT_delay       ; set WD delay counter
        bis.w   #LPM0,SR                ; goto sleep
        mov.w	#CYCLE,r15
delay1:	dec.w	r15
		jne		delay1
        ret                             ; I'm awake - return

;*******************************************************************************
;       get switch subroutine
;
getSwitch:  
                            			; get switch subroutine
        mov.b   &P2IN,r4                ; wait for a switch
        and.b   #0x0f,r4
        xor.b   #0x0f,r4                ; any switch depressed?
          jeq   getSwitch               ; n
          
        ret                             ; y, return
        
        
;*******************************************************************************
;Random seed holder


;*******************************************************************************
;    enable/disable tone
;
toneON:
        push    r12
        rra.w   r12                     ; tone / 2
        mov.w   r12,&TBCCR2             ; use TBCCR2 as 50% duty cycle
        pop     r12
        mov.w   r12,&TBCCR0             ; start clock
        ret

toneOFF:
        mov.w   #0,&TBCCR0              ; Timer B off
        ret

;*******************************************************************************
;       initialize eZ430X development board
;
eZ430X_init:                            ; initialize eZ430X development board
;       eZ430 LED's
        bis.b   #0x03,&P1DIR            ; eZ430 LED's
        bic.b   #0x03,&P1OUT

;       eZ430X push buttons
        bic.b   #0x0f,&P2SEL            ; eZ430X push buttons
        bic.b   #0x0f,&P2DIR            ; Configure P2.0-3 as Inputs
        bis.b   #0x0f,&P2OUT            ; set to pull-up
        bis.b   #0x0f,&P2IES            ; h to l
        bis.b   #0x0f,&P2REN            ; enable pull-ups

;       eZ430X LEDs
        bic.b   #0xc0,&P2SEL            ; LED_2 (P2.7) & LED_1 (P2.6)
        bis.b   #0xc0,&P2DIR
        bic.b   #0xc0,&P2OUT
        bic.b   #0x08,&P3SEL            ; LED_3 P3.3
        bis.b   #0x08,&P3DIR
        bic.b   #0x08,&P3OUT
        bic.b   #0x40,&P4DIR            ; LED_4 P4.6
        bis.b   #0x40,&P4DIR
        bic.b   #0x40,&P4OUT 

;       eZ430X buzzer
        bis.b   #0x20,&P4DIR            ; P4.5 output (buzzer)
        bis.b   #0x20,&P4SEL            ; P4.5 uses TB2
        ret


;*******************************************************************************
;       Interrupt Service Routines
;
WDT_ISR:                                ; Watchdog Timer ISR
        cmp.w   #0,&WDT_delay           ; delaying?
          jeq   WDT_02                  ; n
        dec.w   &WDT_delay              ; y
        cmp.w   #0,&WDT_delay           ; wake-up processor?
          jne   WDT_02                  ; n
        bic.w   #LPM0,0(SP)             ; y, clear low-power bits for reti

WDT_02:
        dec.w   &WDTSecCnt              ; decrement counter, 0?
          jne   WDT_04                  ; n
        mov.w   #WDT_IPS,&WDTSecCnt     ; y, re-initialize counter
        xor.b   #0x01,&P1OUT            ; toggle P1.0

WDT_04: reti                            ; return from interrupt


;*******************************************************************************
;       Interrupt vector sections

        .sect   ".int10"                ; WDT vector section
        .word   WDT_ISR                 ; address of WDT ISR

        .sect   ".reset"                ; reset vector section
        .word   reset                   ; address of reset
        .end
