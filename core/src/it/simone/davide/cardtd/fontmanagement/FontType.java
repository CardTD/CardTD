package it.simone.davide.cardtd.fontmanagement;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader.FreeTypeFontLoaderParameter;
import it.simone.davide.cardtd.CardTDGame;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility class to simplify the management of the fonts
 */
public class FontType {

    /**
     * The list of all fonts
     */
    private static final List<FontType> FONT_TYPES = new ArrayList<>();

    /**
     * The font type used for the game name in the main menu
     */
    public static FontType LOGO;

    /**
     * The font type used for the option labels in the option menu
     */
    public static FontType OPTIONS;

    /**
     * The font type used to specify the number of money in the game
     */
    public static FontType MONEY;

    /**
     * The name of the file of the font
     */
    private final String fileName;

    /**
     * The font size
     */
    private final int size;

    /**
     * The font color
     */
    private final Color color;

    static {

        LOGO = new FontType("Rundown.ttf", 150, Color.WHITE);
        OPTIONS = new FontType("Rundown1.ttf", 100, Color.WHITE);
        MONEY = new FontType("Roboto-Regular.ttf", 25, Color.WHITE);
        FONT_TYPES.add(LOGO);
        FONT_TYPES.add(OPTIONS);
        FONT_TYPES.add(MONEY);
    }

    /**
     * Create a new font type
     *
     * @param fileName The name of the file of the font
     * @param size The font size
     * @param color The font color
     */
    FontType(String fileName, int size, Color color) {
        this.fileName = fileName;
        this.size = size;
        this.color = color;

    }

    /**
     * Getter for the font color
     *
     * @return the font color
     */
    public Color getColor() {

        return color;
    }

    /**
     * Loads the font
     */
    public static void loadFonts() {
        for (FontType fontType : FONT_TYPES) {

            FreeTypeFontLoaderParameter titleParam = new FreeTypeFontLoaderParameter();
            titleParam.fontFileName = fontType.fileName;
            titleParam.fontParameters.size = fontType.size;

            CardTDGame.ASSETSMANAGER.load(fontType.fileName, BitmapFont.class, titleParam);

        }

    }

    /**
     * Returns the bit map font
     *
     * @return the bit map font
     */
    public BitmapFont getBitMapFont() {

        return CardTDGame.ASSETSMANAGER.get(fileName, BitmapFont.class);

    }

}
