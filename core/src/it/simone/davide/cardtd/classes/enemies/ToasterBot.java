package it.simone.davide.cardtd.classes.enemies;

import com.badlogic.gdx.graphics.Texture;
import it.simone.davide.cardtd.CardTDGame;
import it.simone.davide.cardtd.StaticVariables;
import it.simone.davide.cardtd.classes.Enemy;
import it.simone.davide.cardtd.enums.EnemyState;

/**
 * This class manages the enemy ToasterBot
 *
 * @see Enemy
 */
public class ToasterBot extends Enemy {

    /**
     * Create a new enemy ToasterBot
     *
     * @param hp              the hp of the enemy
     * @param damage          the damage it does to the main tower
     * @param speed           the speed of the enemy
     * @param moneyonkill     the money returned to his death
     * @param attackDimension the size (in pixels) of its attack range
     */
    public ToasterBot(int hp, int damage, int speed, int moneyonkill, int attackDimension) {

        super(hp, damage, speed, moneyonkill, attackDimension);
    }

    /**
     * Loading the enemy’s animations (frame by frame)
     */
    @Override
    public void loadAnimations() {
        int rows = 1, cols = 5;

        loadAnimation(CardTDGame.assetManager.<Texture>get(StaticVariables.ToasterBorIDLE), rows, cols, EnemyState.IDLE);

        cols = 8;

        loadAnimation(CardTDGame.assetManager.<Texture>get(StaticVariables.ToasterBorRun), rows, cols, EnemyState.RUN);

        cols = 11;

        loadAnimation(CardTDGame.assetManager.<Texture>get(StaticVariables.ToasterBorAttack), rows, cols, EnemyState.ATTACK);

        cols = 8;

        loadAnimation(CardTDGame.assetManager.<Texture>get(StaticVariables.ToasterBorDeath), rows, cols, EnemyState.DYING);

        cols = 2;

        loadAnimation(CardTDGame.assetManager.<Texture>get(StaticVariables.ToasterBorDamaged), rows, cols, EnemyState.DAMAGED);

        setCurrentState(EnemyState.RUN);
    }

    /**
     * Returns the height (in pixels) of the enemy
     *
     * @return the height (in pixels) of the enemy
     */
    @Override
    public float getHeight() {
        return 66;
    }

    /**
     * Returns the width (in pixels) of the enemy
     *
     * @return the width (in pixels) of the enemy
     */
    @Override
    public float getWidth() {
        return 100;
    }

    /**
     * Return of the size of the entire frame of the enemy’s animation
     *
     * @return of the size of the entire frame of the enemy’s animation
     */
    @Override
    public float getFrameWidth() {
        return 310;
    }

    /**
     * Create a shallow copy of the ToasterBot enemy
     *
     * @return the shallowed copy
     */
    @Override
    public Enemy clone() {
        return new ToasterBot(getHp(), getDamage(), getSpeed(), getMoneyonkill(), getAttackDimension());
    }

}
