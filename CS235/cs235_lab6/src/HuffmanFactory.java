
public class HuffmanFactory
{
	/**
	 * Creates and returns an object whose class implements HuffmanInterface.
	 * This should be an object of the class you have created.
	 * 
	 * Example: If you made a class called "Huffman", you might say "return new Huffman();"
	 * 
	 * @return a new object whose class implements HuffmanInterface
	 */
    public static HuffmanInterface createHuffman() 
    {
		return new Huffman();
    }
}
