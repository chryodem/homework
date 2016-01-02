/*
 * OutputGenerator.h
 *
 *  Created on: May 16, 2012
 *      Author: andrei
 *      this class is responsible for generating the XML output
 */

#ifndef OUTPUTGENERATOR_H_
#define OUTPUTGENERATOR_H_

#include <string>
#include <stack>
#include <iostream>
#include <fstream>

using namespace std;

class OutputGenerator{
private:

	//Generates the output for individual word
	//links that this word was seen on and how
	//many times on each link
	//string GoThroughWords();


public:
	//empty constructor
	OutputGenerator();

	//to start the generation process and to create an xml file
	//goes through individual words -> then goes to
	//GoThroughWords and generates output
	void GenerateOutput(string name,string index, string pages,string startURL);



};



#endif /* OUTPUTGENERATOR_H_ */
