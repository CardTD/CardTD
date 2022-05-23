package it.simone.davide.cardtd.classes.bullets.type;

import com.badlogic.gdx.graphics.Texture;
import it.simone.davide.cardtd.classes.AnimatedImage;
import it.simone.davide.cardtd.classes.bullets.classes.PiercingBullet;
import it.simone.davide.cardtd.classes.bullets.classes.SingularTargetBullet;

public class RockBullet extends PiercingBullet {

    public RockBullet(Texture texture, float speed) {
        super(texture, speed);
        setSize(20, 20);


    }

    @Override
    protected Object clone() {
        return new RockBullet(getTexture(), getSpeed());
    }
}
