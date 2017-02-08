package fr.sharkhendrix.zerogravity.common.map;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MapFactory {

    public static Map createEmptyMap(int width, int height) {
        Map map = new Map(width, height);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (x > 0 && x < width - 1 && y > 0 && y < height - 1) {
                    map.set(x, y, Tile.get(Material.GROUND));
                } else {
                    map.set(x, y, Tile.get(Material.WALL));
                }
            }
        }
        return map;
    }
}
