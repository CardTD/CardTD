package it.simone.davide.cardtd.classes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;

/**
 * The player preferences
 */
public class Options {

    /**
     * The background music that is playing
     */
    private Music music;

    /**
     * The volume of fx sound
     */
    private float fxVolume;

    /**
     * The volume of music
     */
    private float audioVolume;

    /**
     * Create a new option object
     */
    public Options() {
        Preferences prefs = Gdx.app.getPreferences("Options");
        if (prefs.contains("Music")) audioVolume = prefs.getFloat("Music");
        else audioVolume = 0.5f;
        fxVolume = prefs.getFloat("FX");
    }

    /**
     * Sets the volume of the music
     *
     * @param audioVolume the volume
     */
    public void setVolume(float audioVolume) {
        this.audioVolume = audioVolume;
        music.setVolume(audioVolume);
        setVolumePref();
    }

    /**
     * Save the volume preferences into game preferences
     */
    public void setVolumePref() {
        Preferences prefs = Gdx.app.getPreferences("Options");
        prefs.putFloat("Music", getVolume());
        prefs.putFloat("FX", fxVolume);
        prefs.flush();
    }

    /**
     * Set the music that is playing
     *
     * @param music a music
     */
    public void setMusic(Music music) {
        if (this.music != music) {
            if (this.music != null) this.music.stop();
            this.music = music;
            this.music.setVolume(audioVolume);
            this.music.setLooping(true);
            this.music.play();
        }
    }

    /**
     * Returns the music volume
     *
     * @return the music volume
     */
    public float getVolume() {
        return audioVolume;
    }

    /**
     * Returns the fx volume
     *
     * @return the fx volume
     */
    public float getFxVolume() {
        return fxVolume;
    }

    /**
     * Set the fx volume
     *
     * @param fxVolume the fx volume
     */
    public void setFxVolume(float fxVolume) {
        this.fxVolume = fxVolume;
    }

}
