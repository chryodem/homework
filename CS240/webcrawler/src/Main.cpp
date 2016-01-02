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

int main(int argc, char * argv[]) {
	string stopWords = "stopword.txt";

	URLFilter filter;
	URLResolver resolver;
	PageDownloader pageD;
	HTMLParser parser;

	Page page;
	Map map;
	StopWord stop;
	stop.StopWordsArray(stopWords);

	PageQueue queue;
	PageHistory history;

	OutputGenerator gen;

	string BegLink = //"http://students.cs.byu.edu/~cs240ta/crawler_tests/crawlindex.html";
			//"file:///home/andrei/Downloads/file/student/wordtests/punctuation.html";
			"http://lightplanet.com/mormons/people/joseph_smith/index.html";
	string NewLink = resolver.GetDomain(BegLink);
	filter.setDomain(NewLink);
	history.isVisited(BegLink);
	queue.Add(BegLink);

	while (queue.isEmpty() == false) {
		string link_for_process = queue.GetFirst();
//		cout<<"link for process -> " <<link_for_process<<endl;
		string result = pageD.Download(link_for_process);
		if(result.size()>0){
		parser.ParseWords(result);
		parser.ParseDesc(result);

		}

		string desc = parser.GetDesc();//description of the link
		page.Insert(link_for_process,desc);//insert into the page data structure

		LinkedList<string> words = parser.GetWords();
		while (words.IsEmpty() == false) {
			LLNode<string> * temp = words.GetLast();
			bool Check = stop.CheckWord(temp->GetValue());
			if (!Check) {
			//	cout<<temp->GetValue()<<endl;
				map.Insert(temp->GetValue(), link_for_process);

			}
			words.Remove(temp);
		}
		//words->Clear();
		LinkedList<string> link = parser.GetLinks();

		while (link.IsEmpty() == false) {
			string temp = link.GetFirst()->GetValue(); //link from the site
//			cout<<temp<<endl;
			string result = resolver.Resolve(link_for_process, temp);//result link
			bool inDomain = filter.inDomain(result);
			bool isVisited = history.isVisited(result);
//		cout<<inDomain<<endl;
			if (isVisited == false && inDomain == true) {
//				cout<<"resolved URL "<<result<<endl;

				queue.Add(result);
			}
			link.Remove(link.GetFirst());
		}
		parser.Clear();

		//go to the next link
		//link_for_process = queue.GetFirst(); //link from a local directory
	}//end of while


	string startURL = "<start-url>\n" + BegLink + "\n</start-url>\n";

//	MapNode * temp = map.Find("punctuation");
//	SetL * tempS;
//	tempS = temp->GetSet();
//
//	SetNode * sn;
//	sn = tempS->Find("file:///home/andrei/Downloads/file/student/wordtests/punctuation.html");

//	cout<<sn->GetOcc()<<endl;

//	cout<<queue.GetFirst()<<endl;
//	queue.RemoveFirst();

//	cout<<queue.GetSize()<<endl;

//	cout<<map.GetRoot()->GetValue()<<endl;
//	int v = 2;
//	string res = " ";
//	stringstream ss;
//	ss<<v;
//	res+=ss.str();
//	cout<<res<<endl;
		string wordsIndex =  map.Traverse(map.GetRoot());


//		cout<<wordsIndex<<endl;
		string pageIndex = page.Traverse(page.GetRoot());
		gen.GenerateOutput("output.xml",wordsIndex,pageIndex,startURL);
//	cout<<pageIndex<<endl;
	cout<<"SUCCESS!"<<endl;
	cout<<"SUCCESS!"<<endl;
	cout<<"SUCCESS!"<<endl;

	return 0;
}
