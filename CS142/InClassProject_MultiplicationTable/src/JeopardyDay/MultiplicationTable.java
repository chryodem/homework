package JeopardyDay;
// I don't know what the hell I am doing...

public class MultiplicationTable {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		int firstValue = 1;

		for (int i = 1; i < 11; i++) 
		{

			for (int j = 1; j < 11; j++) 
			{

				firstValue = i * j;

				System.out.print(firstValue + "\t");
			}

			System.out.println("");

		}

		// TODO Auto-generated method stub

	}

}
