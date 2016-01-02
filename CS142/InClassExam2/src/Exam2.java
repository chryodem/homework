import java.util.Random;
//INSTRUCTIONS:
//This code is broken. It contains run-time (crashes when you run it) and 
//logical (the code compiles and runs but doesn't do what it is meant to) errors. 
//The comments above each section indicate a clue as to what it is supposed to do
//Fix the code for 70 pts. You will know it works there are no compile errors and the
//code outputs (your numbers will be different):
//	The starting numbers are:  -82565730, -297593534, 112646786, 220439416, -362278623
//	The doubled numbers are:  -165131460, -595187068, 225293572, 440878832, -724557246
//
//For the other 30 points, find ways to reduce the number of lines of code this program takes up.
//YOU ARE NOT ALLOWED TO:
//		remove white space currently in this file (eg blank lines)
//		combine multiple semi-colon ending lines into one like this:
//			Given:
//				int bob; 
//				int john; 
//				int bobby;
//			this is NOT okay: int bob; int john; int bobby;
//			this is okay: int bob, john, bobby;
//		use for/while/if blocks of less than three lines (so the ones already in this code are as short as they get)
//		remove any lines currently containing comments
//		modifying what the result of each block of code (eg you should have an array of random 
//			starting numbers at the end of block one, an array that contains the same values 
//			as starting numbers but doubled for block two, etc.
//You are only allowed to use more concise programming
//You get two points for every line of code you reduce while maintaining what the program does.
//You can't get over 100 for this exam
///////////////////////////////////////////////////////////////////////////////////////////
public class Exam2 {
	public static void main(String[] args){
		/////////////////////////////////////////////////////////////////////////////////
		//BLOCK 1: make an array of 6 random starting numbers and print them to the screen
		Random rand = new Random(); 
		int[] starting_numbers = new int[5];
		System.out.print("The starting numbers are: ");
		for(int i=0; i<5; i++){ 
			starting_numbers[i] = rand.nextInt(); 
		System.out.print(" " + starting_numbers[i] + "\t");
		} // end of for loop
		/////////////////////////////////////////////////////////////////////////////////
		
		/////////////////////////////////////////////////////////////////////////////////
		//BLOCK 2: print the same values as starting numbers but doubled for each value
		System.out.println("\n");
		int[] doubled_numbers = starting_numbers; //new int[number_of_doubled_numbers];
		System.out.print("The doubled numbers are: ");
		for(int i=0; i<5; i++){ 
			doubled_numbers[i]*= 2; 
		System.out.print(" " + doubled_numbers[i] + "\t");
		} //end of second for
		/////////////////////////////////////////////////////////////////////////////////
		boolean needHelp = false;
		//needHelp = true;
		if(needHelp =true)
		{
			System.out.println("Student needs help");
		}
		
	}
}