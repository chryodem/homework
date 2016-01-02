import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class Practice {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		FileReader fr = new FileReader("parseexample.txt");
		BufferedReader br = new BufferedReader(fr);
		
		String s;
		
		while((s = br.readLine()) != null){
			String[] tokens = s.split("-");
			for (String t : tokens){
				
				System.out.println(t);
			}
			
			
		}
		fr.close ();
		

	}

}
