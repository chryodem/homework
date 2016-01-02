

public abstract class Character implements CharacterInterface {

	protected String name;
	protected char type;
	protected int MaxHP;
	protected int CurrentHP;
	protected int Strength;
	protected int Speed;
	protected int Magic;
	
	public Character(String n, char t, int hp, int str, int spe, int ma){
		name=n;
		type=t;
		MaxHP=hp;
		CurrentHP=hp;
		Strength=str;
		Speed=spe;
		Magic=ma;
	}
	
	public String getName() {
		return name;
	}

	public int getMaxHP() {
		return MaxHP;
	}

	public int getCurrentHP() {
		return CurrentHP;
	}

	public int getStrength() {
		return Strength;
	}

	public int getSpeed() {
		return Speed;
	}

	public int getMagic() {
		return Magic;
	}

	public void takeDamage(int damage) {
	
		if(damage-(Speed/4) <= 0){
			damage = 1;
		}else{
			damage = damage-(Speed/4);
		}
		CurrentHP=CurrentHP-damage;
		
	}

	
}
