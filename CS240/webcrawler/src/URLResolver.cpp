#include "URLResolver.h"

URLResolver::URLResolver() :
		base(NULL), rel(NULL) {

}

URLResolver::~URLResolver() {
	//delete[] base;
	//delete[] rel;
	//Free();
	return;
}

bool URLResolver::PoundCheck() {
	if (rel[0] == '#') {
		return true;
	} else {
		return false;
	}
}

void URLResolver::Free() {
	delete[] base;
	delete[] rel;
}

void URLResolver::CountToThreeSlashes() {
	int slashes = 0;
	int i = 0;
	int len = strlen(base);
	while (i < len) {
		if (base[i] == '/') {
			slashes++;
		}
		if (slashes == 3) {
			break;
		}
		i++;
	}

	if (slashes != 3) {
		return;
	} else {
		char copy[i];
		strncpy(copy, base, i);
		copy[i] = 0;

		strcpy(base, copy);
	}

}

int URLResolver::CountBaseSlash() {
	int slash = 0;
	int temp = strlen(base);
	for (int j = 0; j < temp; j++) {
		if (base[j] == '/') {
			slash++;
		}
	}
	return slash;
}

void URLResolver::MoveToSlash(int n) {
	int slashes = 0;
	int base_length = strlen(base);
	int new_base_length = 0;
	for (int i = base_length - 1; i >= 0; i--) {
		if (base[i] == '/') {
			new_base_length = i;
			slashes++;
		}
		if (slashes == n) {
			break;
		}
	}

	char temp[new_base_length + 1];
	strncpy(temp, base, new_base_length + 1);
	temp[new_base_length + 1] = 0;
	strcpy(base, temp);
}

string URLResolver::Resolve(string base1, string relative) {

	//#########################################################
	bool isHTTP = false;
	bool isFile = false;

	string httpCheck = "http://";
	string fileCheck = "file:";

	for (int i = 0; i < httpCheck.size(); i++) {
		if (relative[i] != httpCheck[i]) {
			isHTTP = false;
			break;
		} else {
			isHTTP = true;
		}
	}

	for (int i = 0; i < fileCheck.size(); i++) {
		if (relative[i] != fileCheck[i]) {
			isFile = false;
			break;
		} else {
			isFile = true;
		}
	}

	//###############################################

	int rel_slashes = 0;
	for (int i = 0; i < relative.size(); i++) {
		char c = relative[i];
		if (c == '/') {
			rel_slashes++;
		}
	}

	if (rel_slashes > 1 &&(isHTTP || isFile)) {

		size_t http;
		http = relative.find_first_of("http://");
		if (http) {
//	cout<<http<<endl;

//			if(relative=="file:///users/ta/cs240ta/webcrawler/file/student/linktests/absolute1.html"){
//				cout<<"here!!!"<<endl;
//			}
			size_t found;
			found = base1.find_first_of("file:///");

			int dot = found;
//		cout << found << endl;
			found = relative.find_first_of("file:///");

			int dash = found;
			//cout<<"DASH!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" <<dash<<endl;

			if (dot == dash) {

				//	cout<<"INVALID URL!!!!!!!!!!!"<< relative<<endl;
				//cout << "here" << endl;

//###########################################changed here
				return relative;
			}

		}

	}

	//cout<<base1<<endl;
	base = new char[base1.size() + 1];
	//base_len = strlen(base);
	strcpy(base, base1.c_str());
	rel = new char[relative.size() + 1];
	//rel_len = strlen(rel);
	strcpy(rel, relative.c_str());
	char * pch = NULL;

	pch = strstr(rel, "http://");
	if (pch != NULL) {
		Free();
		return relative;
	}

	char * abs = NULL;

	abs = strstr(rel, "file:///");
	if (abs != NULL) {
		Free();
		return relative;
	}

	//cout<<pch<<endl;

	string result;
	//string * temp = new string;

	bool pound = PoundCheck();
	if (pound) {
		result.append(base);
		result.append(rel);
		//temp->append(result);
		Free();
		return result;
	}

	if (rel[0] == '/') {

		CountToThreeSlashes();
		result.append(base);
		result.append(rel);
		//temp->append(result);
		Free();
		return result;

	}
	int slash = CountBaseSlash();
	if (rel[0] < 64 && slash >= 3) {

		int i = 0;
		while (relative[i] < 64) {
			if (relative[i] == '.' && relative[i + 1] == '/') {
				i += 2;
				MoveToSlash(1);

			} else if (relative[i] == '.' && relative[i + 1] == '.'
					&& relative[i + 2] == '/') {
				i += 3;
				MoveToSlash(2);
			}
		}
		int rel_len = strlen(rel);
		char temp1[rel_len];

		int j = 0;
		while (i < strlen(rel)) {
			temp1[j] = rel[i];
			i++;
			j++;
		}
		temp1[j] = 0;

		result.append(base);
		result.append(temp1);
		//	temp->append(result);
		Free();
		return result;

	} else {
		if (slash < 3) {
			result.append(base);
			result.append("/");
			result.append(rel);
			//	temp->append(result);
			Free();
			return result;

		} else {

			MoveToSlash(1);
			result.append(base);
			result.append(rel);
			Free();
			//	temp->append(result);
			return result;
		}
	}

}

string URLResolver::GetDomain(string base1) {
	base = new char[base1.size() + 1];
	//base_len = strlen(base);
	strcpy(base, base1.c_str());
	MoveToSlash(1);
	string result;
	result.append(base);
	delete[] base;
	delete[] rel;
	return result;
}

// g++ -g -c -o URLResolver.o -I ../inc -I ../utils/inc URLResolver.cpp
