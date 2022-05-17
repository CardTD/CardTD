package it.simone.davide.cardtd.classes.enemies;

import com.badlogic.gdx.graphics.Texture;
import it.simone.davide.cardtd.CardTDGame;
import it.simone.davide.cardtd.StaticVariables;
import it.simone.davide.cardtd.classes.Enemy;
import it.simone.davide.cardtd.enums.EnemyState;

public class SpiritBoxer extends Enemy {
    public SpiritBoxer(int hp, int damage, int speed, int moneyonkill, int attackDimension) {
        super(hp, damage, speed, moneyonkill, attackDimension);
    }

    @Override
    public Enemy clone() {
        return new SpiritBoxer(getHp(), getDamage(), getSpeed(), getMoneyonkill(), getAttackDimension());
    }

    @Override
    public void loadAnimations() {
        int rows = 4, cols = 1;

        loadAnimation(CardTDGame.assetManager.<Texture>get(StaticVariables.SpiritBoxerIDLE), rows, cols, EnemyState.IDLE);

        rows = 6;

        loadAnimation(CardTDGame.assetManager.<Texture>get(StaticVariables.SpiritBoxerRun), rows, cols, EnemyState.RUN);

        rows = 10;

        loadAnimation(CardTDGame.assetManager.<Texture>get(StaticVariables.SpiritBoxerAttack), rows, cols, EnemyState.ATTACK);

        rows = 8;

        loadAnimation(CardTDGame.assetManager.<Texture>get(StaticVariables.SpiritBoxerDeath), rows, cols, EnemyState.DYING);

        rows = 4;

        loadAnimation(CardTDGame.assetManager.<Texture>get(StaticVariables.SpiritBoxerDamaged), rows, cols, EnemyState.DAMAGED);

        setCurrentState(EnemyState.RUN);
    }

    @Override
    public float getHeight() {
        return 26;
    }

    @Override
    public float getWidth() {
        return 36;
    }

    @Override
    public float getFrameWidth() {
        return 137;
    }
}
