package it.simone.davide.cardtd.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FitViewport;
import it.simone.davide.cardtd.CardTDGame;
import it.simone.davide.cardtd.StaticVariables;

public class MainMenu implements Screen {

    private Stage stage;

    @Override
    public void show() {
        stage = new Stage(new FitViewport(StaticVariables.SCREEN_WIDTH, StaticVariables.SCREEN_HEIGHT));
        stage.addActor(new Image(CardTDGame.assetManager.<Texture>get(StaticVariables.MAIN_MENU_IMG)));

    }

    @Override
    public void render(float delta) {
        stage.act();
        stage.draw();
        Batch stageBatch = stage.getBatch();
        stageBatch.begin();

        BitmapFont title = CardTDGame.assetManager.get(StaticVariables.MAIN_FONT, BitmapFont.class);
        final GlyphLayout layout = new GlyphLayout(title, StaticVariables.GAMENAME);
        title.draw(stage.getBatch(), StaticVariables.GAMENAME, StaticVariables.SCREEN_WIDTH / 2 - layout.width / 2, StaticVariables.SCREEN_HEIGHT / 2 - layout.height / 2+200);


        stageBatch.end();

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
        stage.dispose();

    }
}
