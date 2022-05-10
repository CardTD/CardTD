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


    private boolean isPositive(float a) {

        return a > -1;

    }

    public void move(float delta, Enemy enemy) {
        MoveVector2 current;
        Vector2 next;
        try {
            current = points.get(currentPoint);
        } catch (IndexOutOfBoundsException e) {

            current = null;
        }
        float x = enemy.getX(), y = enemy.getY();
        if (current != null) {

            float movimento = speed * delta;

            current.x -= movimento;
            current.y -= movimento;
            if (current.x < 0 && current.y < 0) {

                Vector2 l, n;
                l = current.getRealVector2();
                currentPoint++;
                n = points.get(currentPoint).getRealVector2();

                System.out.println(l.x);
                System.out.println(n.x);
                System.out.println("____________");

                if ((isPositive(l.x) && !isPositive(n.x)) || (!isPositive(l.x) && isPositive(n.x))) {

                    enemy.flip(enemy.getX()-enemy.getFrameWidth()+enemy.getWidth(), enemy.getY());


                }

            }

            x = enemy.getX();
            y = enemy.getY();
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
