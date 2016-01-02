public interface ArenaInterface
{
	//-------------------------------------------------------------------------
	/**
	 * Returns an array of Strings, with each String representing a test case.
	 * The returned array must have at least five test cases. Each test case
	 * consists of two characters and the winner's name should these two
	 * characters fight.
	 * 
	 * It is not required that your program is able to run these test cases on
	 * itself; however, these test cases will be verified for accuracy.
	 * 
	 * @return an array of Strings that contains your test cases
	 */
	public String[] getTests();
	//-------------------------------------------------------------------------

	//-------------------------------------------------------------------------
	/**
	 * Adds a character to the collection of characters in the arena. Do not
	 * allow duplicate names.
	 * 
	 * Character information is provided as a single String to help refresh your
	 * knowledge of parsing and exception handling.
	 * 
	 * @param info
	 *            a String representing the character to be added
	 * @return true if the character was added; false otherwise
	 */
	public boolean addCharacter(String info);
	//-------------------------------------------------------------------------

	//-------------------------------------------------------------------------
	/**
	 * Removes the character whose name is equal to the given name. Does nothing
	 * if no character is found with the given name.
	 * 
	 * @param name
	 *            the name of the character to be removed
	 * @return true if the character was found and removed; false otherwise
	 */
	public boolean removeCharacter(String name);
	//-------------------------------------------------------------------------

	//-------------------------------------------------------------------------
	/**
	 * Returns the character whose name is equal to the given name. Returns null
	 * if no character is found with the given name.
	 * 
	 * @param name
	 *            the name of the character to be returned
	 * @return the character whose name is equal to the given name; null if no
	 *         character is found with the given name
	 */
	public CharacterInterface getCharacter(String name);
	//-------------------------------------------------------------------------

	//-------------------------------------------------------------------------
	/**
	 * Returns the number of characters in the arena.
	 * 
	 * @return the number of characters in the arena
	 */
	public int getSize();
	//-------------------------------------------------------------------------
}
