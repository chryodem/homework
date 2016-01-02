#include "URLFilter.h"

URLFilter::URLFilter() {
	//	BST<string> tree;
	tree.Insert("html");
	tree.Insert("php");
	tree.Insert("htm");
	tree.Insert("cgi");
	tree.Insert("asp");
	tree.Insert("aspx");
	tree.Insert("shtml");
	tree.Insert("jsp");
	tree.Insert("pl");
	tree.Insert("cfm");
}

URLFilter::~URLFilter() {
}

void URLFilter::setDomain(string link) {
	domain = link;
//	cout<<"##################################"<<endl;
//	cout<<link<<endl;
}

string URLFilter::getDomain() {
	return domain;
}

bool URLFilter::inDomain(string & link) {
//		cout<<domain<<endl;
	bool inDomain = true;

	if (isValid(link) == false) {
//		cout << "link is not valid " << link << endl;
		inDomain = false;
	}

//	cout<<endl;
	int slashes =0;
	for(int i =0;i<link.size();i++){

		char c = link[i];
		if(c=='/'){
			slashes++;
		}
		if(slashes ==3){
			break;
		}

		link[i]=tolower(c);
//		cout<<link[i];
	}
//	cout<<endl;

	if (inDomain) {
		for (int i = 0; i < domain.size(); i++) {
			if (domain[i] != link[i]) {
//				cout<<"not in the domain" <<link<<endl;
//				cout<<domain[i]<<endl;
//				cout<<link[i]<<endl;
				inDomain = false;
				break;
			}
		}

	}

	return inDomain;
}

bool URLFilter::isValid(string link) {
	//contains .html,.htm,.php or anything of that nature it's valid

	size_t found;
	found = link.find_last_of(".");

	int dot = found;

	found = link.find_last_of("#");

	int pound = found;

	found = link.find_last_of("?");

	int question = found;

	if(question>dot){
		return true;
	}

//	cout<<"value of dot " <<dot<<endl;
//	cout<<"value of dash " <<dash<<endl;
//	cout << link.substr(dot + 1) << " <-found here!!!!!!!!!!!!!!!!" << endl;
	string check = link.substr(dot + 1);
	BSTNode<string> * search = tree.Find(check);
	if (search != 0) {
		//cout << search->GetValue() << " !!!!!!!!!!!!!!!!!!!!!!!!!!!" << endl;
		if (dot > pound) {
			return true;
		} else {
			return false;
		}

	}

}

// g++ -g -c -o URLFilter.o -I ../inc -I ../utils/inc URLFilter.cpp
