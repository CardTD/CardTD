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

/**
 * The main class that creates the game objects and sets the screen with the main menu
 */
public class CardTDGame extends Game {

    /**
     * The assets' manager of the game
     */
    public static AssetManager assetManager;

    /**
     * The instance of the {@link Game} class
     */
    public static Game INSTANCE;

    /**
     * Creates the game objects and sets the screen with the main menu
     *
     * {@inheritDoc}
     */
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
        assetManager.load(StaticVariables.SHIP, Texture.class);
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
        assetManager.load(StaticVariables.TMXMAP, TiledMap.class);
        assetManager.load(StaticVariables.MAP_OVERLAY, Texture.class);

        assetManager.load(StaticVariables.FIRSTLEVELICON, Texture.class);
        assetManager.load(StaticVariables.FIRSTLEVELICON_PRESSED, Texture.class);
        assetManager.load(StaticVariables.SECONDLEVELICON, Texture.class);
        assetManager.load(StaticVariables.SECONDLEVELICON_PRESSED, Texture.class);
        assetManager.load(StaticVariables.THIRDLEVELICON, Texture.class);
        assetManager.load(StaticVariables.THIRDLEVELICON_PRESSED, Texture.class);
        assetManager.load(StaticVariables.FOURTHLEVELICON, Texture.class);
        assetManager.load(StaticVariables.FOURTHLEVELICON_PRESSED, Texture.class);
        assetManager.load(StaticVariables.FIFTHLEVELICON, Texture.class);
        assetManager.load(StaticVariables.FIFTHLEVELICON_PRESSED, Texture.class);
        assetManager.load(StaticVariables.SIXTHLEVELICON, Texture.class);
        assetManager.load(StaticVariables.SIXTHLEVELICON_PRESSED, Texture.class);

        assetManager.load(StaticVariables.TOASTER_IDLE, Texture.class);
        assetManager.load(StaticVariables.TOASTER_ATTACK, Texture.class);
        assetManager.load(StaticVariables.TOASTER_DEATH, Texture.class);
        assetManager.load(StaticVariables.TOASTER_RUN, Texture.class);
        assetManager.load(StaticVariables.TOASTER_DAMAGED, Texture.class);

        assetManager.load(StaticVariables.STORMHEAD_IDLE, Texture.class);
        assetManager.load(StaticVariables.STORMHEAD_ATTACK, Texture.class);
        assetManager.load(StaticVariables.STORMHEAD_DEATH, Texture.class);
        assetManager.load(StaticVariables.STORMHEAD_RUN, Texture.class);
        assetManager.load(StaticVariables.STORMHEAD_DAMAGED, Texture.class);

        assetManager.load(StaticVariables.SPIRITBOXER_IDLE, Texture.class);
        assetManager.load(StaticVariables.SPIRITBOXER_ATTACK, Texture.class);
        assetManager.load(StaticVariables.SPIRITBOXER_DEATH, Texture.class);
        assetManager.load(StaticVariables.SPIRITBOXER_RUN, Texture.class);
        assetManager.load(StaticVariables.SPIRITBOXER_DAMAGED, Texture.class);
        assetManager.load(StaticVariables.COIN, Texture.class);

        assetManager.load(StaticVariables.FIRETOWERCARD, Texture.class);
        assetManager.load(StaticVariables.ELETTROTOWERCARD, Texture.class);
        assetManager.load(StaticVariables.LASERTOWERCARD, Texture.class);
        assetManager.load(StaticVariables.SNOWTOWERCARD, Texture.class);
        assetManager.load(StaticVariables.DESERTTOWERCARD, Texture.class);

        assetManager.load(StaticVariables.FIRETOWERPLACED, Texture.class);
        assetManager.load(StaticVariables.ELETTROTOWERPLACED, Texture.class);
        assetManager.load(StaticVariables.LASERTOWERPLACED, Texture.class);
        assetManager.load(StaticVariables.SNOWTOWERPLACED, Texture.class);
        assetManager.load(StaticVariables.DESERTTOWERPLACED, Texture.class);

        assetManager.load(StaticVariables.SNOWBALLSHEETS, Texture.class);
        assetManager.load(StaticVariables.FIREBALLSHEETS, Texture.class);
        assetManager.load(StaticVariables.AXE, Texture.class);

        assetManager.load(StaticVariables.SLIDER_BACKGROUND, Texture.class);
        assetManager.load(StaticVariables.SLIDER_KNOB, Texture.class);
        assetManager.load(StaticVariables.BACKGROUND_MUSIC, Music.class);
        assetManager.load(StaticVariables.GAMEOVERVOICE, Music.class);
        assetManager.load(StaticVariables.FIRST_MAP_BGMUSIC, Music.class);

        assetManager.load(StaticVariables.ROCK, Texture.class);
        assetManager.load(StaticVariables.HEALT_BAR_BOTTOMLAYED, Texture.class);
        assetManager.load(StaticVariables.HEALT_BAR_UPPERLAYED, Texture.class);
        assetManager.load(StaticVariables.HEALT_BAR_MIDLAYED, Texture.class);

        FontType.loadFonts();

    }

    /**
     * Load an animation from its texture (frameDuration is set to 0.2f as default)
     *
     * @param texture the frames of the animation
     * @param rows the num of rows of the animation
     * @param cols the num of cols of the animation
     * @return the animation
     */
    public static Animation loadAnimation(Texture texture, int rows, int cols) {

        return loadAnimation(texture, rows, cols, 0.2f);
    }

    /**
     * Load an animation from its texture
     *
     * @param texture the frames of the animation
     * @param rows the num of rows of the animation
     * @param cols the num of cols of the animation
     * @param frameDuration the duration of a frame
     * @return
     */
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
