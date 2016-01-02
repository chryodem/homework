public interface QSInterface
{
	/**
	 * Sorts the given array of integers.
	 * 
	 * After this method is called, every element in the array is less than or
	 * equal to the following element.
	 * 
	 * Does nothing if the given array is null.
	 * 
	 * @param data
	 *            an array of integers
	 */
	public void sort(int[] data);

	/**
	 * Performs median-of-three pivot selection from among the values in the
	 * given array between the two given indices. Median-of-three pivot
	 * selection will sort the first, middle, and last elements in a given array
	 * with respect to each other.
	 * 
	 * After this method is called, data[first] <= data[middle] <= data[last],
	 * where middle = (first + last)/2.
	 * 
	 * Does nothing if the given array is null, if either of the given integers
	 * is out of bounds, or if the first integer is not less than the second
	 * integer.
	 * 
	 * @param data
	 *            an array of integers
	 * @param left
	 *            the left boundary for the subarray from which to find a pivot
	 * @param right
	 *            the right boundary for the subarray from which to find a pivot
	 */
	public void medianOfThree(int[] data, int left, int right);

	/**
	 * Partitions a subarray around a pivot value selected according to
	 * median-of-three pivot selection.
	 * 
	 * The values which are smaller than the pivot should be placed to the left
	 * of the pivot; the values which are larger than the pivot should be placed
	 * to the right of the pivot.
	 * 
	 * Does nothing and returns -1 if the given array is null, if either of the
	 * given integers is out of bounds, or if the first integer is not less than
	 * the second integer.
	 * 
	 * @param data
	 *            an array of integers
	 * @param left
	 *            the left boundary for the subarray to partition
	 * @param right
	 *            the right boundary for the subarray to partition
	 * @return the pivot's ending index after the partition completes; -1 if
	 *         provided with bad input
	 */
	public int partition(int[] data, int left, int right);

	/**
	 * Swaps the values at the given indices within the given array.
	 * 
	 * After this method is called, the values in data[i] and data[j] should be
	 * swapped.
	 * 
	 * Does nothing if the given array is null or if either of the given
	 * integers is out of bounds.
	 * 
	 * @param data
	 *            an array of integers
	 * @param i
	 *            the first index
	 * @param j
	 *            the second index
	 */
	public void swap(int[] data, int i, int j);
}
