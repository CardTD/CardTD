package it.simone.davide.cardtd;

import com.badlogic.gdx.graphics.Texture;
import it.simone.davide.cardtd.classes.Card;
import it.simone.davide.cardtd.classes.Enemy;
import it.simone.davide.cardtd.enums.EnemyType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StaticVariables {

    //screen settings
    public static final int SCREEN_WIDTH = 1280;
    public static final int SCREEN_HEIGHT = 720;

    //main menu variables
    public static final String GAMENAME = "CardTD";

    public static final String MAIN_MENU_IMG = "mainmenu.png";
    public static final String NAVE = "nave.png";
    public static final String DECKMENU = "deckmenu.png";
    public static final String CARDSLOT = "blank.png";
    public static final String DECKBG = "assi.png";

    public static final String BACKBUTTON = "back.png";
    public static final String BACKBUTTON_PRESSED = "backpressend.png";
    public static final String FIRSTMAP = "mappa1.png";
    public static final String PLACEDTOWER_PNG = "placedtower.png";
    public static final String TMXMAP = "mappa1.tmx";
    public static final String IN_GAME_DECK = "ingamedeck.png";

    public static final String ToasterBorIDLE = "cards/toaster/idle.png";
    public static final String ToasterBorRun = "cards/toaster/run.png";
    public static final String ToasterBorAttack = "cards/toaster/attack.png";
    public static final String ToasterBorDeath = "cards/toaster/death.png";
    public static final String ToasterBorDagamed = "cards/toaster/damaged.png";
    public static final String SliderBackground = "slider-Back.png";
    public static final String SliderKnob = "slider-dot.png";
    public static final String BLACK_CIRCLE = "bullet.png";
    public static final String TOWER= "tower.png";
    public static final String BackgroundMusic = "Background.wav";


    public static final Card BLANK_CARD;

    //get card by name


    static {
        Texture i = CardTDGame.assetManager.get(CARDSLOT);
        BLANK_CARD = new Card("blank", i, null);


    }

}
