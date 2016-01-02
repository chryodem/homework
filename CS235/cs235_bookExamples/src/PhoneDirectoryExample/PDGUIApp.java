package PhoneDirectoryExample;

/** Program to display and modify a simple phone directory. */
public class PDGUIApp {
    
    // The main method

    /** This method does the following:
     *  1) Verify that there is a argument, 
     *     the file containing the directory, or a data source.
     *  2) Creates a phone directory object.
     *  3) Calls the phone directory to load the file
     *     or connect to the data source.
     *  4) Creates an interface object.
     *  5) Calls the interface object to process user commands.
     *  6) On return from the interface object, calls the
     *     phone directory object to save the file if
     *     necessary, and/or close the data source.
     */
    public static void main (String args[]) {
        // check to see that there is a command line argument
        if (args.length == 0) {
            System.err.println("You must provide the name of the file"
                               + " that contains the phone directory.");
            System.exit(1);
        }
        // create a PhoneDirectory object
        PhoneDirectory phoneDirectory = new ArrayBasedPD();
        // load the phone directory from the file
        phoneDirectory.loadData(args[0]);
        // create a PhoneDirectoryUserInterface object
        PDUserInterface phoneDirectoryInterface = 
            new PDGUI();
        // process user commands
        phoneDirectoryInterface.processCommands(phoneDirectory);
    }
}
