package it.simone.davide.cardtd.classes;

import com.badlogic.gdx.math.Vector2;

public class MoveVector2 {

    Direction direction;
    float x, y;

    Vector2 real;


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

    public enum Direction {
        X_Y_POSITIVE, X_Y_NEGATIVE, X_POSITIVE_Y_NEGATIVE, X_NEGATIVE_Y_POSITIVE
    }

    public Vector2 getRealVector2() {

        return real;

    }

}
