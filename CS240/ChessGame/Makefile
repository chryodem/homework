################################################################################
# CS 240 Chess Project Makefile
#
# INSTRUCTIONS: This is a starting makefile for the chess project. You are to 
# keep this up-to-date as you add source files. In addition to keeping it 
# up-to-date with new files you create, you need to make the following changes 
# to this makefile:
# 
#	- compile the object files associated with the View into a shared library, 
#	- add pseudo-targets for the different run modes (namely "runhh", "runhc",
#		"runch", and "runcc"),
#	- add a "test" pseudo-target (you will probably want to make a new executable
#		for this with its own main method),
#	- add a "lib" pseudo-target,
#	- edit the command associated with the memcheck pseudo-target (i.e.,
#		the valgrind command. See the note above it for details).
# 
# NOTES: 
# 1. All the code for the GUI side of this project is provided for you. As a 
# result, there are some things in this makefile which you do not need to alter.
# Such items will be clearly marked with comments.
# 2. Out-of-the-box, without making any changes to this initial makefile, 
# running "make run" will compile the code and bring up an empty GUI. 
################################################################################

 
############### --- BEGIN UNTOUCHABLE SECTION - Do Not Edit --- ################
# Macros for GUI libraries.
GTKMM = gtkmm-2.4
SIGC = sigc++-2.0
LIBGLADE = libglademm-2.4

# TODO Re-phrase: CFLAGS are the -I values needed to compile files using the 
# GTKMM, SIGC, and LIBGLADE libraries
CFLAGS = `pkg-config $(GTKMM) $(SIGC) $(LIBGLADE) --cflags`
# TODO Re-phrase: LIBS are the paths to the static libraries for GTKMM, SIGC,
# and LIBGLADE needed for linking
LIBS   = `pkg-config $(GTKMM) $(SIGC) $(LIBGLADE) --libs`
####################### --- END UNTOUCHABLE SECTION --- ########################


# TODO Re-phrase: Macros for directory tree locations
MODEL_SRC = model/src/
MODEL_INC = model/inc/
MODEL_OBJ = model/obj/
VIEW_SRC = view/src/
VIEW_INC = view/inc/
VIEW_OBJ = view/obj/
CONT_SRC = controller/src/
CONT_INC = controller/inc/
CONT_OBJ = controller/obj/
UTSRC = utils/src/
UTINC = utils/inc/
UTOBJ = utils/obj/

EXE = bin/chess			# normal executable
MEXE = bin/mchess			# executable for memory testing

# Macros for compilation flags
INCLUDES = -I$(MODEL_INC) -I$(VIEW_INC) -I$(CONT_INC) -I$(UTINC)
DEBUG = #-fPIC # Uncomment this if you want to compile with debug info

# Uncomment the flag on this next line to turn off the logging messages from the
# GUI
LOG_FLAG = #'-DLOG_LEVEL_HIDE_MASK=(G_LOG_LEVEL_DEBUG|G_LOG_LEVEL_INFO)'


# There are two different main.o files. One is for memory testing.
MAIN_O = $(CONT_OBJ)main.o
MEMMAIN_O = $(CONT_OBJ)memcheck_main.o

# This macro should be used to store all of the object files created 
# from *your* source files
MY_OBJS = $(CONT_OBJ)Controller.o \
	$(MODEL_OBJ)Board.o \
	$(MODEL_OBJ)Rook.o \
	$(MODEL_OBJ)Queen.o \
	$(MODEL_OBJ)Model.o \
	$(MODEL_OBJ)Pawn.o \
	$(MODEL_OBJ)King.o \
	$(MODEL_OBJ)Bishop.o \
	$(MODEL_OBJ)Knight.o \
	$(MODEL_OBJ)Move.o \
	$(MODEL_OBJ)Piece.o \
	$(CONT_OBJ)XMLParser.o \
#	$(CONT_OBJ)ComputerPlayer.o \
#	$(CONT_OBJ)HumanPlayer.o \
	
UTILS_OBJ_FILES = $(UTOBJ)CommandRunner.o $(UTOBJ)FileInputStream.o $(UTOBJ)FileSystem.o \
$(UTOBJ)HTMLToken.o $(UTOBJ)HTMLTokenizer.o $(UTOBJ)HTTPInputStream.o $(UTOBJ)StringUtil.o \
$(UTOBJ)URLInputStream.o


# These are all the object files that go into the library
LIB_OBJS = $(VIEW_OBJ)ChessView.o \
      $(VIEW_OBJ)ChessGuiBoardCell.o \
      $(VIEW_OBJ)ChessGuiBoard.o \
      $(VIEW_OBJ)ChessGui.o \
      $(VIEW_OBJ)ChessGuiImages.o \
      $(VIEW_OBJ)SelectDialog.o





################################################################################
# Pseudo-targets

run: $(EXE)
	$(EXE)

bin: $(EXE)

all: $(EXE)

runhh: $(EXE)
	$(EXE) hh

runch: $(EXE)
	$(EXE) ch

runhc: $(EXE)
	$(EXE) hc

runcc: $(EXE)
	$(EXE) cc
	
test: bin/test
	bin/test
	
bin/test: $(CONT_SRC)test.cpp
	g++ -o bin/test $(CONT_SRC)test.cpp

libu: lib/libcs240utils.a
	
lib/libcs240utils.a : $(UTILS_OBJ_FILES)
	ar -r lib/libcs240utils.a $(UTILS_OBJ_FILES)
	
lib: $(LIB_OBJS)
	g++ -shared -o lib/libchessgui.so $(VIEW_OBJ)*.o
	

# Run the executable for memory testing
# Instructions: Edit the command below so that it runs the  
# memory-check executable in Human-Human mode (i.e., add necessary command  
# line arguments to the end of valgrind the command below).	
memcheck: $(MEXE)
	valgrind --tool=memcheck --leak-check=yes --max-stackframe=5000000 --show-reachable=yes --suppressions=string.supp $(MEXE) hh #command-line args go here

	
# Clean removes all of the .o files, libraries, and executables
clean:
	@rm -f $(MODEL_OBJ)*.o
	@rm -f $(VIEW_OBJ)*.o
	@rm -f $(CONT_OBJ)*.o
	@rm -f $(EXE)
	@rm -f $(MEXE)
	@rm -f $(UTOBJ)*.o
	@rm -f lib/*.so
	@rm -f lib/*.a
	@rm -f bin/*.*


################################################################################
# Targets for executables and main object files

# Explanation: There are two different executables, with two different main 
# object files, because main.cpp needs to be built differently depending on 
# whether you are doing memory testing or not. This is because the GTK library,
# and other GUI tools, have memory leaks that we cannot figure out how to 
# suppress. Therefore, when building an executable for memory testing, we use a
# macro (MEMCHECK) to tell the compiler to not include the code that builds the
# GUI. So when we check for memory leaks during passoff, we don't have to use
# the GUI.

# Main executable
# Note: Once you have added the ability to make a library out of the View files, 
# the executable should be dependent on, and link in the library file, not the 
# .o files! That means, instead of linking in $(LIB_OBJS) below, you should link
# in the actual library file.
$(EXE): $(MAIN_O) $(MY_OBJS) lib/libchessgui.so  lib/libcs240utils.a
	@# Re-link executable. Again, this should link the library file, not the .o's
	g++ -g $(INCLUDES) -o $(EXE) $(MAIN_O) $(MY_OBJS) lib/libchessgui.so $(CFLAGS) $(LIBS) lib/libcs240utils.a
	chmod ugo+x $(EXE)

# Executable for memory testing
$(MEXE): $(MEMMAIN_O) $(MY_OBJS) $(LIB_OBJS) lib/libcs240utils.a
	@# Re-link executable. Again, this should link the library file, not the .o's
	g++ -g $(INCLUDES) -o $(MEXE) $(MEMMAIN_O) $(MY_OBJS) $(LIB_OBJS) $(CFLAGS) $(LIBS) lib/libcs240utils.a
	chmod ugo+x $(MEXE)
	

# Main object file
$(MAIN_O): $(CONT_SRC)main.cpp $(VIEW_INC)ChessGuiImages.h $(VIEW_INC)ChessView.h
	g++ -g $(DEBUG) $(INCLUDES) $(CFLAGS) $(LIBS) -o $(MAIN_O) -c $(LOG_FLAG) $(CONT_SRC)main.cpp

# Main .o for memory testing
$(MEMMAIN_O): $(CONT_SRC)main.cpp $(VIEW_INC)ChessGuiImages.h $(VIEW_INC)ChessView.h
	g++ -g -DMEMCHECK $(DEBUG) $(INCLUDES) $(CFLAGS) $(LIBS) -o $(MEMMAIN_O) -c $(LOG_FLAG) $(CONT_SRC)main.cpp



################################################################################
# Targets for YOUR object files...

lib/libchessgui.so: $(LIB_OBJS)
	g++ -shared -o lib/libchessgui.so $(LIBS) $(VIEW_OBJ)*.o

$(CONT_OBJ)Controller.o: controller/src/Controller.cpp controller/inc/Controller.h \
 controller/inc/IChessController.h view/inc/IChessView.h \
 view/inc/ChessGui.h view/inc/ChessGuiDefines.h view/inc/ChessGuiBoard.h \
 view/inc/ChessGuiBoardCell.h model/inc/Model.h
	g++ -g -c -o $(CONT_OBJ)Controller.o -I $(CONT_INC) -I $(MODEL_INC) -I $(VIEW_INC) -I$(UTINC) $(CFLAGS) $(CONT_SRC)Controller.cpp

$(MODEL_OBJ)Board.o: model/src/Board.cpp model/inc/Board.h model/inc/Piece.h \
 model/inc/Board.h model/inc/BP.h model/inc/Rook.h
	g++ -g -c -o $(MODEL_OBJ)Board.o -I $(MODEL_INC) -I $(VIEW_INC) $(CFLAGS) $(MODEL_SRC)Board.cpp 
 	

$(MODEL_OBJ)Model.o: model/src/Model.cpp model/inc/Model.h model/inc/Board.h \
 model/inc/Piece.h model/inc/BP.h model/inc/Rook.h \
 controller/inc/Controller.h controller/inc/IChessController.h \
 view/inc/IChessView.h view/inc/ChessGui.h view/inc/ChessGuiDefines.h \
 view/inc/ChessGuiBoard.h view/inc/ChessGuiBoardCell.h
	g++ -g -c -o $(MODEL_OBJ)Model.o -I $(MODEL_INC) -I$(CONT_INC) -I$(VIEW_INC) -I$(UTINC) $(CFLAGS) $(MODEL_SRC)Model.cpp 

$(MODEL_OBJ)Queen.o: model/src/Queen.cpp model/inc/Queen.h model/inc/Piece.h \
 model/inc/Board.h model/inc/Rook.h model/inc/BP.h
	g++ -g -c -o $(MODEL_OBJ)Queen.o -I $(MODEL_INC) -I $(VIEW_INC) $(CFLAGS) $(MODEL_SRC)Queen.cpp 

$(MODEL_OBJ)Rook.o: model/src/Rook.cpp model/inc/Rook.h model/inc/Piece.h \
 model/inc/Board.h model/inc/Rook.h model/inc/BP.h
	g++ -g -c -o $(MODEL_OBJ)Rook.o -I $(MODEL_INC) -I $(VIEW_INC) $(CFLAGS) $(MODEL_SRC)Rook.cpp 
	
$(MODEL_OBJ)Bishop.o: model/src/Bishop.cpp model/inc/Bishop.h model/inc/Piece.h \
 model/inc/BP.h
	g++ -g -c -o $(MODEL_OBJ)Bishop.o -I $(MODEL_INC) -I $(VIEW_INC) $(CFLAGS) $(MODEL_SRC)Bishop.cpp

$(MODEL_OBJ)King.o: model/src/King.cpp model/inc/King.h model/inc/Piece.h \
 model/inc/BP.h
	g++ -g -c -o $(MODEL_OBJ)King.o -I $(MODEL_INC) -I $(VIEW_INC) $(CFLAGS) $(MODEL_SRC)King.cpp
	
$(MODEL_OBJ)Knight.o: model/src/Knight.cpp model/inc/Knight.h model/inc/Piece.h \
 model/inc/BP.h
	g++ -g -c -o $(MODEL_OBJ)Knight.o -I $(MODEL_INC) -I $(VIEW_INC) $(CFLAGS) $(MODEL_SRC)Knight.cpp

$(MODEL_OBJ)Pawn.o: model/src/Pawn.cpp model/inc/Pawn.h model/inc/Piece.h \
 model/inc/BP.h
	g++ -g -c -o $(MODEL_OBJ)Pawn.o -I $(MODEL_INC) -I $(VIEW_INC) $(CFLAGS) $(MODEL_SRC)Pawn.cpp
	
$(MODEL_OBJ)Move.o: model/src/Move.cpp model/inc/Move.h
	g++ -g -c -o $(MODEL_OBJ)Move.o -I $(MODEL_INC) -I $(VIEW_INC) -I$(UTINC) $(CFLAGS) $(MODEL_SRC)Move.cpp
	
	
$(MODEL_OBJ)Piece.o: model/src/Piece.cpp model/inc/Piece.h
	g++ -g -c -o $(MODEL_OBJ)Piece.o -I $(MODEL_INC) -I $(VIEW_INC) -I $(CONT_INC) $(CFLAGS) $(MODEL_SRC)Piece.cpp
	
#$(CONT_OBJ)XMLParser.o: $(CONT_SRC) XMLParser.cpp $(CONT_INC)XMLParser.h
#	g++ -g -c -o $(CONT_OBJ)XMLParser.o -I $(MODEL_INC) -I $(VIEW_INC) -I $(CONT_INC) -I $(UTINC) $(CFLAGS) $(CONT_SRC)XMLParser.cpp

$(CONT_OBJ)XMLParser.o: controller/src/XMLParser.cpp controller/inc/XMLParser.h \
 utils/inc/HTMLTokenizer.h utils/inc/URLInputStream.h \
 utils/inc/InputStream.h utils/inc/HTMLToken.h utils/inc/URLInputStream.h \
 view/inc/CS240Exception.h model/inc/Model.h model/inc/Board.h \
 model/inc/Piece.h model/inc/BP.h view/inc/ChessGuiDefines.h \
 model/inc/Rook.h model/inc/Queen.h model/inc/King.h model/inc/Bishop.h \
 model/inc/Knight.h model/inc/Pawn.h controller/inc/Controller.h \
 controller/inc/IChessController.h view/inc/IChessView.h \
 view/inc/ChessGui.h view/inc/ChessGuiDefines.h view/inc/ChessGuiBoard.h \
 view/inc/ChessGuiBoardCell.h model/inc/Piece.h model/inc/Board.h \
 model/inc/BP.h model/inc/Move.h controller/inc/XMLParser.h \
 model/inc/Move.h	
	g++ -g -c -o $(CONT_OBJ)XMLParser.o -I $(MODEL_INC) -I $(VIEW_INC) -I $(CONT_INC) -I $(UTINC) $(CFLAGS) $(CONT_SRC)XMLParser.cpp
	
	
#$(CONT_OBJ)HumanPlayer.o: controller/src/HumanPlayer.cpp controller/inc/HumanPlayer.h
#	g++ -g -c -o $(CONT_OBJ)HumanPlayer.o -I $(MODEL_INC) -I $(VIEW_INC) -I $(CONT_INC) $(CFLAGS) $(CONT_SRC)HumanPlayer.cpp
#	
#$(CONT_OBJ)ComputerPlayer.o: controller/src/ComputerPlayer.cpp controller/inc/ComputerPlayer.h
#	g++ -g -c -o $(CONT_OBJ)ComputerPlayer.o -I $(MODEL_INC) -I $(VIEW_INC) -I $(CONT_INC) $(CFLAGS) $(CONT_SRC)ComputerPlayer.cpp

################################################################################
# Targets for GUI-related object files
# Instructions: These will have to be recompiled as position-independent code, 
# so that they can be put into a shared library.

$(VIEW_OBJ)ChessView.o: $(VIEW_SRC)ChessView.cpp $(VIEW_INC)SelectDialog.h $(VIEW_INC)ChessView.h $(CONT_INC)IChessController.h
	g++ $(DEBUG) $(INCLUDES) $(CFLAGS) $(LIBS) -o $(VIEW_OBJ)ChessView.o -c -fPIC $(LOG_FLAG) $(VIEW_SRC)ChessView.cpp

$(VIEW_OBJ)ChessGuiBoardCell.o: $(VIEW_SRC)ChessGuiBoardCell.cpp $(VIEW_INC)ChessGuiBoardCell.h $(VIEW_INC)ChessGuiImages.h
	g++ $(DEBUG) $(INCLUDES) $(CFLAGS) $(LIBS) -o $(VIEW_OBJ)ChessGuiBoardCell.o -c -fPIC $(VIEW_SRC)ChessGuiBoardCell.cpp

$(VIEW_OBJ)ChessGuiBoard.o: $(VIEW_SRC)ChessGuiBoard.cpp $(VIEW_INC)ChessGuiBoard.h $(VIEW_INC)ChessGui.h $(VIEW_INC)ChessGuiImages.h
	g++ $(DEBUG) $(INCLUDES) $(CFLAGS) $(LIBS) -o $(VIEW_OBJ)ChessGuiBoard.o -c -fPIC $(VIEW_SRC)ChessGuiBoard.cpp

$(VIEW_OBJ)ChessGui.o: $(VIEW_SRC)ChessGui.cpp $(VIEW_INC)ChessGui.h $(VIEW_INC)ChessGuiImages.h $(VIEW_INC)SelectDialog.h
	g++ $(DEBUG) $(INCLUDES) $(CFLAGS) $(LIBS) -o $(VIEW_OBJ)ChessGui.o -c -fPIC $(VIEW_SRC)ChessGui.cpp

$(VIEW_OBJ)ChessGuiImages.o: $(VIEW_SRC)ChessGuiImages.cpp $(VIEW_INC)ChessGuiImages.h $(VIEW_INC)Inline.h
	g++ $(DEBUG) $(INCLUDES) $(CFLAGS) $(LIBS) -o $(VIEW_OBJ)ChessGuiImages.o -c -fPIC $(VIEW_SRC)ChessGuiImages.cpp

$(VIEW_OBJ)SelectDialog.o: $(VIEW_SRC)SelectDialog.cpp
	g++ $(DEBUG) $(INCLUDES) $(CFLAGS) $(LIBS) -o $(VIEW_OBJ)SelectDialog.o -c -fPIC $(VIEW_SRC)SelectDialog.cpp


#############################################################################################
#UTILITIES OBJECTS
#############################################################################################
$(UTOBJ)CommandRunner.o : $(UTSRC)CommandRunner.cpp $(UTINC)CS240Exception.h \
$(UTINC)StringUtil.h $(UTINC)CommandRunner.h
	g++ -g -c -o $(UTOBJ)CommandRunner.o -I $(UTINC) $(UTSRC)CommandRunner.cpp

	
$(UTOBJ)FileInputStream.o : $(UTSRC)FileInputStream.cpp $(UTINC)FileInputStream.h \
$(UTINC)InputStream.h $(UTINC)CS240Exception.h
	g++ -g -c -o $(UTOBJ)FileInputStream.o -I $(UTINC) $(UTSRC)FileInputStream.cpp

	
$(UTOBJ)FileSystem.o : $(UTSRC)FileSystem.cpp $(UTINC)FileSystem.h $(UTINC)CS240Exception.h \
$(UTINC)UnitTest.h
	g++ -g -c -o $(UTOBJ)FileSystem.o -I $(UTINC) $(UTSRC)FileSystem.cpp

	
$(UTOBJ)HTMLToken.o : $(UTSRC)HTMLToken.cpp $(UTINC)HTMLToken.h
	g++ -g -c -o $(UTOBJ)HTMLToken.o -I $(UTINC) $(UTSRC)HTMLToken.cpp
	

$(UTOBJ)HTMLTokenizer.o : $(UTSRC)HTMLTokenizer.cpp $(UTINC)HTMLTokenizer.h \
 $(UTINC)URLInputStream.h $(UTINC)InputStream.h $(UTINC)HTMLToken.h

	g++ -g -c -o $(UTOBJ)HTMLTokenizer.o -I $(UTINC) $(UTSRC)HTMLTokenizer.cpp
	
$(UTOBJ)HTTPInputStream.o : $(UTSRC)HTTPInputStream.cpp $(UTINC)CS240Exception.h \
 $(UTINC)StringUtil.h $(UTINC)HTTPInputStream.h $(UTINC)InputStream.h
	g++ -g -c -o $(UTOBJ)HTTPInputStream.o -I $(UTINC) $(UTSRC)HTTPInputStream.cpp
 
$(UTOBJ)StringUtil.o : $(UTSRC)StringUtil.cpp $(UTINC)StringUtil.h
	g++ -g -c -o $(UTOBJ)StringUtil.o -I $(UTINC) $(UTSRC)StringUtil.cpp
	
$(UTOBJ)URLInputStream.o: $(UTSRC)URLInputStream.cpp $(UTINC)StringUtil.h \
 $(UTINC)/CS240Exception.h $(UTINC)HTTPInputStream.h $(UTINC)InputStream.h \
 $(UTINC)FileInputStream.h $(UTINC)URLInputStream.h $(UTINC)InputStream.h
	g++ -g -c -o $(UTOBJ)URLInputStream.o -I $(UTINC) $(UTSRC)URLInputStream.cpp
