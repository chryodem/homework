
import java.util.Random;

public class ErrorsAndGoodCode {
	public static void main(String[] args) {
		// ///////////////////////////////////////////////////////////////
		// Block 1: setting a counter for heads and total flips
//
//		int totalFlips = 0;
//		int randFlips = 0;
//
//		Random rand1 = new Random();
//		// Random rand2 = new Random();
//
//		while (totalFlips < 1000) {
//
//			if (rand1.nextInt() < rand1.nextInt()) {
//				randFlips++;
//			}
//			totalFlips++;
//		}
//
//		// Block 2: Print out total flips and number of heads
//		System.out.println("the number of total runs is " + totalFlips);
//		// System.out.println(" "+totalFlips);
//		System.out.println("the number of random runs is " + randFlips);
//		// System.out.println(" "+randFlips);

	}
	
	public static void runTimeError(){
		String[] strings = {"bob","had","a","cat"};
		System.out.println(strings[4]); //error here
	}
}
