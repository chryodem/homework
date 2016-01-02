#include "PageQueue.h"


PageQueue::PageQueue():list(){

	}


bool PageQueue::isEmpty(){//returns true if the queue is empty
		return list.IsEmpty();
	}

void PageQueue::Add(string link){//add the link to the list of unprocessed links
		list.Insert(link,0);
	}
void PageQueue::RemoveFirst(){//removes the first link from the queue
		list.Remove(list.GetLast());
	}

string PageQueue::GetFirst(){ //returns string that was entered first
		string temp = list.GetLast()->GetValue();
//		cout<<temp<<" <----" << endl;
		RemoveFirst();
		return temp;

	}
int PageQueue::GetSize(){
	return list.GetSize();
}

//g++ -g -o PageQueue.o -I ../inc -I ../utils/inc PageQueue.cpp
