package CH07;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;


/** Class to build an index.
*   @author Koffman and Wolfgang
**/

public class IndexGenerator {

  // Data Fields
  /** Tree for storing the index. */
    private SortedMap<String, ArrayList<Integer>> index;

  // Methods
  public IndexGenerator() {
      index = new TreeMap<String, ArrayList<Integer>>();
  }

  /*<listing chapter="7" number="2">*/
  /** Reads each word in data file and stores it in a Map
      along with an ArrayList of line numbers (a value).
      pre:  index in an empty Map.
      post: Lowercase form of each word with line
            numbers is stored in index.
      @param scan A Scanner object
   */
  public void buildIndex(Scanner scan) {
    int lineNum = 0; // Line number
    // Keep reading lines until done.
    while (scan.hasNextLine()) {
	String nextLine = scan.nextLine();
	lineNum++;
	// Split the line into words using punctuation and white 
	// space as delimiters.
	String[] words = nextLine.split("[^\\p{L}\\p{N}]+");
	// Insert each word in the index.
	for (String word:words) {
	    word = word.toLowerCase();
	    if (word.length() > 0) {
		ArrayList<Integer> lines = index.get(word);
		if (lines == null) {
		    lines = new ArrayList<Integer>();
		}
		lines.add(lineNum);
		index.put(word, lines);  //Store new list
	    }
	}
    }
  }
  /*</listing>*/

  /** Displays the index, one word per line. */
    public void showIndex() {
	for (Map.Entry<String, ArrayList<Integer>> entry : index.entrySet()) {
	    System.out.print(entry.getKey());
	    System.out.print(":\t");
	    ArrayList<Integer> refs = entry.getValue();
	    System.out.print(refs.get(0));
	    for (int i = 1; i < refs.size(); i++) {
		System.out.print(", ");
		System.out.print(refs.get(i));
	    }
	    System.out.println();
	    System.out.println();
	}
    }

    public static void main(String args[]) {
	if (args.length < 1) {
	    System.err.println("Usage: IndexGenerator <file>");
	    System.exit(1);
	}
	try {
            Scanner scan = new Scanner(new FileReader(args[0]));
	    IndexGenerator indexGenerator = new IndexGenerator();
	    indexGenerator.buildIndex(scan);
	    indexGenerator.showIndex();
	} catch (IOException ioex) {
	    System.err.println("Error building index");
	    ioex.printStackTrace();
	    System.exit(1);
	}
    }
}
