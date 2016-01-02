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

WebCrawler::WebCrawler(string startingURL, string outputFile,
		string stopWordsFileName) {
	BegLink = startingURL;
			//"file:///home/andrei/Downloads/file/student/wordtests/extra.html";
			//"http://students.cs.byu.edu/~cs240ta/crawler_tests/crawlindex.html";
			//"file:///home/andrei/Downloads/file/student/linktests/non-html.html";
			//"http://students.cs.byu.edu/~cs240ta/crawler_tests/crawler/header.html";
			//"http://students.cs.byu.edu/~cs240ta/crawler_tests/crawlindex.html";
			//"http://lightplanet.com/mormons/people/joseph_smith/visions.html";
			//"http://lightplanet.com/mormons/people/joseph_smith/index.html";
			//"file:///home/andrei/Downloads/file/student/index.html";
	outE = //"output1.xml";
	outputFile;
	stopW = //"stopword.txt";
			stopWordsFileName;
}

void WebCrawler::Crawl() {
	string NewLink = resolver.GetDomain(BegLink);
	filter.setDomain(NewLink);
	queue.Add(BegLink);
	history.isVisited(BegLink);
	stop.StopWordsArray(stopW);

	while (queue.isEmpty() == false) {
		string link_for_process = queue.GetFirst();
		string result = pageD.Download(link_for_process);

		if (result.size() > 0) {

			parser.ParseWords(result);
			parser.ParseDesc(result);

		}

		string desc = parser.GetDesc(); //description of the link
		StringUtil::EncodeToXml(desc);
		page.Insert(link_for_process, desc); //insert into the page data structure
//		if(link_for_process==" http://lightplanet.com/mormons/people/joseph_smith/worship.html"){
//			cout<<"here"<<endl;
//			cout<<desc<<endl;
//
//
//		}

		LinkedList<string> words = parser.GetWords();
		while (words.IsEmpty() == false) {
			LLNode<string> * temp = words.GetLast();
			bool Check = stop.CheckWord(temp->GetValue());
			if (!Check) {
				//	cout<<temp->GetValue()<<endl;
				string check = temp->GetValue();

				map.Insert(temp->GetValue(), link_for_process);

			}
			words.Remove(temp);
		}
		LinkedList<string> link = parser.GetLinks();
//		cout<<endl;
//		cout<<link_for_process << " ################## link where found these links"<<endl;
//		cout<<desc<<"################ page description"<<endl;

		while (link.IsEmpty() == false) {
			string temp = link.GetFirst()->GetValue(); //link from the site
			string result = resolver.Resolve(link_for_process, temp); //result link
			bool inDomain = filter.inDomain(result);
			bool isVisited = history.isVisited(result);
//			if(temp=="file:///users/ta/cs240ta/webcrawler/file/student/linktests/absolute1.html"){
//				cout<<"here!!!"<<endl;
//			cout<<endl;
//			cout<<link_for_process<<" <-link to be resolved"<<endl;
//			cout<<temp<< " <-link for process"<<endl;
//			cout <<result << " <-resolved URL" <<endl;
//			cout<<endl;
//			cout<<"inDomain " <<inDomain<<" isVisited " <<isVisited<<endl;
//			cout<<endl;
//			}

			if (isVisited == false && inDomain == true) {

				queue.Add(result);
			}
			link.Remove(link.GetFirst());
		}
		parser.Clear();

		//go to the next link
		//link_for_process = queue.GetFirst(); //link from a local directory
	} //end of while
	//cout<<"is visited " <<history.isVisited("http://lightplanet.com/mormons/people/joseph_smith/visions.html")<<endl;
	string startURL = "<start-url>\n" + BegLink + "\n</start-url>\n";

	string wordsIndex = map.Traverse(map.GetRoot());

	string pageIndex = page.Traverse(page.GetRoot());
	gen.GenerateOutput(outE, wordsIndex, pageIndex, startURL);

}

WebCrawler::~WebCrawler() {
	return;
}

int main(int argc, char * argv[]) {
	if (argc != 4) {
		cout << "USAGE webcrawler <start-url> <output-file> <stopword-file>";
		return 0;
	}
	string BegLink = argv[1];
	string output = argv[2];
	string stop = argv[3];

	WebCrawler crawler(BegLink, output, stop);
	crawler.Crawl();

	return 0;
}

//g++ -g -o WebCrawler.o -I ../inc -I ../utils/inc WebCrawler.cpp
