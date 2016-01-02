import java.util.ArrayList;


public class Duck implements DuckInterface {
	
	String [] jewishName = {"Reuben", "Elizur", "Shedeur", "Simeon", "Shelumeil", "Koppel", "Lavi", "Maayan", "Neta", "Ofek", 
			"Rani", "Zurishaddai", "Judah", "Nashlon", "Aminadab", "Issachar", "Nethaneel", "Zuar", "Zebulum", "Josephus"};

	//protected ArrayList<String> names = new ArrayList<String>();
	public ArrayList<String> circles = new ArrayList<String>();
	
	public ArrayList<String> killed = new ArrayList<String>();
	public ArrayList<String> originalCircles = new ArrayList<String>();
	public ArrayList<String> originalIndex = new ArrayList<String>();
	
	int temp = 0;
	int adjustment = 0;
	int i =0;
	
	@Override
	public ArrayList<String> getNames() {
		ArrayList<String> names = new ArrayList<String>();//creates new list of names
		/*
		for(int a=names.size()-1; a>=0;a--){
			names.remove(a);
		}
		*/
		for (int a = 0; a<jewishName.length;a++){ 
			names.add(jewishName[a]);//populates the list of names
		}
		
		return names;
	}

	@Override
	public ArrayList<String> playGame(int n, int m) {
		
        //reset the variables
        temp =0;
		adjustment = 0;
		i = 0;
        
		//reset the lists
		circles = new ArrayList<String>();
		originalCircles = new ArrayList<String>();
		killed = new ArrayList<String>();
        
        //create a kill list
		for (int i=0; i < n; i++){
			circles.add(jewishName[i]);
			originalCircles.add(jewishName[i]);
		}
		//while there's someone left kill him	
		while(circles.size()>0){
		kill(n,m-1); //method called for killing
		temp++;
		adjustment= i - circles.size(); //adjustment after going through the circle
		}
		
		return killed;
	}
	
	private void kill(int n, int m) { //kills people in order
		
        //for cases when m is larger than n
        // do division to find where to start going through the list the first time
		if(n<m){
			double remainder = m/n;
			
			int thisRemainder = (int) remainder;
			i = thisRemainder;
		}
		
		i = 0;
		if(temp ==0 ){ //if this is a first time going through the list...
			i = m;
		}
		else{
			i = adjustment; //or use the adjustment variable for the next round
		}
		
		
		while(i<circles.size()){
			
			killed.add(circles.get(i));
			circles.remove(i);
			i+=m;
		}
		
	}

	@Override
	public int reportSafeIndex(int n, int m) {
		//crear the variable
        
        temp = 0;
		adjustment = 0;
		i=0;
		//call the constructors again
		circles = new ArrayList<String>();
		originalIndex = new ArrayList<String>();
		killed = new ArrayList<String>();
		
		for(int a=circles.size()-1; a>=0;a--){
			circles.remove(a);
		}
		for(int a=originalIndex.size()-1; a>=0;a--){
			originalIndex.remove(a);
		}
		for(int a=killed.size()-1; a>=0;a--){
			killed.remove(a);
		}
			
		for (int i=0; i < n; i++){
			circles.add(jewishName[i]);
			originalIndex.add(jewishName[i]);
		}
			
		while(circles.size()>0){
		kill(n,m-1);
		temp++;
		adjustment= i - circles.size();
		}
		
		
		String savedMan = killed.get(killed.size()-1);
		
		kill(n,m-1);
		int safe=0;
		
		for(int j = 0; j < originalIndex.size();j++){
			if(originalIndex.get(j).equals(savedMan)){
				//System.out.println("Safe index is: " + j);
				safe = j;
			}
		}
		return safe;
	}

}