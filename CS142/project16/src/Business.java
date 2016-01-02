//Business has members called purchasePrice, annualIncome, yearsOwned, and currentValue.
public class Business implements Investment {
	int purchasePrice;
	int annualIncome;
	int yearsOwned;
	int currentValue;
	int profit;
	
	public Business(int cV, int pP, int aI, int yO){
		currentValue=cV;
		annualIncome=aI;
		purchasePrice=pP;
		yearsOwned=yO;
		
	}
	public void profit(){
		profit = currentValue - purchasePrice + annualIncome * yearsOwned;
		System.out.println("Business profit is: " + profit);
	}
	
	public int getCurrentValue(){
		return currentValue;
	}
	public int getAnnualIncome(){
		return annualIncome;
	}
	public int getPurchasePrice(){
		return purchasePrice;
	}
	public int getYearsOwned(){
		return yearsOwned;
	}

}
