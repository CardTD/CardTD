package it.simone.davide.cardtd.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import it.simone.davide.cardtd.CardTDGame;
import it.simone.davide.cardtd.StaticVariables;
import com.badlogic.gdx.scenes.scene2d.ui.Slider.SliderStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import it.simone.davide.cardtd.fontmanagement.FontType;
import it.simone.davide.cardtd.fontmanagement.LabelAdapter;

public class OptionsMenu implements Screen {

    SliderStyle uiSliderStyle = new SliderStyle();
    float audioVolume;

    private Stage stage;
    Skin skin, skinButton;
    Music music;
    Slider audioSlider;

    TextureAtlas atlas = new TextureAtlas();

    public OptionsMenu() {
        skin = new Skin();
        skin.add("sliderBack", new Texture(Gdx.files.internal("assets/background.png")));
        skin.add("sliderKnob", new Texture(Gdx.files.internal("assets/dotpiccolopiccolo.png")));
        skin.add("sliderAfter", new Texture(Gdx.files.internal("assets/background.png")));
        skin.add("sliderBefore", new Texture(Gdx.files.internal("assets/dotpiccolopiccolo.png")));
        stage = new Stage(new FitViewport(StaticVariables.SCREEN_WIDTH, StaticVariables.SCREEN_HEIGHT));
        Gdx.input.setInputProcessor(stage);

        skin = new Skin();
        skinButton = new Skin();

        music = Gdx.audio.newMusic(Gdx.files.internal("Background.wav"));
        music.setVolume(0.1f);
        music.setLooping(true);
        music.play();

        Texture bg = CardTDGame.assetManager.get(StaticVariables.MAIN_MENU_IMG);
        Table table = new Table();
        table.setFillParent(true);
        table.background(new TextureRegionDrawable(new TextureRegion(bg)));

     /*   atlas = new TextureAtlas("assets/ui/button.pack");
        skinButton = new Skin(atlas);

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skinButton.getDrawable("button.up");
        textButtonStyle.down = skinButton.getDrawable("button.down");
        textButtonStyle.pressedOffsetX = 1;
        textButtonStyle.pressedOffsetY = -1;
        TextButton buttonPlay = new TextButton("ON", textButtonStyle);
        buttonPlay.setPosition(110,260);
        buttonPlay.setSize(280,60);
        table.add(buttonPlay);
        table.debug();*/

        stage.addActor(table);

        LabelAdapter slider = new LabelAdapter("Slider", FontType.LOGO);
        slider.toStage(stage, StaticVariables.SCREEN_WIDTH / 2f - slider.getWidth() / 2, StaticVariables.SCREEN_HEIGHT / 2f - slider.getHeight() / 2);
        LabelAdapter button_on_off = new LabelAdapter("Music", FontType.LOGO);
        button_on_off.toStage(stage, StaticVariables.SCREEN_WIDTH / 2f - button_on_off.getWidth() / 2, StaticVariables.SCREEN_HEIGHT / 2f - button_on_off.getHeight() / 2 + 200);

        skin.add("sliderBack", new Texture(Gdx.files.internal("assets/slider-Back.png")));
        skin.add("sliderKnob", new Texture(Gdx.files.internal("assets/slider-dot.png")));

        uiSliderStyle.background = skin.getDrawable("sliderBack");
        uiSliderStyle.knob = skin.getDrawable("sliderKnob");
        uiSliderStyle.knobAfter = skin.getDrawable("sliderAfter");
        uiSliderStyle.knobBefore = skin.getDrawable("sliderBefore");

    }

    @Override
    public void show() {

        resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

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
        audioSlider.setPosition((StaticVariables.SCREEN_WIDTH / 2f - audioSlider.getWidth() / 2) + 200, StaticVariables.SCREEN_HEIGHT / 2f - audioSlider.getHeight() / 2);
        //audioSlider.setPosition(StaticVariables.SCREEN_WIDTH - 120, StaticVariables.SCREEN_HEIGHT - 120);
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


        stage.getViewport().update(width, height, true);

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