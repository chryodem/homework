package PriesthoodHolder;

import java.util.Random;

public abstract class PriesthoodHolder 
{
	protected String name;
	protected int age;
	protected boolean isWorthy;
	protected String tieColor;
	protected int spirituality;
	
	//constructor 1
	public PriesthoodHolder(String givenName, int givenAge, String givenTieColor)
	{
		name = givenName;
		age = givenAge;
		isWorthy = true;
		tieColor = givenTieColor;
		spirituality = 50;
		
	}
	//constructor 2
	public PriesthoodHolder()
	{
		
		Random rand = new Random();
		name = "Peter Priesthood";
		age = rand.nextInt(39) + 12 ; //12 and 50, biggest number can generate is 38
		isWorthy = true;
		tieColor = "blue";
		spirituality = 50;
		
	}
	
	public String getName(){
		return name;
	}
	
	public void attendMeeting(){
		spirituality +=5;
	}
	public void sin(){
		spirituality -=5;
	}
	
	public String toString(){
		return "Name" + name + "\n" +
				"Age" + age + "\n" +
				"Worthy" + isWorthy + "\n" +
				"Color" + tieColor + "\n" +
				"Spirituality" + spirituality + "\n";
		
		
		
	}
	
	public abstract void performDuty(){
		
	}

}
