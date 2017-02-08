package fr.sharkhendrix.zerogravity.common.map;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Tile {

    private static Tile[] instances;

    static {
        Material[] matarialValues = Material.values();
        Direction[] directionValues = Direction.values();
        instances = new Tile[matarialValues.length * directionValues.length];
        for (int m = 0; m < matarialValues.length; m++) {
            for (int d = 0; d < directionValues.length; d++) {
                instances[m * directionValues.length + d] = new Tile(matarialValues[m], directionValues[d]);
            }
        }
    }

    private Material material;
    private Direction direction;

    public static Tile get(Material material, Direction direction) {
        return instances[material.getId() * Direction.values().length + direction.getId()];
    }

    public static int getCount() {
        return (Material.values().length * Direction.values().length);
    }

    public static Tile get(Material material) {
        return get(material, Direction.RIGHT);
    }

    public static Tile get(int id) {
        return instances[id];
    }

    public int getId() {
        return material.getId() * Direction.values().length + direction.getId();
    }

}
