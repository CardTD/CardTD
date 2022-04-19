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
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader.FreeTypeFontLoaderParameter;
import it.simone.davide.cardtd.screens.MainMenu;

public class CardTDGame extends Game {

    public static AssetManager assetManager;

    @Override
    public void create() {

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

        //load font for the title
         FontType.loadFonts();


    }
}
