package it.simone.davide.cardtd.classes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
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
    private float width, height;

    //TODO add damage animation
    public Enemy(int hp, int damage, int speed, int moneyonkill, int attackDimension) {

        this.hp = hp;
        this.damage = damage;
        this.speed = speed;
        this.moneyonkill = moneyonkill;
        animations = new HashMap<>();
        this.attackDimension = attackDimension;
        loadAnimations();

    }

    @Override
    public float getWidth() {
        return width;
    }

    @Override
    public float getHeight() {
        return height;
    }

    public void setDimensions(float width, float height) {
        this.width = width;
        this.height = height;
    }

    public abstract void loadAnimations();

    public void setCurrentState(EnemyState currentState) {
        if (this.currentState != currentState) {
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

        switch (currentState) {
            case IDLE:

                currentRegion = animations.get(EnemyState.IDLE).getKeyFrame(time, true);
                break;
            case RUN:

                Vector2 v = path.move(delta, getX(), getY());

                setPosition(v.x, v.y);
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
        batch.setColor(Color.WHITE);
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

    @Override
    public void damage(int damage) {
        int oldHp = hp;
        hp -= damage;
        if (oldHp > 0 && hp <= 0) {

            die();
        }

    }

    public boolean isDead() {
        return hp <= 0;
    }

    public void die() {
        setCurrentState(EnemyState.DEATH);

        addAction(Actions.sequence(Actions.delay(1), Actions.fadeOut(0.5f), Actions.removeActor(), Actions.run(new Runnable() {

            @Override
            public void run() {
                remove = true;
            }
        })));
        hp = 0;
    }

    public boolean canRemove() {
        return remove;
    }
}
