

import java.io.FileNotFoundException;
import java.io.IOException;

import org.xml.sax.SAXException;

public class DataDriverProject5 {
	
		
	

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 * @throws IOException 
	 * @throws SAXException 
	 */
	public static void main(String[] args) throws FileNotFoundException  {
		
		
		
		Flashcard flashcard = new Flashcard();
		flashcard.readDataRecursive(args[0]);
		

	}

}

