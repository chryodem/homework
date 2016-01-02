/**
 * This class must use nodes that implement the NodeInterface
 */
public interface AVLInterface
{

	/**
	 * Returns the root node of this AVL tree.
	 * 
	 * @return the root node of this AVL tree
	 */
	public NodeInterface getRootNode();

	/**
	 * Attempts to add the given String to the AVL tree; rebalances the tree if successful. Do not
	 * allow duplicate Strings.
	 * 
	 * @return true if the given String was added successfully; false otherwise
	 */
	public boolean add(String toAdd);

	/**
	 * Attempts to remove the given String from the AVL tree; rebalances the tree if successful.
	 * Does nothing if the String is not found within the tree.
	 * 
	 * @return true if the given String was removed successfully; false otherwise
	 */
	public boolean remove(String toRemove);
}
