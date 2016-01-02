
public class polyDriver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int [] coefficients = {1, 0, 0, 4};
		int [] newP = {1,3,2,5,1};
		Polynomial myPoly = new Polynomial(coefficients);
		System.out.println(myPoly + "\n");
		
		Polynomial polyP = new Polynomial(newP);
		
		Polynomial q = polyP.add(myPoly);
		System.out.println(q + "\n");
		
		q = polyP.subtract(myPoly);
		System.out.println(q + "\n");
		
		
	}

}
