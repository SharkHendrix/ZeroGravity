package fr.sharkhendrix.zerogravity.common.map;

import lombok.Getter;

public enum Direction {
	RIGHT,
	DOWN,
	LEFT,
	UP;

	@Getter
	private int id;

	static {
		Direction[] values = Direction.values();
		for (int i = 0; i < values.length; i++) {
			values[i].id = i;
		}
	}
}
