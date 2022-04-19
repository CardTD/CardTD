package it.simone.davide.cardtd.fontmanagement;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader.FreeTypeFontLoaderParameter;
import it.simone.davide.cardtd.CardTDGame;

import java.util.ArrayList;
import java.util.List;

/**
 * Class per standardizzare i font utilizzati
 */
public class FontType {

    private static List<FontType> fontTypes = new ArrayList();

    public static FontType LOGO, OPTIONS;

    static {

        LOGO = new FontType("LOGO", "Rundown.ttf", 150, Color.WHITE);
        OPTIONS = new FontType("OPTIONS", "Rundown1.ttf", 100, Color.BLACK);
        fontTypes.add(LOGO);
        fontTypes.add(OPTIONS);
    }

    private String fontName, typename;
    private int size;
    private Color color;

    FontType(String typename, String fontName, int size, Color color) {
        this.fontName = fontName;
        this.size = size;
        this.color = color;
        this.typename = typename;

    }

    public String getFontName() {
        return fontName;
    }

    public int getSize() {
        return size;
    }

    public Color getColor() {

        return color;
    }

    public static void loadFonts() {
        for (FontType fontType : fontTypes) {

            FreeTypeFontLoaderParameter titleParam = new FreeTypeFontLoaderParameter();
            titleParam.fontFileName = fontType.fontName;
            titleParam.fontParameters.size = fontType.size;

            CardTDGame.assetManager.load( fontType.fontName, BitmapFont.class, titleParam);

        }

    }

    public BitmapFont getBitMapFont() {

        return CardTDGame.assetManager.get(fontName, BitmapFont.class);

    }

}