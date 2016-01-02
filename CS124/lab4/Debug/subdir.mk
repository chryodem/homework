################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
C_SRCS += \
C:/Users/Andrei/Desktop/cs\ 124/blinky.c 

ASM_SRCS += \
C:/Users/Andrei/Desktop/cs\ 124/blinky.asm \
../blinkylab4.asm 

CMD_SRCS += \
../lnk_msp430f2013.cmd 

ASM_DEPS += \
./blinky.pp \
./blinkylab4.pp 

OBJS += \
./blinky.obj \
./blinkylab4.obj 

C_DEPS += \
./blinky.pp 

ASM_SRCS_QUOTED += \
"C:/Users/Andrei/Desktop/cs 124/blinky.asm" \
"../blinkylab4.asm" 

C_SRCS_QUOTED += \
"C:/Users/Andrei/Desktop/cs 124/blinky.c" 


# Each subdirectory must supply rules for building sources it contributes
blinky.obj: C:/Users/Andrei/Desktop/cs\ 124/blinky.asm $(GEN_SRCS) $(GEN_OPTS)
	@echo 'Building file: $<'
	@echo 'Invoking: MSP430 Compiler'
	"C:/Program Files (x86)/Texas Instruments/ccsv4/tools/compiler/msp430/bin/cl430" --silicon_version=msp -g --include_path="C:/Program Files (x86)/Texas Instruments/ccsv4/msp430/include" --include_path="C:/Program Files (x86)/Texas Instruments/ccsv4/tools/compiler/msp430/include" --diag_warning=225 --sat_reassoc=off --fp_reassoc=off --plain_char=unsigned --printf_support=minimal --preproc_with_compile --preproc_dependency="blinky.pp" $(GEN_OPTS_QUOTED) $(subst #,$(wildcard $(subst $(SPACE),\$(SPACE),$<)),"#")
	@echo 'Finished building: $<'
	@echo ' '

blinky.obj: C:/Users/Andrei/Desktop/cs\ 124/blinky.c $(GEN_SRCS) $(GEN_OPTS)
	@echo 'Building file: $<'
	@echo 'Invoking: MSP430 Compiler'
	"C:/Program Files (x86)/Texas Instruments/ccsv4/tools/compiler/msp430/bin/cl430" --silicon_version=msp -g --include_path="C:/Program Files (x86)/Texas Instruments/ccsv4/msp430/include" --include_path="C:/Program Files (x86)/Texas Instruments/ccsv4/tools/compiler/msp430/include" --diag_warning=225 --sat_reassoc=off --fp_reassoc=off --plain_char=unsigned --printf_support=minimal --preproc_with_compile --preproc_dependency="blinky.pp" $(GEN_OPTS_QUOTED) $(subst #,$(wildcard $(subst $(SPACE),\$(SPACE),$<)),"#")
	@echo 'Finished building: $<'
	@echo ' '

blinkylab4.obj: ../blinkylab4.asm $(GEN_SRCS) $(GEN_OPTS)
	@echo 'Building file: $<'
	@echo 'Invoking: MSP430 Compiler'
	"C:/Program Files (x86)/Texas Instruments/ccsv4/tools/compiler/msp430/bin/cl430" --silicon_version=msp -g --include_path="C:/Program Files (x86)/Texas Instruments/ccsv4/msp430/include" --include_path="C:/Program Files (x86)/Texas Instruments/ccsv4/tools/compiler/msp430/include" --diag_warning=225 --sat_reassoc=off --fp_reassoc=off --plain_char=unsigned --printf_support=minimal --preproc_with_compile --preproc_dependency="blinkylab4.pp" $(GEN_OPTS_QUOTED) $(subst #,$(wildcard $(subst $(SPACE),\$(SPACE),$<)),"#")
	@echo 'Finished building: $<'
	@echo ' '


