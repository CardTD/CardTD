package it.simone.davide.cardtd.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FillViewport;
import it.simone.davide.cardtd.CardTDGame;
import it.simone.davide.cardtd.StaticVariables;
import it.simone.davide.cardtd.fontmanagement.FontType;
import it.simone.davide.cardtd.fontmanagement.LabelAdapter;

/**
 * Placeholder screen for not implemented screens
 */
public class NotImplementedYet implements Screen {

    /**
     * The stage of the screen
     */
    private final Stage fillstage;

    /**
     * Create a new NotImplementedYet screen
     *
     * @param backscreen which screen to return to when exiting the options menu
     */
    public NotImplementedYet(final Screen backscreen) {

        fillstage = new Stage(new FillViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        Texture bg = CardTDGame.ASSETSMANAGER.get(StaticVariables.MAIN_MENU_IMG);
        Table table = new Table();
        table.setFillParent(true);
        table.background(new TextureRegionDrawable(new TextureRegion(bg)));

        fillstage.addActor(table);

        LabelAdapter select_level = new LabelAdapter("NOT IMPLEMENTED YET", FontType.OPTIONS);
        select_level.toStage(fillstage, fillstage.getWidth() / 2f - select_level.getWidth() / 2, fillstage.getHeight() / 2f - select_level.getHeight() / 2 + 200);

        TextureRegionDrawable b = new TextureRegionDrawable(CardTDGame.ASSETSMANAGER.<Texture>get(StaticVariables.BACKBUTTON));
        TextureRegionDrawable bp = new TextureRegionDrawable(CardTDGame.ASSETSMANAGER.<Texture>get(StaticVariables.BACKBUTTON_PRESSED));
        Button back = new Button(b, bp);
        back.setSize(100, 100);
        back.setPosition(fillstage.getWidth() - 150, fillstage.getHeight() - 150);
        back.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                onExit(backscreen);
            }
        });
        fillstage.addActor(back);

    }

    /**
     * {inheritDoc}
     */
    @Override
    public void show() {

        InputMultiplexer inputMultiplexer = new InputMultiplexer();
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

    }

    /**
     * {inheritDoc}
     */
    @Override
    public void resize(int width, int height) {

        fillstage.getViewport().update(width, height, true);

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
        fillstage.dispose();
    }

    /**
     * Before exit go back to the "back screen"
     *
     * @param backscreen which screen to return to when exiting the options menu
     */
    public void onExit(Screen backscreen) {

        CardTDGame.INSTANCE.setScreen(backscreen);
    }
}
