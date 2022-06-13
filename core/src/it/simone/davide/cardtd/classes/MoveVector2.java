package it.simone.davide.cardtd.classes;

import com.badlogic.gdx.math.Vector2;

/**
 * Represents a displacement by specifying how much must go forward/backward
 */
public class MoveVector2 {

    /**
     * The direction of the movement
     */
    private Direction direction;

    /**
     * The amount of displacement to be made to coordinate x
     */
    private float x;

    /**
     * The amount of displacement to be made to coordinate y
     */
    private float y;

    /**
     * The real Vector2
     */
    private Vector2 real;

    /**
     * Create a new MoveVector2
     *
     * @param x the amount of displacement to be made to coordinate x
     * @param y the amount of displacement to be made to coordinate y
     */
    public MoveVector2(float x, float y) {

        real = new Vector2(x, y);

        if (x > -1 && y > -1) {
            this.x = x;
            this.y = y;
            direction = Direction.X_Y_POSITIVE;
        }

        if (!(x > -1 && y > -1)) {
            this.x = -x;
            this.y = -y;
            direction = Direction.X_Y_NEGATIVE;
        }

        if (x > -1 && y < 0) {
            this.x = x;
            this.y = -y;
            direction = Direction.X_POSITIVE_Y_NEGATIVE;
        }

        if (x < 0 && y > -1) {
            this.x = -x;
            this.y = y;
            direction = Direction.X_NEGATIVE_Y_POSITIVE;
        }

    }

    private enum Direction {
        X_Y_POSITIVE, X_Y_NEGATIVE, X_POSITIVE_Y_NEGATIVE, X_NEGATIVE_Y_POSITIVE
    }

    public Vector2 getRealVector2() {

        return real;

    }

    public Direction getDirection() {
        return direction;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void addToX(float add) {

        x += add;

    }

    public void addToY(float add) {

        y += add;

    }
}
