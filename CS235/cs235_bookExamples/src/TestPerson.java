/*<listing chapter="A" number="2">*/
/** TestPerson is an application that tests class Person.
 *  @author Koffman and Wolfgang
 **/
public class TestPerson {

    public static void main(String[] args) {
        Person p1 = new Person("Sam", "Jones", "1234", 1930);
        Person p2 = new Person("Jane", "Jones", "5678", 1990);
        System.out.println("Age of " + p1.getGivenName()
                + " is " + p1.age(2004));
        if (p1.isSenior(2004)) {
            System.out.println(p1.getGivenName()
                    + " can ride the subway for free");
        } else {
            System.out.println(p1.getGivenName()
                    + " must pay to ride the subway");
        }

        System.out.println("Age of " + p2.getGivenName()
                + " is " + p2.age(2004));
        if (p2.canVote(2004)) {
            System.out.println(p2.getGivenName() + " can vote");
        } else {
            System.out.println(p2.getGivenName() + " can't vote");
        }
    }
}
/*</listing>*/
