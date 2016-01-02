import java.util.List;

public interface MimicInterface
{

	/**
	 * Create a prefix-suffix map based on the input text.
	 * 
	 * @param input
	 *            the sample text to be mimicked
	 */
	public void createMap(String input);

	/**
	 * Return the list of suffixes associated with the given prefix. Return null
	 * if the given prefix is not in the map or no map has been created yet
	 * 
	 * @param prefix
	 *            the prefix to be found
	 * @return a list of suffixes associated with the given prefix if the prefix
	 *         is found; null otherwise
	 */
	public List<String> getSuffixList(String prefix);

	/**
	 * Generates random text using the map created with the sample text.
	 * 
	 * @return random text generated using the map created with the sample text;
	 *         null if no map has been created yet
	 */
	public String generateText();

}
