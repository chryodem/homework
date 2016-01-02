/*
 * PageQueue.h
 *
 *  Created on: May 16, 2012
 *      Author: andrei
 *      is used to track the pages that still need to be processed
 */

#ifndef PAGEQUEUE_H_
#define PAGEQUEUE_H_

#include "LinkedList.h"
#include <string>
using namespace std;

class PageQueue{
private:
LinkedList<string> list;

public:
	PageQueue();

	bool isEmpty();//returns true if the queue is empty


	void Add(string link);//add the link to the list of unprocessed links

private:
	void RemoveFirst();//removes the first link from the queue

public:
	string GetFirst(); //returns string that was entered first

	int GetSize();

};


#endif /* PAGEQUEUE_H_ */
