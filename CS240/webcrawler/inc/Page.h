/*
 * Page.h
 *
 *  Created on: May 23, 2012
 *      Author: andrei
 */

#ifndef PAGE_H_
#define PAGE_H_

#include <string>
#include <iostream>

using namespace std;

//create forward declaration so that compilers knows it exists

//!  BSTNode implements a binary search tree node
class BSTPageNode
{
		friend class Page;   //!< BST can access private members of BSTNode

	public:

		//!  Constructor
		BSTPageNode(const std::string & v, const std::string & t) :
		  value(v), left(0), right(0),desc(t) //used to be NULL for left and right
		{
		}

		//! Copy Constructor
		BSTPageNode(const BSTPageNode & other) :
		  value(other.value),
		  left(other.left == 0 ? 0: new BSTPageNode(*other.left)), //used to be NULL ? NULL
		  right(other.right == 0 ? 0: new BSTPageNode(*other.right))
		{
		}

		~BSTPageNode()
		{
			delete left;
			delete right;
		}


		//!  Read-only public methods for use by clients of the BST class
		const std::string & GetValue() const
		{
		  return value;
		}

		const std::string & GetDesc() const
		{
			return desc;
		}



		BSTPageNode * GetLeft()const
		{
		  return left;
		}


		BSTPageNode* GetRight()const
		{
		  return right;
		}

		//! Assignment operator
		BSTPageNode & operator=(const BSTPageNode & other)
		{
			if(this!=&other)
			{
				*this = other;
			}

			return *this;
		}

	private:
		std::string value;  //!< value stored in the node
		std::string desc;
		BSTPageNode * left;     //!< pointer to the node's left child
		BSTPageNode * right;    //!< pointer to the node's right child
};


//!  BST implements a binary search tree
class Page
{
private:
	BSTPageNode * root;
	int size;

	public:

		//!  No-arg constructor.  Initializes an empty BST
		Page();

		//!  Copy constructor.  Makes a complete copy of its argument
		Page(const Page & other);


		//!  Destructor
		~Page();


		//!  Assignment operator.  Makes a complete copy of its argument
		//!  @return Reference to oneself
		Page& operator =(const Page & other);


		//!  @return a pointer to the root node of the tree, or NULL if the tree is empty.
		//!  @note This is useful for BST clients that need to traverse the tree.)
		BSTPageNode * GetRoot()const;


		//!  @return true if the BST is empty, or false if the BST is not empty
		bool IsEmpty() const;


		//!  Removes all values from the BST
		void Clear();


		//!  @return the number of values in the BST
		int GetSize() const;


		//!  Inserts value v into the BST
		//!
		//!  @param v The new value being inserted
		//!
		//!  @return a pointer to the newly inserted node, or NULL if v was already
		//!          in the tree (i.e., NULL is used to indicate a duplicate insertion)
		BSTPageNode * Insert(const std::string & v,const std::string & t);

		//!  Searches the tree for value v
		//!
		//!  @param v The new value being searched for
		//!
		//!  @return a pointer to the node containing v, or NULL if v is not in the tree
		BSTPageNode * Find(const std::string & v) const;


		//! @NOTE: YOU ARE NOT REQUIRED TO IMPLEMENT THE Remove METHOD BELOW
		//!        (BUT YOU CAN IF YOU WANT TO)
		//!
		//!  Removes value v from the tree
		//!
		//!  @param v The value being removed from the tree
		//!
		//!  @return true if v was removed from the tree, or false if v was not in the tree
		//bool Remove(const std::string & v);

		string Traverse(BSTPageNode * root);

	private:
};



#endif /* PAGE_H_ */
