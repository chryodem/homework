/*<listing chapter="9" section="6">*/
package CH09;

import CH06.SearchTree;
import java.util.Arrays;
import java.util.Random;

/**
 * A Skip List is an alternative to a binary tree that provides for
 * approximately logorithmic searching, insertion, and deletion.
 * Skip Lists were developed by William Pugh and first described in
 * "Skip Lists: A Probabilistic Alternative to Balanced Treees",
 * CACM 13:8 (June 1990) pp 668-676
 * @author Koffman & Wolfgang
 */
public class SkipList<E extends Comparable<E>> implements SearchTree<E> {

    /** Static class to contain the data and the links */
    static class SLNode<E> {

        SLNode<E>[] links;
        E data;

        /** Create a node of level n */
        @SuppressWarnings("unchecked")
        SLNode(int n, E data) {
            links = (SLNode<E>[]) new SLNode[n];
            this.data = data;
        }
    }

    /** Maximum level */
    int maxLevel = 2;
    /** Nominal maximum capacity */
    int maxCap = computeMaxCap(maxLevel);
    /** Natural Log of 2 */
    static final double LOG2 = Math.log(2.0);
    /** A random number generator */
    final static Random rand = new Random();
    /** The current size of the skipList */
    int size;

    /**
     * Method to compute the maximum capacity, given the maximum
     * level. It computes Math.pow(2, maxLevel) - 1, using shift.
     * @return Math.pow(2, maxLevel+1) - 1;
     */
    private static int computeMaxCap(int maxLevel) {
        return ~(~0 << maxLevel);
    }

    /** Method to generate a lograthmic distributed integer between
     *  1 and maxLevel.  I.E. half of the values returned are 1, 1/4
     *  are 2, 1/8 3, 1/16 4, etc.
     *  @return a random lograthmic distributed int between 1 and maxLevel
     */
    private int logRandom() {
        int r = rand.nextInt(maxCap);
        int k = (int) (Math.log(r + 1) / LOG2);
        if (k > maxLevel - 1) {
            k = maxLevel - 1;
        }
        return maxLevel - k;
    }

    /** The head node is a dummy node, it contains no data */
    SLNode<E> head = new SLNode<E>(maxLevel, null);

    /*<listing chapter="9" number="7">*/
    @SuppressWarnings("unchecked")
    /** Search for an item in the list
     * @param item The item being sought
     * @return A SLNode array which references the nodes
     *         preceeding the sought item at each level.
     */
    private SLNode<E>[] search(E item) {
        SLNode<E>[] result = (SLNode<E>[]) new SLNode[maxLevel];
        SLNode<E> current = head;
        for (int i = current.links.length - 1; i >= 0; i--) {
            while (current.links[i] != null &&
                    current.links[i].data.compareTo(item) < 0) {
                current = current.links[i];
            }
            result[i] = current;
        }
        return result;
    }

    /** Find an object in the skip list
     *  @param target The item being sought
     *  @return A reference to the object in the skip list that compares
     *          equal as determined by compareTo to the target. If not
     *          found null is returned.
     */
    @Override
    public E find(E target) {
        SLNode<E>[] update = search(target);
        if (update[0].links[0] != null &&
                update[0].links[0].data.compareTo(target) == 0) {
            return update[0].links[0].data;
        } else {
            return null;
        }
    }
    /*</listing>/

    /*<exercise chapter="9" section="6" type="programming" number="1">*/
     /** Inserts item where it belongs in the skip list.
     *  @param item The item to be inserted
     *  @return true If the item is inserted, false if the
     *  item was already in the tree.
     */
    public boolean add(E item) {
        SLNode<E>[] update = search(item);
        if (update[0].links[0] != null &&
                update[0].links[0].data.compareTo(item) == 0) {
            return false; // Item already in Skip List
        }
        // Increment size and adjust maxLevel
        size++;
        if (size > maxCap) {
            maxLevel++;
            maxCap = computeMaxCap(maxLevel);
            head.links = Arrays.copyOf(head.links, maxLevel);
            update = Arrays.copyOf(update, maxLevel);
            update[maxLevel - 1] = head;
        }
        // Create new node for item
        SLNode<E> newNode = new SLNode<E>(logRandom(), item);
        // Splice new node into list
        for (int i = 0; i < newNode.links.length; i++) {
            newNode.links[i] = update[i].links[i];
            update[i].links[i] = newNode;
        }
        return true;
    }
    /*</exercise>*/

    /** Determine if an item is in the tree
     *  @param target Item being sought in tree
     *  @return true If the item is in the tree, false otherwise
     */
    @Override
    public boolean contains(E target) {
        return find(target) != null;
    }

    /*<exercise chapter="9" section="6" type="programming" number="2">*/
    /** Removes target from tree.
     *  post: target is not in the tree
     *  @param target Item to be removed
     *  @return A reference to the object in the tree that compares
     *          equal as determined by compareTo to the target. If
     *          not found null is returned.
     */
    @Override
    public E delete(E target) {
        SLNode<E>[] update = search(target);
        if (update[0].links[0] == null ||
                update[0].links[0].data.compareTo(target) != 0) {
            return null;
        }
        SLNode<E> x = update[0].links[0]; // x is the node to be deleted
        for (int i = 0; i < update.length; i++) {
            if (update[i].links[i] != x) {
                break;
            }
            update[i].links[i] = x.links[i];
        }
        return x.data;
    }
    /*</exercise>*/

    /** Removes target from tree.
     *  post: target is not in the tree
     *  @param target Item to be removed
     *  @return true if the object was in the tree, false otherwise
     */
    @Override
    public boolean remove(E target) {
        return delete(target) != null;
    }

    /** Remove all data from the tree */
    public void clear() {
        for (int i = 0; i < maxLevel; i++) {
            head.links[i] = null;
        }
        size = 0;
    }
}
/*</listing>*/