/*
 * URLResolver.h
 *
 *  Created on: May 16, 2012
 *      Author: andrei
 *      is used for resolving URL's
 */

#ifndef URLRESOLVER_H_
#define URLRESOLVER_H_

#include <iostream>
#include <string.h>
#include <cstring>
#include <stdio.h>
#include <string>

using namespace std;

class URLResolver{
private:
	char * base;
	char * rel;

public:
	URLResolver();//empty constructor
	~URLResolver();//destructor
	void Free();

private:


	bool PoundCheck();

	void CountToThreeSlashes();

	int CountBaseSlash();

	void MoveToSlash(int n);
public:
	string Resolve(string base, string relative); //resovles the URL and returns it

	string GetDomain(string base);
};



#endif /* URLRESOLVER_H_ */
