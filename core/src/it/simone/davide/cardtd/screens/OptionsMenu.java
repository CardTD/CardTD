package it.simone.davide.cardtd.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Slider.SliderStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import it.simone.davide.cardtd.CardTDGame;
import it.simone.davide.cardtd.StaticVariables;
import it.simone.davide.cardtd.fontmanagement.FontType;
import it.simone.davide.cardtd.fontmanagement.LabelAdapter;

/**
 * The option menu screen
 */
public class OptionsMenu implements Screen {

    private SliderStyle uiSliderStyle = new SliderStyle();
  
    private Stage stage, backgroundStage;
    private Skin skin, skinButton;
    private Slider audioSlider, fxSlider;

    public OptionsMenu(final Screen backscreen) {

        skin = new Skin();
        backgroundStage = new Stage(new FillViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        stage = new Stage(new FitViewport(StaticVariables.SCREEN_WIDTH, StaticVariables.SCREEN_HEIGHT));
        Gdx.input.setInputProcessor(stage);

        skin = new Skin();
        skinButton = new Skin();

        Texture bg = CardTDGame.assetManager.get(StaticVariables.MAIN_MENU_IMG);
        Table table = new Table();
        table.setFillParent(true);
        table.background(new TextureRegionDrawable(new TextureRegion(bg)));

        backgroundStage.addActor(table);

        LabelAdapter MusicSlider = new LabelAdapter("Music", FontType.LOGO);
        MusicSlider.toStage(stage, StaticVariables.SCREEN_WIDTH / 2f - MusicSlider.getWidth() / 2, StaticVariables.SCREEN_HEIGHT / 2f - MusicSlider.getHeight() / 2);
        LabelAdapter FxSlider = new LabelAdapter("FX", FontType.LOGO);
        FxSlider.toStage(stage, StaticVariables.SCREEN_WIDTH / 2f - MusicSlider.getWidth() / 2, StaticVariables.SCREEN_HEIGHT / 2f - MusicSlider.getHeight() / 2 - 200);
        LabelAdapter button_on_off = new LabelAdapter("Options", FontType.LOGO);
        button_on_off.toStage(stage, StaticVariables.SCREEN_WIDTH / 2f - button_on_off.getWidth() / 2, StaticVariables.SCREEN_HEIGHT / 2f - button_on_off.getHeight() / 2 + 200);

        skin.add("sliderBack", CardTDGame.assetManager.get(StaticVariables.SLIDER_BACKGROUND));
        skin.add("sliderKnob", CardTDGame.assetManager.get(StaticVariables.SLIDER_KNOB));

        uiSliderStyle.background = skin.getDrawable("sliderBack");
        uiSliderStyle.knob = skin.getDrawable("sliderKnob");

        fxSlider = new Slider(0, 1, 0.005f, false, uiSliderStyle);
        fxSlider.setVisualPercent(MainMenu.OPTIONS.getFxVolume());

        fxSlider.addListener(
                new ChangeListener() {
                    //@Override
                    public void changed(ChangeEvent event, Actor actor) {

                        MainMenu.OPTIONS.setFxVolume(fxSlider.getValue());
                    }
                }
        );

        audioSlider = new Slider(0, 1, 0.005f, false, uiSliderStyle);
        audioSlider.setVisualPercent(MainMenu.OPTIONS.getVolume());

        audioSlider.addListener(
                new ChangeListener() {
                    //@Override
                    public void changed(ChangeEvent event, Actor actor) {

                        MainMenu.OPTIONS.setVolume(audioSlider.getValue());
                    }
                }
        );

        audioSlider.setPosition(StaticVariables.SCREEN_WIDTH - 500, StaticVariables.SCREEN_HEIGHT - 500);
        audioSlider.setPosition((StaticVariables.SCREEN_WIDTH / 2f - audioSlider.getWidth() / 2) + 200, StaticVariables.SCREEN_HEIGHT / 2f - audioSlider.getHeight() / 2);
        stage.addActor(audioSlider);

        fxSlider.setPosition(StaticVariables.SCREEN_WIDTH - 500, StaticVariables.SCREEN_HEIGHT - 500);
        fxSlider.setPosition((StaticVariables.SCREEN_WIDTH / 2f - fxSlider.getWidth() / 2) + 200, StaticVariables.SCREEN_HEIGHT / 2f - fxSlider.getHeight() / 2 - 200);
        stage.addActor(fxSlider);

        TextureRegionDrawable b = new TextureRegionDrawable(CardTDGame.assetManager.<Texture>get(StaticVariables.BACKBUTTON));
        TextureRegionDrawable bp = new TextureRegionDrawable(CardTDGame.assetManager.<Texture>get(StaticVariables.BACKBUTTON_PRESSED));
        Button back = new Button(b, bp);
        back.setSize(80, 80);
        back.setPosition(stage.getWidth() - 100, stage.getHeight() - 100);
        back.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                onExit(backscreen);
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

        backgroundStage.getViewport().apply();
        backgroundStage.act(delta);
        backgroundStage.draw();

        stage.getViewport().apply();
        stage.act(delta);
        stage.draw();

    }

    @Override
    public void resize(int width, int height) {

        backgroundStage.getViewport().update(width, height, true);
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

    public void onExit(Screen backscreen) {

        MainMenu.OPTIONS.setVolumePref();
        CardTDGame.INSTANCE.setScreen(backscreen);
    }
}