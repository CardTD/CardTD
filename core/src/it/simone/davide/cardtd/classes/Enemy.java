package it.simone.davide.cardtd.classes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.TimeUtils;
import it.simone.davide.cardtd.enums.EnemyState;

import java.util.HashMap;
import java.util.Map;

public abstract class Enemy extends Actor implements Cloneable, Damageable {

    protected Map<EnemyState, Animation<TextureRegion>> animations;
    protected EnemyState currentState;
    protected TextureRegion currentRegion;
    protected float time = 0f;
    private int hp, damage, speed, moneyonkill, attackDimension;
    private boolean remove = false;
    private Path path;
    private float alpha = 2;
    private long startTime = 0;

    public Enemy(int hp, int damage, int speed, int moneyonkill, int attackDimension) {

        this.hp = hp;
        this.damage = damage;
        this.speed = speed;
        this.moneyonkill = moneyonkill;
        animations = new HashMap<>();
        this.attackDimension = attackDimension;
        loadAnimations();

    }

    public void setCurrentState(EnemyState currentState) {

        if (!currentState.equals(this.currentState)) {
            time = 0f;

            this.currentState = currentState;
        }

    }

    public void setPath(Path path) {
        this.path = path;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        time += delta;
        if (currentState != null)
            switch (currentState) {
                case IDLE:

                    currentRegion = animations.get(EnemyState.IDLE).getKeyFrame(time, true);
                    break;
                case RUN:

                    Vector2 v = path.move(delta, getX(), getY());

                    setPosition(v.x, v.y);
                    currentRegion = animations.get(EnemyState.RUN).getKeyFrame(time, true);
                    break;
                case DYING:
                    long elapsedTime;
                    if (startTime != 0) {
                        elapsedTime = TimeUtils.timeSinceMillis(startTime);

                        alpha -= elapsedTime / 1000f;
                    }

                    startTime = TimeUtils.millis();

                case DEATH:
                    currentRegion = animations.get(EnemyState.DEATH).getKeyFrame(time, true);

                    break;

                case ATTACK:
                    currentRegion = animations.get(EnemyState.ATTACK).getKeyFrame(time, true);
                    break;

                case DAMAGED:
                    if (animations.get(EnemyState.DAMAGED).isAnimationFinished(time)) {
                        if (!isDead())
                            setCurrentState(EnemyState.RUN);
                    }
                    v = path.move(delta, getX(), getY());

                    setPosition(v.x, v.y);
                    currentRegion = animations.get(EnemyState.DAMAGED).getKeyFrame(time, false);

                    break;

            }

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.setColor(Color.WHITE);
        if (currentState == EnemyState.DYING) {
            batch.setColor(Color.WHITE.r, Color.WHITE.g, Color.WHITE.b, alpha);

            if (alpha <= 0) {
                setCurrentState(EnemyState.DEATH);
                remove();

                remove = true;

            }
        }
        if (currentRegion != null)
            batch.draw(currentRegion, getX(), getY());

    }

    public Rectangle getRectangle() {

        return new Rectangle(getX(), getY(), getWidth(), getHeight());

    }

    @Override
    public void damage(float damage) {

        int oldHp = hp;
        hp -= damage;
        if (oldHp > 0 && hp <= 0) {

            die();
        } else {

            if (currentState.equals(EnemyState.RUN)) {

                setCurrentState(EnemyState.DAMAGED);
            }
        }

    }

    public boolean isDead() {
        return currentState.equals(EnemyState.DEATH) || currentState.equals(EnemyState.DYING);
    }

    public void die() {
        setCurrentState(EnemyState.DYING);

        hp = 0;
    }

    public boolean canRemove() {
        return remove;
    }

    @Override
    public abstract Enemy clone();

    public abstract void loadAnimations();

    @Override
    public abstract float getHeight();

    @Override
    public abstract float getWidth();

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

}
