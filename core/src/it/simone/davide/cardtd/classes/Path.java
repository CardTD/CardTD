package it.simone.davide.cardtd.classes;

import com.badlogic.gdx.math.Vector2;

import java.util.Arrays;
import java.util.List;

/**
 * The path an enemy must take
 */
public class Path {

    /**
     * The movements that an enemy must perform
     */
    private List<MoveVector2> points;

    /**
     * The speed to travel the path
     */
    private int speed;

    /**
     * What point the enemy is on the path
     */
    private int currentPoint = 0;

    /**
     * Create a new path
     *
     * @param speed the speed to travel the path
     * @param list  the list of the points that make up the path
     */
    public Path(int speed, MoveVector2... list) {
        this.speed = speed;
        this.points = Arrays.asList(list);

    }

    private boolean isPositive(float a) {

        return a > -1;

    }

    /**
     * The method to call to move the enemy in the path
     *
     * @param delta the delta time between 2 frames
     * @param enemy the enemy to move
     */
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

            current.addToX(-movimento);
            current.addToY(-movimento);
            if (current.getX() < 0 && current.getY() < 0) {

                Vector2 l, n;
                l = current.getRealVector2();
                currentPoint++;
                n = points.get(currentPoint).getRealVector2();

                if ((isPositive(l.x) && !isPositive(n.x)) || (!isPositive(l.x) && isPositive(n.x))) {

                    enemy.flip(enemy.getX() - enemy.getFrameWidth() + enemy.getWidth(), enemy.getY());

                }

            }

            x = enemy.getX();
            y = enemy.getY();
            float movX = x, movY = y;

            switch (current.getDirection()) {

                case X_Y_NEGATIVE:
                    if (current.getX() > 0) {
                        movX = x - movimento;
                    }
                    if (current.getY() > 0) {
                        movY = y - movimento;
                    }
                    break;
                case X_Y_POSITIVE:
                    if (current.getX() > 0) {
                        movX = x + movimento;
                    }
                    if (current.getY() > 0) {
                        movY = y + movimento;
                    }
                    break;
                case X_NEGATIVE_Y_POSITIVE:
                    if (current.getX() > 0) {
                        movX = x - movimento;
                    }
                    if (current.getY() > 0) {
                        movY = y + movimento;
                    }
                    break;

                case X_POSITIVE_Y_NEGATIVE:
                    if (current.getX() > 0) {
                        movX = x + movimento;
                    }
                    if (current.getY() > 0) {
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
