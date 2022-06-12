package it.simone.davide.cardtd.classes.bullets.classes;

import com.badlogic.gdx.graphics.Texture;
import it.simone.davide.cardtd.classes.Bullet;
import it.simone.davide.cardtd.classes.Enemy;

import java.util.List;

/**
 * The class to handle the penetrating bullet, that is, the bullet passes through multiple enemies by hitting them
 */
public class PiercingBullet extends Bullet {

    /**
     * Creates a new piercing bullet
     *
     * @param texture texture of piercing bullet
     * @param speed   speed of piercing bullet, the speed it will have in the game
     */
    public PiercingBullet(Texture texture, float speed) {
        super(texture, speed);
    }

    /**
     * Checks if the bullet hit an enemy (or more in this case)
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
                    if (e.damage(damage))
                        money += e.getMoneyonkill();

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
        return new PiercingBullet(getTexture(), getSpeed());
    }
}
