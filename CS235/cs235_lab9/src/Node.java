public class Node implements NodeInterface, Comparable<Node> {

	private String data;
	private Node left, right;
	private int height = 1;
	
	public Node() {
		this(null, null, null);
	}
	
	public Node(String data) {
		this(data, null, null);
	}
	
	public Node(String data, Node l, Node r) {
		this.data = data;
		this.left = l;
		this.right = r;
		
		updateHeight();
	}
	
	@Override
	public String getData() {
		return data;
	}
	
	public void setData(String s) {
	    data = s;
	}

	@Override
	public NodeInterface getLeftChild() {
		return left;
	}
	
	public Node getLeft() {
	    return left;
	}
	
	public void setLeft(Node n) {
	    left = n;
	}
	
	@Override
	public NodeInterface getRightChild() {
		return right;
	}
	
	public Node getRight() {
        return right;
    }
	
	public void setRight(Node n) {
        right = n;
    }
	
	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public int compareTo(Node o) {
		return data.compareTo(o.data);
	}

	
	public boolean updateHeight() {
	    int height;
	    
		if (left == null && right == null) {
			height = 1;
		}
		
		else if (left == null) {
			height = right.getHeight() + 1;
		}
		
		else if (right == null) {
			height = left.getHeight() + 1;
		}
		
		else {
			height = left.getHeight() > right.getHeight()
					? left.getHeight() + 1 : right.getHeight() + 1;
		}
		
		if (height == this.height)
		    return false;
		
		this.height = height;
		return true;
	}
	

	public static Node rotateRight(Node root) {
		Node left = root.left;
		
		Node tmpRoot = root;
		
		tmpRoot.left = left.right;
		root = left;
		root.right = tmpRoot;

		updateFamily(root);
		
		return root;
	}
	

	public static Node rotateLeft(Node root) {
		Node right = root.right;
		
		Node tmpRoot = root;
		
		tmpRoot.right = right.left;
		root = right;
		root.left = tmpRoot;

		updateFamily(root);
        
		return root;
	}
	

	private static void updateFamily(Node n) {
	    if (n == null)
	        return;
	    
	    if (n.left != null)
	        n.left.updateHeight();
	    
	    if (n.right != null)
	        n.right.updateHeight();
	    
        n.updateHeight();
	}
	
	public static Node doubleRotateRight(Node root) {
	    Node left = root.left;
	    
	    left = rotateLeft(left);
	    root.setLeft(left);
	    
	    return rotateRight(root);
	}
	
	public static Node doubleRotateLeft(Node root) {
        Node right = root.right;
        
        right = rotateRight(right);
        root.setRight(right);
        
        return rotateLeft(root);
    }
	
	public static int getBalanceOfChildren(Node l, Node r) {
	    int lh, rh;
	    
	    lh = l == null ? 0 : l.getHeight();
	    rh = r == null ? 0 : r.getHeight();
	    
	    return lh - rh;
	}
}