package flashcard;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Data {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		
		//creates FileReader instance, new, then initiates it, activates.
		FileReader fr = new FileReader("classes.txt");
		
		//creates BufferedReader instance, reads code line by line
		BufferedReader br = new BufferedReader(fr);
		
		//String where the file being read is stored
		String s;
		
		//while loop executes until there is nothing else to read
		while((s = br.readLine()) != null) {
			
		//prints out the results to screen	
		System.out.println(s); 
		} 
		//fr.close(); 
		} 
		
		

	

		// TODO Auto-generated method stub

	}


