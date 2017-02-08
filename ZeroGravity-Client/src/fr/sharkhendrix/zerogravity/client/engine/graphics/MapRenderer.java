package fr.sharkhendrix.zerogravity.client.engine.graphics;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;

import fr.sharkhendrix.zerogravity.client.Constants;
import fr.sharkhendrix.zerogravity.client.engine.ImageStore;
import fr.sharkhendrix.zerogravity.client.engine.TileConnection;
import fr.sharkhendrix.zerogravity.common.map.Direction;
import fr.sharkhendrix.zerogravity.common.map.Map;
import fr.sharkhendrix.zerogravity.common.map.Material;
import fr.sharkhendrix.zerogravity.common.map.Tile;
import lombok.Getter;

public class MapRenderer implements Renderer {

    private static final double REPAIRING_GROUND_ANIMATION_SPEED = 1;

    @Getter
    private Map map;

    private TileConnection[][] connections;

    private double repairingGroundAnimationState;

    public void setMap(Map map) {
        this.map = map;
        connections = new TileConnection[map.getWidth()][];
        for (int i = 0; i < map.getWidth(); i++) {
            connections[i] = new TileConnection[map.getHeight()];
        }
        updateAllConnections();
    }

    public void updateAllConnections() {
        for (int tx = 0; tx < map.getWidth(); tx++) {
            for (int ty = 0; ty < map.getWidth(); ty++) {
                updateConnection(tx, ty);
            }
        }
    }

    public void updateConnection(int tx, int ty) {

        Tile tile = map.get(tx, ty);
        if (tile.getMaterial() == Material.WALL) {
            boolean right = tx < (map.getWidth() - 1) && (map.get(tx + 1, ty).getMaterial() == Material.WALL
                    || isCornerConnected(Direction.RIGHT, map.get(tx + 1, ty)));
            boolean down = ty < (map.getHeight() - 1) && (map.get(tx, ty + 1).getMaterial() == Material.WALL
                    || isCornerConnected(Direction.DOWN, map.get(tx, ty + 1)));
            boolean left = tx > 0 && (map.get(tx - 1, ty).getMaterial() == Material.WALL
                    || isCornerConnected(Direction.LEFT, map.get(tx - 1, ty)));
            boolean up = ty > 0 && (map.get(tx, ty - 1).getMaterial() == Material.WALL
                    || isCornerConnected(Direction.UP, map.get(tx, ty - 1)));
            connections[tx][ty] = TileConnection.get(right, down, left, up);
        }
    }

    @Override
    public void render(Graphics2D g, double delta) {
        repairingGroundAnimationState += delta;
        if (repairingGroundAnimationState > REPAIRING_GROUND_ANIMATION_SPEED) {
            repairingGroundAnimationState -= REPAIRING_GROUND_ANIMATION_SPEED;
        }
        if (map == null) {
            return;
        }
        for (int tx = 0; tx < map.getWidth(); tx++) {
            for (int ty = 0; ty < map.getWidth(); ty++) {
                Tile tile = map.get(tx, ty);
                if (tile.getMaterial() == Material.WALL) {
                    g.drawImage(ImageStore.getWallImage(connections[tx][ty]), tx * Constants.TILE_WIDTH,
                            ty * Constants.TILE_WIDTH, null);
                } else if (tile.getMaterial() == Material.WALL_CORNER) {
                    Material groundMaterial = drawWallCornerGround(g, tx, ty);
                    if (groundMaterial == Material.REPAIRING_GROUND) {
                        drawRepairingGroundAnimation(g, tx, ty);
                    }
                    g.drawImage(ImageStore.getTileImage(tile), tx * Constants.TILE_WIDTH, ty * Constants.TILE_WIDTH,
                            null);
                } else if (tile.getMaterial() == Material.REPAIRING_GROUND) {
                    g.drawImage(ImageStore.getTileImage(tile), tx * Constants.TILE_WIDTH, ty * Constants.TILE_WIDTH,
                            null);
                    drawRepairingGroundAnimation(g, tx, ty);
                } else if (tile.getMaterial() != Material.VOID) {
                    g.drawImage(ImageStore.getTileImage(tile), tx * Constants.TILE_WIDTH, ty * Constants.TILE_WIDTH,
                            null);
                }
            }
        }
    }

    private boolean isCornerConnected(Direction direction, Tile tile) {
        if (tile.getMaterial() == Material.WALL_CORNER) {
            Direction opposite = tile.getDirection().opposite();
            return opposite == direction || opposite.nextClockWise() == direction;
        } else {
            return false;
        }
    }

    private void drawRepairingGroundAnimation(Graphics2D g, int tx, int ty) {
        float opacity = (float) (Math.abs(repairingGroundAnimationState - 0.5) * 2.0);
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
        g.drawImage(ImageStore.getRepairingGroundAnimationImage(), tx * Constants.TILE_WIDTH, ty * Constants.TILE_WIDTH,
                null);
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
    }

    private Material drawWallCornerGround(Graphics2D g, int tx, int ty) {
        Tile tile = map.get(tx, ty);
        Direction directionRef = tile.getDirection().opposite();
        Material groundMaterial = getGroundMaterial(tx + directionRef.getNormX(), ty + directionRef.getNormY());
        if (groundMaterial == null) {
            directionRef = tile.getDirection().nextCounterClockWise();
            groundMaterial = getGroundMaterial(tx + directionRef.getNormX(), ty + directionRef.getNormY());
        }
        if (groundMaterial != null) {
            g.drawImage(ImageStore.getTileImage(Tile.get(groundMaterial, Direction.RIGHT)), tx * Constants.TILE_WIDTH,
                    ty * Constants.TILE_WIDTH, null);
        }
        return groundMaterial;
    }

    private Material getGroundMaterial(int tx, int ty) {
        if (map.isValidLocation(tx, ty)) {
            Material material = map.get(tx, ty).getMaterial();
            if (material == Material.GROUND || material == Material.VOID || material == Material.REPAIRING_GROUND) {
                return material;
            } else if (material == Material.LAP_LINE || material == Material.WEAPON_DISPENSER) {
                return Material.GROUND;
            }
        }
        return null;
    }
}
