package it.simone.davide.cardtd.classes.waves;

import com.badlogic.gdx.utils.Timer;
import it.simone.davide.cardtd.classes.Level;
import it.simone.davide.cardtd.enums.EnemyType;

public class MonsterWave extends Wave {

    private EnemyType Enemy;
    private int numEnemy;

    public MonsterWave(EnemyType enemy, int numEnemy) {
        Enemy = enemy;
        this.numEnemy = numEnemy;


    }

    public void onStage(final Level l) {
        launched = true;
        final Timer.Task initialTimerTask = new Timer.Task() {
            @Override
            public void run() {
                l.addEnemy(Enemy);
                numEnemy--;
                if (numEnemy == 0) {
                    terminated = true;
                    cancel();
                }
            }
        };
        Timer.schedule(initialTimerTask, 1f, 0.5f);




    }

}
