
public class Stock implements Investment {
	int currentValue;
	int purchasePrice;
	int profit;
	
	public Stock(int cV, int cP)
	{
		currentValue=cV;
		purchasePrice = cP;
	}
	public void profit(){
		 profit = purchasePrice - currentValue;
		 System.out.println("Profit on the stock is: " + profit);
		 
	}
	
	public int getCurrentValue(){
		return currentValue;
	}
	public int getPurchasePrice(){
		return purchasePrice;
	}


}
