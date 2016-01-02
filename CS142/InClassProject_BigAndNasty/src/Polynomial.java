public class Polynomial {
	int[] coefficients;
	public Polynomial(int[] coeff){
		coefficients = coeff;
	}
	public String toString(){
		String output = "";
		for(int i=coefficients.length-1; i>=0; i--){
			//if(coefficients[i]>=0 ){
				if (i<coefficients.length-1 || coefficients[i]<coefficients.length-1 && coefficients[i]>0){
					output+= "+";
				}
				
		//	}
			output += coefficients[i];
			if(i!=0){
				output += "x" + "^" + i + " " ;
			}
		}
	
	return output;
}
	public Polynomial add(Polynomial p){
		
		int[] newP;
		
		int[] storage;
		
		int arrayLength;
		if (p.coefficients.length>coefficients.length)
		{
			arrayLength = p.coefficients.length;
			storage = new int[arrayLength];
			for(int i=coefficients.length-1; i>=0; i--)
			{
				storage[i]+=coefficients[i];
			}
			for(int i=arrayLength-1; i>coefficients.length-1; i--)
			{
				storage[i]= 0;
			}
		}
		
		else
		{
			arrayLength = coefficients.length;
			storage = new int[arrayLength];
			for(int i=p.coefficients.length-1; i>=0; i--)
			{
				storage[i]+=p.coefficients[i];
			}
			for(int i=arrayLength-1; i>p.coefficients.length-1; i--)
			{
				storage[i]= 0;
			}
		}
		newP = new int[arrayLength];
		for(int i=coefficients.length-1; i>=0; i--)
		{
			newP[i]+=storage[i];
		}
		Polynomial q = new Polynomial(newP);
		return q;
	}
public Polynomial subtract(Polynomial p){
	int[] storage;
	
	int arrayLength;
	if (p.coefficients.length>coefficients.length)
	{
		arrayLength = p.coefficients.length;
		storage = new int[arrayLength];
		for(int i=coefficients.length-1; i>=0; i--)
		{
			storage[i]+=coefficients[i];
		}
		for(int i=arrayLength-1; i>coefficients.length-1; i--)
		{
			storage[i]= 0;
		}
	}
	
	else
	{
		arrayLength = coefficients.length;
		storage = new int[arrayLength];
		for(int i=p.coefficients.length-1; i>=0; i--)
		{
			storage[i]+=p.coefficients[i];
		}
		for(int i=arrayLength-1; i>p.coefficients.length-1; i--)
		{
			storage[i]= 0;
		}
	}
		int[] newP = new int[arrayLength]; //new Array newP
		
		for(int i=coefficients.length-1; i>=0; i--)
		{
			newP[i]-=storage[i];
		}
		Polynomial q = new Polynomial(newP);
		return q;
	}
	
	public int[] getCoefficients (){
		return coefficients;
	}
	public void setP(int[] I){
		coefficients=I;
	}
}
