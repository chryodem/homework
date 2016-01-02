#include "Page.h"

Page::Page() :
		root(0), size(0) {

}

//!  Copy constructor.  Makes a complete copy of its argument
Page::Page(const Page & other) {
	if (other.root != 0) {
		root = new BSTPageNode(*other.root);
		size = other.GetSize();
	} else {
		root = 0;
		size = 0;
	}
}

//!  Destructor
Page::~Page() {
	delete root;

}

//!  Assignment operator.  Makes a complete copy of its argument
//!  @return Reference to oneself
Page& Page::operator =(const Page & other) {
	if (this != &other) {
		delete root;
		root = 0;
		size = 0;
		if (other.root != 0) {
			root = new BSTPageNode(*other.root);
			size = other.size;

		}

	}
	return *this;
}

//!  @return a pointer to the root node of the tree, or NULL if the tree is empty.
//!  @note This is useful for BST clients that need to traverse the tree.)
BSTPageNode * Page::GetRoot() const {
	return root;
}

//!  @return true if the BST is empty, or false if the BST is not empty
bool Page::IsEmpty() const {
	if (size == 0) {
		return true;
	} else {
		return false;
	}
}

//!  Removes all values from the BST
void Page::Clear() {
	delete root;
	root = 0;
	size = 0;
}

//!  @return the number of values in the BST
int Page::GetSize() const {
	return size;
}

//!  Inserts value v into the BST
//!
//!  @param v The new value being inserted
//!
//!  @return a pointer to the newly inserted node, or NULL if v was already
//!          in the tree (i.e., NULL is used to indicate a duplicate insertion)
BSTPageNode * Page::Insert(const std::string & v, const std::string & t) {
	if (size == 0) {
		++size;
		return root = new BSTPageNode(v, t);
	} else {

		BSTPageNode * pointer = root;
		while (true) {
			if (v > (pointer->GetValue())) { //if larger go right
				if (pointer->right == 0) {
					pointer->right = new BSTPageNode(v, t);
					++size;
					return pointer->right;
				} else {
					pointer = pointer->GetRight();
				}
			} else if (v < (pointer->GetValue())) { //go left if value is less than
				if (pointer->left == 0) {
					pointer->left = new BSTPageNode(v, t);
					++size;
					return pointer->left;
				} else {
					pointer = pointer->GetLeft();
				}
			} else { //if the node already exists in the structure
				return 0;
			}

		}

	}
}

//!  Searches the tree for value v
//!
//!  @param v The new value being searched for
//!
//!  @return a pointer to the node containing v, or NULL if v is not in the tree
BSTPageNode * Page::Find(const std::string & v) const {
	if (size == 0) { //nothing to return
		return 0;
	} else {
		BSTPageNode * pointer = root;
		while (true) {
			if (v == (pointer->GetValue())) {
				return pointer;
			} else if (v > (pointer->GetValue())) { //v is larger than local root
				if (pointer->right != 0) {
					pointer = pointer->right;
				} else {
					return 0;
				}
			} else { //v is less than the current root
				if (pointer->left != 0) {
					pointer = pointer->left;
				} else {
					return 0;
				}
			}
		}
	}
}

string Page::Traverse(BSTPageNode* root) {
	if (!root) {
		return 0;
	}

	BSTPageNode * cur = root;
	BSTPageNode * pre;
	string res = "<pages>\n";
	while (cur != NULL) {
		if (cur->left == NULL) {
			//	cout<<cur->GetValue()<< " "<<endl;
			//	cout<<cur->GetDesc()<<" "<<endl;
			res += "<page>\n";
			res += "<url>\n";
			res += cur->GetValue() + "\n";
			res += "</url>\n";
			res += "<description>";
			res += cur->GetDesc();
			res += "</description>\n";
			res += "</page>\n";

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
				res += "<page>\n";
				res += "<url>\n";
				res += cur->GetValue() + "\n";
				res += "</url>\n";
				res += "<description>";
				res += cur->GetDesc();
				res += "</description>\n";
				res += "</page>\n";
				cur = cur->right;
			}

		}
	}

	res+="</pages>\n";
	return res;
}

//g++ -g -c -o Page.o -I ../inc -I ../utils/inc Page.cpp
//g++ -g -o test main.o Page.o ../lib/libcs240utils.a
