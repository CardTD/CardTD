package it.simone.davide.cardtd.classes;

import com.badlogic.gdx.math.Vector2;

import java.util.Arrays;
import java.util.List;

public class Path {

    List<MoveVector2> points;
    int speed;
    int currentPoint = 0;

    public Path(int speed, MoveVector2... list) {
        this.speed = speed;
        this.points = Arrays.asList(list);

    }

    public void move(float delta, float x, float y, Enemy enemy) {
        MoveVector2 current;
        Vector2 next;
        try {
            current = points.get(currentPoint);
        } catch (IndexOutOfBoundsException e) {

            current = null;
        }

        if (current != null) {

            float movimento = speed * delta;

            current.x -= movimento;
            current.y -= movimento;
            if (current.x < 0 && current.y < 0) {

                currentPoint++;
                
            }
            float movX = x, movY = y;


            switch (current.direction) {

                case X_Y_NEGATIVE:
                    if (current.x > 0) {
                        movX = x - movimento;
                    }
                    if (current.y > 0) {
                        movY = y - movimento;
                    }
                    break;
                case X_Y_POSITIVE:
                    if (current.x > 0) {
                        movX = x + movimento;
                    }
                    if (current.y > 0) {
                        movY = y + movimento;
                    }
                    break;
                case X_NEGATIVE_Y_POSITIVE:
                    if (current.x > 0) {
                        movX = x - movimento;
                    }
                    if (current.y > 0) {
                        movY = y + movimento;
                    }
                    break;

                case X_POSITIVE_Y_NEGATIVE:
                    if (current.x > 0) {
                        movX = x + movimento;
                    }
                    if (current.y > 0) {
                        movY = y - movimento;
                    }
                    break;
            }
            next = new Vector2(movX, movY);

        } else
            next = new Vector2(x, y);

        enemy.setPosition(next.x, next.y);

    }
}
