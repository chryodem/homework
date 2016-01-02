/*<example chapter="8" number="3">*/
package CH08;

import java.util.Comparator;

/** A class to compare Person objects
 *  @author Koffman and Wolfgang
 */
public class ComparePerson implements Comparator<Person> {

    /** Compare two Person objects based on birth date.
    @param left The left-hand side of the comparison
    @param right The right-hand side of the comparison
    @return A negative integer if the left person�s birthday
            precedes the right person�s birthday;
            0 if the birthdays are the same;
            a positive integer if the left person�s birthday
            follows the right person�s birthday.
     */
    @Override
    public int compare(Person left, Person right) {
        return left.getBirthDay() - right.getBirthDay();
    }
}
/*</example>*/
