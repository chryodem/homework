#include "PageDownloader.h"
#include "URLInputStream.h"
#include "CS240Exception.h"
#include <string>

using namespace std;

PageDownloader::PageDownloader() { //empty constructor

}
PageDownloader::~PageDownloader(){
	//delete result;
	return;
}

string PageDownloader::Download(string link) { //downloads and returns the page
	try {
		URLInputStream stream(link);
		string html;

		while (!stream.IsDone())
		    {
		        html+=stream.Read();
		    }



		//string * result1 = new string;
		//result1->append(html);

		//cout<<*result1<<endl;

		stream.Close();

		return html;

	} catch (std::exception &e) {
		std::cout << "Exception Occurred:" << e.what() << std::endl;
	} catch (CS240Exception &e) {
		std::cout << "CS 240 Exception Occurred:" << e.GetMessage() << std::endl;
		return "";
	} catch (...) {
		std::cout << "Unknown Exception Occurred" << std::endl;
	}
}


//make lib first
// g++ -c -o PageDownloader.o -I ../inc -I ../utils/inc PageDownloader.cpp
// g++ -c -o main.o -I ../inc -I ../utils/inc main.cpp
// g++ -o test main.o PageDownloader.o ../lib/libcs240utils.a


