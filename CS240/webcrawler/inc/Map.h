#ifndef BSTSET_H
#define BSTSET_H

#include <string>
#include <iostream>
#include "LinkedList.h"
#include <sstream>

//create forward declaration so that compilers knows it exists
class SetNode {
	friend class SetL; //!< BST can access private members of BSTNode

public:

	//!  Constructor
	SetNode(const std::string & v) :
			value(v), left(NULL), right(NULL), occ(1) //used to be NULL for left and right
	{

	}

	//! Copy Constructor
	SetNode(const SetNode & other) :
			value(other.value),
			left(other.left == NULL ? NULL : new SetNode(*other.left)), //used to be NULL ? NULL
			right(other.right == NULL ? NULL : new SetNode(*other.right)) {
	}

	~SetNode() {
		delete left;
		delete right;
	}

	//!  Read-only public methods for use by clients of the BST class
	const std::string & GetValue() const {
		return value;
	}

	int GetOcc() const {
		return occ;
	}

	void IncOcc() {
		occ++;
	}

	SetNode * GetLeft() const {
		return left;
	}

	SetNode * GetRight() const {
		return right;
	}

	//! Assignment operator
	SetNode & operator=(const SetNode & other) {
		if (this != &other) {
			*this = other;
		}

		return *this;
	}

private:
	std::string value; //!< value stored in the node
	SetNode * left; //!< pointer to the node's left child
	SetNode * right; //!< pointer to the node's right child
	int occ;
};

class SetL {
	friend class MapNode;
private:
	SetNode * root;
	int size;

public:
	SetL();


	//!  Copy constructor.  Makes a complete copy of its argument
	SetL(const SetL & other);

	//!  Destructor
	~SetL() ;
	//!  Assignment operator.  Makes a complete copy of its argument
	//!  @return Reference to oneself
	SetL& operator =(const SetL & other);

	//!  @return a pointer to the root node of the tree, or NULL if the tree is empty.
	//!  @note This is useful for BST clients that need to traverse the tree.)
	SetNode * GetRoot() const ;

	//!  @return true if the BST is empty, or false if the BST is not empty
	bool IsEmpty() const;

	//!  Removes all values from the BST
	void Clear() ;

	void SetSize();

	//!  @return the number of values in the BST
	int GetSize() const ;

	//!  Inserts value v into the BST
	//!
	//!  @param v The new value being inserted
	//!
	//!  @return a pointer to the newly inserted node, or NULL if v was already
	//!          in the tree (i.e., NULL is used to indicate a duplicate insertion)
	SetNode * Insert(const std::string & v);



	//!  Searches the tree for value v
	//!
	//!  @param v The new value being searched for
	//!
	//!  @return a pointer to the node containing v, or NULL if v is not in the tree
	SetNode * Find(const std::string & v) const ;

	//! @NOTE: YOU ARE NOT REQUIRED TO IMPLEMENT THE Remove METHOD BELOW
	//!        (BUT YOU CAN IF YOU WANT TO)
	//!
	//!  Removes value v from the tree
	//!
	//!  @param v The value being removed from the tree
	//!
	//!  @return true if v was removed from the tree, or false if v was not in the tree
	//bool Remove(const std::string & v);
	string Traverse(SetNode * root);

};

//!  BSTNode implements a binary search tree node
class MapNode {
	friend class Map; //!< BST can access private members of BSTNode
private:
	std::string value; //!< value stored in the node
	MapNode * left; //!< pointer to the node's left child
	MapNode * right; //!< pointer to the node's right child
	SetL * set;

public:

	//!  Constructor
	MapNode(const std::string & v) :
			value(v), left(NULL), right(NULL) //used to be NULL for left and right
	{set = new SetL;}

	//! Copy Constructor
	MapNode(const MapNode & other) :
			value(other.value), left(
					other.left == NULL ? NULL : new MapNode(*other.left)), //used to be NULL ? NULL
			right(other.right == NULL ? NULL : new MapNode(*other.right))

	{
	}

	~MapNode() {
		delete left;
		delete right;
		delete set;
	}

	//!  Read-only public methods for use by clients of the BST class
	const std::string & GetValue() const {
		return value;
	}

	MapNode * GetLeft() const {
		return left;
	}

	MapNode * GetRight() const {
		return right;
	}

	SetL * GetSet() const {
		return set;
	}

	void SetSet(){
		set->SetSize();
	}

	MapNode * InsertLink(const std::string & v){

		set->Insert(v);
	}

	//! Assignment operator
	MapNode & operator=(const MapNode & other) {
		if (this != &other) {
			*this = other;
		}

		return *this;
	}


};

//!  BST implements a binary search tree
class Map {
private:
	MapNode * root;
	int size;

public:

	//!  No-arg constructor.  Initializes an empty BST
	Map();
	//!  Copy constructor.  Makes a complete copy of its argument
	Map(const Map & other);

	//!  Destructor
	~Map();

	//!  Assignment operator.  Makes a complete copy of its argument
	//!  @return Reference to oneself
	Map& operator =(const Map & other);
	//!  @return a pointer to the root node of the tree, or NULL if the tree is empty.
	//!  @note This is useful for BST clients that need to traverse the tree.)
	MapNode * GetRoot() const;

	//!  @return true if the BST is empty, or false if the BST is not empty
	bool IsEmpty() const;

	//!  Removes all values from the BST
	void Clear() ;

	//!  @return the number of values in the BST
	int GetSize() const ;

	//!  Inserts value v into the BST
	//!
	//!  @param v The new value being inserted
	//!
	//!  @return a pointer to the newly inserted node, or NULL if v was already
	//!          in the tree (i.e., NULL is used to indicate a duplicate insertion)
	MapNode * Insert(const std::string & v, const std::string & link) ;
	//!
	//!  @param v The new value being searched for
	//!
	//!  @return a pointer to the node containing v, or NULL if v is not in the tree
	MapNode * Find(const std::string & v) const ;

	//! @NOTE: YOU ARE NOT REQUIRED TO IMPLEMENT THE Remove METHOD BELOW
	//!        (BUT YOU CAN IF YOU WANT TO)
	//!
	//!  Removes value v from the tree
	//!
	//!  @param v The value being removed from the tree
	//!
	//!  @return true if v was removed from the tree, or false if v was not in the tree
	//bool Remove(const std::string & v);

	string Traverse(MapNode * root);

private:
};

#endif
