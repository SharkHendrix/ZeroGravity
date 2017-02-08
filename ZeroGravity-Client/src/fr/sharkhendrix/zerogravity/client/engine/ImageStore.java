package fr.sharkhendrix.zerogravity.client.engine;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import fr.sharkhendrix.zerogravity.client.Constants;
import fr.sharkhendrix.zerogravity.common.map.Direction;
import fr.sharkhendrix.zerogravity.common.map.Material;
import fr.sharkhendrix.zerogravity.common.map.Tile;
import lombok.Getter;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ImageStore {

    private static Map<Tile, BufferedImage> tileImages;

    private static Map<TileConnection, BufferedImage> walls;

    @Getter
    private static BufferedImage repairingGroundAnimationImage;

    public static void load() throws IOException {

        BufferedImage[][] tiles = loadTiledImages("map_tiles.png", Constants.TILE_WIDTH);

        repairingGroundAnimationImage = tiles[2][2];

        BufferedImage tmp;
        tileImages = new HashMap<>();

        putAllDirections(Material.VOID, createImage(Constants.TILE_WIDTH, Constants.TILE_WIDTH, Color.BLACK));

        putAllDirections(Material.GROUND, tiles[0][0]);

        putAllDirections(Material.WALL, tiles[1][0]);

        tileImages.put(Tile.get(Material.WALL_CORNER, Direction.RIGHT), tiles[3][1]);
        tmp = rotate90(tiles[3][1]);
        tileImages.put(Tile.get(Material.WALL_CORNER, Direction.DOWN), tmp);
        tmp = rotate90(tmp);
        tileImages.put(Tile.get(Material.WALL_CORNER, Direction.LEFT), tmp);
        tmp = rotate90(tmp);
        tileImages.put(Tile.get(Material.WALL_CORNER, Direction.UP), tmp);

        tileImages.put(Tile.get(Material.LAP_LINE, Direction.RIGHT), tiles[0][2]);
        tileImages.put(Tile.get(Material.LAP_LINE, Direction.LEFT), tiles[0][2]);
        tmp = rotate90(tiles[0][2]);
        tileImages.put(Tile.get(Material.LAP_LINE, Direction.UP), tmp);
        tileImages.put(Tile.get(Material.LAP_LINE, Direction.DOWN), tmp);

        putAllDirections(Material.WEAPON_DISPENSER, tiles[1][2]);

        putAllDirections(Material.REPAIRING_GROUND, tiles[3][2]);

        walls = new HashMap<>();
        walls.put(TileConnection.get(false, false, false, false), tiles[1][0]);

        walls.put(TileConnection.get(true, false, false, false), tiles[2][0]);
        tmp = rotate90(tiles[2][0]);
        walls.put(TileConnection.get(false, true, false, false), tmp);
        tmp = rotate90(tmp);
        walls.put(TileConnection.get(false, false, true, false), tmp);
        tmp = rotate90(tmp);
        walls.put(TileConnection.get(false, false, false, true), tmp);

        walls.put(TileConnection.get(true, false, true, false), tiles[3][0]);
        tmp = rotate90(tiles[3][0]);
        walls.put(TileConnection.get(false, true, false, true), tmp);

        walls.put(TileConnection.get(true, true, false, false), tiles[0][1]);
        tmp = rotate90(tiles[0][1]);
        walls.put(TileConnection.get(false, true, true, false), tmp);
        tmp = rotate90(tmp);
        walls.put(TileConnection.get(false, false, true, true), tmp);
        tmp = rotate90(tmp);
        walls.put(TileConnection.get(true, false, false, true), tmp);

        walls.put(TileConnection.get(true, true, true, false), tiles[1][1]);
        tmp = rotate90(tiles[1][1]);
        walls.put(TileConnection.get(false, true, true, true), tmp);
        tmp = rotate90(tmp);
        walls.put(TileConnection.get(true, false, true, true), tmp);
        tmp = rotate90(tmp);
        walls.put(TileConnection.get(true, true, false, true), tmp);

        walls.put(TileConnection.get(true, true, true, true), tiles[2][1]);

    }

    public static BufferedImage getTileImage(Tile tile) {
        return tileImages.get(tile);
    }

    public static BufferedImage getWallImage(TileConnection connection) {
        return walls.get(connection);
    }

    private static void putAllDirections(Material material, BufferedImage image) {
        tileImages.put(Tile.get(material, Direction.RIGHT), image);
        tileImages.put(Tile.get(material, Direction.DOWN), image);
        tileImages.put(Tile.get(material, Direction.LEFT), image);
        tileImages.put(Tile.get(material, Direction.UP), image);
    }

    private static BufferedImage[][] loadTiledImages(String imageFile, int tileWidth) throws IOException {
        BufferedImage tiles = loadImage(imageFile);
        int xSize = tiles.getWidth() / tileWidth;
        int ySize = tiles.getHeight() / tileWidth;
        BufferedImage[][] result = new BufferedImage[xSize][];
        for (int x = 0; x < xSize; x++) {
            BufferedImage[] resultRow = new BufferedImage[ySize];
            result[x] = resultRow;
            for (int y = 0; y < ySize; y++) {
                BufferedImage tile = new BufferedImage(tileWidth, tileWidth, BufferedImage.TYPE_INT_ARGB);
                Graphics2D g = tile.createGraphics();
                g.drawImage(tiles, 0, 0, tileWidth, tileWidth, x * tileWidth, y * tileWidth, (x + 1) * tileWidth,
                        (y + 1) * tileWidth, null);
                g.dispose();
                resultRow[y] = tile;
            }
        }
        return result;
    }

    private static BufferedImage loadImage(String imageFile) throws IOException {
        InputStream stream = ImageStore.class.getClassLoader().getResourceAsStream("images/" + imageFile);
        BufferedImage image = ImageIO.read(stream);
        stream.close();
        return image;
    }

    private static BufferedImage rotate90(BufferedImage image) {
        BufferedImage rotated = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                rotated.setRGB(image.getWidth() - 1 - y, x, image.getRGB(x, y));
            }
        }
        return rotated;
    }

    private static BufferedImage createImage(int width, int height, Color color) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
        g.setColor(color);
        g.fillRect(0, 0, width, height);
        g.dispose();
        return image;
    }
}
