/*
 * XMLParser.h
 *
 *  Created on: Jun 8, 2012
 *      Author: rybin
 */

#ifndef XMLPARSER_H_
#define XMLPARSER_H_

#include <string>
#include "HTMLTokenizer.h"
#include "URLInputStream.h"
#include "CS240Exception.h"
#include <iostream>
#include <fstream>
#include "Move.h"
#include <list>
class Model;

using namespace std;

class XMLParser {
public:
	XMLParser(string fileName, Model * m);
	virtual ~XMLParser();
    void StartParsing();
    void SaveFile();

private:
	string file;
	Model * model;
};

#endif /* XMLPARSER_H_ */
