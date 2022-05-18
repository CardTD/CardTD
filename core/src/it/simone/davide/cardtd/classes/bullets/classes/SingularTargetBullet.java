package it.simone.davide.cardtd.classes.bullets.classes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import it.simone.davide.cardtd.classes.Bullet;
import it.simone.davide.cardtd.classes.Damageable;
import it.simone.davide.cardtd.classes.Enemy;
import it.simone.davide.cardtd.classes.Level;

import java.util.List;

public class SingularTargetBullet extends Bullet {

    public SingularTargetBullet(Texture texture, float speed) {
        super(texture, speed);

    }

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

    @Override
    protected Object clone() {
        return new SingularTargetBullet(getTexture(), getSpeed());
    }
}
