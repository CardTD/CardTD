package it.simone.davide.cardtd.classes;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import it.simone.davide.cardtd.enums.EnemyState;

import java.util.HashMap;
import java.util.Map;

public abstract class Enemy extends Actor implements Cloneable {

    protected Map<EnemyState, Animation<TextureRegion>> animations;
    protected EnemyState currentState;
    protected TextureRegion currentRegion;
    protected float time = 0f;
    private int hp, damage, speed, moneyonkill, attackDimension;

    public Enemy(int hp, int damage, int speed, int moneyonkill, int attackDimension) {
        this.hp = hp;
        this.damage = damage;
        this.speed = speed;
        this.moneyonkill = moneyonkill;
        animations = new HashMap<>();
        this.attackDimension = attackDimension;
        loadAnimations();
    }

    public abstract void loadAnimations();

    public void setCurrentState(EnemyState currentState) {
        if (this.currentState != currentState) {
            time = 0f;
            this.currentState = currentState;
        }

    }

    @Override
    public void act(float delta) {
        super.act(delta);
        time += delta;

        switch (currentState) {
            case IDLE:

                currentRegion = animations.get(EnemyState.IDLE).getKeyFrame(time, true);
                break;
            case RUN:
                setPosition(getX() + getSpeed() * delta, getY());
                currentRegion = animations.get(EnemyState.RUN).getKeyFrame(time, true);
                break;
            case DEATH:
                currentRegion = animations.get(EnemyState.DEATH).getKeyFrame(time, true);
                break;

            case ATTACK:
                currentRegion = animations.get(EnemyState.ATTACK).getKeyFrame(time, true);
                break;

            case DAMAGED:
                currentRegion = animations.get(EnemyState.DAMAGED).getKeyFrame(time, true);
                break;

        }

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(currentRegion, getX(), getY());
    }

    @Override
    public abstract Enemy clone();

    public int getHp() {
        return hp;
    }

    public int getDamage() {
        return damage;
    }

    public int getSpeed() {
        return speed;
    }

    public int getMoneyonkill() {
        return moneyonkill;
    }

    public int getAttackDimension() {
        return attackDimension;
    }

    public Rectangle getRectangle() {

        return new Rectangle(getX(), getY(), getWidth(), getHeight());

    }
}
