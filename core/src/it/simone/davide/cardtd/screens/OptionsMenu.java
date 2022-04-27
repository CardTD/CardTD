package it.simone.davide.cardtd.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.actions.ScaleToAction;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
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

    public OptionsMenu() {
        skin = new Skin();
        stage = new Stage(new FitViewport(StaticVariables.SCREEN_WIDTH, StaticVariables.SCREEN_HEIGHT));
        Gdx.input.setInputProcessor(stage);

        skin = new Skin();
        skinButton = new Skin();

        music = CardTDGame.assetManager.get(StaticVariables.BackgroundMusic);
        music.setVolume(0.1f);
        music.setLooping(true);
        music.play();

        Texture bg = CardTDGame.assetManager.get(StaticVariables.MAIN_MENU_IMG);
        Table table = new Table();
        table.setFillParent(true);
        table.background(new TextureRegionDrawable(new TextureRegion(bg)));

        stage.addActor(table);

        LabelAdapter slider = new LabelAdapter("Slider", FontType.LOGO);
        slider.toStage(stage, StaticVariables.SCREEN_WIDTH / 2f - slider.getWidth() / 2, StaticVariables.SCREEN_HEIGHT / 2f - slider.getHeight() / 2);
        LabelAdapter button_on_off = new LabelAdapter("Music", FontType.LOGO);
        button_on_off.toStage(stage, StaticVariables.SCREEN_WIDTH / 2f - button_on_off.getWidth() / 2, StaticVariables.SCREEN_HEIGHT / 2f - button_on_off.getHeight() / 2 + 200);

        skin.add("sliderBack", CardTDGame.assetManager.get(StaticVariables.SliderBackground));
        skin.add("sliderKnob", CardTDGame.assetManager.get(StaticVariables.SliderKnob));

        uiSliderStyle.background = skin.getDrawable("sliderBack");
        uiSliderStyle.knob = skin.getDrawable("sliderKnob");

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

        TextureRegionDrawable b = new TextureRegionDrawable(CardTDGame.assetManager.<Texture>get(StaticVariables.BACKBUTTON));
        TextureRegionDrawable bp = new TextureRegionDrawable(CardTDGame.assetManager.<Texture>get(StaticVariables.BACKBUTTON_PRESSED));
        Button back = new Button(b, bp);
        back.setSize(80, 80);
        back.setPosition(1180, 635);
        back.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                onExit();
            }
        });
        stage.addActor(back);

    }

    @Override
    public void show() {

        resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

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
    public void onExit() {
        CardTDGame.INSTANCE.setScreen(new MainMenu());

        Preferences prefs = Gdx.app.getPreferences("deck");
        prefs.flush();

    }
}