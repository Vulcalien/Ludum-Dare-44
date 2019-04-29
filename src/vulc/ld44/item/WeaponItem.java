package vulc.ld44.item;

public class WeaponItem extends Item {

	private final int dmg;
	private int rangeBonus;

	public WeaponItem(String name, int xs, int ys, int dmg, int rangeBonus) {
		super(name, xs, ys);
		this.dmg = dmg;
		this.rangeBonus = rangeBonus;
	}

	public int getDamage() {
		return dmg;
	}

	public int getAttackRangeBonus() {
		return rangeBonus;
	}

}
