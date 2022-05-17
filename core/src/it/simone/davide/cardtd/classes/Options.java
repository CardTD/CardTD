package it.simone.davide.cardtd.classes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import it.simone.davide.cardtd.CardTDGame;
import it.simone.davide.cardtd.StaticVariables;
import it.simone.davide.cardtd.screens.MainMenu;

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
    }

    public void setVolume(float audioVolume) {
        music.setVolume(audioVolume);
        setVolumePref();
    }

    public void setVolumePref() {
        Preferences prefs = Gdx.app.getPreferences("Options");
        prefs.putFloat("Music", music.getVolume());
        prefs.putFloat("FX", fxVolume);
        prefs.flush();
    }
//Todo  balance, vita del castello, modificare le torri (skin e animazioni), FX(?), PinchToZoom, fuoriscena, creare le wave

    public void setMusic(Music music) {
        if (this.music != music) {
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
