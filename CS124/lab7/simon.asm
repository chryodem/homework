       .title  "simon.asm"
;*******************************************************************************
;  Project:  simon.asm
;  Author:  Student, W2011
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
        .ref    rand16                 ; get random #
  
;*******************************************************************************
;       constants and equates
;
        .cdecls C,LIST,"msp430x22x4.h"

myCLOCK .set    1200000                 ; 1.2 Mhz clock
WDT_CTL .equ    WDT_MDLY_32             ; WD configuration (Timer, SMCLK, 32 ms)
WDT_CPI .equ    32000                   ; WDT Clocks Per Interrupt (@1 Mhz)
WDT_IPS .equ    myCLOCK/WDT_CPI         ; WDT Interrupts Per Second
STACK   .equ    0x0600                  ; top of stack

TONE    .equ    2000                    ; buzzer tone
DELAY   .equ    20                      ; delay count

;*******************************************************************************
;       RAM section
;
        .bss    WDTSecCnt,2             ; watchdog counts/second
        .bss    WDT_delay,2             ; watchdog delay counter

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
;
loop:
        mov.w   #4,r15

loop02: call    #rand16                 ; get random # (returned in r12)
        and.w   #0x0fff,r12
        add.w   #TONE,r12
        call    #LEDs                   ; turn on an LED
        call    #toneON                 ; turn on tone
        call    #delay                  ; delay
        dec.w	r15
          jne   loop02
        call    #toneOFF                ; turn off tone
        call    #getSwitch              ; get switch
        jmp     loop

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
        ret                             ; I'm awake - return

;*******************************************************************************
;       get switch subroutine
;
getSwitch:                              ; get switch subroutine
        mov.b   &P2IN,r4                ; wait for a switch
        and.b   #0x0f,r4
        xor.b   #0x0f,r4                ; any switch depressed?
          jeq   getSwitch               ; n
        ret                             ; y, return

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