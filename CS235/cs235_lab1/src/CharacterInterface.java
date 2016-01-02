/**
 * This interface specifies the methods of a character.
 * 
 * All characters have the following attributes:
 * 		Name - The name of a character.
 * 		Hit Points - The amount of health a character has, with a specified maximum. Reaching 0 is equivalent to death.
 * 		Strength - Physical power, used to determine hit point regeneration.
 * 		Speed - Dexterity and physical movement, used to reduce damage when being attacked.
 * 		Magic - Magical prowess, used for some special abilities.
 * 
 * The three character types have unique attributes:
 * 		Robot - Relies on strength to do damage. Also can use stored electricity to increase damage.
 * 		Arcer - Relies on speed to do damage. Also can increase speed for the remainder of the battle.
 * 		Cleric - Relies on magic to do damage. Also can heal itself using mana, restoring hit points.
 * 
 * More details about how stats are used and how abilities work can be found in the comments below.
 */
public interface CharacterInterface
{
	//-------------------------------------------------------------------------
	/**
	 * The amount of energy required for a Robot to use its special ability.
	 */
	final int ROBOT_ABILITY_COST = 5;
	//-------------------------------------------------------------------------

	//-------------------------------------------------------------------------
	/**
	 * The amount of mana required for a Cleric to use its special ability.
	 */
	final int CLERIC_ABILITY_COST = 25;
	//-------------------------------------------------------------------------

	//-------------------------------------------------------------------------
	/**
	 * Returns the name of this character.
	 * 
	 * @return the name of this character
	 */
	public String getName();
	//-------------------------------------------------------------------------

	//-------------------------------------------------------------------------
	/**
	 * Returns the maximum hit points of this character.
	 * 
	 * @return the maximum hit points of this character
	 */
	public int getMaxHP();
	//-------------------------------------------------------------------------

	//-------------------------------------------------------------------------
	/**
	 * Returns the current hit points of this character.
	 * 
	 * @return the current hit points of this character
	 */
	public int getCurrentHP();
	//-------------------------------------------------------------------------

	//-------------------------------------------------------------------------
	/**
	 * Returns the strength stat of this character.
	 * 
	 * @return the strength stat of this character
	 */
	public int getStrength();
	//-------------------------------------------------------------------------

	//-------------------------------------------------------------------------
	/**
	 * Returns the speed stat of this character.
	 * 
	 * @return the speed stat of this character
	 */
	public int getSpeed();
	//-------------------------------------------------------------------------

	//-------------------------------------------------------------------------
	/**
	 * Returns the magic stat of this character.
	 * 
	 * @return the magic stat of this character
	 */
	public int getMagic();
	//-------------------------------------------------------------------------

	//-------------------------------------------------------------------------
	/**
	 * Returns the amount of damage this character will do. This value is equal
	 * to the character's strength (if the character is a Robot), speed (if the
	 * character is an Archer), or magic (if the character is a Cleric).
	 * 
	 * Note that a Robot may also add additional damage by having just used its
	 * special ability.
	 * 
	 * @return the amount of damage this character can do
	 */
	public int getDamage();
	//-------------------------------------------------------------------------

	//-------------------------------------------------------------------------
	/**
	 * Reduces the character's current hit points by an amount equal to the
	 * given damage minus one fourth of the character's speed (one hit point
	 * minimum). It is acceptable for this to give the character negative
	 * current hit points.
	 * 
	 * @param damage
	 *            the amount of damage dealt to this character (may be reduced
	 *            by speed before affecting current hit points)
	 */
	public void takeDamage(int damage);
	//-------------------------------------------------------------------------

	//-------------------------------------------------------------------------
	/**
	 * Increases the character's current hit points by an amount equal to one
	 * sixth of the character's strength (one hit point minimum). Do not allow
	 * the current hit points to exceed the maximum.
	 * 
	 * Also increases a Cleric's current mana by an amount equal to one fifth of
	 * the character's magic (one mana minimum). Do not allow the current mana
	 * to exceed the maximum.
	 */
	public void regenerate();
	//-------------------------------------------------------------------------

	//-------------------------------------------------------------------------
	/**
	 * Restores the character's current hit points to maximum.
	 * 
	 * Also restores a Robot's current energy to maximum (which is [2 * magic]) and resets its bonus damage.
	 * Also restores an Archer's speed back to its original value.
	 * Also restores a Cleric's current mana to maximum (which is [5 * magic]).
	 */
	public void reset();
	//-------------------------------------------------------------------------

	//-------------------------------------------------------------------------
	/**
	 * Attempts to perform a special ability based on the type of character. The
	 * character will attempt to use this special ability just prior to
	 * attacking every turn.
	 * 
	 * Robot: Shockwave Punch
	 * 		Adds bonus damage to the Robot's next attack equal to [strength * ((current energy / maximum energy) ^ 4)].
	 * 		Decreases the Robot's current energy by [ROBOT_ABILITY_COST] after calculating the additional damage.
	 * 		Can only be used if the Robot has at least [ROBOT_ABILITY_COST] energy remaining.
	 * Archer: Quickstep
	 * 		Increases the Archer's speed stat by one each time it is used.
	 * 		This bonus lasts until the reset() method is used.
	 * 		Always works; this means it is possible for an Archer to add any amount of bonus speed.
	 * Cleric: Healing Light
	 * 		Increases the Cleric's current hit points by an amount equal to [magic/3] (one hit point minimum, cannot exceed maximum hit points).
	 * 		Decreases the Cleric's current mana by [CLERIC_ABILITY_COST] when used.
	 * 		Can only be used if the Cleric has at least [CLERIC_ABILITY_COST] mana remaining.
	 * 
	 * The exponent part of the Robot's special ability MUST be calculated using
	 * a recursive power function of your own creation. In addition, the bonus
	 * damage formula should be computed using double arithmetic, and only the
	 * final result should be cast into an integer.
	 * 
	 * @return true if the ability was used; false otherwise
	 */
	public boolean useAbility();
	//-------------------------------------------------------------------------
}
