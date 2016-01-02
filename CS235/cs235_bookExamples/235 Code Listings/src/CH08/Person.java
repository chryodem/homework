/*<example chapter="8" number="2">*/

package CH08;

/** A class to represent a person.
 *  @author Koffman and Wolfgang
 */
public class Person implements Comparable<Person> {
  // Data Fields
  /* The last name */
  private String lastName;

  /* The first name */
  private String firstName;

  /* Birthday represented by an integer from 1 to 366 */
  private int birthDay;

  // Methods
  /** Compares two Person objects based on names. The result
      is based on the last names if they are different;
      otherwise, it is based on the first names.
      @param obj The other Person
      @return A negative integer if this person�s name
              precedes the other person’s name;
              0 if the names are the same;
              a positive integer if this person�s name follows
              the other person’s name.
   */
  @Override
  public int compareTo(Person obj) {
    Person other = obj;
    // Compare this Person to other using last names.
    int result = lastName.compareTo(other.lastName);
    // Compare first names if last names are the same.
    if (result == 0)
      return firstName.compareTo(other.firstName);
    else
      return result;
  }

  // Other methods
  /** Gets the person's year of birth
   * @return the year of birth as an int value
   */
  public int getBirthDay() {
    return birthDay;
  }

}
/*</example>*/