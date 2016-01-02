public class Cleric extends Character {

	protected int mana;
	protected int origmana;

	public Cleric(String n, char t, int hp, int str, int spe, int ma) {
		super(n, t, hp, str, spe, ma);
		mana = ma * 5;
		origmana = mana;
	}

	public int getDamage() {
		return Magic;
	}

	public void regenerate() {
		if (Strength / 6 == 0)
			CurrentHP = CurrentHP + 1;

		CurrentHP = CurrentHP + (Strength / 6);
		if (CurrentHP > MaxHP)
			CurrentHP = MaxHP;

		if (mana + (Magic / 5) <= origmana) {
			mana = mana + (Magic / 5);
		}
	}

	public void reset() {
		CurrentHP = MaxHP;
		mana = origmana;
	}

	public boolean useAbility() {
		if (mana >= CLERIC_ABILITY_COST) {
			if (CurrentHP + (Magic / 3) <= MaxHP) {
				CurrentHP = CurrentHP + (Magic / 3);
				mana = mana - CLERIC_ABILITY_COST;
			} else if (CurrentHP + (Magic / 3) > MaxHP) {
				CurrentHP = MaxHP;
				mana = mana - CLERIC_ABILITY_COST;
			}
			return true;
		}

		return false;
	}
}
