package flashcard;

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
		//flashcard.readDataRecursive(args[0]);
		//flashcard.readDataRecursive(args[1]);
		//flashcard.readDataRecursive(args[2]);
		//flashcard.readDataRecursive(args[3]);
		//flashcard.readDataRecursive(args[4]);
		flashcard.readDataRecursive(args[5]);
		//flashcard.readDataRecursive(args[0]);

	}

}

