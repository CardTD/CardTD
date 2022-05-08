package it.simone.davide.cardtd.classes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
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

    public Enemy(int hp, int damage, int speed, int moneyonkill, int attackDimension) {

        this.hp = hp;
        this.damage = damage;
        this.speed = speed;
        this.moneyonkill = moneyonkill;
        animations = new HashMap<>();
        this.attackDimension = attackDimension;
        loadAnimations();


    }

    public void flip() {


        for (Map.Entry<EnemyState, Animation<TextureRegion>> i : animations.entrySet()) {

            for (TextureRegion f : i.getValue().getKeyFrames()) {

                f.flip(true, false);



            }
        }

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

                    path.move(delta, getX(), getY(), this);

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
                    currentRegion = animations.get(EnemyState.ATTACK).getKeyFrame(time, true);
                    break;

                case DAMAGED:
                    if (animations.get(EnemyState.DAMAGED).isAnimationFinished(time)) {
                        if (!isDead())
                            setCurrentState(EnemyState.RUN);
                    }
                    path.move(delta, getX(), getY(), this);


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
        return currentState.equals(EnemyState.DYING);
    }

    public void loadAnimation(Texture texture, int rows, int cols, EnemyState enemyState) {

        TextureRegion[][] tmp = TextureRegion.split(texture, texture.getWidth() /
                cols, texture.getHeight() / rows);

        TextureRegion[] frames = new TextureRegion[cols * rows];
        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                frames[index++] = tmp[i][j];
            }
        }
        Animation<TextureRegion> anim = new Animation<>(0.2f, frames);

        animations.put(enemyState, anim);
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
