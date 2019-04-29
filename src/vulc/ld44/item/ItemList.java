package vulc.ld44.item;

import vulc.ld44.level.Trade;

public class ItemList {

	public static final WeaponItem
	USELESS_STICK = new WeaponItem("useless stick", 0, 0, 50, 1),
	WOODEN_SWORD = new WeaponItem("wooden sword", 1, 0, 70, 0),
	IRON_SWORD = new WeaponItem("iron sword", 2, 0, 100, 1),
	FIRE_SWORD = new WeaponItem("fire sword", 3, 0, 130, 2),
	OCEANIC_SWORD = new WeaponItem("oceanic sword", 4, 0, 120, 3),
	LEAF_SWORD = new WeaponItem("left sword", 5, 0, 150, -1);

	public static final ArmorItem
	WOODEN_ARMOR = new ArmorItem("wooden armor", 0, 1, 5),
	FIRE_ARMOR = new ArmorItem("fire armor", 1, 1, 10),
	OCEANIC_ARMOR = new ArmorItem("oceanic armor", 2, 1, 12),
	LEAF_ARMOR = new ArmorItem("leaf armor", 3, 1, 18),
	SUPER_ARMOR = new ArmorItem("super armor", 4, 1, 25);

	public static final Trade
	TRADE_USELESS_STICK = new Trade(5, USELESS_STICK),
	TRADE_WOODEN_SWORD = new Trade(100, WOODEN_SWORD),
	TRADE_IRON_SWORD = new Trade(180, IRON_SWORD),
	TRADE_FIRE_SWORD = new Trade(250, FIRE_SWORD),
	TRADE_OCEANIC_SWORD = new Trade(250, OCEANIC_SWORD),
	TRADE_LEAF_SWORD = new Trade(250, LEAF_SWORD),
	TRADE_WOODEN_ARMOR = new Trade(50, WOODEN_ARMOR),
	TRADE_FIRE_ARMOR = new Trade(200, FIRE_ARMOR),
	TRADE_OCEANIC_ARMOR = new Trade(230, OCEANIC_ARMOR),
	TRADE_LEAF_ARMOR = new Trade(300, LEAF_ARMOR),
	TRADE_SUPER_ARMOR = new Trade(400, SUPER_ARMOR);

	public static void init() {
	}

}
