package PriesthoodHolder;

public class Deacon extends PriesthoodHolder {
	
	public Deacon(String givenName, int givenAge, String givenTieColor){
		
		super(givenName,givenAge,givenTieColor);
		System.out.println("Deacon constructor");
	}

	public void performDuty(){
		
	}
	public void passTheSacrament(){
		System.out.println(name + "just passed the sacrament");
		if(isWorthy){
			spirituality++;
		}
		else
			spirituality--;
	}
}
