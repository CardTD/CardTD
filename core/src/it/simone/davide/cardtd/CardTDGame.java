package it.simone.davide.cardtd;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import it.simone.davide.cardtd.fontmanagement.FontType;
import it.simone.davide.cardtd.screens.MainMenu;

public class CardTDGame extends Game {

    public static AssetManager assetManager;
    public static Game INSTANCE;

    @Override
    public void create() {
        INSTANCE = this;
        assetManager = new AssetManager();

        // set the font loaders
        FileHandleResolver resolver = new InternalFileHandleResolver();
        assetManager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
        assetManager.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));
        assetManager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));

        loadAssets();

        assetManager.finishLoading();
        new GameObjects();
        setScreen(new MainMenu());

    }

    private void loadAssets() {

        //load main menu bg
        assetManager.load(StaticVariables.MAIN_MENU_IMG, Texture.class);

        assetManager.load(StaticVariables.CARDSLOT, Texture.class);
        assetManager.load(StaticVariables.NAVE, Texture.class);
        assetManager.load(StaticVariables.DECKMENU, Texture.class);
        assetManager.load(StaticVariables.DECKBG, Texture.class);
        assetManager.load(StaticVariables.BACKBUTTON, Texture.class);
        assetManager.load(StaticVariables.BACKBUTTON_PRESSED, Texture.class);
        assetManager.load(StaticVariables.PAUSEBUTTON, Texture.class);
        assetManager.load(StaticVariables.PAUSEBUTTON_PRESSED, Texture.class);
        assetManager.load(StaticVariables.RESUMEBUTTON, Texture.class);
        assetManager.load(StaticVariables.RESUMEBUTTON_PRESSED, Texture.class);
        assetManager.load(StaticVariables.RELOADBUTTON, Texture.class);
        assetManager.load(StaticVariables.RELOADBUTTON_PRESSED, Texture.class);
        assetManager.load(StaticVariables.OPTIONBUTTON, Texture.class);
        assetManager.load(StaticVariables.OPTIONBUTTON_PRESSED, Texture.class);
        assetManager.load(StaticVariables.HOMEBUTTON, Texture.class);
        assetManager.load(StaticVariables.HOMEBUTTON_PRESSED, Texture.class);

        assetManager.load(StaticVariables.FIRSTMAP, Texture.class);
        assetManager.load(StaticVariables.PLACEDTOWER_PNG, Texture.class);
        assetManager.load(StaticVariables.TMXMAP, TiledMap.class);
        assetManager.load(StaticVariables.MAP_OVERLAY, Texture.class);

        assetManager.load(StaticVariables.ToasterBorIDLE, Texture.class);
        assetManager.load(StaticVariables.ToasterBorAttack, Texture.class);
        assetManager.load(StaticVariables.ToasterBorDeath, Texture.class);
        assetManager.load(StaticVariables.ToasterBorRun, Texture.class);
        assetManager.load(StaticVariables.ToasterBorDamaged, Texture.class);

        assetManager.load(StaticVariables.StormHeadBorIDLE, Texture.class);
        assetManager.load(StaticVariables.StormHeadAttack, Texture.class);
        assetManager.load(StaticVariables.StormHeadDeath, Texture.class);
        assetManager.load(StaticVariables.StormHeadRun, Texture.class);
        assetManager.load(StaticVariables.StormHeadDamaged, Texture.class);

        assetManager.load(StaticVariables.SpiritBoxerIDLE, Texture.class);
        assetManager.load(StaticVariables.SpiritBoxerAttack, Texture.class);
        assetManager.load(StaticVariables.SpiritBoxerDeath, Texture.class);
        assetManager.load(StaticVariables.SpiritBoxerRun, Texture.class);
        assetManager.load(StaticVariables.SpiritBoxerDamaged, Texture.class);
        assetManager.load(StaticVariables.COIN, Texture.class);

        assetManager.load(StaticVariables.TOWER, Texture.class);

        assetManager.load(StaticVariables.SliderBackground, Texture.class);
        assetManager.load(StaticVariables.SliderKnob, Texture.class);
        assetManager.load(StaticVariables.BackgroundMusic, Music.class);
        assetManager.load(StaticVariables.FirstMapSound, Music.class);

        assetManager.load(StaticVariables.ROCK, Texture.class);

        //load font for the title
        FontType.loadFonts();

    }

    public static Animation loadAnimation(Texture texture, int rows, int cols) {

        return loadAnimation(texture, rows, cols, 0.2f);
    }

    public static Animation loadAnimation(Texture texture, int rows, int cols, float frameDuration) {

        TextureRegion[][] tmp = TextureRegion.split(texture, texture.getWidth() /
                cols, texture.getHeight() / rows);

        TextureRegion[] frames = new TextureRegion[cols * rows];
        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                frames[index++] = tmp[i][j];
            }
        }
        Animation<TextureRegion> anim = new Animation<>(frameDuration, frames);

        return anim;
    }
}
