/*
 * HTMLParser.h
 *
 *  Created on: May 16, 2012
 *      Author: andrei
 *      is used to parse HTML documents
 */

#ifndef HTMLPARSER_H_
#define HTMLPARSER_H_

#include "LinkedList.h"
#include <string>
#include "HTMLTokenizer.h"
#include "CS240Exception.h"
#include "StringUtil.h"

using namespace std;

class HTMLParser{
private:
	LinkedList<string> links;
	LinkedList<string> words;
	string desc;
	string global_desc;
	void CheckForBody(string& BodyCheck, HTMLToken& temp, bool& isBody);
	void CheckForTitle(string& BodyCheck, HTMLToken& temp, bool& isTitle);
	void AddLinks(HTMLToken& temp);
	bool CheckHeader(string BodyCheck);
	void CheckForHeader(string& BodyCheck, HTMLToken& temp, bool& isHeader);
	void CheckForScript(string& BodyCheck, HTMLToken& temp, bool& isScript);

public:
	//string output;
	HTMLParser(); //empty constructor
	~HTMLParser();
	void AddWords(string wor);
	void ParseWords(string link); //parses specific downloaded web page
	void ParseDesc(string link); //parses for description of the page
	LinkedList<string>& GetWords(); //returns the words from specific page
	LinkedList<string>& GetLinks(); //returns the links
	string GetDesc(); //returns descriptions
	void Clear();

};




#endif /* HTMLPARSER_H_ */
