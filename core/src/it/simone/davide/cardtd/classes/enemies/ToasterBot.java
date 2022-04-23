package it.simone.davide.cardtd.classes.enemies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import it.simone.davide.cardtd.classes.Enemy;
import it.simone.davide.cardtd.classes.EnemyState;

public class ToasterBot extends Enemy {
    public ToasterBot(int hp, int damage, int speed, int moneyonkill) {
        super(hp, damage, speed, moneyonkill);
    }

    @Override
    public void loadAnimations() {
        Texture texture = new Texture("cards/toaster/idle.png");
        TextureRegion[][] tmp = TextureRegion.split(texture, texture.getWidth() /
                5, texture.getHeight() / 1);
        TextureRegion[] frames = new TextureRegion[5 * 1];
        int index = 0;
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < 5; j++) {
                frames[index++] = tmp[i][j];
            }
        }
        Animation anim = new Animation(0.2f, frames);

        animations.put(EnemyState.IDLE, anim);

        texture = new Texture("cards/toaster/run.png");
        tmp = TextureRegion.split(texture, texture.getWidth() /
                8, texture.getHeight() / 1);
        frames = new TextureRegion[8 * 1];
        index = 0;
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < 8; j++) {
                frames[index++] = tmp[i][j];
            }
        }
        anim = new Animation(0.2f, frames);

        animations.put(EnemyState.RUN, anim);
        setCurrentState(EnemyState.RUN);
    }

    @Override
    public Enemy clone() {
        return new ToasterBot(getHp(), getDamage(), getSpeed(), getMoneyonkill());
    }

}
