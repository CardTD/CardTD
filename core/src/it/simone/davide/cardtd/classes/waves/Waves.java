package it.simone.davide.cardtd.classes.waves;

import com.badlogic.gdx.scenes.scene2d.Actor;
import it.simone.davide.cardtd.classes.Level;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Waves extends Actor {

    Level level;
    Queue<Wave> waves;
    Wave current;
    private boolean terminated = false;

    public boolean isTerminated() {
        return terminated;
    }

    public Waves(Level l, Wave... w) {
        level = l;
        waves = new LinkedList(Arrays.asList(w)) {
        };
        current = waves.poll();
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if (current != null && current.terminated) {
            current = waves.poll();

            if(current == null)
                terminated = true;
        }

        if (current != null && !current.launched) {

            current.onStage(level);

        }



    }
}
