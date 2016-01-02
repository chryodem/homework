#include "PageHistory.h"


PageHistory::PageHistory():link(){//empty constructor

	}

bool PageHistory::isVisited(string v){ //returns true if the link has been visited

	if(link.Find(v)==0){
		link.Insert(v);
		return false;
	}else{
		return true;
	}
}

//g++ -g -o PageHistory.o -I ../inc -I ../utils/inc PageHistory.cpp

