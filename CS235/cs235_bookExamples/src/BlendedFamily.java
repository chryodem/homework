/*<listing chapter="A" number="5">*/

import java.util.Scanner;

/** A class to count and display children in a blended family.
 *   @author Koffman and Wolfgang
 * */
public class BlendedFamily {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("For your blended family, \nenter the wife's name: ");
        String wife = sc.nextLine();
        System.out.print("Enter the number of her children: ");
        int herKids = sc.nextInt();

        System.out.print("Enter the husband's name: ");
        sc.nextLine(); // Skip over trailing newline character.
        String husband = sc.nextLine();
        System.out.print("Enter the number of his children: ");
        int hisKids = sc.nextInt();

        System.out.println(wife + " and " + husband + " have "
                + (herKids + hisKids) + " children.");
    }
}
/*</listing>*/
