package it.simone.davide.cardtd.classes.waves;

import it.simone.davide.cardtd.classes.Level;

public abstract class Wave {

    protected boolean terminated = false;
    protected boolean launched = false;

    public abstract void onStage(Level l);
}
