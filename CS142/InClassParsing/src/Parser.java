import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class Parser {
	public static void main (String args) throws IOException{
		
		
		Scanner scanner = new Scanner (new File("inclass.xml"));
		scanner.useDelimiter("[<>\n]");
		String temporary ="";
		
		temporary = scanner.next();
		
		if (temporary.equals("element")){
			
			while(scanner.hasNext()){
				
				temporary= scanner.next();
				
				if((scanner.next().equals("subelement"))){
					System.out.println(scanner.next());
					
				}
			}
			
	}
	}
}
