package it.simone.davide.cardtd.classes.bullets;

import com.badlogic.gdx.graphics.Texture;
import it.simone.davide.cardtd.classes.Bullet;
import it.simone.davide.cardtd.classes.Enemy;

import java.util.List;

public class PiercingBullet extends Bullet {
    public PiercingBullet(Texture texture, float speed) {
        super(texture, speed);
    }

    public void hitEnemies(List<Enemy> enemies, int damage) {

        for (Enemy e : enemies) {
            if (!getHitted().contains(e) && canHit(e)) {
                if (getRectangle().overlaps(e.getRectangle())) {
                    getHitted().add(e);
                    e.damage(damage);

                }

            }

        }

    }

    @Override
    protected Object clone() {
        return new PiercingBullet(getTexture(), getSpeed());
    }
}
