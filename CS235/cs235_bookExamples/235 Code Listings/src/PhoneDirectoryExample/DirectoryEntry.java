package PhoneDirectoryExample;

/** The DirectoryEntry contains the name and number, both
 *  stored as strings. The name is not changable.
 *  @author Koffman & Wolfgang
 */

public class DirectoryEntry {
  /**** BEGIN EXERCISE ****/
  // Data fields
  /** The name of the person in the directory entry.
   *  The name is not changeable
   */
  private final String name;

  /** The phone number for the person.
   */
  private String number;

  // Constuctors
  /** Construct a new DirectoryEntry with the given
   *  name and numer.
   *  @param name The name of the person
   *  @param number The initial number of the person
   */
  public DirectoryEntry(String name, String number) {
    this.name = name;
    this.number = number;
  }

  // Accessors
  /** Retrieve the value of the name
   *  @return the value of the name
   */
  String getName() {
    return name;
  }

  /** Retrieve the value of the number
   *  @return the value of the number
   */
  String getNumber() {
    return number;
  }

  // Mutators
  /** Change the value of the number
   *  @param number - The new value of the number
   */
  void setNumber(String number) {
    this.number = number;
  }

  // Other methods
  /** Compare for equality. Equality is based only on the
   *  name field.
   *  @param o - The other object
   *  @return true If the objects are equal or if the other
   *  object is a String equal to the name.
   */
  public boolean equals(Object o) {
    if (o instanceof DirectoryEntry) {
      DirectoryEntry e = (DirectoryEntry) o;
      return name.equals(e.name);
    }
    else {
      return false;
    }
  }

  /** Compute a hash code that is consisten with the equals
   *  method.
   *  @return hashCode of the name
   */
  public int hashCode() {
    return name.hashCode();
  }
  /**** END EXERCISE ****/
}

