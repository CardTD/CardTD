package it.simone.davide.cardtd.classes.waves;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Timer;
import it.simone.davide.cardtd.classes.Level;
import it.simone.davide.cardtd.fontmanagement.FontType;
import it.simone.davide.cardtd.fontmanagement.LabelAdapter;

public class TitleWave extends Wave{

    LabelAdapter label;
    private int initialCountDown = 4;

    public TitleWave(String s, FontType f) {

        label = new LabelAdapter(s, f);

    }

    @Override
    public void onStage(Level l) {
        launched = true;
        Stage overlaystage = l.getOverlaystage();
        label.toStage(overlaystage, overlaystage.getWidth() / 2f - label.getWidth() / 2, overlaystage.getHeight() / 2f - label.getHeight() / 2);

        final Timer.Task initialTimerTask = new Timer.Task() {
            @Override
            public void run() {
                initialCountDown --;
                if(initialCountDown == 0){
                    label.remove();
                    terminated = true;
                    cancel();
                }

                }

        };
        Timer.schedule(initialTimerTask, 1f, 1f);


    }
}
