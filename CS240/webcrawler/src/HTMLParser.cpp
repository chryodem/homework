#include "HTMLParser.h"
#include <iostream>
#include <exception>
#include <string.h>

using namespace std;

HTMLParser::HTMLParser() {
}

void HTMLParser::AddWords(string wor) {

//	cout << wor << " <-value of words" << endl;
	StringUtil::ToLower(wor);
	int w_len = wor.size();
	char temporal[w_len + 1];
	strcpy(temporal, wor.c_str());
	char * temp1 = temporal;
	char * pch;
	char * word;
	pch = strtok(temp1, "\r\n\t[]\"!@#$%^&*()~`\\{}|/+:;<>?= ,.'&");
	while (pch != NULL) {
		word = pch;
//		string t = word;
//		if(t=="file"){
//			cout<<"here"<<endl;
//		}
//		output+=word+"\n";
//		cout<<pch<<endl;
		if ((word[0] >= 97 && word[0] <= 122)) {
			string temp = word;
			//		cout<<temp<<endl;
			words.Insert(temp, 0);
			//	cout << temp << endl;

		}
		pch = strtok(NULL, "\r\n\t[]\"!@#$%^&*()~`\\{}|/+:;<>?= ,.'&");
	}
}

void HTMLParser::CheckForBody(string& BodyCheck, HTMLToken& temp,
		bool& isBody) {
	if (BodyCheck == "body" && temp.GetType() == TAG_START) {
		isBody = true;
	} else if (BodyCheck == "body" && temp.GetType() == TAG_END) {
		isBody = false;
	}
}

void HTMLParser::CheckForTitle(string & BodyCheck, HTMLToken& temp,
		bool & isTitle) {
	if (BodyCheck == "title" && temp.GetType() == TAG_START) {
		isTitle = true;
	} else if (BodyCheck == "title" && temp.GetType() == TAG_END) {
		isTitle = false;
	}
}

void HTMLParser::AddLinks(HTMLToken& temp) {
	string value = temp.GetValue();
	StringUtil::ToLower(value);
	if (value == "a") {
		string link = temp.GetAttribute("href");
		if (link.size() != 0) {
			//		cout<<link<<" inserting this link"<<endl;
			links.Insert(link, 0);
		}
	}

}

void HTMLParser::CheckForScript(string & BodyCheck, HTMLToken & temp,
		bool & isScript) {
	if (BodyCheck == "script" && temp.GetType() == TAG_START) {
		isScript = true;
	} else if (BodyCheck == "script" && temp.GetType() == TAG_END) {
		isScript = false;
	}
}

void HTMLParser::ParseWords(string link) {
	try {

//		if(link=="http://lightplanet.com/mormons/people/joseph_smith/visions.html"){
//			cout<<"this is the link!"<<endl;
//		}

		HTMLTokenizer tokenizer(link); // = new HTMLTokenizer(link);
		while (tokenizer.HasNextToken()) {

			HTMLToken temp = tokenizer.GetNextToken();
			string tempsV = temp.GetValue();
			StringUtil::ToLower(tempsV);

			if (temp.GetType() == TAG_START && tempsV.compare("html") == 0) {

				temp = tokenizer.GetNextToken();

				string OtherTempsV = temp.GetValue();
				StringUtil::ToLower(OtherTempsV);

				bool isBody = false;
				bool isTitle = false;
				bool isScript = false;
				bool added = false;
				while (true) {

					string BodyCheck = temp.GetValue();
					StringUtil::ToLower(BodyCheck);

					CheckForBody(BodyCheck, temp, isBody);
//					cout<<isBody<<endl;
					CheckForTitle(BodyCheck, temp, isTitle);

					CheckForScript(BodyCheck, temp, isScript);
					AddLinks(temp);

					if (isScript == false) {

						if (isBody && temp.GetType() == TEXT) {

							string words = temp.GetValue();

//							cout<<words<<" isBody"<<endl;

							AddWords(words);
							added = true;

						}
						if (isTitle && temp.GetType() == TEXT
								&& added == false) {
							string words = temp.GetValue();
//							cout<<words<<" isTitle"<<endl;
							AddWords(words);
						}

					}

					temp = tokenizer.GetNextToken();
					OtherTempsV = temp.GetValue();
					StringUtil::ToLower(OtherTempsV);
					string t_value = temp.GetValue();
					StringUtil::ToLower(t_value);
					if (temp.GetType() == TAG_END
							&& t_value.compare("html") == 0) {

//						cout<<"here!!!!!!"<<endl;
//						cout<<link<<endl;
//						cout<<temp.GetValue()<<endl;
						break;
					}

				}

			}

		}

	} catch (exception & e) {
		cout << "Exception Occurred:" << e.what() << endl;
	} catch (CS240Exception & e) {
		cout << "CS240 Exception Occurred:" << e.GetMessage() << endl;
	} catch (...) {
		cout << "Unknown Exception has occurred" << endl;
	}

}

bool HTMLParser::CheckHeader(string BodyCheck) {
	StringUtil::ToLower(BodyCheck);
	//StringUtil::Trim(BodyCheck);
	char first = BodyCheck.c_str()[0];
	if (first == 'h') {
		char second = BodyCheck.c_str()[1];

		if (isdigit(second)) {
			return true;
		} else {
			return false;
		}
	} else {
		return false;
	}

}

void HTMLParser::CheckForHeader(string & BodyCheck, HTMLToken & temp,
		bool & isHeader) {
	bool check = false;
	check = CheckHeader(BodyCheck);

	if (check && temp.GetType() == TAG_START) {
		isHeader = true;
	} else if (check && temp.GetType() == TAG_END) {
		isHeader = false;
	}
}

void HTMLParser::ParseDesc(string link) {
	desc = "";
	bool isTitle = false;
	bool isBody = false;
	bool isHeader = false;
	int counter = 0;
	int totalChars = 0;
	string appendix = "";
	try {
		HTMLTokenizer tok(link);
		while (tok.HasNextToken()) {
			HTMLToken temp = tok.GetNextToken();
			string tempsV = temp.GetValue();
			StringUtil::ToLower(tempsV);

			if (temp.GetType() == TAG_START && tempsV.compare("html") == 0) {
				temp = tok.GetNextToken();
				string OtherTempsV = temp.GetValue();
				StringUtil::ToLower(OtherTempsV);
				while (OtherTempsV.compare("html") != 0) {

					string BodyCheck = temp.GetValue();
					StringUtil::ToLower(BodyCheck);
					//cout<<BodyCheck<<endl;
					CheckForBody(BodyCheck, temp, isBody);
					CheckForTitle(BodyCheck, temp, isTitle);
					CheckForHeader(BodyCheck, temp, isHeader);

//					cout<<"is header "<<isHeader<<endl;
//					cout<<"is body "<<isBody<<endl;
//					cout<<"is title " <<isTitle<<endl;
//					cout<<" " <<BodyCheck<<endl;

					if (isTitle && temp.GetType() == TEXT) {
						string words = temp.GetValue();
						//StringUtil::Trim(words);

						desc += words;
						break;

					}
					if (isBody && temp.GetType() == TEXT) {
						string words = temp.GetValue();
						//	cout<<words<< "########################## words"<<endl;
						appendix.append(words);

					}

					if (isHeader && temp.GetType() == TEXT) {

						string words = temp.GetValue();
						StringUtil::Trim(words);
						desc += words;
						break;

					}

					temp = tok.GetNextToken();
					OtherTempsV = temp.GetValue();
					StringUtil::ToLower(OtherTempsV);

				}

			}
			if (isTitle || isHeader) {
				break;
			}
		}
		if (!isTitle && !isHeader) {
//			cout<<appendix<<"                !!!!!!!!!!!!!!!!!!!!"<<endl;

			for (int i = 0; i < appendix.size(); i++) {
				if (counter == 100) {
					break;
				}
				if (isspace(appendix[i])) {
				}else{
					counter++;

				}
				totalChars++;
			}
			appendix.resize(totalChars);

			//cout<<appendix<<endl;

			desc.append(appendix);
		}

	} catch (exception & e) {
		cout << "Exception Occurred:" << e.what() << endl;
	} catch (CS240Exception & e) {
		cout << "CS240 Exception Occurred:" << e.GetMessage() << endl;
	} catch (...) {
		cout << "Unknown Exception has occurred" << endl;
	}

}

HTMLParser::~HTMLParser() {
	//delete words;
	//delete links;

	return;
}

LinkedList<string>& HTMLParser::GetWords() {
	return words;
}

LinkedList<string>& HTMLParser::GetLinks() {
	return links;
}

string HTMLParser::GetDesc() {
	return desc;
}

void HTMLParser::Clear() {
	words.Clear();
	links.Clear();
	desc = "";

}
//g++ -g -c -o HTMLParser.o -I ../inc -I ../utils/inc HTMLParser.cpp
//g++ -o test main.o PageDownloader.o HTMLParser.o ../lib/libcs240utils.a

