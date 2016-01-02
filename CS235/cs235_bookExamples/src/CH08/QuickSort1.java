package CH08;

/**
 * Class to extend QuickSort implementing the first version of the
 * partition algorithm.
 * @author Paul Wolfgang
 */
public class QuickSort1 extends QuickSort {

  /*<listing chapter="8" number="9">*/

  /** Partition the table so that values from first to pivIndex
       are less than or equal to the pivot value, and values from
       pivIndex to last are greater than the pivot value.
       @param table The table to be partitioned
       @param first The index of the low bound
       @param last  The index of the high bound
       @return The location of the pivot value
   */
   @Override
  protected <T extends Comparable<T>> int partition(T[] table,
                                      int first,
                                      int last) {
    // Select the first item as the pivot value.
    T pivot = table[first];
    int up = first;
    int down = last;
    do {
      /* Invariant:
         All items in table[first . . . up - 1] <= pivot
         All items in table[down + 1 . . . last] > pivot
       */
      while ( (up < last) && (pivot.compareTo(table[up]) >= 0)) {
        up++;
      }
      // assert: up equals last or table[up] > pivot.
      while (pivot.compareTo(table[down]) < 0) {
        down--;
      }
      // assert: down equals first or table[down] <= pivot.
      if (up < down) { // if up is to the left of down.
        // Exchange table[up] and table[down].
        swap(table, up, down);
      }
    }
    while (up < down); // Repeat while up is left of down.

    // Exchange table[first] and table[down] thus putting the
    // pivot value where it belongs.
    swap(table, first, down);

    // Return the index of the pivot value.
    return down;
  }
  /*</listing>*/

}
