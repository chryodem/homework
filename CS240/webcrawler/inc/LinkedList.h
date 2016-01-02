#ifndef CS240_LINKED_LIST_H
#define CS240_LINKED_LIST_H

#include <iostream>
using namespace std;
template <typename E> class LinkedList;

//! LLNode implements a doubly-linked list node
template <typename E>
class LLNode 
{
		friend class LinkedList<E>;  //!< LinkedList can access private members of LLNode
	public:
	
		//!  Constructor
		LLNode(const E & v, LLNode<E> * p, LLNode<E> * n) :
		  value(v), prev(p), next(n)
		{
		}
		
		//! Copy Constructor 
		LLNode(const LLNode<E> & other) :
		   value(other.value),prev(other.prev),next(other.next)
		{
		}
	
		//!  Read-only public methods for use by clients of the LinkedList class
		const E & GetValue() const
		{
		  return value;
		}
	
	
		LLNode<E> * GetPrevious()const
		{
		  return prev;
		}
	
	
		LLNode<E> * GetNext()const
		{
		  return next;
		}
		
		//! Assignment operator 
		LLNode<E> & operator=(const LLNode<E> & other)
		{
			if(this!=&other)
			{
				value=other.value;
				prev=other.prev;
				next=other.next;
			}
			return *this;
		}
	
	private:
		E value;        //!< value stored in the node
		LLNode<E> * prev;            //!< pointer to previous node in the list
		LLNode<E> * next;            //!< pointer to next node in the list
};


//! LinkedList implements a doubly-linked list
template <typename E>
class LinkedList
{
private:
	LLNode<E> * head;
	LLNode<E> * tail;
	int size;
	void Free() {
		while (IsEmpty()==false) {
			Remove(head);
		}
	}

	public:
	
		//!  No-arg constructor.  Initializes an empty linked list
		LinkedList():head(0), tail(0), size(0) {
		}
	
	
		//!  Copy constructor.  Makes a complete copy of its argument
		LinkedList(const LinkedList<E> & other){
			if (this != &other) {
				size = 0;
				head = 0;
				tail = 0;

				LLNode<E> * temp = other.tail;
					while (temp != 0) {
						Insert(temp->GetValue(), 0);
						temp = temp->prev;
					}


			}
		}
	
	
		//!  Destructor
		~LinkedList(){
			Free();
		}
	
	
		//! Assignment operator.  Makes a complete copy of its argument
		//! @return A reference to oneself
		LinkedList<E>& operator =(const LinkedList<E> & other){
			if (this != &other) {
				Free();

				LLNode<E> * temp = other.tail;
				while (temp != 0) {
					Insert(temp->GetValue(), 0);
					temp = temp->prev;
				}

			}
			return *this;
		}
	
	
		//!  @return true if the list is empty, or false if the list is not empty
		bool IsEmpty() const{
			if (size == 0) {
				return true;
			} else {
				return false;
			}
		}
	
	
		//!  Removes all values from the list
		void Clear(){
			Free();
		}
	
	
		//!  @return the number of values in the list
		int GetSize() const{
			return size;
		}
	
	
	
		//!  @return a pointer to the first node in the list, or NULL if the list is empty
		LLNode<E> * GetFirst()const{
			return head;
		}
	
	
	
		//!  @returns a pointer to the last node in the list, or NULL if the list is empty
		LLNode<E> * GetLast()const{
			return tail;
		}
	
	
		//!  Inserts value v into the list after node n
		//!  
		//!  @param v The new value being inserted
		//!  @param n A node that is already in the list after which the new node should 
		//!      be inserted.
		//!      If n is NULL, the new node should be inserted at the beginning of the list.
		//!
		//!  @return a pointer to the newly inserted node
		LLNode<E> * Insert(const E & v, LLNode<E> * n){
			LLNode<E> * temp;

			if (n == 0) {
				if (size != 0) {
					//no previous node when item is first
					//next node is where head was pointing
					temp = new LLNode<E>(v, 0, head);
					head->prev = temp; //updating the pointer from the next node to one inserted
					head = temp; //now head points to temp
				} else {
					//no pointers to head and tail when creating a node
					temp = new LLNode<E>(v, 0, 0);
					tail = temp;
					head = temp;
				}
				++size;
				return temp;

			} else {
				if (n == tail) {
					temp = new LLNode<E>(v, n, 0);
					n->next = temp;
					tail = temp;
				} else {
					temp = new LLNode<E>(v, n, n->next);
					n->next->prev = temp;
					n->next = temp;
				}
				++size;
				return temp;
			}
		}
	
	
		//! Searches for the first occurrence of value v that appears in the list 
		//!   after node n
		//!   
		//!  @param v The value being searched for
		//!  @param n The node in the list after which the search should begin.
		//!      If n is NULL, the list should be searched from the beginning.
		//!
		//!  @return a pointer to the node containing v, or NULL if v is not found
		LLNode<E> * Find(const E & v, LLNode<E>* n) const {
			LLNode<E> * temp = 0;
			//start with at the beginning of the linked list
			if (size == 1 && head != 0) {
				if (v==(head->GetValue())) {
					return head;
				} else {
					return 0;
				}
			}

			if (n == 0) {
				temp = head;

				if (temp->next == 0) {
					return 0;
				}
				while (v!=(temp->GetValue()) ) {
					if (temp->next == 0) {
						return 0;
					}
					temp = temp->next;

				}
				return temp;
				//start someplace else
			} else {
				if (n->next == 0) {
					return 0;
				}
				temp = n->next;
				while (v!=(temp->GetValue())) {
					if (temp->next == 0) {
						return 0;
					}
					temp = temp->next;
				}

				return temp;
			}
		}
	
	
		//!  Removes node n from the list
		//!  
		//!  @param n The node being removed from the list
		void Remove(LLNode<E> * n){
			if (n == 0) {
				return;
			}
			if (n == head) {
				if (n == tail) {
					head = 0;
					tail = 0;

				}
				else{
					head = head->next;
					head->prev = 0;
				}
			} else if (n == tail) {

				tail = tail->prev;
				tail->next = 0;
			}

			else {

				if (n->prev != 0) {
					n->prev->next = n->next;
				}
				if (n->next != 0) {
					n->next->prev = n->prev;
				}

			}
			delete n;
			--size;

		}
	
	private:
};


#endif

