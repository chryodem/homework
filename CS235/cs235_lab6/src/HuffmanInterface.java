public interface HuffmanInterface
{
	/**
	 * Generates a Huffman tree based on the given sample text. This method only needs to create the
	 * tree; other methods will be used to encode and decode text.
	 * 
	 * Note that we will always provide sample texts which only contain at least one of each
	 * character in Beeoeyehuish.
	 * 
	 * @param sampleTest
	 *            the sample text to be used when generating the tree
	 */
	public void createTree(String sampleText);

	/**
	 * Encodes the given message using the tree created in the createTree(String) method. The
	 * encoded message should only contain 1's and 0's. If no tree has been created yet, simply
	 * return null.
	 * 
	 * Note that we will always provide messages which only contain characters in Beeoeyehuish.
	 * 
	 * @param message
	 *            The message to be encoded using the stored tree
	 * 
	 * @return the encoded message or null if no tree has been created yet
	 */
	public String encodeMessage(String message);

	/**
	 * Decode the given message of 1's and 0's using the tree created in the createTree(String)
	 * method. The decoded message should only contain lowercase characters in Beeoeyehuish. If no
	 * tree has been created yet, simply return null.
	 * 
	 * Note that we will always provide encoded messages that only contain 1's and 0's.
	 * 
	 * @param encodedMessage
	 *            the encoded message to be decoded using the stored tree
	 * 
	 * @return the decoded message or null if no tree has been created yet
	 */
	public String decodeMessage(String encodedMessage);

}
