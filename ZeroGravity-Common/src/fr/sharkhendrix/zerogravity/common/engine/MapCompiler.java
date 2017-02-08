package fr.sharkhendrix.zerogravity.common.engine;

import java.util.Arrays;
import java.util.List;

import org.dyn4j.geometry.Polygon;

import fr.sharkhendrix.zerogravity.common.map.Map;
import fr.sharkhendrix.zerogravity.common.map.Material;
import fr.sharkhendrix.zerogravity.common.map.Tile;

public class MapCompiler {

    public Polygon[] buildPolygons(Map map, Material... materials) {
        boolean[][] consumeMap = createBooleanArray(map);
        List<Material> materialList = Arrays.asList(materials);

        for (int y = 0; y < map.getHeight(); y++) {
            for (int x = 0; x < map.getWidth(); x++) {
                Tile currentTile = map.get(x, y);
                if (!consumeMap[x][y] && materialList.contains(currentTile.getMaterial())) {

                }
            }
        }
        return null;
    }

    private boolean[][] createBooleanArray(Map map) {
        boolean[][] array = new boolean[map.getWidth()][];
        for (int i = 0; i < map.getWidth(); i++) {
            array[i] = new boolean[map.getHeight()];
        }
        return array;
    }

    private Polygon buildPolygon(Map map, List<Material> materialList, boolean[][] consumeMap, int x, int y) {
        // for(int y = 0; y <)
        return null;
    }
}
