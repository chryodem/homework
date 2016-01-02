public interface NodeInterface
{
	/**
	 * Returns the data that is stored in this node.
	 * 
	 * @return the data that is stored in this node
	 */
	public String getData();

	/**
	 * Returns the left child of this node or null if it doesn't have one.
	 * 
	 * @return the left child of this node or null if it doesn't have one
	 */
	public NodeInterface getLeftChild();

	/**
	 * Returns the right child of this node or null if it doesn't have one.
	 * 
	 * @return the right child of this node or null if it doesn't have one
	 */
	public NodeInterface getRightChild();

	/**
	 * Returns the height of this node.
	 * 
	 * The height is the number of edges from this node to this node's farthest child.
	 * 
	 * @return the height of this node
	 */
	public int getHeight();

}
