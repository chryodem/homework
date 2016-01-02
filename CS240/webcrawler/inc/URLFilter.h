/*
 * URLFilter.h
 *
 *  Created on: May 16, 2012
 *      Author: andrei
 */

#ifndef URLFILTER_H_
#define URLFILTER_H_
#include <string>
#include <iostream>
#include <BST.h>

using namespace std;

class URLFilter{

private:
	string domain;
	BST<string> tree;

public:

	URLFilter();//empty constructor
	~URLFilter();//destructor

	void setDomain(string link);//set the domain

	string getDomain();//get the domain

	bool inDomain(string & link); //check to see if the link is in the domain

	bool isValid(string link);//checks to see if the link is valid HTML link

};




#endif /* URLFILTER_H_ */
