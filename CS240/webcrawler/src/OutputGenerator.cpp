#include "OutputGenerator.h"

//g++ -g -o OutputGenerator.o -I ../inc -I ../utils/inc OutputGenerator.cpp

OutputGenerator::OutputGenerator() {
}

void OutputGenerator::GenerateOutput(string name, string index, string pages,
		string startURL) {
	ofstream file(name.c_str());

	file<<"<website>\n";
	file<<startURL;
	file<<pages;
	file<<"<index>\n";
	file<<index;
	file<<"</index>\n";
	file<<"</website>";

	file.close();


}

