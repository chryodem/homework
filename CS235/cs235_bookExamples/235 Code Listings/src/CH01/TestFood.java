/*<example chapter="1" number="5">*/
/**
   Example 1.5
   @author Koffman and Wolfgang
*/

package CH01;

public class TestFood {

    public static void main(String[] args) {

	Food mySnack = new Vegetable("carrot sticks");
	System.out.println("Total Calories: " + mySnack.getCalories());
	System.out.println("Percent fat: " + mySnack.percentFat());
	System.out.println("Percent protein: " + mySnack.percentProtein());
	System.out.println("Percent carbohydrates: " + mySnack.percentCarbohydrates());
    }
}
