package fr.sharkhendrix.zerogravity.client.engine;

import java.awt.Point;

import org.dyn4j.geometry.Vector2;

import fr.sharkhendrix.zerogravity.client.Constants;
import lombok.Data;

@Data
public class Camera {

    private Vector2 position = new Vector2();
    private double scale = Constants.TILE_WIDTH;
    private double width = 1;
    private double height = 1;

    public Vector2 screenToWorld(Point p) {
        return screenToWorld(new Vector2(p.x, p.y));
    }

    public Vector2 screenToWorld(Vector2 screenVector) {
        Vector2 worldVector = new Vector2(screenVector).subtract(width / 2, height / 2);
        return worldVector.multiply(1.0 / scale).add(position);
    }
}
