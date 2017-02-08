package fr.sharkhendrix.zerogravity.common.map;

import lombok.Getter;

public enum Material {
    VOID,
    GROUND,
    WALL,
    WALL_CORNER,
    LAP_LINE,
    REPAIRING_GROUND,
    WEAPON_DISPENSER;

    @Getter
    private int id;

    static {
        Material[] values = Material.values();
        for (int i = 0; i < values.length; i++) {
            values[i].id = i;
        }
    }
}
