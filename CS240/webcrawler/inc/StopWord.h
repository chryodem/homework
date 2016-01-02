/*
 * StopWord.h
 *
 *  Created on: May 16, 2012
 *      Author: andrei
 *      stores stopword in an array
 *      performs binary search on the array
 *      returns true if passed in word is a stop word
 *      returns false if the word passed is not a stop word
 */

#ifndef STOPWORD_H_
#define STOPWORD_H_

#include <string>
#include <fstream>
#include <iostream>
#include <string.h>

using namespace std;

class StopWord {
private:

	string stopWordsFile;
	string * stopWords;
	int size;

	class Parser {
	public:
		friend class StopWord;
		char * fileN;
		long lineNumbers;
		ifstream file;
		Parser(string & fileName) {
			fileN = new char[fileName.size() + 1];
			strcpy(fileN, fileName.c_str());

		}

		~Parser(){
			delete[] fileN;
		}
		string * CountLines() {

			lineNumbers = 0;
			file.open(fileN);
			if (!file.is_open()) {
				cout << "Unable to open file " << fileN << endl;
			}
			char curLine[1024];
			while (file.getline(curLine, sizeof(curLine))) {

				++lineNumbers;
			}
			file.close();
			file.open(fileN);

			string Line[lineNumbers];
			int i = 0;
			while (file.getline(curLine, sizeof(curLine))) {
				Line[i] = curLine;
				i++;
			}

			string * linePtr = new string[lineNumbers];
			for (int j = 0; j < lineNumbers; j++) {
				linePtr[j] = Line[j];
			}
			return linePtr;

		}

		int GetSize() {
			return lineNumbers;
		}

	};
public:
	StopWord(); //empty constructor

	~StopWord();

	void StopWordsArray(const string & v); //counts the words in the stop words document

	bool CheckWord(const string & w); //checks if a word is a stop word

	int GetSize();

};

#endif /* STOPWORD_H_ */
