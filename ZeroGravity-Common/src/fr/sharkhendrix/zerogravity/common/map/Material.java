package fr.sharkhendrix.zerogravity.common.map;

import lombok.Getter;

public enum Material {
	GROUND,
	WALL,
	LAP_LINE,
	REPAIRING_GROUND,
	SPEED_BOOSTER,
	WEAPON_DISPENSER,
	CHECKPOINT;

	@Getter
	private int id;

	static {
		Material[] values = Material.values();
		for (int i = 0; i < values.length; i++) {
			values[i].id = i;
		}
	}
}
