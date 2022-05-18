package it.simone.davide.cardtd.classes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import it.simone.davide.cardtd.CardTDGame;
import it.simone.davide.cardtd.enums.EnemyState;

import java.util.HashMap;
import java.util.Map;

public abstract class Enemy extends Actor implements Cloneable, Damageable {

    protected Map<EnemyState, Animation<TextureRegion>> animations;
    protected EnemyState currentState;
    protected TextureRegion currentRegion;
    protected float time = 0f;
    private int hp;
    private final int damage;
    private final int speed;
    private final int moneyonkill;
    private final int attackDimension;
    private boolean remove = false;
    private Path path;
    private Vector2 center = new Vector2(0, 0);
    private boolean isFlipped = false;

    public Enemy(int hp, int damage, int speed, int moneyonkill, int attackDimension) {

        this.hp = hp;
        this.damage = damage;
        this.speed = speed;
        this.moneyonkill = moneyonkill;
        animations = new HashMap<>();
        this.attackDimension = attackDimension;
        loadAnimations();

    }

    public Vector2 getCenter() {
        return center;
    }

    @Override
    public void setX(float x) {
        super.setX(x);
        center.x = getX() + getFrameWidth() / 2;
    }

    @Override
    public void setY(float y) {
        super.setY(y);
        center.y = getY() + getHeight() / 2;
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        if (isFlipped)
            center.set(getX() - getWidth() / 2 + getFrameWidth(), getY() + getHeight() / 2);
        else
            center.set(getX() + getWidth() / 2, getY() + getHeight() / 2);

    }

    public void flip(float newX, float newY) {

        isFlipped = !isFlipped;

        for (Map.Entry<EnemyState, Animation<TextureRegion>> i : animations.entrySet()) {

            for (TextureRegion f : i.getValue().getKeyFrames()) {

                f.flip(true, false);

            }

        }
        setPosition(newX, newY);

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

                    path.move(delta, this);

                    currentRegion = animations.get(EnemyState.RUN).getKeyFrame(time, true);
                    break;
                case DYING:
                    if (animations.get(EnemyState.DYING).isAnimationFinished(time)) {
                        remove();

                        remove = true;

                    }

                    currentRegion = animations.get(EnemyState.DYING).getKeyFrame(time, true);

                    break;

                case ATTACK:
                    if (animations.get(EnemyState.ATTACK).isAnimationFinished(time)) {

                        Level.HEALTHBAR.damage(damage);
                        time = 0;
                    }

                    currentRegion = animations.get(EnemyState.ATTACK).getKeyFrame(time, true);
                    break;

                case DAMAGED:
                    if (animations.get(EnemyState.DAMAGED).isAnimationFinished(time)) {
                        if (!isDead())
                            setCurrentState(EnemyState.RUN);
                    }
                    path.move(delta, this);

                    currentRegion = animations.get(EnemyState.DAMAGED).getKeyFrame(time, false);

                    break;

            }

    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.setColor(Color.WHITE);
        if (currentRegion != null) {

            batch.draw(currentRegion, getX(), getY());
        }


    }

    public Rectangle getRectangle() {

        return new Rectangle(center.x-getHeight()/2, center.y-getHeight()/2, getHeight(), getHeight());

    }

    /**
     * @param damage
     * @return id diead or not
     */
    @Override
    public boolean damage(float damage) {




        int oldHp = hp;
        hp -= damage;
        if (oldHp > 0 && hp <= 0) {

            die();

            return true;
        } else {

            if (currentState.equals(EnemyState.RUN)) {

                setCurrentState(EnemyState.DAMAGED);
            }
        }
        return false;
    }

    public boolean isDead() {
        return currentState.equals(EnemyState.DYING);
    }

    public void loadAnimation(Texture texture, int rows, int cols, EnemyState enemyState) {

        animations.put(enemyState, CardTDGame.loadAnimation(texture, rows, cols));
    }

    public void die() {

        setCurrentState(EnemyState.DYING);

        hp = -1;
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

    public abstract float getFrameWidth();

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
