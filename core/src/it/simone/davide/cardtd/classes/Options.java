package it.simone.davide.cardtd.classes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import it.simone.davide.cardtd.CardTDGame;
import it.simone.davide.cardtd.StaticVariables;

public class Options {

    Music music;
    float fxVolume;
    float audioVolume;

    public Options() {
        Preferences prefs = Gdx.app.getPreferences("Options");
        if (prefs.contains("Music"))
            audioVolume = prefs.getFloat("Music");
        else
            audioVolume = 0.5f;
        fxVolume = prefs.getFloat("FX");
        setMusic((Music) CardTDGame.assetManager.get(StaticVariables.BackgroundMusic));
    }

    public void setVolume(float audioVolume) {
        music.setVolume(audioVolume);
    }

    public void setVolumePref() {
        Preferences prefs = Gdx.app.getPreferences("Options");
        prefs.putFloat("Music", music.getVolume());
        prefs.putFloat("FX", fxVolume);
        prefs.flush();
    }
//Todo  Menù di pausa in game, balance, vita del castello, Interfaccia della partita, modificare le torri (skin e animazioni), FX(?), PinchToZoom, fuoriscena,


    public void setMusic(Music music) {
        if(this.music != music) {
            if (this.music != null)
                this.music.stop();
            this.music = music;
            this.music.setVolume(audioVolume);
            this.music.setLooping(true);
            this.music.play();
        }
    }

    public float getVolume() {
        return music.getVolume();
    }

    public float getFxVolume() {
        return fxVolume;
    }

    public void setFxVolume(float fxVolume) {
        this.fxVolume = fxVolume;
    }

}
