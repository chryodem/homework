/*<listing chapter="A" number="4">*/

import java.io.*;

/** FileTest is an application that illustrates stream operations.
 *  @author Koffman and Wolfgang
 **/
public class FileTest {

    /** Reads a line from an input file and a line from the console.
     *  Concatenates the two lines and writes them to an output file.
     *  Does this until all input lines have been read.
     *  @param args[0] The input file name
     *  @param args[1] The output file name
     */
    public static void main(String[] args) {

        try {
            String inFileName = args[0]; // First main parameter
            String outFileName = args[1]; // Second main parameter
            BufferedReader ins =
                    new BufferedReader(new FileReader(inFileName));
            BufferedReader con =
                    new BufferedReader(new InputStreamReader(System.in));
            PrintWriter outs =
                    new PrintWriter(new FileWriter(outFileName));

            // Reads words and writes them to the output file until done.
            String first = ins.readLine(); // Read from file
            while (first != null) {
                System.out.print("Type in a word to follow "
                        + first + ": ");
                String second = con.readLine(); // Read from console
                // Append and write
                outs.println(first + ", " + second);
                first = ins.readLine(); // Read from file
            }

            // Close files.
            ins.close();
            outs.close();
        } catch (IOException ex) {
            ex.printStackTrace(System.err); // Display stack trace
            System.exit(1); // Exit with an error indication
        }
    }
}
/*</listing>*/
