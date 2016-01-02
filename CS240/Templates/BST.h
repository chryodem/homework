#ifndef CS240_BST_H
#define CS240_BST_H

//create forward declaration so that compilers knows it exists
template <typename T> class BST;

//!  BSTNode implements a binary search tree node
template <typename E> //can pass in as many parameters as you want
class BSTNode 
{
		friend class BST<E>;   //!< BST can access private members of BSTNode
	
	public:
	
		//!  Constructor
		BSTNode(const E & v) :
		  value(v), left(0), right(0) //used to be NULL for left and right
		{
		}
		
		//! Copy Constructor
		BSTNode(const BSTNode<E> & other) :
		  value(other.value),
		  left(other.left == 0 ? 0: new BSTNode<E>(*other.left)), //used to be NULL ? NULL
		  right(other.right == 0 ? 0: new BSTNode<E>(*other.right))
		{
		}
		
		~BSTNode()
		{
			delete left;
			delete right;
		}

	
		//!  Read-only public methods for use by clients of the BST class
		const E & GetValue() const
		{
		  return value;
		}
	
		
	
		BSTNode<E> * GetLeft()const
		{
		  return left;
		}
	
	
		BSTNode<E> * GetRight()const
		{
		  return right;
		}
		
		//! Assignment operator 
		BSTNode<E> & operator=(const BSTNode<E> & other)
		{
			if(this!=&other)
			{
				*this = other;
			}
			
			return *this;
		}
		//find min value
		const E MinValue() {
			if(left == 0) {
				return left->value;
			} else {
				return left->MinValue();
			}
		}
	
	private:
		E value;  //!< value stored in the node
		BSTNode<E> * left;     //!< pointer to the node's left child
		BSTNode<E> * right;    //!< pointer to the node's right child
};


//!  BST implements a binary search tree
template<typename E>
class BST 
{
private:
	BSTNode<E> * root;
	int size;
	
	public:
	
		//!  No-arg constructor.  Initializes an empty BST
		BST():root(0), size(0){

		}

		//!  Copy constructor.  Makes a complete copy of its argument
		BST(const BST<E> & other){
			if (other.root != 0) {
					root = new BSTNode<E>(*other.root);
					size = other.GetSize();
				} else {
					root = 0;
					size = 0;
				}
		}
	
	
		//!  Destructor
		~BST(){
			delete root;

		}
	
	
		//!  Assignment operator.  Makes a complete copy of its argument
		//!  @return Reference to oneself
		BST<E>& operator =(const BST<E> & other){
			if (this != &other) {
					delete root;
					root = 0;
					size = 0;
					if (other.root != 0) {
						root = new BSTNode<E>(*other.root);
						size = other.size;

					}

				}
				return *this;
		}
	
	
		//!  @return a pointer to the root node of the tree, or NULL if the tree is empty.
		//!  @note This is useful for BST clients that need to traverse the tree.)
		BSTNode<E> * GetRoot()const{
			return root;
		}
	
	
		//!  @return true if the BST is empty, or false if the BST is not empty
		bool IsEmpty() const{
			if (size == 0) {
					return true;
				} else {
					return false;
				}
		}
	
	
		//!  Removes all values from the BST
		void Clear(){
			delete root;
				root = 0;
				size = 0;
		}
	
	
		//!  @return the number of values in the BST
		int GetSize() const{
			return size;
		}
	
	
		//!  Inserts value v into the BST
		//!  
		//!  @param v The new value being inserted
		//!
		//!  @return a pointer to the newly inserted node, or NULL if v was already
		//!          in the tree (i.e., NULL is used to indicate a duplicate insertion)
		BSTNode<E> * Insert(const E & v){
			if (size == 0) {
					++size;
					return root = new BSTNode<E>(v);
				} else {

					BSTNode<E> * pointer = root;
					while (true) {
						if (v>(pointer->GetValue())) { //if larger go right
							if (pointer->right == 0) {
								pointer->right = new BSTNode<E>(v);
								++size;
								return pointer->right;
							} else {
								pointer = pointer->GetRight();
							}
						} else if (v<(pointer->GetValue())) { //go left if value is less than
							if (pointer->left == 0) {
								pointer->left = new BSTNode<E>(v);
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
		BSTNode<E> * Find(const E & v) const{
			if (size == 0) { //nothing to return
					return 0;
				} else {
					BSTNode<E> * pointer = root;
					while (true) {
						if (v==(pointer->GetValue())) {
							return pointer;
						} else if (v>(pointer->GetValue())) { //v is larger than local root
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

		bool Remove(const E & v) {
			BSTNode<E> * nodeToRemove = Find(v);
			//this value doesn't exist in the tree
			if(nodeToRemove == 0) {
				return false;
			} else { //cover the 3 cases for removal
                
            }
		}
	
	
		//! @NOTE: YOU ARE NOT REQUIRED TO IMPLEMENT THE Remove METHOD BELOW
		//!        (BUT YOU CAN IF YOU WANT TO)
		//!
		//!  Removes value v from the tree
		//!  
		//!  @param v The value being removed from the tree
		//!
		//!  @return true if v was removed from the tree, or false if v was not in the tree
		//bool Remove(const std::string & v);
	
	private:
		BSTNode<E> * FindParent(const E & v, BSTNode<E> * parent) const {
            if(parent->GetLeft() == v || parent->GetRight() == v) {
                return parent;
            }
            
            if (parent->GetLeft() != 0) { //make sure child is not null not null
                if(parent->GetLeft()->GetValue() == v) {
                    return parent;
                }
            }
            
            if (parent->GetRight() != 0) {
                if(parent->GetRight()->GetValue() == v) {
                    return parent;
                }
            }
            
            if(v < parent->GetValue()) { //check the left child first
                if(parent->GetLeft() == 0) {
                    return 0;
                } else {
                    FindParent(v, parent->GetLeft());
                }
            } else { //go to the right child
                if(parent->GetRight() == 0) {
                    return 0;
                } else {
                    FindParent(v, parent->GetRight());
                }
            }
		}
};


#endif
