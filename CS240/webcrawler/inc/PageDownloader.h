/*
 * PageDownloader.h
 *
 *  Created on: May 16, 2012
 *      Author: andrei
 *      this class is used to download pages and returns
 *      them as a string for further processing
 */

#ifndef PAGEDOWNLOADER_H_
#define PAGEDOWNLOADER_H_

#include "URLInputStream.h"
#include "CS240Exception.h"
#include <string>
#include <iostream>

using namespace std;

class PageDownloader{
private:

public:
	PageDownloader();//empty constructor

	~PageDownloader();

	string Download(string link);//downloads and returns the page
};




#endif /* PAGEDOWNLOADER_H_ */
