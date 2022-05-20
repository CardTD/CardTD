package it.simone.davide.cardtd.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import it.simone.davide.cardtd.CardTDGame;
import it.simone.davide.cardtd.StaticVariables;
import it.simone.davide.cardtd.classes.levels.FirstMap;
import it.simone.davide.cardtd.fontmanagement.FontType;
import it.simone.davide.cardtd.fontmanagement.LabelAdapter;

public class LevelSelector implements Screen, InputProcessor {

    private final Stage fillstage, fitstage;
    private Button FirstLevel, SecondLevel, ThirdLevel, FourthLevel, FifthLevel, SixthLevel;


    public LevelSelector(final Screen backscreen) {
        fillstage = new Stage(new FillViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        fitstage = new Stage(new FitViewport(StaticVariables.SCREEN_WIDTH, StaticVariables.SCREEN_HEIGHT));
        Texture bg = CardTDGame.assetManager.get(StaticVariables.MAIN_MENU_IMG);
        Table table = new Table();
        table.setFillParent(true);
        table.background(new TextureRegionDrawable(new TextureRegion(bg)));

        fillstage.addActor(table);

        LabelAdapter select_level = new LabelAdapter("Select Level", FontType.OPTIONS);
        select_level.toStage(fitstage, StaticVariables.SCREEN_WIDTH / 2f - select_level.getWidth() / 2, StaticVariables.SCREEN_HEIGHT / 2f - select_level.getHeight() / 2 + 200);

        TextureRegionDrawable b = new TextureRegionDrawable(CardTDGame.assetManager.<Texture>get(StaticVariables.BACKBUTTON));
        TextureRegionDrawable bp = new TextureRegionDrawable(CardTDGame.assetManager.<Texture>get(StaticVariables.BACKBUTTON_PRESSED));
        Button back = new Button(b, bp);
        back.setSize(80, 80);
        back.setPosition(fitstage.getWidth() - 100, fitstage.getHeight() - 100);
        back.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                onExit(backscreen);
            }
        });
        fitstage.addActor(back);

        TextureRegionDrawable PrimoLivello = new TextureRegionDrawable(CardTDGame.assetManager.<Texture>get(StaticVariables.PRIMOLIVELLOICO));
        TextureRegionDrawable PrimoLivelloP = new TextureRegionDrawable(CardTDGame.assetManager.<Texture>get(StaticVariables.PRIMOLIVELLOICOP));
        FirstLevel = new Button(PrimoLivello, PrimoLivelloP);
        FirstLevel.setSize(100, 100);
        FirstLevel.setPosition(fitstage.getWidth() / 2f - FirstLevel.getWidth() / 2 - 150, fitstage.getHeight() / 2f - FirstLevel.getHeight() / 2 + 75);
        FirstLevel.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                CardTDGame.INSTANCE.setScreen(new FirstMap(CardTDGame.assetManager.<Texture>get(StaticVariables.FIRSTMAP), CardTDGame.assetManager.<TiledMap>get(StaticVariables.TMXMAP)));

            }
        });
        fitstage.addActor(FirstLevel);

        TextureRegionDrawable SecondoLivello = new TextureRegionDrawable(CardTDGame.assetManager.<Texture>get(StaticVariables.SECONDOLIVELLOICO));
        TextureRegionDrawable SecondoLivelloP = new TextureRegionDrawable(CardTDGame.assetManager.<Texture>get(StaticVariables.SECONDOLIVELLOICOP));
        SecondLevel = new Button(SecondoLivello, SecondoLivelloP);
        SecondLevel.setSize(100, 100);
        SecondLevel.setPosition(fitstage.getWidth() / 2f - SecondLevel.getWidth() / 2, fitstage.getHeight() / 2f - SecondLevel.getHeight() / 2 + 75);
        SecondLevel.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                CardTDGame.INSTANCE.setScreen(new NotImplementedYet(new LevelSelector(new MainMenu())));

            }
        });
        fitstage.addActor(SecondLevel);

        TextureRegionDrawable TerzoLivello = new TextureRegionDrawable(CardTDGame.assetManager.<Texture>get(StaticVariables.TERZOLIVELLOICO));
        TextureRegionDrawable TerzoLivelloP = new TextureRegionDrawable(CardTDGame.assetManager.<Texture>get(StaticVariables.TERZOLIVELLOICOP));
        ThirdLevel = new Button(TerzoLivello, TerzoLivelloP);
        ThirdLevel.setSize(100, 100);
        ThirdLevel.setPosition(fitstage.getWidth() / 2f - ThirdLevel.getWidth() / 2 + 150, fitstage.getHeight() / 2f - ThirdLevel.getHeight() / 2 + 75);
        ThirdLevel.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                CardTDGame.INSTANCE.setScreen(new NotImplementedYet(new LevelSelector(new MainMenu())));

            }
        });
        fitstage.addActor(ThirdLevel);

        TextureRegionDrawable QuartoLivello = new TextureRegionDrawable(CardTDGame.assetManager.<Texture>get(StaticVariables.QUARTOLIVELLOICO));
        TextureRegionDrawable QuartoLivelloP = new TextureRegionDrawable(CardTDGame.assetManager.<Texture>get(StaticVariables.QUARTOLIVELLOICOP));
        FourthLevel = new Button(QuartoLivello, QuartoLivelloP);
        FourthLevel.setSize(100, 100);
        FourthLevel.setPosition(fitstage.getWidth() / 2f - FourthLevel.getWidth() / 2 - 150, fitstage.getHeight() / 2f - FourthLevel.getHeight() / 2 -75);
        FourthLevel.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                CardTDGame.INSTANCE.setScreen(new NotImplementedYet(new LevelSelector(new MainMenu())));
            }
        });
        fitstage.addActor(FourthLevel);

        TextureRegionDrawable QuintoLivello = new TextureRegionDrawable(CardTDGame.assetManager.<Texture>get(StaticVariables.QUINTOLIVELLOICO));
        TextureRegionDrawable QuintoLivelloP = new TextureRegionDrawable(CardTDGame.assetManager.<Texture>get(StaticVariables.QUINTOLIVELLOICOP));
        FifthLevel = new Button(QuintoLivello, QuintoLivelloP);
        FifthLevel.setSize(100, 100);
        FifthLevel.setPosition(fitstage.getWidth() / 2f - FifthLevel.getWidth() / 2, fitstage.getHeight() / 2f - FifthLevel.getHeight() / 2 - 75);
        FifthLevel.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                CardTDGame.INSTANCE.setScreen(new NotImplementedYet(new LevelSelector(new MainMenu())));
            }
        });
        fitstage.addActor(FifthLevel);

        TextureRegionDrawable SestoLivello = new TextureRegionDrawable(CardTDGame.assetManager.<Texture>get(StaticVariables.SESTOLIVELLOICO));
        TextureRegionDrawable SestoLivelloP = new TextureRegionDrawable(CardTDGame.assetManager.<Texture>get(StaticVariables.SESTOLIVELLOICOP));
        SixthLevel = new Button(SestoLivello, SestoLivelloP);
        SixthLevel.setSize(100, 100);
        SixthLevel.setPosition(fitstage.getWidth() / 2f - SixthLevel.getWidth() / 2 + 150, fitstage.getHeight() / 2f - SixthLevel.getHeight() / 2 - 75);
        SixthLevel.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                CardTDGame.INSTANCE.setScreen(new NotImplementedYet(new LevelSelector(new MainMenu())));
            }
        });
        fitstage.addActor(SixthLevel);


    }


    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }

    @Override
    public void show() {

        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(fitstage);
        inputMultiplexer.addProcessor(fillstage);
        Gdx.input.setInputProcessor(inputMultiplexer);
        resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        fillstage.getViewport().apply();
        fillstage.act(delta);
        fillstage.draw();

        fitstage.getViewport().apply();
        fitstage.act(delta);
        fitstage.draw();

    }

    @Override
    public void resize(int width, int height) {

        fillstage.getViewport().update(width, height, true);
        fitstage.getViewport().update(width, height, true);

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

        fitstage.dispose();
        fillstage.dispose();

    }

    public void onExit(Screen backscreen) {

        CardTDGame.INSTANCE.setScreen(backscreen);
    }
}
