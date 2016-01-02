import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Parser {

	/**
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub

		Scanner scanner = new Scanner(new File("words.rtml"));
		scanner.useDelimiter("[<>\n]");
		String temporary = "";

		// only run this loop if the first line == element
		// temporary = scanner.next();
		if ((scanner.next()).equals("vocab")) {

			// loop to keep running until it runs out of space.
			while (scanner.hasNext()) {

				// if the next line says subsubelement, print out the name
				if ((scanner.next()).equals("word")) 
				{
					System.out.println(scanner.next());

				}
				
			}
		}
	}
}
