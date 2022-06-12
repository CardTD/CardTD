package it.simone.davide.cardtd.classes.bullets.classes;

import com.badlogic.gdx.graphics.g2d.Animation;
import it.simone.davide.cardtd.classes.AnimatedBullet;
import it.simone.davide.cardtd.classes.Enemy;

import java.util.List;

/**
 * The class to handle the singular bullet, that is, the bullet hit only one enemy
 */
public class SingularTargetBullet extends AnimatedBullet {

    /**
     * Creates a new singular bullet
     *
     * @param texture texture of singular bullet
     * @param speed   speed of singular bullet, the speed it will have in the game
     */
    public SingularTargetBullet(Animation texture, float speed) {
        super(texture, speed);

    }

    /**
     * Checks if the bullet hit an enemy
     *
     * @param enemies list of all enemies
     * @param damage  the damage of the bullet
     * @return the sum of money on kill
     */
    public int hitEnemies(List<Enemy> enemies, int damage) {


        int money = 0;
        for (Enemy e : enemies) {
            if (!getHitted().contains(e) && canHit(e)) {
                if (getRectangle().overlaps(e.getRectangle())) {
                    getHitted().add(e);


                    if (e.damage(damage)) {
                        money += e.getMoneyonkill();
                    }
                    remove();
                    return money;

                }

            }

        }
        return money;

    }

    /**
     * Create a shallow copy of the bullet
     *
     * @return the shallowed copy
     */
    @Override
    protected Object clone() {
        return new SingularTargetBullet(animation, getSpeed());
    }
}
