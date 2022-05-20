package it.simone.davide.cardtd.classes.bullets.classes;

import com.badlogic.gdx.graphics.Texture;
import it.simone.davide.cardtd.classes.Bullet;
import it.simone.davide.cardtd.classes.Enemy;

import java.util.List;

public class AxeBullet extends Bullet {

    public AxeBullet(Texture texture, float speed){
        super(texture, speed);
    }


    @Override
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

    @Override
    protected Object clone() {
        return new AxeBullet(getTexture(), getSpeed());
    }
}