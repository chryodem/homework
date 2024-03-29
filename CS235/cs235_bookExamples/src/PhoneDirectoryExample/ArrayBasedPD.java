package PhoneDirectoryExample;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


 /** This is an implementation of the PhoneDirectory interface that uses
  *   an array to store the data.
  *   @author Koffman & Wolfgang
  */

public class ArrayBasedPD
    implements PhoneDirectory {

  // Data Fields

  /** The initial capacity of the array */
  private static final int INITIAL_CAPACITY = 100;

  /** The current capacity of the array */
  private int capacity = INITIAL_CAPACITY;

  /** The current size of the array (number of directory entries) */
  private int size = 0;

  /** The array to contain the directory data */
  private DirectoryEntry[] theDirectory =
      new DirectoryEntry[capacity];

  /** The data file that contains the directory data */
  private String sourceName = null;

  /** Boolean flag to indicate whether the directory was
      modified since it was either loaded or saved. */
  private boolean modified = false;

  /** Method to load the data file.
       pre:  The directory storage has been created and it is empty.
        If the file exists, it consists of name-number pairs
        on adjacent lines.
       post: The data from the file is loaded into the directory.
       @param sourceName The name of the data file
   */
  public void loadData(String sourceName) {
    // Remember the source name.
    this.sourceName = sourceName;
    try {
      // Create a BufferedReader for the file.
      BufferedReader in = new BufferedReader(
          new FileReader(sourceName));
      String name;
      String number;

      // Read each name and number and add the entry to the array.
      while ( (name = in.readLine()) != null) {
        // Read name and number from successive lines.
        if ( (number = in.readLine()) == null) {
          break; // No number read, exit loop.
        }
        // Add an entry for this name and number.
        add(name, number);
      }

      // Close the file.
      in.close();
    }
    catch (FileNotFoundException ex) {
      // Do nothing � no data to load.
      return;
    }
    catch (IOException ex) {
      System.err.println("Load of directory failed.");
      ex.printStackTrace();
      System.exit(1);
    }
  }

  /** Add an entry or change an existing entry.
      @param name The name of the person being added or changed
      @param number The new number to be assigned
      @return The old number or, if a new entry, null
   */
  public String addOrChangeEntry(String name, String number) {
    String oldNumber = null;
    int index = find(name);
    if (index > -1) {
      oldNumber = theDirectory[index].getNumber();
      theDirectory[index].setNumber(number);
    }
    else {
      add(name, number);
    }
    modified = true;
    return oldNumber;
  }

  /** Look up an entry.
    @param name The name of the person
    @return The number. If not in the directory, null is returned
   */
  public String lookupEntry(String name) {
    int index = find(name);
    if (index > -1) {
      return theDirectory[index].getNumber();
    }
    else {
      return null;
    }
  }

  /** Method to save the directory.
      pre:  The directory has been loaded with data.
      post: Contents of directory written back to the file in the
            form of name-number pairs on adjacent lines.
            modified is reset to false.
   */
  public void save() {
    if (modified) { // If not modified, do nothing.
      try {
        // Create PrintWriter for the file.
        PrintWriter out = new PrintWriter(
            new FileWriter(sourceName));

        // Write each directory entry to the file.
        for (int i = 0; i < size; i++) {
          // Write the name.
          out.println(theDirectory[i].getName());
          // Write the number.
          out.println(theDirectory[i].getNumber());
        }

        // Close the file and reset modified.
        out.close();
        modified = false;
      }
      catch (Exception ex) {
        System.err.println("Save of directory failed");
        ex.printStackTrace();
        System.exit(1);
      }
    }
  }

  /** Find an entry in the directory.
      @param name The name to be found
      @return The index of the entry with the requested name.
              If the name is not in the directory, returns -1
   */
  private int find(String name) {
    for (int i = 0; i < size; i++) {
      if (theDirectory[i].getName().equals(name)) {
        return i;
      }
    }
    return -1; // Name not found.
  }

  /** Add an entry to the directory.
      @param name The name of the new person
      @param number The number of the new person
   */
  private void add(String name, String number) {
    if (size >= capacity) {
      reallocate();
    }
    theDirectory[size] = new DirectoryEntry(name, number);
    size++;
  }

  /** Allocate a new array to hold the directory. */
  private void reallocate() {
    capacity *= 2;
    DirectoryEntry[] newDirectory = new DirectoryEntry[capacity];
    System.arraycopy(theDirectory, 0, newDirectory, 0,
                     theDirectory.length);
    theDirectory = newDirectory;
  }

   /**** BEGIN EXERCISE ****/
  /** Remove an entry from the directory.
   *  @param name - The name of the person to be removed.
   *  @return The current number. If not in directory, null is returned.
   */
  public String removeEntry(String name) {
      int index = find(name);
      if (index < size) {
          String returnValue = theDirectory[index].getNumber();
          remove(index);
          modified = true;
          return returnValue;
      } else {
          return null;
      }
  }

  /** Remove an entry from the directory
   *  @param index The index of the item to be removed
   */
  private void remove(int index) {
    for (int i = index; i < size - 1; i++) {
      theDirectory[i] = theDirectory[i + 1];
    }
    --size;
  }
  /**** END EXERCISE ****/
}
