package fr.sharkhendrix.zerogravity.common.map;

import lombok.Getter;

public enum Direction {
    RIGHT(1, 0),
    DOWN(0, 1),
    LEFT(-1, 0),
    UP(0, -1);

    @Getter
    private int id;
    @Getter
    private int normX;
    @Getter
    private int normY;

    private Direction(int normX, int normY) {
        this.normX = normX;
        this.normY = normY;
    }

    public Direction nextClockWise() {
        return Direction.values()[(id + 1) % 4];
    }

    public Direction nextCounterClockWise() {
        return Direction.values()[(id + 3) % 4];
    }

    public Direction opposite() {
        return Direction.values()[(id + 2) % 4];
    }

    static {
        Direction[] values = Direction.values();
        for (int i = 0; i < values.length; i++) {
            values[i].id = i;
        }
    }
}
