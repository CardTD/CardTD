package it.simone.davide.cardtd.classes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import it.simone.davide.cardtd.CardTDGame;
import it.simone.davide.cardtd.StaticVariables;

public class Options {

    Music music;

    float fxVolume;

    public Options(){
        float audioVolume;
        Preferences prefs = Gdx.app.getPreferences("Options");
        audioVolume = prefs.getFloat("Music");
        fxVolume = prefs.getFloat("FX");

        music = CardTDGame.assetManager.get(StaticVariables.BackgroundMusic);
        music.setVolume(audioVolume);
        music.setLooping(true);
        music.play();
    }

    public void setVolume(float audioVolume){
        music.setVolume(audioVolume);
    }

    public void setVolumePref(){
        Preferences prefs = Gdx.app.getPreferences("Options");
        prefs.putFloat("Music", music.getVolume());
        prefs.putFloat("FX", fxVolume);
        prefs.flush();
    }

    public float getVolume(){
        return music.getVolume();
    }

    public float getFxVolume(){
        return fxVolume;
    }

    public void setFxVolume(float fxVolume){
        this.fxVolume = fxVolume;
    }

}
