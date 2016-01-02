################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
C_SRCS += \
../blinky.c 

CMD_SRCS += \
../lnk_msp430f2013.cmd 

OBJS += \
./blinky.obj 

C_DEPS += \
./blinky.pp 

C_SRCS_QUOTED += \
"../blinky.c" 


# Each subdirectory must supply rules for building sources it contributes
blinky.obj: ../blinky.c $(GEN_SRCS) $(GEN_OPTS)
	@echo 'Building file: $<'
	@echo 'Invoking: MSP430 Compiler'
	"C:/Program Files (x86)/Texas Instruments/ccsv4/tools/compiler/msp430/bin/cl430" --silicon_version=msp -g --include_path="C:/Program Files (x86)/Texas Instruments/ccsv4/msp430/include" --include_path="C:/Program Files (x86)/Texas Instruments/ccsv4/tools/compiler/msp430/include" --diag_warning=225 --sat_reassoc=off --fp_reassoc=off --plain_char=unsigned --printf_support=minimal --preproc_with_compile --preproc_dependency="blinky.pp" $(GEN_OPTS_QUOTED) $(subst #,$(wildcard $(subst $(SPACE),\$(SPACE),$<)),"#")
	@echo 'Finished building: $<'
	@echo ' '


