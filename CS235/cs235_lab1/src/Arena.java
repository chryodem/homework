
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class Arena implements ArenaInterface {

	Character [] charactersInArena = new Character [100];
	List<Character> inArena = new ArrayList<Character>();

	protected String name, charactersType;
	protected int healthP,sTR,sP,mAG, tokens,numberInArena=0;
	protected char characterType;


	/*
	Characters are represented as Strings when added to the arena and when stored in the tests you will create. 
	The following format is expected:
	(name) (type) (maximum hit points) (strength) (speed) (magic)
	Example: Xephos A 200 13 21 10
	 */
	@Override
	public String[] getTests() {

		String[] myTests;

		myTests = new String[5];

		myTests[0] = "Gandolf C 250 10 50 100 Optimus R 500 150 150 70 Optimus";
		myTests[1] = "DarthVaider R 250 150 150 70 LukeSkywalker C 750 200 375 150 LukeSkywalker";
		myTests[2] = "LukeSkywalker A 750 200 375 150 DarkSith A 300 150 50 10 LukeSkywalker";
		myTests[3] = "Honeydew R 150 14 8 11 Father_Braeburn C 90 4 12 20 Honeydew";
		myTests[4] = "HolyCow R 150 43 40 6 HolyCrap C 110 8 24 40 HolyCow";

		return myTests;
	}

	@Override
	public boolean addCharacter(String info) {
		if(info==null){
			return false;
		}

		StringTokenizer tok = new StringTokenizer(info);//initiates tokanizer
		
		try{

		name = tok.nextToken();

		//checks to make sure the name is unique
		if(numberInArena>0) //number of characters in arena, start with 0, so no need to check then
		{
			for(int i=0; i<numberInArena;i++) //goes through all the characters created
			{
				if(inArena.get(i).name.equals(name)) //checks this array to make sure there's not a character with the same name
				{
					return false;//stops the execution
				}
				//keeps going through the execution
			} // end of for loop
		} // end of if statement

		charactersType=tok.nextToken(); //to convert from String to character
		if(charactersType.length()>1)
		{
			return false;
		}
		else{
			characterType=charactersType.charAt(0);
		if(characterType != 'R' && characterType!='A' && characterType!='C')
		{
			return false;
		}
		}

		healthP=Integer.parseInt(tok.nextToken().trim());
		
		if(healthP<0 || healthP==0)
		{
			return false;
		}

		sTR = Integer.parseInt(tok.nextToken().trim());
		if(sTR<0 || sTR == 0)
		{
			return false;
		}
		sP=Integer.parseInt(tok.nextToken().trim());
		if(sP<0 || sP==0)
		{
			return false;
		}

		mAG=Integer.parseInt(tok.nextToken().trim());
		if(mAG<0 || mAG ==0)
		{
			return false;
		}
		}//end of try
		
		//catches to make sure all the stats are entered correctly
		catch (NoSuchElementException E) {
			return false;
			// TODO: handle exception
		}
		//catches to make sure the input is of the correct type
		catch (NumberFormatException e){
			return false;
		}
		catch (Exception e){
			return false;
		}

		switch(characterType)
		{
		case 'A':
			inArena.add(new Archer(name,characterType,healthP,sTR,sP,mAG));
			numberInArena++;
			return true;


		case 'R':
			inArena.add(new Robot(name,characterType,healthP,sTR,sP,mAG));
			numberInArena++;
			return true;

		case 'C':
			inArena.add(new Cleric(name,characterType,healthP,sTR,sP,mAG));
			numberInArena++;
			return true;

		default:
			return false;
		} //assigns the values and creates appropriate characters if the type is valid
	}

	@Override
	public boolean removeCharacter(String name) {
		String charName = name;
		for(int i = 0; i<inArena.size();i++) //goes through all the characters created
		{
			if(inArena.get(i).name.equals(charName)) //checks this array to make sure there's not a character with the same name
			{
				inArena.remove(i);
				numberInArena--;
				return true;
			}
		}

		return false;
	}

	@Override
	public CharacterInterface getCharacter(String name) {
		String charInfo = name; //gets the name of the char

		for(int i = 0; i<inArena.size();i++) //goes through all the characters created
		{
			if(inArena.get(i).name.equals(charInfo)) //checks this array to make sure there's not a character with the same name
			{

			/*	System.out.println(inArena.get(i).name + "\t" +
						inArena.get(i).characterType + "\t" +
						inArena.get(i).MaxHP + "\t" +
						inArena.get(i).Speed + "\t" +
						inArena.get(i).Magic + "\t"+
						inArena.get(i).Strength);*/
				return inArena.get(i);		
			}
			//keeps going through the execution
		} // end of for loop
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getSize() {
	int size = inArena.size();
	return size;
	}

}
