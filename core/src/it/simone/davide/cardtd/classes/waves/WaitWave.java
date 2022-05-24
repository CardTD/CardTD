package it.simone.davide.cardtd.classes.waves;

import com.badlogic.gdx.utils.Timer;
import it.simone.davide.cardtd.classes.Level;
import it.simone.davide.cardtd.fontmanagement.FontType;
import it.simone.davide.cardtd.fontmanagement.LabelAdapter;

public class WaitWave extends Wave {

    public int initialCountDown;
    private boolean timer = false;
    LabelAdapter timerwait, timerlabl;

    public WaitWave(int time) {

        initialCountDown = time;

    }

    public WaitWave(int time, boolean timer) {

        initialCountDown = time;
        this.timer = timer;

    }

    @Override
    public void onStage(Level l) {
        launched = true;

        final Timer.Task initialTimerTask = new Timer.Task() {
            @Override
            public void run() {
                initialCountDown--;
                timerwait.setText(initialCountDown);
                if (initialCountDown == 0) {
                    terminated = true;
                    timerwait.setVisible(false);
                    timerlabl.setVisible(false);
                    cancel();
                }

            }

        };
        Timer.schedule(initialTimerTask, 1f, 1f);

        if (timer) {

            timerlabl = new LabelAdapter("Next Wave in", FontType.MONEY);
            timerwait = new LabelAdapter(initialCountDown + "", FontType.MONEY);
            timerlabl.toStage(l.getOverlaystage(), l.getOverlaystage().getWidth() / 2f - (timerlabl.getWidth() + timerwait.getWidth()) / 2, l.getOverlaystage().getHeight() - timerlabl.getHeight());
            timerwait.toStage(l.getOverlaystage(), l.getOverlaystage().getWidth() / 2f - (timerlabl.getWidth() + timerwait.getWidth()) / 2 + timerlabl.getWidth() + 10, l.getOverlaystage().getHeight() - timerwait.getHeight());
        } else {
            timerwait.setVisible(false);
            timerlabl.setVisible(false);
        }

    }
}
