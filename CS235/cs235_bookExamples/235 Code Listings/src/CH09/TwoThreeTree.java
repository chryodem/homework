/*<exercise chapter="9" type="programming-project">*/
package CH09;

import CH06.SearchTree;

/** An implementation of the 2-3 tree. A 2-3 tree is a search
 *  tree in which each node contains either one or two data
 *  items and (other than leaves) two or three children.
 *  The tree is always balanced in that all leaves are on
 *  the same level, i.e., the length of the path from the root
 *  to a leaf is constant.  This data structure was originally
 *  developed by Hoppcroft and was the inspirtion for the
 *  B-tree and the B-tree specializations the 2-3-4 tree
 *  and red-black tree.
 *  @author Koffman and Wolfgang
 */
public class TwoThreeTree<E extends Comparable<E>>
        implements SearchTree<E> {

    // Nested class
    /** A Node represents a node in a 2-3 tree. This class
     *  has no methods, it is merely a container of private data. */
    private static class Node<E> {
        // Data Fields

        /** The number of data items in this node */
        private int n = 0;
        /** The first info field*/
        private E info1;
        /** The second info field */
        private E info2;
        /** Reference to the left child */
        private Node<E> left;
        /** Reference to the middle child */
        private Node<E> middle;
        /** Reference to the right child */
        private Node<E> right;
    }
    // Data fields
    /** The reference to the root. */
    private Node<E> root = null;
    /** The new parent if needed */
    private E newParent = null;
    /** The child of the new parent */
    private Node<E> newChild = null;

    // Methods
    /** Determine if an item is in the tree
     *  @param target Item being sought in tree
     *  @return true If the item is in the tree, false otherwise
     */
    @Override
    public boolean contains(E target) {
        return find(target) != null;
    }

    /** Find an object in the tree
     *  @param obj The object sought, must be Comparable
     *  @return The object in the tree, or null
     *  if the object is not in the tree
     */
    @Override
    public E find(E obj) {
        return find(root, obj);
    }

    /** Recursive find method. Find the object
     *  in the node or one of its children
     *  @param node The node to search
     *  @param obj The object sought, must be Comparable
     *  @return The object in the tree, or null
     *          if the object is not in the tree
     */
    private E find(Node<E> node, E obj) {
        if (node == null) {
            return null;
        } else if (node.n == 1) {
            if (obj.compareTo(node.info1) == 0) {
                return node.info1;
            } else if (obj.compareTo(node.info1) < 0) {
                return find(node.left, obj);
            } else {
                return find(node.right, obj);
            }
        } else {
            if (obj.compareTo(node.info1) == 0) {
                return node.info1;
            } else if (obj.compareTo(node.info2) == 0) {
                return node.info2;
            } else if (obj.compareTo(node.info1) < 0) {
                return find(node.left, obj);
            } else if (obj.compareTo(node.info2) < 0) {
                return find(node.middle, obj);
            } else {
                return find(node.right, obj);
            }
        }
    }

    /** Insert an Object into the tree.  Inserted item
     *  must implement the Comparable interface
     *  @param o The object to be inserted
     *  @return true if the item was inserted
     */
    @Override
    public boolean add(E obj) {
        if (root == null) {
            root = new Node<E>();
            root.info1 = obj;
            root.n = 1;
            return true;
        } else {
            boolean returnValue = insert(root, obj);
            if (newParent != null) {
                Node<E> oldRoot = root;
                root = new Node<E>();
                root.info1 = newParent;
                root.left = oldRoot;
                root.right = newChild;
                root.n = 1;
                newParent = null;
            }
            return returnValue;
        }
    }

    /** Remove an object from the tree. This is an
     *  unsupported operation.
     *  @param o - The object to be removed
     *  @return The object removed or null if not in the
     *  tree
     *  @throws UnsupportedOperationException if called.
     */
    public boolean remove(E o) {
        throw new UnsupportedOperationException("Remove from 2-3 trees not implemented");
    }

    /** Remove an object from the tree. This is an
     *  unsupported operation.
     *  @param o - The object to be removed
     *  @return The object removed or null if not in the
     *  tree
     *  @throws UnsupportedOperationException if called.
     */
    public E delete(E o) {
        throw new UnsupportedOperationException("Remove from 2-3 trees not implemented");
    }

    /** Insert an Object into the tree.  Inserted item
     *  must impelment the Comparable interface.
     *  PostCondition: If an item is promoted it will
     *  be returned in newParent.  Also newChild will
     *  refernce the node that represents this new
     *  parent's children
     *  @param node - The node to insert the item into
     *  @param obj - The item to be inserted
     *  @return true if item is inserted, false if
     *  already in the tree.
     */
    private boolean insert(Node<E> node, E obj) {
        // See if item is already in the tree
        if (obj.compareTo(node.info1) == 0 || (node.n == 2 && obj.compareTo(node.info2) == 0)) {
            return false;
        }
        // See if this is a leaf
        if (node.left == null) {
            if (node.n == 1) { // insert into this node
                if (obj.compareTo(node.info1) < 0) {
                    // move existing data to the right
                    node.info2 = node.info1;
                    // insert new item
                    node.info1 = obj;
                } else {
                    node.info2 = obj;
                }
                node.n = 2;
                return true;
            } else { // need to split leaf
                splitNode(node, obj, null);
                return true;
            }
        } else { // search for the leaf
            boolean returnValue = false;
            if (obj.compareTo(node.info1) < 0) {
                returnValue = insert(node.left, obj);
            } else if (node.n == 1) {
                returnValue = insert(node.right, obj);
            } else if (obj.compareTo(node.info2) < 0) {
                returnValue = insert(node.middle, obj);
            } else if (obj.compareTo(node.info2) > 0) {
                returnValue = insert(node.right, obj);
            }
            if (newParent != null) {
                if (node.n == 2) {
                    splitNode(node, newParent, newChild);
                    return returnValue;
                } else {
                    if (newParent.compareTo(node.info1) < 0) {
                        node.info2 = node.info1;
                        node.middle = newChild;
                        node.info1 = newParent;
                    } else {
                        node.info2 = newParent;
                        node.middle = node.right;
                        node.right = newChild;
                    }
                    node.n = 2;
                    newParent = null;
                    newChild = null;
                }
            }
            return returnValue;
        }
    }

    /** Method to insert an item into a full node and
     *  split that node into two.
     *  Post: parent is set to the parent of the new right
     *  child, and child is set to the reference this child.
     *  @param node the node to be split
     *  @param obj - the item to insert
     *  @param child - o's child if it has one.
     */
    @SuppressWarnings("unchecked")
    private void splitNode(Node<E> node, E obj, Node<E> child) {
        // Sort the data items and the new value
        E[] data = (E[]) new Comparable[3];
        Node<E>[] newChildren = new Node[4];
        if (obj.compareTo(node.info2) > 0) {
            data[0] = node.info1;
            data[1] = node.info2;
            data[2] = obj;
            newChildren[0] = node.left;
            newChildren[1] = node.middle;
            newChildren[2] = node.right;
            newChildren[3] = child;
        } else if (obj.compareTo(node.info1) > 0) {
            data[0] = node.info1;
            data[1] = obj;
            data[2] = node.info2;
            newChildren[0] = node.left;
            newChildren[1] = node.middle;
            newChildren[2] = child;
            newChildren[3] = node.right;
        } else {
            data[0] = obj;
            data[1] = node.info1;
            data[2] = node.info2;
            newChildren[0] = node.left;
            newChildren[1] = child;
            newChildren[2] = node.middle;
            newChildren[3] = node.right;
        }
        node.n = 1;
        node.info1 = data[0];
        node.left = newChildren[0];
        node.right = newChildren[1];
        newParent = data[1];
        newChild = new Node();
        newChild.n = 1;
        newChild.info1 = data[2];
        newChild.left = newChildren[2];
        newChild.right = newChildren[3];
    }

    /** Return a pre-order traversal as a String object
     *  @return String representation of the tree
     */
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        preOrderTraverse(root, 0, sb);
        return sb.toString();
    }

    /** Perform a pre-order traversal
     *  @param node - The local root
     *  @param d - The depth
     *  @param sb - The string buffer to put the output
     */
    private void preOrderTraverse(Node<E> node, int d, StringBuffer sb) {
        for (int i = 0; i != d; ++i) {
            sb.append("  ");
        }
        if (node == null) {
            sb.append("null");
        } else {
            sb.append(node.info1);
            if (node.n == 2) {
                sb.append(", ");
                sb.append(node.info2);
            }
            sb.append("\n");
            preOrderTraverse(node.left, d + 1, sb);
            if (node.n == 2) {
                sb.append("\n");
                preOrderTraverse(node.middle, d + 1, sb);
            }
            sb.append("\n");
            preOrderTraverse(node.right, d + 1, sb);
            sb.append("\n\n");
        }
    }
}
/*</exercise>*/

