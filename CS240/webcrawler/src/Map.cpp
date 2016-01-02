#include "Map.h"
#include <string>
#include <iostream>

//create forward declaration so that compilers knows it exists

SetL::SetL() :
		root(NULL), size(0) {

	//SetSize();
}

//!  Copy constructor.  Makes a complete copy of its argument
SetL::SetL(const SetL & other) {
	if (other.root != NULL) {
		root = new SetNode(*other.root);
		size = other.GetSize();
	} else {
		root = NULL;
		size = 0;
	}
}

//!  Destructor
SetL::~SetL() {
	delete root;

}

//!  Assignment operator.  Makes a complete copy of its argument
//!  @return Reference to oneself
SetL& SetL::operator =(const SetL & other) {
	if (this != &other) {
		delete root;
		root = 0;
		size = 0;
		if (other.root != NULL) {
			root = new SetNode(*other.root);
			size = other.size;

		}

	}
	return *this;
}

//!  @return a pointer to the root node of the tree, or NULL if the tree is empty.
//!  @note This is useful for BST clients that need to traverse the tree.)
SetNode * SetL::GetRoot() const {
	return root;
}

//!  @return true if the BST is empty, or false if the BST is not empty
bool SetL::IsEmpty() const {
	if (size == 0) {
		return true;
	} else {
		return false;
	}
}

//!  Removes all values from the BST
void SetL::Clear() {
	delete root;
	root = NULL;
	size = 0;
}

void SetL::SetSize() {
	size = 0;
}

//!  @return the number of values in the BST
int SetL::GetSize() const {
	return size;
}

//!  Inserts value v into the BST
//!
//!  @param v The new value being inserted
//!
//!  @return a pointer to the newly inserted node, or NULL if v was already
//!          in the tree (i.e., NULL is used to indicate a duplicate insertion)
SetNode * SetL::Insert(const std::string & v) {

	if (size == 0) {

		++size;
		return root = new SetNode(v);
	} else {

		SetNode * pointer = root;
		while (true) {
			if (v > (pointer->GetValue())) { //if larger go right
				if (pointer->right == NULL) {
					pointer->right = new SetNode(v);
					//	pointer->right->IncOcc();
					++size;
					return pointer->right;
				} else {
					pointer = pointer->GetRight();
				}
			} else if (v < (pointer->GetValue())) { //go left if value is less than
				if (pointer->left == NULL) {
					pointer->left = new SetNode(v);
					//	pointer->left->IncOcc();
					++size;
					return pointer->left;
				} else {
					pointer = pointer->GetLeft();
				}
			} else { //if the node already exists in the structure

				pointer->IncOcc();
				return NULL;
			}

		}

	}
}

//!  Searches the tree for value v
//!
//!  @param v The new value being searched for
//!
//!  @return a pointer to the node containing v, or NULL if v is not in the tree
SetNode * SetL::Find(const std::string & v) const {

	if (size == 0) { //nothing to return
		return NULL;
	} else {
		SetNode * pointer = root;
		while (true) {
			if (v == (pointer->GetValue())) {
				return pointer;
			} else if (v > (pointer->GetValue())) { //v is larger than local root
				if (pointer->right != NULL) {
					pointer = pointer->right;
				} else {
					return NULL;
				}
			} else { //v is less than the current root
				if (pointer->left != NULL) {
					pointer = pointer->left;
				} else {
					return NULL;
				}
			}
		}
	}
}

//!  BSTNode implements a binary search tree node

//!  BST implements a binary search tree

//!  No-arg constructor.  Initializes an empty BST
Map::Map() :
		root(NULL), size(0) {

}

//!  Copy constructor.  Makes a complete copy of its argument
Map::Map(const Map & other) {
	if (other.root != NULL) {
		root = new MapNode(*other.root);
		size = other.GetSize();
	} else {
		root = NULL;
		size = 0;
	}
}

//!  Destructor
Map::~Map() {
	delete root;

}

//!  Assignment operator.  Makes a complete copy of its argument
//!  @return Reference to oneself
Map& Map::operator =(const Map & other) {
	if (this != &other) {
		delete root;
		root = 0;
		size = 0;
		if (other.root != NULL) {
			root = new MapNode(*other.root);
			size = other.size;

		}

	}
	return *this;
}

//!  @return a pointer to the root node of the tree, or NULL if the tree is empty.
//!  @note This is useful for BST clients that need to traverse the tree.)
MapNode * Map::GetRoot() const {
	return root;
}

//!  @return true if the BST is empty, or false if the BST is not empty
bool Map::IsEmpty() const {
	if (size == 0) {
		return true;
	} else {
		return false;
	}
}

//!  Removes all values from the BST
void Map::Clear() {
	delete root;
	root = NULL;
	size = 0;
}

//!  @return the number of values in the BST
int Map::GetSize() const {
	return size;
}

MapNode * Map::Insert(const std::string & v, const std::string & link) {

	if (size == 0) {
		++size;
		root = new MapNode(v);
		//root->SetSet();
		root->GetSet()->Insert(link);

		return root;
	} else {

		MapNode * pointer = root;
		while (true) {
			if (v > (pointer->GetValue())) { //if larger go right
				if (pointer->right == NULL) {
					pointer->right = new MapNode(v);
					pointer->right->InsertLink(link);
					++size;
					return pointer->right;
				} else {
					pointer = pointer->GetRight();
				}
			} else if (v < (pointer->GetValue())) { //go left if value is less than
				if (pointer->left == NULL) {
					pointer->left = new MapNode(v);
					pointer->left->InsertLink(link);
					++size;
					return pointer->left;
				} else {
					pointer = pointer->GetLeft();
				}
			} else { //if the node already exists in the structure
				pointer->GetSet()->Insert(link);
				return NULL;
			}

		}

	}
}

MapNode * Map::Find(const std::string & v) const {
	if (size == 0) { //nothing to return
		return NULL;
	} else {
		MapNode * pointer = root;
		while (true) {
			if (v == (pointer->GetValue())) {
				return pointer;
			} else if (v > (pointer->GetValue())) { //v is larger than local root
				if (pointer->right != NULL) {
					pointer = pointer->right;
				} else {
					return NULL;
				}
			} else { //v is less than the current root
				if (pointer->left != NULL) {
					pointer = pointer->left;
				} else {
					return NULL;
				}
			}
		}
	}
}

string Map::Traverse(MapNode * root) {
	if (!root) {
		return 0;
	}

	MapNode * cur = root;
	MapNode * pre;
	SetL * temp;
	string res = "";
	while (cur != NULL) {
		if (cur->left == NULL) {
			res += "<word> \n";
			res += "<value>" + cur->GetValue() + "</value>\n";
			//res += "<occurrence>\n";
			temp = cur->GetSet();
			res += temp->Traverse(temp->GetRoot());
			//res += "</occurrence>\n";
			res += "</word>\n ";
			cur = cur->right;
		} else {
			pre = cur->left;
			while (pre->right != NULL && pre->right != cur)
				pre = pre->right;

			if (pre->right == NULL) {
				pre->right = cur;
				cur = cur->left;

			} else {
				pre->right = NULL;
				res += "<word> \n";
				res += "<value>" + cur->GetValue() + "</value>\n";
			//	res += "<occurrence>\n";
				temp = cur->GetSet();
				res += temp->Traverse(temp->GetRoot());
			//	res += "</occurrence>\n";
				res += "</word>\n ";
				cur = cur->right;
			}

		}
	}
	return res;

}

string SetL::Traverse(SetNode* root) {
	if (!root) {
		return 0;
	}

	SetNode * cur = root;
	SetNode * pre;
	string res = "";
	while (cur != NULL) {
		if (cur->left == NULL) {
			res += "<occurrence>\n";
			res += "<url>\n" + cur->GetValue() + "\n</url>\n";

			stringstream s;
			res += "<count> ";
			s << cur->GetOcc();
			res += s.str();
			res += "</count>\n";
			res += "</occurrence>\n";

			cur = cur->right;
		} else {
			pre = cur->left;
			while (pre->right != NULL && pre->right != cur)
				pre = pre->right;

			if (pre->right == NULL) {
				pre->right = cur;
				cur = cur->left;

			} else {
				pre->right = NULL;
				res += "<occurrence>\n";
				res += "<url>\n" + cur->GetValue() + "\n</url>\n";
				stringstream s;
				res += "<count>";
				s << cur->GetOcc();
				res += s.str();
				res += "</count>\n";
				res += "</occurrence>\n";
				cur = cur->right;
			}

		}
	}
	return res;

}

//g++ -g -o BSTSet.o -I ../inc -I ../utils/inc BSTSet.cpp
