package it.simone.davide.cardtd.classes.bullets.type;

import com.badlogic.gdx.graphics.Texture;
import it.simone.davide.cardtd.classes.bullets.classes.PiercingBullet;

public class Axe extends PiercingBullet {

    public Axe(Texture texture, float speed) {
        super(texture, speed);
        setSize(20, 20);

    }

    @Override
    public void act(float delta) {
        super.act(delta);
        rotateBy(25);

    }

    @Override
    protected Object clone() {
        return new Axe(getTexture(), getSpeed());
    }
}