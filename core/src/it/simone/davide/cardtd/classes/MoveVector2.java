package it.simone.davide.cardtd.classes;

import com.badlogic.gdx.math.Vector2;
import it.simone.davide.cardtd.enums.Direction;

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

    /**
     * Returns the real Vector2
     *
     * @return the real Vector2
     */
    public Vector2 getRealVector2() {

        return real;

    }

    /**
     * Returns the direction of the movement
     *
     * @return the direction of the movement
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * Returns the coordinate x
     *
     * @return the coordinate x
     */
    public float getX() {
        return x;
    }

    /**
     * Returns the coordinate y
     *
     * @return the coordinate y
     */
    public float getY() {
        return y;
    }

    /**
     * Adds a value to coordinate x
     *
     * @param add value to add
     */
    public void addToX(float add) {

        x += add;

    }

    /**
     * Adds a value to coordinate y
     *
     * @param add value to add
     */
    public void addToY(float add) {

        y += add;

    }

}
