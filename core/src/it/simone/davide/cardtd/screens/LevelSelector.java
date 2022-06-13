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

/**
 * The level selector screen
 */
public class LevelSelector implements Screen, InputProcessor {

    /**
     * The stage to set a responsive background
     */
    private final Stage fillstage;

    /**
     * The main stage where there are all the graphic components
     */
    private final Stage fitstage;

    /**
     * Creates a new level selector screen
     *
     * @param backscreen which screen to return to when exiting the options menu
     */
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

        TextureRegionDrawable PrimoLivello = new TextureRegionDrawable(CardTDGame.assetManager.<Texture>get(StaticVariables.FIRSTLEVELICON));
        TextureRegionDrawable PrimoLivelloP = new TextureRegionDrawable(CardTDGame.assetManager.<Texture>get(StaticVariables.FIRSTLEVELICON_PRESSED));
        Button firstLevel = new Button(PrimoLivello, PrimoLivelloP);
        firstLevel.setSize(100, 100);
        firstLevel.setPosition(fitstage.getWidth() / 2f - firstLevel.getWidth() / 2 - 150, fitstage.getHeight() / 2f - firstLevel.getHeight() / 2 + 75);
        firstLevel.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                CardTDGame.INSTANCE.setScreen(new FirstMap(CardTDGame.assetManager.<Texture>get(StaticVariables.FIRSTMAP), CardTDGame.assetManager.<TiledMap>get(StaticVariables.TMXMAP)));

            }
        });
        fitstage.addActor(firstLevel);

        TextureRegionDrawable SecondoLivello = new TextureRegionDrawable(CardTDGame.assetManager.<Texture>get(StaticVariables.SECONDLEVELICON));
        TextureRegionDrawable SecondoLivelloP = new TextureRegionDrawable(CardTDGame.assetManager.<Texture>get(StaticVariables.SECONDLEVELICON_PRESSED));
        Button secondLevel = new Button(SecondoLivello, SecondoLivelloP);
        secondLevel.setSize(100, 100);
        secondLevel.setPosition(fitstage.getWidth() / 2f - secondLevel.getWidth() / 2, fitstage.getHeight() / 2f - secondLevel.getHeight() / 2 + 75);
        secondLevel.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                CardTDGame.INSTANCE.setScreen(new NotImplementedYet(new LevelSelector(new MainMenu())));

            }
        });
        fitstage.addActor(secondLevel);

        TextureRegionDrawable TerzoLivello = new TextureRegionDrawable(CardTDGame.assetManager.<Texture>get(StaticVariables.THIRDLEVELICON));
        TextureRegionDrawable TerzoLivelloP = new TextureRegionDrawable(CardTDGame.assetManager.<Texture>get(StaticVariables.THIRDLEVELICON_PRESSED));
        Button thirdLevel = new Button(TerzoLivello, TerzoLivelloP);
        thirdLevel.setSize(100, 100);
        thirdLevel.setPosition(fitstage.getWidth() / 2f - thirdLevel.getWidth() / 2 + 150, fitstage.getHeight() / 2f - thirdLevel.getHeight() / 2 + 75);
        thirdLevel.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                CardTDGame.INSTANCE.setScreen(new NotImplementedYet(new LevelSelector(new MainMenu())));

            }
        });
        fitstage.addActor(thirdLevel);

        TextureRegionDrawable QuartoLivello = new TextureRegionDrawable(CardTDGame.assetManager.<Texture>get(StaticVariables.FOURTHLEVELICON));
        TextureRegionDrawable QuartoLivelloP = new TextureRegionDrawable(CardTDGame.assetManager.<Texture>get(StaticVariables.FOURTHLEVELICON_PRESSED));
        Button fourthLevel = new Button(QuartoLivello, QuartoLivelloP);
        fourthLevel.setSize(100, 100);
        fourthLevel.setPosition(fitstage.getWidth() / 2f - fourthLevel.getWidth() / 2 - 150, fitstage.getHeight() / 2f - fourthLevel.getHeight() / 2 - 75);
        fourthLevel.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                CardTDGame.INSTANCE.setScreen(new NotImplementedYet(new LevelSelector(new MainMenu())));
            }
        });
        fitstage.addActor(fourthLevel);

        TextureRegionDrawable QuintoLivello = new TextureRegionDrawable(CardTDGame.assetManager.<Texture>get(StaticVariables.FIFTHLEVELICON));
        TextureRegionDrawable QuintoLivelloP = new TextureRegionDrawable(CardTDGame.assetManager.<Texture>get(StaticVariables.FIFTHLEVELICON_PRESSED));
        Button fifthLevel = new Button(QuintoLivello, QuintoLivelloP);
        fifthLevel.setSize(100, 100);
        fifthLevel.setPosition(fitstage.getWidth() / 2f - fifthLevel.getWidth() / 2, fitstage.getHeight() / 2f - fifthLevel.getHeight() / 2 - 75);
        fifthLevel.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                CardTDGame.INSTANCE.setScreen(new NotImplementedYet(new LevelSelector(new MainMenu())));
            }
        });
        fitstage.addActor(fifthLevel);

        TextureRegionDrawable SestoLivello = new TextureRegionDrawable(CardTDGame.assetManager.<Texture>get(StaticVariables.SIXTHLEVELICON));
        TextureRegionDrawable SestoLivelloP = new TextureRegionDrawable(CardTDGame.assetManager.<Texture>get(StaticVariables.SIXTHLEVELICON_PRESSED));
        Button sixthLevel = new Button(SestoLivello, SestoLivelloP);
        sixthLevel.setSize(100, 100);
        sixthLevel.setPosition(fitstage.getWidth() / 2f - sixthLevel.getWidth() / 2 + 150, fitstage.getHeight() / 2f - sixthLevel.getHeight() / 2 - 75);
        sixthLevel.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                CardTDGame.INSTANCE.setScreen(new NotImplementedYet(new LevelSelector(new MainMenu())));
            }
        });
        fitstage.addActor(sixthLevel);

    }

    /**
     * {inheritDoc}
     */
    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    /**
     * {inheritDoc}
     */
    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    /**
     * {inheritDoc}
     */
    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    /**
     * {inheritDoc}
     */
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    /**
     * {inheritDoc}
     */
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    /**
     * {inheritDoc}
     */
    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    /**
     * {inheritDoc}
     */
    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    /**
     * {inheritDoc}
     */
    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }

    /**
     * {inheritDoc}
     */
    @Override
    public void show() {

        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(fitstage);
        inputMultiplexer.addProcessor(fillstage);
        Gdx.input.setInputProcessor(inputMultiplexer);
        resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

    }

    /**
     * {inheritDoc}
     */
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

    /**
     * {inheritDoc}
     */
    @Override
    public void resize(int width, int height) {

        fillstage.getViewport().update(width, height, true);
        fitstage.getViewport().update(width, height, true);

    }

    /**
     * {inheritDoc}
     */
    @Override
    public void pause() {

    }

    /**
     * {inheritDoc}
     */
    @Override
    public void resume() {

    }

    /**
     * {inheritDoc}
     */
    @Override
    public void hide() {

    }

    /**
     * {inheritDoc}
     */
    @Override
    public void dispose() {

        fitstage.dispose();
        fillstage.dispose();

    }

    /**
     * Before exit save on the game preferences the modified values and go back to the "back screen"
     *
     * @param backscreen which screen to return to when exiting the options menu
     */
    public void onExit(Screen backscreen) {

        CardTDGame.INSTANCE.setScreen(backscreen);
    }
}
