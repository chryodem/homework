

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Flashcard {

	public boolean readDataRecursive(String reader) throws FileNotFoundException {
		try {

			Scanner scanner = new Scanner(new File(reader));
			scanner.useDelimiter("[<>\n\r]");
			//scanner.next();  //vocab
			//recursion(scanner);
			String words = "";
			String definitions = "";
			String temp = "";
			while (scanner.hasNext()){
				if (scanner.equals("vocab")) {

					if (scanner.equals("word")) {

						words = scanner.next();
						System.out.println(words);

					}
					if (scanner.equals("def")) {

						definitions = scanner.next();
						System.out.println(definitions + "\n");

					}
				
			}

			}
		} catch (NoSuchElementException e) {
			System.out.println(e);
			System.out.println("document " + reader + " is not properly formatted");
			System.exit(0);
		}

		return true;

}
}

	/*public void recursion(Scanner scanner) {
	//	String words = "";
		//String definitions = "";
		//String temp = "";

		if (scanner.next().equals("/vocab")) //word
		{
			return;
		} 
		else 
		{
		String	temp = scanner.next(); //abate
		scanner.next();//  /word
		scanner.next();// def
		String def = scanner.next(); // definition of abate
		scanner.next();
		
		
		System.out.println(temp + "\n" + def + "\n");
			recursion(scanner);
		}
	}
	
}
/*	if (temp.equals("vocab")) {

			if (temp.equals("word")) {

				words = scanner.next();
				System.out.println(words);

			}
			if (temp.equals("def")) {

				definitions = scanner.next();
				System.out.println(definitions + "\n");

			}*/