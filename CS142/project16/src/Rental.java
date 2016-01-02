//Rental has members called currentValue, purchasePrice, monthlyRent, monthsRented, and repairCost.
public class Rental implements Investment {
	int currentValue;
	int purchasePrice;
	int monthlyRent;
	int monthsRented;
	int repairCost;
	int profit;

	public Rental(int pP, int mR, int mRe, int rC, int cV){
		currentValue=cV;
		monthlyRent=mR;
		monthsRented=mRe;
		purchasePrice=pP;
		repairCost=rC;

	}
	public void profit(){
		profit = purchasePrice - monthlyRent * monthsRented - repairCost;
		System.out.println("Profit on the rentals is: " + profit);
	}

	public int getCurrentValue(){
		return currentValue;
	}
	public int getPurchasePrice(){
		return purchasePrice;
	}
	public int getMonthlyRent(){
		return monthlyRent;
	}
	public int getRepairCost(){
		return repairCost;
	}
	public int getMonthsRented(){
		return monthsRented;
	}
	public int getProfit(){
		return profit;
	}

}
