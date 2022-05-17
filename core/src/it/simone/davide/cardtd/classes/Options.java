package it.simone.davide.cardtd.classes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;

public class Options {

    private Music music;
    private float fxVolume;
    private float audioVolume;

    public Options() {
        Preferences prefs = Gdx.app.getPreferences("Options");
        if (prefs.contains("Music")) audioVolume = prefs.getFloat("Music");
        else audioVolume = 0.5f;
        fxVolume = prefs.getFloat("FX");
    }

    public void setVolume(float audioVolume) {
        this.audioVolume = audioVolume;
        music.setVolume(audioVolume);
        setVolumePref();
    }

    public void setVolumePref() {
        Preferences prefs = Gdx.app.getPreferences("Options");
        prefs.putFloat("Music", getVolume());
        prefs.putFloat("FX", fxVolume);
        prefs.flush();
    }
//Todo  balance, vita del castello, modificare le torri (skin e animazioni), FX(?), PinchToZoom, fuoriscena, creare le wave

    public void setMusic(Music music) {
        if (this.music != music) {
            if (this.music != null) this.music.stop();
            this.music = music;
            this.music.setVolume(audioVolume);
            this.music.setLooping(true);
            this.music.play();
        }
    }

    public float getVolume() {
        return audioVolume;
    }

    public float getFxVolume() {
        return fxVolume;
    }

    public void setFxVolume(float fxVolume) {
        this.fxVolume = fxVolume;
    }

}
