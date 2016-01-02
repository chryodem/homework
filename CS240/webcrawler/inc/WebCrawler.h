/*
 * WebCrawler.h
 *
 *  Created on: May 16, 2012
 *      Author: andrei
 *      This class implements a web crawler that is used to
 *      go through web pages specified from a start link and
 *      to stain in the same domain of search. This class is responsible
 *      for downloading web pages, indexing words on it, counting the
 *      number of occurrences of a specific word on a page and to generate
 *      an XML output document.
 *
 *
 */
#ifndef WEBCRAWLER_H_
#define WEBCRAWLER_H_

#include "WebCrawler.h"
#include "StopWord.h"
#include <string>
#include "PageQueue.h"
#include <iostream>
#include "PageHistory.h"
#include "PageDownloader.h"
#include "HTMLParser.h"
#include "Page.h"
#include "URLResolver.h"
#include "LinkedList.h"
#include "BST.h"
#include "Map.h"
#include "URLFilter.h"
#include <sstream>
#include "OutputGenerator.h"

using namespace std;

class WebCrawler {
private:
	URLFilter filter;
	URLResolver resolver;
	PageDownloader pageD;
	HTMLParser parser;
	Page page;
	Map map;
	StopWord stop;
	PageQueue queue;
	PageHistory history;
	OutputGenerator gen;

	string BegLink;
	string outE;
	string stopW;


public:
	//constructor
	WebCrawler(string startingURL,string outputFile, string stopWordsFileName);

	//responsible for starting the crawling process and generating the output
	void Crawl();
	//destructor to clean up the mess
	~WebCrawler();

};

#endif /* WEBCRAWLER_H_ */
