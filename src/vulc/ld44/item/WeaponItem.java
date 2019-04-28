package vulc.ld44.item;

public class WeaponItem extends Item {

	public int dmg;
	public int rangeBonus;

	public WeaponItem(String name, int xs, int ys, int dmg, int rangeBonus) {
		super(name, xs, ys);
		this.dmg = dmg;
		this.rangeBonus = rangeBonus;
	}

	public int getDamageBonus() {
		return dmg;
	}

	public int getAttackRangeBonus() {
		return rangeBonus;
	}

}
