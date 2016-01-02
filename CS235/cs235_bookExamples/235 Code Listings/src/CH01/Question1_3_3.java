/*<exercise chapter="1" section="3" number="3" type="self-check">*/

package CH01;

public class Question1_3_3 {
    public static void main(String[] args) {
	Computer comp[] = new Computer[3];
	comp[0] = new Computer("Ace", "AMD", 3, 160, 2.4);
	comp[1] = new Notebook("Dell", "Intel", 4, 350, 2.2, 15.5, 7.5);
	comp[2] = comp[1];
	for (int i = 0; i < comp.length; i++) {
	    System.out.println(comp[i].getRamSize() +"\n" +
			       comp[i].toString());
	}
    }
}
/*</exercise>*/
