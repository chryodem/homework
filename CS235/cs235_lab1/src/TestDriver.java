
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestDriver {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		
		Arena arena = new Arena();
		
		//System.out.println(arena.getTests()[1]);
		//*
		Character myRobot = new Robot("Me",'R', 100, 20, 2, 3);
		//Character myCleric = new Robot("Luke", 100, 6, 18,10);
		
		Character myCleric = new Cleric("Father",'C', 90,4,12,20);
		
		Character c1 = new Robot("C3p0",'R', 100,50,7,1);
		Character c2 = new Archer("THE_MAN",'A',100,6,18,1);
		
		Battle.fight(c2, myRobot);
		
		/*
		System.out.println("Please enter your characters for fight");
		System.out.println("Format Name, Type, HP, Strength,Speed,Magic: ");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); //creates an instance of reader

		String valueToConvert = null; //string where the response will be stored

		valueToConvert=br.readLine();
		
		arena.addCharacter(valueToConvert);
		
		System.out.println("Do you wanna add another character: Y|N");
		
		String answer=null;
		
		answer=br.readLine();
		System.out.println("your answer was: " + answer);
		
		char myAnswer = answer.charAt(0);
		
		switch(myAnswer)
		{
		case 'Y':
			System.out.println("Please enter your characters for fight");
			System.out.println("Format Name, Type, HP, Strength,Speed,Magic: ");
			valueToConvert=br.readLine();
			//System.out.println("new value to convert is: " + valueToConvert);
			arena.addCharacter(valueToConvert);
			//System.out.println("You have this character in your array: " + arena.inArena.get(0).name);
			//System.out.println("You have this character in your array: " + arena.inArena.get(1).name);
			Battle.fight(arena.inArena.get(0), arena.inArena.get(1));
			int characters = arena.getSize();
			System.out.println("characters after fight is: " + characters);
			
			System.out.println(arena.inArena.size());
			for(int i =0; i < arena.inArena.size();i++)
			{
				System.out.println(arena.inArena.get(i).name);
			}
			System.out.println("which characted would you like info on: ");
			String getCharInfo  = br.readLine();
			arena.getCharacter(getCharInfo);
			
			System.out.println("which character would you like to remove: ");
			String charToRemove = br.readLine();
			arena.removeCharacter(charToRemove);
			int charLeft = arena.getSize();
			System.out.println("characters left in arena: " + charLeft);
			
			
			break;
		case 'N':
			System.out.println("No more characters = no battle!");
			break;
			
		}
		*/
		
	} // end of main

}
