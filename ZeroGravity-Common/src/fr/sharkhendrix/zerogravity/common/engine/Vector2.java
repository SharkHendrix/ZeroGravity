package fr.sharkhendrix.zerogravity.common.engine;

import fr.sharkhendrix.zerogravity.common.Util;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vector2 {

    private double x = 0;
    private double y = 0;

    public Vector2(Vector2 v) {
        x = v.x;
        y = v.y;
    }

    public static Vector2 fromPolar(double length, double angle) {
        return new Vector2(Math.cos(angle) * length, Math.sin(angle) * length);
    }

    public void set(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2 add(Vector2 v) {
        x += v.x;
        y += v.y;
        return this;
    }

    public Vector2 sub(Vector2 v) {
        x -= v.x;
        y -= v.y;
        return this;
    }

    public Vector2 mul(Vector2 v) {
        x *= v.x;
        y *= v.y;
        return this;
    }

    public Vector2 div(Vector2 v) {
        x /= v.x;
        y /= v.y;
        return this;
    }

    public double lengthSquared() {
        return x * x + y * y;
    }

    public double length() {
        return Math.sqrt(lengthSquared());
    }

    public double angle() {
        return Math.atan2(y, x);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(").append(Util.format(x)).append(", ").append(Util.format(y)).append(")");
        return sb.toString();
    }
}
