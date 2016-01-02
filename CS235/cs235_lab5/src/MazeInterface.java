public interface MazeInterface
{
	/**
	 * Create and store a random maze with an entry and exit point.
	 */
	public void createRandomMaze();
	
	/**
	 * Read from the given file (see maze_sample.txt for format) and store it as a maze. If the file doesn't exist
	 * or is formatted incorrectly (missing or extra numbers, invalid characters, etc.) return false.
	 * 
	 * @param fileName The file name of the file to be imported
	 * 
	 * @return true if the file was successfully read/imported, false if an error occurred when parsing/reading the file
	 */
	public boolean importMaze(String fileName);
	
	/**
	 * Traverses the current maze in storage, storing the path taken if the maze was solvable.
	 * 
	 * @return true if the maze was solvable, false if unsolvable
	 */
	public boolean traverseMaze();
	
	/**
	 * Returns the path from the most recently traversed maze. The String format should be each position taken 
	 * separated by newlines (see format on website).
	 * 
	 * @return the path to the most recent maze traversed
	 * 		if no maze has been solved yet, return a null string
	 */
	public String getMazePath();
	
	/**
	 * Get the current maze stored in a single String (see website for format)
	 * 
	 * @return a String representation of your maze
	 */
	public String getMaze();
}
