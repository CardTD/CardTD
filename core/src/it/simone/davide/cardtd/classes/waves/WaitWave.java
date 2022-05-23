package it.simone.davide.cardtd.classes.waves;

import com.badlogic.gdx.utils.Timer;
import it.simone.davide.cardtd.classes.Level;

public class WaitWave extends Wave{

    public int initialCountDown;

    public WaitWave(int time) {

        initialCountDown = time;

    }

    @Override
    public void onStage(Level l) {
        launched = true;

        final Timer.Task initialTimerTask = new Timer.Task() {
            @Override
            public void run() {
                initialCountDown --;
                if(initialCountDown == 0){
                    terminated = true;
                    cancel();
                }

            }

        };
        Timer.schedule(initialTimerTask, 1f, 1f);

    }
}
