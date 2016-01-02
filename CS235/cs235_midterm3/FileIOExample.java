import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;


public class FileIOExample
{

	/**
	 * The following document demonstrates how to read from and write to files. It is not
	 * recommended that you use this file directly in any manner; it is merely provided as an
	 * example.
	 */
	public static void main(String[] args)
	{
		//Example text
		String text = "This is my output to a file!";
		
		//Writing to a file
		try
		{
			PrintWriter out = new PrintWriter(new File("example_io.txt"));
			out.println("Output: " + text);	//Functions like System.out.println(), but to a file
			out.close();	//This line is vital!
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Could not create file");
		}
	
		//Reading from a file
		try
		{
			Scanner in = new Scanner(new File("example_io.txt"));
			while(in.hasNext())
			{
				String input = in.next();
				System.out.println("Input: [" + input + "]");
			}
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Could not find file");
		}
	}

}
