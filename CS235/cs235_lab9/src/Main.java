
public class Main {
    public static void main (String [] args) {
        AVLTree tree = new AVLTree();
        
        for(int i=0; i<50; i++) {
            tree.add(String.valueOf(i));
            tree.add(String.valueOf(-i));
        }

        for(int i=0; i<10; i++) {
            tree.remove(String.valueOf(i));
            tree.remove(String.valueOf(-i));
        }
        
        tree.printTree();
    }
}
