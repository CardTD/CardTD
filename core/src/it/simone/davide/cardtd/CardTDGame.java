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
    public static AssetManager ASSETSMANAGER;

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
        ASSETSMANAGER = new AssetManager();

        // set the font loaders
        FileHandleResolver resolver = new InternalFileHandleResolver();
        ASSETSMANAGER.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
        ASSETSMANAGER.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));
        ASSETSMANAGER.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));

        loadAssets();

        ASSETSMANAGER.finishLoading();
        new GameObjects();
        setScreen(new MainMenu());

    }

    private void loadAssets() {

        //load main menu bg
        ASSETSMANAGER.load(StaticVariables.MAIN_MENU_IMG, Texture.class);

        ASSETSMANAGER.load(StaticVariables.CARDSLOT, Texture.class);
        ASSETSMANAGER.load(StaticVariables.SHIP, Texture.class);
        ASSETSMANAGER.load(StaticVariables.DECKMENU, Texture.class);
        ASSETSMANAGER.load(StaticVariables.DECKBG, Texture.class);
        ASSETSMANAGER.load(StaticVariables.BACKBUTTON, Texture.class);
        ASSETSMANAGER.load(StaticVariables.BACKBUTTON_PRESSED, Texture.class);
        ASSETSMANAGER.load(StaticVariables.PAUSEBUTTON, Texture.class);
        ASSETSMANAGER.load(StaticVariables.PAUSEBUTTON_PRESSED, Texture.class);
        ASSETSMANAGER.load(StaticVariables.RESUMEBUTTON, Texture.class);
        ASSETSMANAGER.load(StaticVariables.RESUMEBUTTON_PRESSED, Texture.class);
        ASSETSMANAGER.load(StaticVariables.RELOADBUTTON, Texture.class);
        ASSETSMANAGER.load(StaticVariables.RELOADBUTTON_PRESSED, Texture.class);
        ASSETSMANAGER.load(StaticVariables.OPTIONBUTTON, Texture.class);
        ASSETSMANAGER.load(StaticVariables.OPTIONBUTTON_PRESSED, Texture.class);
        ASSETSMANAGER.load(StaticVariables.HOMEBUTTON, Texture.class);
        ASSETSMANAGER.load(StaticVariables.HOMEBUTTON_PRESSED, Texture.class);

        ASSETSMANAGER.load(StaticVariables.FIRSTMAP, Texture.class);
        ASSETSMANAGER.load(StaticVariables.TMXMAP, TiledMap.class);
        ASSETSMANAGER.load(StaticVariables.MAP_OVERLAY, Texture.class);

        ASSETSMANAGER.load(StaticVariables.FIRSTLEVELICON, Texture.class);
        ASSETSMANAGER.load(StaticVariables.FIRSTLEVELICON_PRESSED, Texture.class);
        ASSETSMANAGER.load(StaticVariables.SECONDLEVELICON, Texture.class);
        ASSETSMANAGER.load(StaticVariables.SECONDLEVELICON_PRESSED, Texture.class);
        ASSETSMANAGER.load(StaticVariables.THIRDLEVELICON, Texture.class);
        ASSETSMANAGER.load(StaticVariables.THIRDLEVELICON_PRESSED, Texture.class);
        ASSETSMANAGER.load(StaticVariables.FOURTHLEVELICON, Texture.class);
        ASSETSMANAGER.load(StaticVariables.FOURTHLEVELICON_PRESSED, Texture.class);
        ASSETSMANAGER.load(StaticVariables.FIFTHLEVELICON, Texture.class);
        ASSETSMANAGER.load(StaticVariables.FIFTHLEVELICON_PRESSED, Texture.class);
        ASSETSMANAGER.load(StaticVariables.SIXTHLEVELICON, Texture.class);
        ASSETSMANAGER.load(StaticVariables.SIXTHLEVELICON_PRESSED, Texture.class);

        ASSETSMANAGER.load(StaticVariables.TOASTER_IDLE, Texture.class);
        ASSETSMANAGER.load(StaticVariables.TOASTER_ATTACK, Texture.class);
        ASSETSMANAGER.load(StaticVariables.TOASTER_DEATH, Texture.class);
        ASSETSMANAGER.load(StaticVariables.TOASTER_RUN, Texture.class);
        ASSETSMANAGER.load(StaticVariables.TOASTER_DAMAGED, Texture.class);

        ASSETSMANAGER.load(StaticVariables.STORMHEAD_IDLE, Texture.class);
        ASSETSMANAGER.load(StaticVariables.STORMHEAD_ATTACK, Texture.class);
        ASSETSMANAGER.load(StaticVariables.STORMHEAD_DEATH, Texture.class);
        ASSETSMANAGER.load(StaticVariables.STORMHEAD_RUN, Texture.class);
        ASSETSMANAGER.load(StaticVariables.STORMHEAD_DAMAGED, Texture.class);

        ASSETSMANAGER.load(StaticVariables.SPIRITBOXER_IDLE, Texture.class);
        ASSETSMANAGER.load(StaticVariables.SPIRITBOXER_ATTACK, Texture.class);
        ASSETSMANAGER.load(StaticVariables.SPIRITBOXER_DEATH, Texture.class);
        ASSETSMANAGER.load(StaticVariables.SPIRITBOXER_RUN, Texture.class);
        ASSETSMANAGER.load(StaticVariables.SPIRITBOXER_DAMAGED, Texture.class);
        ASSETSMANAGER.load(StaticVariables.COIN, Texture.class);

        ASSETSMANAGER.load(StaticVariables.FIRETOWERCARD, Texture.class);
        ASSETSMANAGER.load(StaticVariables.ELETTROTOWERCARD, Texture.class);
        ASSETSMANAGER.load(StaticVariables.LASERTOWERCARD, Texture.class);
        ASSETSMANAGER.load(StaticVariables.SNOWTOWERCARD, Texture.class);
        ASSETSMANAGER.load(StaticVariables.DESERTTOWERCARD, Texture.class);

        ASSETSMANAGER.load(StaticVariables.FIRETOWERPLACED, Texture.class);
        ASSETSMANAGER.load(StaticVariables.ELETTROTOWERPLACED, Texture.class);
        ASSETSMANAGER.load(StaticVariables.LASERTOWERPLACED, Texture.class);
        ASSETSMANAGER.load(StaticVariables.SNOWTOWERPLACED, Texture.class);
        ASSETSMANAGER.load(StaticVariables.DESERTTOWERPLACED, Texture.class);

        ASSETSMANAGER.load(StaticVariables.SNOWBALLSHEETS, Texture.class);
        ASSETSMANAGER.load(StaticVariables.FIREBALLSHEETS, Texture.class);
        ASSETSMANAGER.load(StaticVariables.AXE, Texture.class);

        ASSETSMANAGER.load(StaticVariables.SLIDER_BACKGROUND, Texture.class);
        ASSETSMANAGER.load(StaticVariables.SLIDER_KNOB, Texture.class);
        ASSETSMANAGER.load(StaticVariables.BACKGROUND_MUSIC, Music.class);
        ASSETSMANAGER.load(StaticVariables.GAMEOVERVOICE, Music.class);
        ASSETSMANAGER.load(StaticVariables.FIRST_MAP_BGMUSIC, Music.class);

        ASSETSMANAGER.load(StaticVariables.ROCK, Texture.class);
        ASSETSMANAGER.load(StaticVariables.HEALT_BAR_BOTTOMLAYED, Texture.class);
        ASSETSMANAGER.load(StaticVariables.HEALT_BAR_UPPERLAYED, Texture.class);
        ASSETSMANAGER.load(StaticVariables.HEALT_BAR_MIDLAYED, Texture.class);

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
