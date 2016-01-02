import java.util.Stack;

public class AVLTree implements AVLInterface {

   
    private Node rootNode;
    
  
    private final Stack<TraverseNode> traverseStack;
    
  
    private int size = 0;

    private class TraverseNode {
        Node n;
        boolean isLeftNext;
        
        public TraverseNode(Node n) {
            this.n = n;
        }
    }
    
    public AVLTree() {
        rootNode = null;
        traverseStack = new Stack<TraverseNode>();
    }

    @Override
    public NodeInterface getRootNode() {
        return rootNode;
    }

    @Override
    public boolean add(String toAdd) {
        
  
        if (rootNode == null) {
            rootNode = new Node(toAdd);
            size++;
            return true;
        }

        if (toAdd == null)
            return false;
        
        Node cur = rootNode;

    
        while(true) {
            traverseStack.push(new TraverseNode(cur));

          
            if (toAdd.equals(cur.getData())) {
                traverseStack.clear();
                return false;
            }

         
            if (toAdd.compareTo(cur.getData()) < 0) {
                if (cur.getLeftChild() != null) {
                    cur = cur.getLeft();
                    traverseStack.peek().isLeftNext = true;
                } else {
                    Node n = new Node(toAdd);
                    cur.setLeft(n);
                    
                    break;
                }
            }
     
            else {
                if (cur.getRightChild() != null) {
                    cur = cur.getRight();
                    traverseStack.peek().isLeftNext = false;
                } else {
                    Node n = new Node(toAdd);
                    cur.setRight(n);
                    
                    break;
                }
            }
        }

        keepChangedHeight();
        
        size++;
        
        return true;
    }

    private void keepChangedHeight() {
        
        while(traverseStack.size() > 0) {
            boolean isLeft = true, isRoot = false;
            Node cur = traverseStack.pop().n;
            
            isRoot = traverseStack.size() == 0;
            
            if (!isRoot)
                isLeft = traverseStack.peek().isLeftNext;
            
        
            int balance = Node.getBalanceOfChildren(cur.getLeft(), cur.getRight());
            
           
            if (Math.abs(balance) <= 1)
                cur.updateHeight();
            
   
            else {
                if (balance > 1) {
                    if (Node.getBalanceOfChildren(cur.getLeft().getRight(), cur.getLeft().getLeft()) > 0)
                        cur = Node.doubleRotateRight(cur);
                    else
                        cur = Node.rotateRight(cur);
                }
                else if (balance < -1) {
                    if (Node.getBalanceOfChildren(cur.getRight().getLeft(), cur.getRight().getRight()) > 0)
                        cur = Node.doubleRotateLeft(cur);
                    else
                        cur = Node.rotateLeft(cur);
                }
                
                if(isRoot)
                    rootNode = cur;
                else {
                    if (isLeft)
                        traverseStack.peek().n.setLeft(cur);
                    else
                        traverseStack.peek().n.setRight(cur);
                }
            }
        }
    }

    @Override
    public boolean remove(String toRemove) {
        
        Node cur = rootNode;
        
        while (cur != null) {
            traverseStack.push(new TraverseNode(cur));
            
            String data = cur.getData();
            
            if (data.equals(toRemove))
                break;
            
            if (toRemove.compareTo(data) < 0) {
                cur = cur.getLeft();
                traverseStack.peek().isLeftNext = true;
            }
            else {
                cur = cur.getRight();
                traverseStack.peek().isLeftNext = false;
            }
        }
        

        if (cur == null) {
            traverseStack.clear();
            return false;
        }
        
        Node removeTarget = cur;
        Node left = cur.getLeft();

 
        if (size == 1) {
            rootNode = null;
            size = 0;
            return true;
        }
        
      
        
  
        if (left == null) {
            traverseStack.pop();
            
            resetChildOfParent(cur.getRight());
        } 

        else if (left.getRight() == null) {
            traverseStack.pop();
            
            left.setRight(cur.getRight());

            resetChildOfParent(left);

            traverseStack.push(new TraverseNode(left));
        }
        
  
        else {
            cur = left.getRight();
            
            traverseStack.peek().isLeftNext = true;
            traverseStack.push(new TraverseNode(left));
            traverseStack.peek().isLeftNext = false;
            
            while(cur != null) {
                traverseStack.push(new TraverseNode(cur));
                traverseStack.peek().isLeftNext = false;
                
                cur = cur.getRight();
            }
            
            cur = traverseStack.pop().n;
            
            removeTarget.setData(cur.getData());
            
            resetChildOfParent(cur.getLeft());
        }
        
        size--;
        
        keepChangedHeight();
        
        return true;
    }

   
    private void resetChildOfParent(Node n) {
        if (traverseStack.size() == 0) {
            rootNode = n;
            return;
        }
        
        TraverseNode parent = traverseStack.peek();
        
        if (parent.isLeftNext)
            parent.n.setLeft(n);
        else
            parent.n.setRight(n);
    }
    
   
    public int getSize() {
        return size;
    }
    
   
    public void printTree() {
        printNode(rootNode, 0);
    }
    
   
    private static void printNode(Node n, int t) {
        if (n == null) {
            for(int i=0; i<t; i++) System.out.print("    ");
            System.out.println("*");
            return;
        }
        
        for(int i=0; i<t; i++) System.out.print("    ");
        System.out.println(n.getData());
        
        printNode(n.getLeft(), t+1);
        
        printNode(n.getRight(), t+1);

    }
}