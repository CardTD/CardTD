package it.simone.davide.cardtd;

import com.badlogic.gdx.graphics.Texture;
import it.simone.davide.cardtd.classes.Card;

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
    public static final String ToasterBorDamaged = "cards/toaster/damaged.png";

    public static final String StormHeadBorIDLE = "cards/stormhead/idle.png";
    public static final String StormHeadRun = "cards/stormhead/run.png";
    public static final String StormHeadAttack = "cards/stormhead/attack.png";
    public static final String StormHeadDeath = "cards/stormhead/death.png";
    public static final String StormHeadDamaged = "cards/stormhead/damaged.png";

    public static final String SpiritBoxerIDLE = "cards/spiritboxer/idle.png";
    public static final String SpiritBoxerRun = "cards/spiritboxer/run.png";
    public static final String SpiritBoxerAttack = "cards/spiritboxer/attack.png";
    public static final String SpiritBoxerDeath = "cards/spiritboxer/death.png";
    public static final String SpiritBoxerDamaged = "cards/spiritboxer/damaged.png";

    public static final String ROCK = "bullet.png";
    public static final String TOWER = "tower.png";

    public static final Card BLANK_CARD;

    //get card by name

    static {
        Texture i = CardTDGame.assetManager.get(CARDSLOT);
        BLANK_CARD = new Card("blank", i, null);

    }

}
