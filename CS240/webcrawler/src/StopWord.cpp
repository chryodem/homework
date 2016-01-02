#include "StopWord.h"


StopWord::StopWord():size(0),stopWordsFile(),stopWords(){

}

void StopWord::StopWordsArray(const string & v) { //counts the words in the stop words document
		//and stores an array of those word in this class
		stopWordsFile = v;
		Parser pars(stopWordsFile);
		stopWords = pars.CountLines();
		size = pars.GetSize();

	}
StopWord::~StopWord(){
	delete[] stopWords;
	return;
}

bool StopWord::CheckWord(const string & w) { //checks if a word is a stop word
		bool result = false;
		int first = 0;
		int last = size - 1;

		while (first <= last) {
			int mid = (first + last) / 2;
			if (w.compare(stopWords[mid]) > 0) {
				first = mid + 1;
			} else if (w.compare(stopWords[mid]) < 0) {
				last = mid - 1;
			} else if (w.compare(stopWords[mid]) == 0) {
				result = true;
				break;
			}

		}
		return result;

	}

//string * StopWord::GetArray() {
//		return stopWords;
//	}

int StopWord::GetSize() {
		return size;
	}


//g++ -g -o StopWord.o -I ../inc -I ../utils/inc StopWord.cpp
