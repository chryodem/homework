public interface MergeInterface
{
	/**
	 * Merges the records of the given file according to the exam requirements. See the exam packet
	 * for details.
	 * 
	 * @param file_name
	 *            the name of the secondary-memory file
	 * @param record_count
	 *            the number of records in the secondary-memory file
	 * @param main_memory_array_size
	 *            the size of each of the two main-memory arrays
	 * @return the name of the secondary memory file to which the sorted results have been written
	 */
	public String sort(String file_name, int record_count, int main_memory_array_size);

	/**
	 * Reports the names of the intermediate secondary-memory files or null if no sorting has
	 * occurred yet.
	 * 
	 * @return an array containing the names of the secondary files; null if no sorting has occurred
	 */
	public String[] reportIntermediateFileNames();

	/**
	 * Reports the number of read and write operations that occurred or -1 if no sorting has occurred
	 * yet. Each read or write operation is a single call to the methods [loadArray1All],
	 * [loadArray1Part], [printArray1], or [printArray2].
	 * 
	 * @return the number of read and write operations that occurred; -1 if no sorting has occurred
	 */
	public int reportReadsAndWrites();

	/**
	 * Loads the first main-memory array in full with data from the given file. The chunk index
	 * specifies which piece of the file should be read, with the indexing beginning at 0. The size
	 * of this chunk is the same as the size of the first main-memory array.
	 * 
	 * This method will not be called by our test driver; it is strictly to control your read/write
	 * operations. Recall that a single call to this method counts as one read/write operation.
	 * 
	 * Example 1:
	 * For a main-memory array of size 3, a chunk index of 2, and a file containing the elements
	 * below, the first main-memory array should be loaded as seen below.
	 * 		File = ["aa", "ab", "ac", "ad", "ae", "af", "ag", "ah", "ai", "aj", "ak", "al", "am", "an"] 
	 * 		First Main-Memory Array = ["ag", "ah", "ai"]
	 * 
	 * Example 2:
	 * For a main-memory array of size 3, a chunk index of 4, and a file containing the elements
	 * below, the first main-memory array should be loaded as seen below.
	 * 		File = ["aa", "ab", "ac", "ad", "ae", "af", "ag", "ah", "ai", "aj", "ak", "al", "am", "an"] 
	 * 		First Main-Memory Array = ["am", "an", null]
	 * 
	 * @param file_name
	 *            the name of the file from which to read
	 * @param chunk
	 *            the index of the chunk to be read
	 */
	public void loadArray1All(String file_name, int chunk);

	/**
	 * Loads the first main-memory array in part with data from the given file. The chunk index
	 * specifies which piece of the file should be read, with the indexing beginning at 0. The size
	 * of this chunk is equal to the size of the first main-memory array divided by the number of
	 * intermediate files created, rounded down. The write_index specifies where the chunk should be
	 * stored within the first main-memory array.
	 * 
	 * This method will not be called by our test driver; it is strictly to control your read/write
	 * operations. Recall that a single call to this method counts as one read/write operation.
	 * 
	 * Example 1: For a main-memory array of size 10, a chunk index of 1, a write_index of 0, 3
	 * intermediate files, and a file containing the elements below, the first main-memory array
	 * should be loaded as seen below.
	 * 		File = ["aa", "ab", "ac", "ad", "ae", "af", "ag", "ah", "ai", "aj"] 
	 * 		First Main-Memory Array = ["ad", "ae", "af", --, --, --, --, --, --, --]
	 * 			-- = unchanged 
	 * 
	 * Example 2: For a main-memory array of size 10, a chunk index of 3, a write_index of 2, 3
	 * intermediate files, and a file containing the elements below, the first main-memory array
	 * should be loaded as seen below.
	 * 		File = ["aa", "ab", "ac", "ad", "ae", "af", "ag", "ah", "ai", "aj"] 
	 * 		First Main-Memory Array = [--, --, --, --, --, --, "aj", null, null, --]
	 * 			-- = unchanged 
	 * 
	 * @param file_name
	 *            the name of the file from which to read
	 * @param chunk
	 *            the index of the chunk to be read
	 * @param write_index
	 *            the index of the chunk within the array to store the file's data
	 */
	public void loadArray1Part(String file_name, int chunk, int write_index);

	/**
	 * Writes the contents of the first main-memory array to the end of the given file. If no file
	 * yet exists with the given file name, create such a file and write the contents of the first
	 * main-memory array to the file.
	 * 
	 * This method will not be called by our test driver; it is strictly to control your read/write
	 * operations. Recall that a single call to this method counts as one read/write operation.
	 * 
	 * @param file_name
	 *            the name of the file from which to read
	 */
	public void printArray1(String file_name);

	/**
	 * Writes the contents of the second main-memory array to the end of the given file. If no file
	 * yet exists with the given file name, create such a file and write the contents of the second
	 * main-memory array to the file.
	 * 
	 * This method will not be called by our test driver; it is strictly to control your read/write
	 * operations. Recall that a single call to this method counts as one read/write operation.
	 * 
	 * @param file_name
	 *            the name of the file from which to read
	 */
	public void printArray2(String file_name);
}
