package it.simone.davide.cardtd.classes;

import com.badlogic.gdx.math.Vector2;

import java.util.Arrays;
import java.util.List;

public class Path {

    List<Vector2> points;
    int speed;
    int currentPoint = 0;

    public Path(int speed, Vector2... list) {
        this.speed = speed;
        this.points = Arrays.asList(list);
    }

    public Vector2 move(float delta, float x, float y) {
        Vector2 current;
        try {

            current = points.get(currentPoint);
        } catch (IndexOutOfBoundsException e) {

            current = null;
        }

        if (current != null) {

            float movimento = speed * delta;

            current.x-= movimento;
            current.y-= movimento;
            if (current.x  < 0 && current.y < 0) {

                currentPoint++;
            }
            float movX = x, movY = y;
            if (current.x  > 0) {
                movX = x + movimento;
            }
            if (current.y  > 0) {
                movY = y + movimento;
            }
            return new Vector2(movX, movY);

        } else {

        }
        return new Vector2(x, y);
    }
}
