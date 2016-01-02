UTSRC = utils/src/
UTINC = utils/inc/
UTOBJ = utils/obj/
BIN_TEST_FILE = bin/test
BIN_TRY_FILE = bin/test
BIN_MAIN_FILE = bin/webcrawler

OBJ = obj/
SRC = src/
INC = inc/

UTILS_OBJ_FILES = $(UTOBJ)CommandRunner.o $(UTOBJ)FileInputStream.o $(UTOBJ)FileSystem.o \
$(UTOBJ)HTMLToken.o $(UTOBJ)HTMLTokenizer.o $(UTOBJ)HTTPInputStream.o $(UTOBJ)StringUtil.o \
$(UTOBJ)URLInputStream.o

OBJ_FILES = $(OBJ)HTMLParser.o $(OBJ)OutputGenerator.o $(OBJ)Page.o \
$(OBJ)PageDownloader.o $(OBJ)PageHistory.o $(OBJ)PageQueue.o $(OBJ)StopWord.o \
$(OBJ)URLFilter.o $(OBJ)URLResolver.o $(OBJ)Map.o

TEST_OBJ_FILE = obj/test.o

TRY_OBJ_FILE = obj/Main.o

BIN_OBJ_FILE = obj/WebCrawler.o

#test : $(BIN_TEST_FILE)
#	$(BIN_TEST_FILE)	
#		
#$(BIN_TEST_FILE) : src/Test.cpp lib/libcs240utils.a obj/test.o
#	g++ -g -o $(BIN_TEST_FILE) src/Test.cpp lib/libcs240utils.a
	
test: $(BIN_TRY_FILE)
	$(BIN_TRY_FILE)
	
$(BIN_TRY_FILE) : src/Main.cpp lib/libcs240utils.a $(TRY_OBJ_FILE) $(OBJ_FILES)
	g++ -g -o $(BIN_TRY_FILE) -I $(INC) -I $(UTINC) $(TRY_OBJ_FILE) $(OBJ_FILES) lib/libcs240utils.a
	
bin : $(BIN_MAIN_FILE)
	
$(BIN_MAIN_FILE) : $(OBJ_FILES) lib/libcs240utils.a $(BIN_OBJ_FILE) $(OBJ_FILES)
	g++ -g -o $(BIN_MAIN_FILE) -I $(INC) -I $(UTINC) $(BIN_OBJ_FILE) $(OBJ_FILES) lib/libcs240utils.a
	
clean: 
	- echo "Removing Generated Files!"
	- rm -f $(UTOBJ)*
	- rm -f bin/*
	- rm -f lib/*
	- rm -f obj/*	

lib: lib/libcs240utils.a
	
lib/libcs240utils.a : $(UTILS_OBJ_FILES)
	ar -r lib/libcs240utils.a $(UTILS_OBJ_FILES)
	
	
############################################################################################
#MAIN OBJECTS
############################################################################################

$(OBJ)Map.o: $(SRC)Map.cpp $(INC)Map.h
	g++ -g -c -o $(OBJ)Map.o -I $(INC) -I $(UTINC) $(SRC)Map.cpp

	
$(OBJ)HTMLParser.o: $(SRC)HTMLParser.cpp $(INC)HTMLParser.h $(INC)LinkedList.h \
 $(UTINC)HTMLTokenizer.h $(UTINC)URLInputStream.h \
 $(UTINC)InputStream.h $(UTINC)HTMLToken.h $(UTINC)CS240Exception.h \
 $(UTINC)StringUtil.h
	g++ -g -c -o $(OBJ)HTMLParser.o -I $(INC) -I $(UTINC) $(SRC)HTMLParser.cpp


$(OBJ)Main.o: $(SRC)Main.cpp $(INC)PageDownloader.h $(UTINC)URLInputStream.h \
 $(UTINC)InputStream.h $(UTINC)CS240Exception.h $(INC)HTMLParser.h \
 $(INC)LinkedList.h $(UTINC)HTMLTokenizer.h $(UTINC)URLInputStream.h \
 $(UTINC)HTMLToken.h $(UTINC)StringUtil.h $(INC)Map.h $(INC)Page.h \
 $(INC)URLResolver.h $(INC)URLFilter.h
	g++ -g -c -o $(OBJ)Main.o -I $(INC) -I $(UTINC) $(SRC)Main.cpp
 	
 	
$(OBJ)OutputGenerator.o: $(SRC)OutputGenerator.cpp $(INC)OutputGenerator.h
	g++ -g -c -o $(OBJ)OutputGenerator.o -I $(INC) -I $(UTINC) $(SRC)OutputGenerator.cpp
	

$(OBJ)Page.o: $(SRC)Page.cpp $(INC)Page.h
	g++ -g -c -o $(OBJ)Page.o -I $(INC) -I $(UTINC) $(SRC)Page.cpp
	

$(OBJ)PageDownloader.o: $(SRC)PageDownloader.cpp $(INC)PageDownloader.h \
 $(UTINC)URLInputStream.h $(UTINC)InputStream.h \
 $(UTINC)CS240Exception.h
	g++ -g -c -o $(OBJ)PageDownloader.o -I $(INC) -I $(UTINC) $(SRC)PageDownloader.cpp
	

$(OBJ)PageHistory.o: $(SRC)PageHistory.cpp $(INC)PageHistory.h $(INC)BST.h
	g++ -g -c -o $(OBJ)PageHistory.o -I $(INC) -I $(UTINC) $(SRC)PageHistory.cpp
	

$(OBJ)PageQueue.o: $(SRC)PageQueue.cpp $(INC)PageQueue.h $(INC)LinkedList.h
	g++ -g -c -o $(OBJ)PageQueue.o -I $(INC) -I $(UTINC) $(SRC)PageQueue.cpp


$(OBJ)StopWord.o: $(SRC)StopWord.cpp $(INC)StopWord.h
	g++ -g -c -o $(OBJ)StopWord.o -I $(INC) -I $(UTINC) $(SRC)StopWord.cpp
	

$(OBJ)URLFilter.o: $(SRC)URLFilter.cpp $(INC)URLFilter.h
	g++ -g -c -o $(OBJ)URLFilter.o -I $(INC) -I $(UTINC) $(SRC)URLFilter.cpp
	

$(OBJ)URLResolver.o: $(SRC)URLResolver.cpp $(INC)URLResolver.h $(INC)BST.h
	g++ -g -c -o $(OBJ)URLResolver.o -I $(INC) -I $(UTINC) $(SRC)URLResolver.cpp
	

$(OBJ)WebCrawler.o: $(SRC)WebCrawler.cpp $(INC)WebCrawler.h $(INC)HTMLParser.h \
 $(INC)LinkedList.h $(UTINC)HTMLTokenizer.h $(UTINC)URLInputStream.h \
 $(UTINC)InputStream.h $(UTINC)HTMLToken.h $(UTINC)CS240Exception.h \
 $(UTINC)StringUtil.h $(INC)OutputGenerator.h $(INC)PageDownloader.h \
 $(UTINC)URLInputStream.h $(INC)PageHistory.h $(INC)BST.h $(INC)PageQueue.h \
 $(INC)StopWord.h $(INC)URLFilter.h $(INC)URLResolver.h $(INC)Map.h
	g++ -g -c -o $(OBJ)WebCrawler.o -I $(INC) -I $(UTINC) $(SRC)WebCrawler.cpp
	
	

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

obj/test.o : src/Test.cpp
	g++ -g -c -o obj/test.o src/Test.cpp	
