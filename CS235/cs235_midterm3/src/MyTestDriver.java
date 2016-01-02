import java.util.Arrays;

public class MyTestDriver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Merge merge = new Merge();
		
	String result =	merge.sort("small.dat", 28, 6);
	System.out.println(result);
	String[] fileNames = merge.reportIntermediateFileNames();
	System.out.println(Arrays.toString(fileNames));
	
	int reads = merge.reportReadsAndWrites();
	System.out.println(reads);
	
	
	
		

	}

}
