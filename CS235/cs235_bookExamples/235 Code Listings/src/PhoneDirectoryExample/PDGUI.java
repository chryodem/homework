package PhoneDirectoryExample;

import javax.swing.JOptionPane;

/** This class is an implementation of PDUserInterface
 *   that uses JOptionPane to display the menu of command choices.
 *   @author Koffman & Wolfgang
 */

public class PDGUI
    implements PDUserInterface {

  /** A reference to the PhoneDirectory object to be processed.
      Globally available to the command-processing methods.
   */
  private PhoneDirectory theDirectory = null;

  // Methods
  /** Method to display the command choices and process user
      commands.
      pre:  The directory exists and has been loaded with data.
      post: The directory is updated based on user commands.
      @param thePhoneDirectory A reference to the PhoneDirectory
             to be processed
   */
  public void processCommands(PhoneDirectory thePhoneDirectory) {

    String[] commands = {
        "Add/Change Entry",
        "Look Up Entry",
        "Remove Entry",
        "Save Directory",
        "Exit"};

    theDirectory = thePhoneDirectory;
    int choice;

    do {
      choice = JOptionPane.showOptionDialog(
          null, // No parent
          "Select a Command", // Prompt message
          "PhoneDirectory", // Window title
          JOptionPane.YES_NO_CANCEL_OPTION, // Option type
          JOptionPane.QUESTION_MESSAGE, // Message type
          null, // Icon
          commands, // List of commands
          commands[commands.length - 1]); // Default choice
      switch (choice) {
        case 0:
          doAddChangeEntry();
          break;
        case 1:
          doLookupEntry();
          break;
        case 2:
          doRemoveEntry();
          break;
        case 3:
        case 4:
          doSave();
          break;
        default: // Do nothing.
      }
    }
    while (choice != commands.length - 1);
    System.exit(0);
  }

  /** Method to add or change an entry.
      pre:  The directory exists and has been loaded with data.
      post: A new name is added, or the value for the name is
            changed, modified is set to true.
   */
  private void doAddChangeEntry() {
    // Request the name
    String newName = JOptionPane.showInputDialog("Enter name");
    if (newName == null) {
      return; // Dialog was cancelled.
    }
    // Request the number
    String newNumber = JOptionPane.showInputDialog("Enter number");
    if (newNumber == null) {
      return; // Dialog was cancelled.
    }
    // Insert/change name-number
    String oldNumber = theDirectory.addOrChangeEntry(newName,
        newNumber);
    String message = null;
    if (oldNumber == null) { // New entry.
      message = newName + " was added to the directory"
          + "\nNew number: " + newNumber;
    }
    else { // Changed entry.
      message = "Number for " + newName + " was changed "
          + "\nOld number: " + oldNumber
          + "\nNew number: " + newNumber;
    }
    // Display confirmation message.
    JOptionPane.showMessageDialog(null, message);
  }

  /** Method to look up an entry.
      pre:  The directory has been loaded with data.
      post: No changes made to the directory.
   */
  private void doLookupEntry() {
    // Request the name.
    String theName = JOptionPane.showInputDialog("Enter name");
    if (theName == null) {
      return; // Dialog was cancelled.
    }
    // Look up the name.
    String theNumber = theDirectory.lookupEntry(theName);
    String message = null;
    if (theNumber != null) { // Name was found.
      message = "The number for " + theName + " is " + theNumber;
    }
    else { // Name was not found.
      message = theName + " is not listed in the directory";
    }
    // Display the result.
    JOptionPane.showMessageDialog(null, message);
  }

  /** Method to remove an entry
       Pre:  The directory has been loaded with data.
       Post: The requested name is removed, modifed is set true
   */
  private void doRemoveEntry() {
    /**** BEGIN EXERCISE ****/
    // Request the name
    String theName = JOptionPane.showInputDialog("Enter name");
    if (theName == null) {
      return; // Dialog was canceled
    }
    // Remove the entry
    theDirectory.removeEntry(theName);
    // Display confirmaion message
    JOptionPane.showMessageDialog(null, theName
                                  + " has been removed from"
                                  + " the directory");
    /**** END EXERCISE ****/
  }

  /** Method to save the directory to the data file.
      pre:  The directory has been loaded with data.
      post: The current contents of the directory have been saved
            to the data file.
   */
  private void doSave() {
    theDirectory.save();
  }

}
