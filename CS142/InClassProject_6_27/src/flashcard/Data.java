package flashcard;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Data {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {

		
		Student student1 = new Student ("Nicky");
		
		
		//String [] s = new String [100];
 
		int [] intArray;
 
		//intArray = new int [10];
 
		//intArray [1] = 500; //second value in the array 
		
		String[] names = {"alice", "mary", "bob"};

//		same thing
//		new String[3];		
//		names[0]= "alice";
//		names [1]= "mary";
//		names [2]="bob";
//		
		
		
 
		Student [] students = new Student[3];
		for (int i=0; i<3 ; i++)
		{
		//students[i] = new Student(names[i]);
		System.out.println(names[i]);
			
		}
		
		
		//students [0]= new Student ("Bob");
		//students [1]= new Student ("John");
		
		//System.out.println(students[0].getName());
		//System.out.println(students[1].getName());
		
		
		
 
 
		student1.getName();
 
	
		
		
		
	}
}
	






