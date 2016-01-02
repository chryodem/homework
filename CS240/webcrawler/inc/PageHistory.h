/*
 * PageHistory.h
 *
 *  Created on: May 16, 2012
 *      Author: andrei
 *      is used to keep track of already visited page
 */

#ifndef PAGEHISTORY_H_
#define PAGEHISTORY_H_
#include "BST.h"
#include <string>

using namespace std;

class PageHistory{

private:
	BST<string> link;

public:

	PageHistory();//empty constructor


	bool isVisited(string v); //returns true if the link has been visited


};



#endif /* PAGEHISTORY_H_ */
