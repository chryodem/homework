/**
 * Simulates a battle between two given characters, with optional print messages
 * to assist in testing.
 */

public class Battle
{
	private static boolean VERBOSE = true;
	//-------------------------------------------------------------------------
	/**
	 * Runs through the fighting algorithm for the two given characters.
	 * 
	 * Each character takes a turn using its special ability, attacking, and
	 * regenerating. If the other character's current hit points falls to or
	 * below 0, then the other character has lost. This process repeats until
	 * one character has fallen or 100 rounds have occured with neither
	 * character falling.
	 * 
	 * You may alter the value of the [VERBOSE] constant above to help with
	 * testing, but you may not make any other changes to this code.
	 * 
	 * @param c1
	 *            the first character to fight
	 * @param c2
	 *            the second character to fight
	 * @return the winner of the fight; null if the fight ends in a draw
	 */
	public static CharacterInterface fight(CharacterInterface c1, CharacterInterface c2)
	{
		speak("----------" + c1.getName() + " vs " + c2.getName() + "----------");
		c1.reset();
		c2.reset();
		for(int i=0; i<100; i++)
		{
			takeTurn(c1,c2);
			if(c2.getCurrentHP() <= 0)
			{
				speak(c1.getName() + " wins! ("+c1.getCurrentHP()+"/"+c1.getMaxHP()+" HP left)\n");
				return c1;
			}
			takeTurn(c2,c1);			
			if(c1.getCurrentHP() <= 0)
			{
				speak(c2.getName() + " wins! ("+c2.getCurrentHP()+"/"+c2.getMaxHP()+" HP left)\n");
				return c2;
			}
		}
		speak("After 100 rounds, neither character has fallen.  It's a draw!\n");
		return null;
	}
	//-------------------------------------------------------------------------
	/**
	 * Runs through a single turn for [attacker] attacking [defender]. Each turn
	 * consists of [attacker] trying to use its special ability, attacking
	 * [defender], and regenerating.
	 * 
	 * @param attacker
	 *            the character whose turn it is
	 * @param defender
	 *            the character being attacked this turn
	 */
	private static void takeTurn(CharacterInterface attacker, CharacterInterface defender)
	{		
		String name = attacker.getName();
		speak("It's " + name + "\'s turn ("+attacker.getCurrentHP()+"/"+attacker.getMaxHP()+" HP).");
		
		//Use ability
		boolean special = attacker.useAbility();
		if(special)
			speak("\t" + name + " uses a special ability!");
		
		//Attack
		int damage = attacker.getDamage();
		int before_attack = defender.getCurrentHP();
		defender.takeDamage(damage);
		int after_attack = defender.getCurrentHP();
		speak("\t" + name + " attacks with " + damage + " damage, and " 
				+ defender.getName() + " takes " + (before_attack-after_attack) + " damage.");

		
		//Regenerate
		int before_regen = attacker.getCurrentHP();
		attacker.regenerate();
		int after_regen = attacker.getCurrentHP();
		speak("\t" + name + " regenerates " + (after_regen-before_regen) + " HP.");
	}
	//-------------------------------------------------------------------------
	/**
	 * Displays the given message to System.out if the debugging flag is set.
	 * 
	 * @param message
	 *            the message to print
	 */
	private static void speak(String message)
	{
		if(VERBOSE)
			System.out.println(message);
	}
	//-------------------------------------------------------------------------
}
