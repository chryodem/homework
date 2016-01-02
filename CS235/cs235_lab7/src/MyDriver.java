import java.util.ArrayList;


public class MyDriver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Mimic mimic = new Mimic();
		
		String str = "I like apples. I like bananas.";
		//String str1 = "Spot run!";
		
		mimic.createMap(str);
		
		System.out.println(mimic.generateText());
		
		
//		ArrayList<Integer> ints = new ArrayList<Integer>();
//		
//		ints.add(1);
//		ints.add(2);
//		System.out.println(ints.size());
		
	//	System.out.println(mimic.getSuffixList(str1));
		
		
		
		// TODO Auto-generated method stub

	}

}
