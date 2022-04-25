package it.simone.davide.cardtd.classes.enemies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import it.simone.davide.cardtd.CardTDGame;
import it.simone.davide.cardtd.StaticVariables;
import it.simone.davide.cardtd.classes.Enemy;
import it.simone.davide.cardtd.enums.EnemyState;

public class ToasterBot extends Enemy {
    public ToasterBot(int hp, int damage, int speed, int moneyonkill, int attackdim) {

        super(hp, damage, speed, moneyonkill, attackdim, CardTDGame.assetManager.<Texture>get(StaticVariables.ToasterBorDagamed));
    }

    @Override
    public void loadAnimations() {
        int rows = 1, cols = 5;
        Texture texture = new Texture(StaticVariables.ToasterBorIDLE);
        TextureRegion[][] tmp = TextureRegion.split(texture, texture.getWidth() /
                cols, texture.getHeight() / rows);

        TextureRegion[] frames = new TextureRegion[cols * rows];
        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                frames[index++] = tmp[i][j];
            }
        }
        Animation anim = new Animation(0.2f, frames);

        animations.put(EnemyState.IDLE, anim);

        cols = 8;

        texture = new Texture(StaticVariables.ToasterBorRun);
        tmp = TextureRegion.split(texture, texture.getWidth() /
                cols, texture.getHeight() / rows);
        frames = new TextureRegion[cols * rows];
        index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                frames[index++] = tmp[i][j];
            }
        }
        anim = new Animation(0.2f, frames);

        animations.put(EnemyState.RUN, anim);

        cols = 11;

        texture = new Texture(StaticVariables.ToasterBorAttack);
        tmp = TextureRegion.split(texture, texture.getWidth() /
                cols, texture.getHeight() / rows);
        frames = new TextureRegion[cols * rows];
        index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                frames[index++] = tmp[i][j];
            }
        }
        anim = new Animation(0.2f, frames);

        animations.put(EnemyState.ATTACK, anim);

        cols = 5;

        texture = new Texture(StaticVariables.ToasterBorDeath);
        tmp = TextureRegion.split(texture, texture.getWidth() /
                cols, texture.getHeight() / rows);
        frames = new TextureRegion[cols * rows];
        index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                frames[index++] = tmp[i][j];
            }
        }
        anim = new Animation(0.2f, frames);

        animations.put(EnemyState.DEATH, anim);

        cols = 2;

        texture = new Texture(StaticVariables.ToasterBorDagamed);
        tmp = TextureRegion.split(texture, texture.getWidth() /
                cols, texture.getHeight() / rows);
        frames = new TextureRegion[cols * rows];
        index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                frames[index++] = tmp[i][j];
            }
        }
        anim = new Animation(0.2f, frames);

        animations.put(EnemyState.DAMAGED, anim);

        setCurrentState(EnemyState.RUN);
    }

    @Override
    public Enemy clone() {
        return new ToasterBot(getHp(), getDamage(), getSpeed(), getMoneyonkill(), getAttackDimension());
    }

}
