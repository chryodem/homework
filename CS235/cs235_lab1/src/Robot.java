public class Robot extends Character {

	protected double energy;
	protected double origenergy;
	protected int bonusdamage;
	protected boolean specialability=false;
	

	public Robot(String n, char t, int hp, int str, int spe, int ma) {
		super(n, t, hp, str, spe, ma);
		energy = ma * 2;
		origenergy = energy;
	}

	public int getDamage() {
		if (specialability == true) {
			return (Strength + bonusdamage);
		}
		return Strength;
	}

	public void regenerate() {
		if (Strength / 6 == 0)
			CurrentHP = CurrentHP + 1;

		CurrentHP = CurrentHP + (Strength / 6);
		if (CurrentHP > MaxHP)
			CurrentHP = MaxHP;
				

	}

	public void reset() {
		CurrentHP = MaxHP;
		energy = origenergy;
		bonusdamage = 0;
	}

	public boolean useAbility() {
		
		if (energy >= ROBOT_ABILITY_COST) {
			specialability=true;
			bonusdamage = (int) (Strength * recurse(energy / origenergy, 4));

			energy = energy - ROBOT_ABILITY_COST;

			return true;
		}
		specialability=false;
		return false;
	}

	public double recurse(double x, int num) {
		if (num == 1) {
			return x;
		}
		x = recurse(x, (num - 1)) * x;
		return x;

	}
}
