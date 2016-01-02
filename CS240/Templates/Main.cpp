#include "LinkedList.h"
#include "BST.h"
#include <string>
#include <iostream>

using namespace std;


int main(){

	LinkedList<string> list;


	list.Insert("value", 0);
	list.Insert("another value", 0);
	list.Insert("new value", 0);
	list.Insert("new thing", 0);

	LinkedList<string> new_list(list);

	string value = "new thing";

	LLNode<string> * temp = list.Find(value,0);
	LLNode<string> * other_temp = list.GetFirst();
	cout<<temp->GetValue()<<endl;
	cout<<other_temp->GetValue()<<endl;


	BST<string> bst;

	bst.Insert("hello");
	bst.Insert("you");

	BSTNode<string> * temp_1 = bst.Find("you");
	cout<<temp_1->GetValue()<<endl;



//	LLNode * temp = list.Find("value", NULL);
//	cout<<"found-> " << temp->GetValue() <<endl;
//	LinkedList other_list(list);
//	LLNode * other_temp = other_list.Find("another Value", NULL);
//	cout<<"found-> " <<other_temp->GetValue() <<endl;
//
//	list.Clear();
//	cout<<"list's size " <<list.GetSize() <<endl;
//
//	list.Insert("this", NULL);
//	cout<<"list's size " <<list.GetSize() <<endl;
//
//	cout<<"other_list's size " <<other_list.GetSize() <<endl;
//
//	other_list= list;
//
//	cout<<"other_list's size " <<other_list.GetSize() <<endl;
//
//
//
//	LinkedList newOne;
//
//	newOne = list;
//
//
//
//	cout<<"First node of the New one "<< newOne.GetFirst()->GetValue()<<endl;
//
//	newOne.Insert("just inserted this!", NULL);
//
//	cout<<"First node of the New one "<< newOne.GetFirst()->GetValue()<<endl;
//
//	newOne = list;
//
//	cout<<"First node of the New one "<< newOne.GetFirst()->GetValue()<<endl;


	return 0;
}
