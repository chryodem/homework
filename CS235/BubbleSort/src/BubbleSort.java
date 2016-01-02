//This project uses double randomisation, array size is random, values in the array are random as well.

import java.util.Random;

class BubbleSort
{
	public static void main(String arg[])
	{
		int arrayLength = 20;//length of the array
		int rangeOfRandomNumbers = 5;//sets the range in which random numbers will be picked
		Random rand = new Random(); 
		int[] starting_numbers = new int[rand.nextInt(arrayLength)]; //randomly choses the array size
		System.out.print("The starting numbers are :");
		for(int i=0; i<starting_numbers.length; i++){ 
			starting_numbers[i] = rand.nextInt(rangeOfRandomNumbers); //populates the array with random number in the given range
			System.out.print(" " + starting_numbers[i] + "\t"); //shows the random values
		} // end of for loop
		for (int i=starting_numbers.length; --i>=0;) //goes through the array
		{
			for (int j =0; j<i;j++){
				
				if (starting_numbers[j]>starting_numbers[j+1]) //compares the values in the array
				{
					int temp = starting_numbers[j];//moves the value into a temp variable
					starting_numbers[j]= starting_numbers[j+1]; //replaces the values
					starting_numbers[j+1]= temp;
				}
			}
			
		}
		System.out.println("\n");
		System.out.print("And numbers after sorting: ");
		for(int i =0; i<starting_numbers.length;i++){
			System.out.print(starting_numbers[i] + "\t");
		}
	}
}

