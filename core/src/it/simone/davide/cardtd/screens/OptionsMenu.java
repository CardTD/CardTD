package it.simone.davide.cardtd.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import it.simone.davide.cardtd.StaticVariables;
import com.badlogic.gdx.scenes.scene2d.ui.Slider.SliderStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;

public class OptionsMenu implements Screen {

    SliderStyle uiSliderStyle = new SliderStyle();
    float audioVolume;

    private Stage stage;
    Skin skin;
    Music music;
    Slider audioSlider;

    public OptionsMenu() {
        skin = new Skin();
        skin.add("sliderBack", new Texture(Gdx.files.internal("assets/background.png")));
        skin.add("sliderKnob", new Texture(Gdx.files.internal("assets/dotpiccolopiccolo.png")));
        skin.add("sliderAfter", new Texture(Gdx.files.internal("assets/background.png")));
        skin.add("sliderBefore", new Texture(Gdx.files.internal("assets/dotpiccolopiccolo.png")));
        stage = new Stage(new FitViewport(StaticVariables.SCREEN_WIDTH, StaticVariables.SCREEN_HEIGHT));
        Gdx.input.setInputProcessor(stage);


        music = Gdx.audio.newMusic(Gdx.files.internal("Background.wav"));
        music.setVolume(0.1f);
        music.setLooping(true);
        music.play();


        uiSliderStyle.background = skin.getDrawable("sliderBack");
        uiSliderStyle.knob = skin.getDrawable("sliderKnob");
        uiSliderStyle.knobAfter = skin.getDrawable("sliderAfter");
        uiSliderStyle.knobBefore = skin.getDrawable("sliderBefore");

    }

    @Override
    public void show() {
        audioSlider = new Slider(0, 1, 0.005f, false, uiSliderStyle);

        audioSlider.addListener(
                new ChangeListener() {
                    //@Override
                    public void changed(ChangeEvent event, Actor actor) {
                        audioVolume = audioSlider.getValue();
                        music.setVolume(audioVolume);
                    }
                }
        );
        audioSlider.setPosition(StaticVariables.SCREEN_WIDTH - 500, StaticVariables.SCREEN_HEIGHT - 500);
        stage.addActor(audioSlider);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
