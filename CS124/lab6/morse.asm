			.title	"morse.asm"
;*******************************************************************************
;     Project:  morse.asm
;      Author:  Andrei Rybin
;
; Description:  Write a MSP430 assembler program that outputs a message
;               in Morse Code on the red LED and buzzer using a watchdog
;               interrupt service routine (ISR). Also, blink the eZ430
;               green LED on and off at one second intervals.
;
;    Solution:  Toggle P1.0 by xor'ing P1.0 inside of a software loop.
;
;    Concepts:  Watchdog   MMB 8.1, pg 276.
;               ISR's      MMB 6.8, pg 190.
;
;     Jumpers:  A  P4.4     Left Pot  O==O  O  Servo #1
;               B  P4.5       Buzzer  O==O  O  Servo #2
;               C  P4.6   Thermistor  O  O==O  LED_4
;               D  P2.2  ADXL345 INT  O  O==O  SW_3
;               E  P2.3         SW_4  O==O  O  Servo #3
;               F  P2.4    Backlight  O==O  O  Servo #4
;               G  P4.3    Right Pot  O==O  O  EZ0_AN
;               H  P2.1         SW_2  O==O  O  EZ0_TX
;
;                   MSP430F2274
;               .-----------------.
;               |             P1.0|-->RED LED
;               |             P1.1|-->GREEN LED
;               |                 |
;               |             P4.5|-->BUZZER
;
;*******************************************************************************
            .cdecls C,LIST,"msp430x22x4.h"  ; include c header
           

myCLOCK     .set    1200000                 ; 1.2 Mhz clock
WDT_CTL     .equ    WDT_MDLY_0_5            ; WD configuration (SMCLK, 0.5 ms)
WDT_CPI     .equ    500                     ; 500 clocks / WD interval (@1 Mhz)
WDT_IPS     .equ    myCLOCK/WDT_CPI         ; WD intervals / second (@1.2 Mhz)
STACK       .equ    0x0600                  ; top of stack

END         .equ    0
DOT         .equ    1
DASH        .equ    2
SPACE       .equ    3
END_MESSAGE .equ    4
CYCLE		.equ	41000
                            
;------------------------------------------------------------------------------
;	Global variables
            .bss    WDTSecCnt,2             ; WDT second counter
			.bss	BuzzerCheck,2			; Sets memory for the global variable BUZZERCHECK                            
;------------------------------------------------------------------------------
            .text                           ; program section
message:    .byte   DOT,DOT,DOT,DOT,END     ; H		Hello World Message
            .byte   DOT,END         	    ; E
            .byte   DOT,DASH,DOT,DOT,END    ; L
            .byte   DOT,DASH,DOT,DOT,END    ; L
            .byte	DASH,DASH,DASH,END		; O
            .byte	SPACE,END				;space
            .byte	DOT,DASH,DASH, END		; W
            .byte	DASH,DASH,DASH,END		; O
            .byte	DOT,DASH,DOT, END		; R
            .byte   DOT,DASH,DOT,DOT,END    ; L
            .byte	DASH,DOT,DOT, END		; D
            .byte   SPACE,END               ; space
            .byte   END_MESSAGE             ; end of message
            .align  2						; align on word boundary
            ; .string "Hello World"

RESET:      mov.w   #STACK,SP               ; initialize stack pointer
            mov.w   #WDT_CTL,&WDTCTL        ; set WD timer interval
            mov.w   #WDT_IPS,&WDTSecCnt     ; initialize 1 sec WD counter
            mov.b   #WDTIE,&IE1             ; enable WDT interrupt
            bis.b   #0x01,&P1OUT			; enable P1 output
            bis.b   #0x02,&P2OUT			; enable P2 output
            bis.b   #0x20,&P4DIR            ; P4.5 output (transducer)
            bis.b   #0x03,&P1DIR            ; P4.5 output (transducer)
            bis.w   #GIE,SR                 ; enable interrupts

loop:                                       ; output morse code message
;           << add your code here >>

           ; jmp     loop                   ; repeat message
            mov.w	#message,r15

nextel:    mov.b	@r15+,r14				; inderect auto-increment
			cmp.b		#END, r14 			; if (end == r14)
			jne		nextel2					; then go back up for next element
			call	#DOEND
			jmp		nextel
						
nextel2:	cmp.b		#DOT, r14			; if (dot==r14)
			jne		nextel3
			call	#DODOT
			jmp		nextel
			
nextel3:	cmp.b		#DASH,r14
			jne		nextel4
			call	#DODASH
			jmp		nextel
			
nextel4:	cmp.b		#SPACE, r14
			jne		nextel5
			call	#DOSPACE
			jmp		nextel
			
nextel5:	cmp.b		#END_MESSAGE, r14
			call	#DOENDMESSAGE
			jmp		message
			
DOEND:		call	#delay
			call	#delay
			ret
			
DODOT:		
			mov.w	#1,&BuzzerCheck
			call	#delay
			mov.w	#0,&BuzzerCheck
			call	#delay
			ret

DODASH:		mov.w	#1,&BuzzerCheck
			call	#delay
			call	#delay
			call	#delay
			mov.w	#0,&BuzzerCheck
			call	#delay
			ret

DOSPACE:	call	#delay
			call	#delay
			ret
DOENDMESSAGE:

			ret
			
			
delay:		mov.w	#CYCLE,r12
onestep:	dec.w	r12
			jne		onestep
			ret
			
			
			
			
			
			
            
            

;------------------------------------------------------------------------------
;   Watchdog Timer interrupt service routine
;
WDT_ISR:
;..............................................................................
;           << add your code here >>
            ;bit.w   #0x0200,&WDTSecCnt      ; turn off? << remove/replace >>
            cmp		#0, &BuzzerCheck		;checks if there is something in the buzzer
              jeq   WDT_02                  ; y
            xor.b   #0x20,&P4OUT            ; n, buzz using 50% PWM
			xor.b	#0x01,&P1OUT
			bic.b	#0x01,&P1OUT
WDT_02:	    dec.w   &WDTSecCnt              ; decrement counter, 0?
              jne   WDT_04                  ; n
            mov.w   #WDT_IPS,&WDTSecCnt     ; y, re-initialize counter
            xor.b	#0x02,&P1OUT

WDT_04:     reti                            ; return from interrupt

;------------------------------------------------------------------------------
;           Interrupt Vectors
;------------------------------------------------------------------------------
            .sect   ".int10"                ; Watchdog Vector
            .word   WDT_ISR                 ; Watchdog ISR

            .sect   ".reset"                ; PUC Vector
            .word   RESET                   ; RESET ISR
            .end
