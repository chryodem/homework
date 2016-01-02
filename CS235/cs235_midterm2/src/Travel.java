/*
 * Name: Andrei Y Rybin
 * Student ID: 609676182
 * CS 235 Mid Term 2
 * 
 * For this midterm I've used several different algorithms in different parts of the program
 * 
 * Part1 - loadFlights
 * a. First I parse in the file into an array list which is done in O(n)
 * b. Then the array list is separated into two pieces: cities and digits which is done in O(n)
 * c. After splitting the array list there is a check to make sure it contains valid symbols in O(1)
 * d. Cities ArrayList is built in O(n)
 * e. Digits String[][] is built in O(n)
 * 
 * Overall efficiency for part1 is O(n)
 * Best case: O(n)
 * Average: O(n)
 * Worse case: O(n)
 * 
 * Part2 - reportCost
 * 
 * a.Get indexes of the departure and destination strings, done in O(n)
 * b. Comparing indexes to produce results O(1)
 * 
 * Overall efficiency for part2 is O(n)
 * Best case: O(1)
 * Average: O(n)
 * Worse case: O(n)
 * 
 * Part3 - developItinerary
 * 
 * a. Get indexes of the origin and destination, O(n)
 * b. Comparing indexes to produce results, O(1)
 * c. If not good results found call recursion function to find the path, O(n^2)
 * d. Then calculate the cost done in, O(n)
 * e. Build a string of cities including the cost at the end of the string O(n)
 * 
 * Overall efficiency for part3 is O(n)
 * 
 * Best case: O(1)
 * Average: O(n)
 * Worse case: O(2^n) //for recursion
 * 
 */


import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;


public class Travel implements TravelInterface {

	private ArrayList<String> inputFileParsed, cities;
	private String[][] myMaze;
	private boolean [][] path;
	private boolean traversability;
	private Stack<String> stackX, stackY;
	
	
	@Override
	public boolean loadFlights(String input_file_name) {
		Scanner scan;
		String nextToken;
		inputFileParsed = new ArrayList<String>();
		
		try {
			scan = new Scanner(new FileReader(input_file_name));
			while(scan.hasNext()){
				nextToken = scan.next();
				if(Character.isDigit(nextToken.charAt(0)) || nextToken.charAt(0)=='-'){
					inputFileParsed.add(nextToken);
				}
				//if character is non digit and already is in the ArrayList
				else{
					if(inputFileParsed.contains(nextToken)){
						return false;
					}
					else{
						inputFileParsed.add(nextToken);
					}
				}
			}
		} catch (Exception e) {
			return false;
		}
		int numberDigits = 0;
		for(int i = 0; i < inputFileParsed.size();i++){
			if(Character.isDigit(inputFileParsed.get(i).charAt(0)) || inputFileParsed.get(i).charAt(0)=='-'){
				numberDigits++;
			}
		}
		int arrayBuilding = inputFileParsed.size()-numberDigits; //used to define the size of the array
		
		//checks to make sure there are enough values
		if(numberDigits%arrayBuilding!=0){
			return false;
		}
		
		
		myMaze = new String [arrayBuilding][arrayBuilding];
		path = new boolean [arrayBuilding][arrayBuilding];
		cities = new ArrayList<String>();
		
		//first items are always cities
		for(int i =0; i<arrayBuilding;i++){
			cities.add(inputFileParsed.remove(0));
		}
		
		//now populate the array and clean up the array list for memory
		for(int i =0; i < myMaze.length;i++){
			for(int j =0; j < myMaze.length;j++){
				myMaze[j][i] = inputFileParsed.remove(0);
			}
		}
		
		return true;
	}

	@Override
	public String reportCost(String departure, String arrival) {
		
		int indexArrival = cities.indexOf(arrival);
		int indexDeparture = cities.indexOf(departure);
		
		if(indexArrival ==-1 && indexDeparture == -1){
			return "No Data";
		}
		else if(indexArrival == indexDeparture){
			return "Same city";
		}
		else if(indexArrival ==-1 || indexDeparture == -1){
			return "Invalid city";
		}
		
		int yPosition = 0;
		for(int i =0; i < myMaze.length;i++){
			if(myMaze[indexDeparture][i].equals("0")){
				yPosition = i;
			}
		}
		
		String result = "$"+myMaze[indexArrival][yPosition];
		if(result.equals("$-1")){
			return "No direct flight";
		}
		
		return result;
	}

	@Override
	public String developItinerary(String origin, String destination) {
		stackX = new Stack<String>();
		stackY = new Stack<String>();
		path = new boolean[myMaze.length][myMaze.length];
		
		//using this ArrayList to make sure there are no duplicate cities
		ArrayList<String> iten = new ArrayList<String>(); //use this to build itinerary
		int indexArrival = cities.indexOf(destination);
		int indexDeparture = cities.indexOf(origin);
		
		if(indexArrival ==-1 && indexDeparture == -1){
			return "No Data";
		}
		else if(indexArrival == indexDeparture){
			return "Same city";
		}
		else if(indexArrival ==-1 || indexDeparture == -1){
			return "Invalid city";
		}
		
		//get the starting coordinates of x and y
		int yPosition = 0;
		for(int i =0; i < myMaze.length;i++){
			if(myMaze[indexDeparture][i].equals("0")){
				yPosition = i;
			}
		}
		//get the answer
		String result = myMaze[indexArrival][yPosition];
		
		//if result is -1, check if there is a possible itinerary
		if(result.equals("-1")){
			
			traversability = findMazePath(indexDeparture,yPosition, indexArrival);
			//if false, no possibilities
			if(traversability == false){
				return "No possible itinerary";
			}
			//else print the possibility
			else{
				
				//build list of cities
				for (int i = 0; i < stackX.size(); i++) {
					if(i==0){
						iten.add(cities.get(Integer.parseInt(stackX.get(i))));	
					}
					//checks to make sure that the values are not the same
					if(iten.contains(cities.get(Integer.parseInt(stackX.get(i))))){
						i++;
					}
					else{
					iten.add(cities.get(Integer.parseInt(stackX.get(i))));
					}
				}
				//calculate the cost
				int cost = 0;
				for(int i = 0; i<stackX.size();i++){
					cost+= Integer.parseInt(myMaze[Integer.parseInt(stackX.get(i))][Integer.parseInt(stackY.get(i))]);
				}
				iten.add(Integer.toString(cost));
				
				//return the answer as a string
				StringBuilder sb = new StringBuilder();
				
				for(int i =0;i< iten.size();i++){
					//if it's the price
					if(i == iten.size()-1){
						sb.append("$" + iten.get(i));
					}
					//else if it's the cities
					else{
						sb.append(iten.get(i)+ " ");
					}
				}
				result = sb.toString();
			}
			
		} else{
			StringBuilder sb = new StringBuilder();
			sb.append(cities.get(indexDeparture)+ " " + cities.get(indexArrival) + " " + "$" + myMaze[indexArrival][yPosition]);
			result = sb.toString();
		}
		return result;
	}
	
	//returns the cost of the itinerary

	private boolean findMazePath(int x, int y,int x1) {
		
		//check if out of bounds
		if(x<0 || y < 0 || x> myMaze.length-1 || y> myMaze.length-1){
			return false;
		}
		//check if already had been there
		else if(path[x][y] == true){
			return false;
		}
		//check if can't fly there directly
		else if(myMaze[x][y].equals("-1")){
			return false;
		}
		//check if reached the destination
		else if(x==x1){
			path[x][y] = true;
			stackX.push(Integer.toString(x));
			stackY.push(Integer.toString(y));
			return true;
			
		}
		//find the path to destination part
		
		//first find where to start, or make sure location is 0
		//then search for a possible entry point
		//if point is -1 or marked as true come back and try again
		
		else if(myMaze[x][y].equals("0")){
			path[x][y] = true; //mark that have been there
			//save the location
			stackX.push(Integer.toString(x)); 
			stackY.push(Integer.toString(y));
			
			//start searching for possible directions in x direction
			for(int i =0; i<myMaze.length;i++){
				if(findMazePath(i,y,x1)){
					return true;
				}
			}
			return false;
		}
		
		//else if already jumped to a location find 0 in that location
		else{
			path[x][y] = true;
			//save the location
			stackX.push(Integer.toString(x)); 
			stackY.push(Integer.toString(y));
			//start searching for 0 and then jump there
			for(int i =0; i < myMaze.length;i++){
				if(myMaze[x][i].equals("0")){
					if(findMazePath(x,i,x1)){
						return true;
					}
				}
			}
			stackX.pop();
			stackY.pop();
		}
		
		//recursion needs two parts
		/*
		 * 1. searching for non -1 in the x direction
		 * for (int i = 0;i < myMaze.length;i++)
		 * {
		 * 
		 * findMazePath(i,y,x1);
		 * }
		 * 
		 * 2. jump to the non -1 location
		 * 3. find 0 in there, unless x == x1, or the place where trying to go
		 * 4. search for the next non -1 location
		 * 5. jump to the non -1 location
		 * 6. find 0 in there, unless x == x1, or the place where trying to go
		 */
		return false;
	}
}
