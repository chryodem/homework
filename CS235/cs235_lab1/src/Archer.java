public class Archer extends Character {

	protected int origspeed;

	public Archer(String n, char t, int hp, int str, int spe, int ma) {
		super(n, t, hp, str, spe, ma);
		origspeed = Speed;
	}

	public int getDamage() {
		return Speed;

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
		Speed = origspeed;
	}

	public boolean useAbility() {

		Speed = Speed + 1;

		return true;
	}

}
