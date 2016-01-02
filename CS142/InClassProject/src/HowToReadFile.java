import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class HowToReadFile {
	
	public static void main(String[] args ) throws IOException 
	
	{
		readFile();
		
	}
		
		public static void readFile() throws IOException{
			
			
			FileReader fr = new FileReader("words.rtml"); 
			BufferedReader br = new BufferedReader(fr); 
			String s; 
			while((s = br.readLine()) != null) { 
			System.out.println(s); 
			} 
			//fr.close(); 
			} 
			
			
		}
		
	


