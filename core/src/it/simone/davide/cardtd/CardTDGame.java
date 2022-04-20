package it.simone.davide.cardtd;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
import it.simone.davide.cardtd.fontmanagement.FontType;
import it.simone.davide.cardtd.screens.DeckMenu;
import it.simone.davide.cardtd.screens.MainMenu;
import it.simone.davide.cardtd.screens.Test;

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

        loadAssets();
        assetManager.finishLoading();
        setScreen(new MainMenu());

    }

    private void loadAssets() {


        //load main menu bg
        assetManager.load(StaticVariables.MAIN_MENU_IMG, Texture.class);

        assetManager.load(StaticVariables.CARDSLOT, Texture.class);
        assetManager.load(StaticVariables.NAVE, Texture.class);

        //load font for the title
        FontType.loadFonts();

    }
}
