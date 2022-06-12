package it.simone.davide.cardtd;

import com.badlogic.gdx.graphics.Texture;
import it.simone.davide.cardtd.classes.Card;

/**
 * A class contains all the static variables of the game
 */
public class StaticVariables {

    /**
     * The screen width
     */
    public static final int SCREEN_WIDTH = 1280;

    /**
     * The screen height
     */
    public static final int SCREEN_HEIGHT = 720;

    /**
     * The game name
     */
    public static final String GAMENAME = "CardTD";

    /**
     * The image of the main menu
     */
    public static final String MAIN_MENU_IMG = "mainmenu.png";

    /**
     * The image of the ship
     */
    public static final String SHIP = "nave.png";

    /**
     * The background image of the deck menu
     */
    public static final String DECKMENU = "deckmenu.png";

    /**
     * A transparent image with card sizes
     */
    public static final String CARDSLOT = "blank.png";

    /**
     * The background of the off-screen portions
     */
    public static final String DECKBG = "assi.png";

    /**
     * The image of the back button
     */
    public static final String BACKBUTTON = "back.png";

    /**
     * The image of the back button while pressed
     */
    public static final String BACKBUTTON_PRESSED = "backpressend.png";

    /**
     * The image of the pause button
     */
    public static final String PAUSEBUTTON = "pause.png";

    /**
     * The image of the pause button while pressed
     */
    public static final String PAUSEBUTTON_PRESSED = "pausepressed.png";

    /**
     * The image of the resume button
     */
    public static final String RESUMEBUTTON = "resume.png";

    /**
     * The image of the resume button while pressed
     */
    public static final String RESUMEBUTTON_PRESSED = "resumepressed.png";

    /**
     * The image of the reload button
     */
    public static final String RELOADBUTTON = "reload.png";

    /**
     * The image of the reload button while pressed
     */
    public static final String RELOADBUTTON_PRESSED = "reloadpressed.png";

    /**
     * The image of the option button
     */
    public static final String OPTIONBUTTON = "optionbutton.png";

    /**
     * The image of the option button while pressed
     */
    public static final String OPTIONBUTTON_PRESSED = "optionbuttonpressed.png";

    /**
     * The image of the home button
     */
    public static final String HOMEBUTTON = "home.png";

    /**
     * The image of the home button while pressed
     */
    public static final String HOMEBUTTON_PRESSED = "homepressend.png";

    /**
     * The image of the first map
     */
    public static final String FIRSTMAP = "mappa1.png";

    /**
     * The .tmx file of the first map
     */
    public static final String TMXMAP = "mappa1.tmx";

    /**
     * The overlay of the game
     */
    public static final String MAP_OVERLAY = "Overlay Mappa.png";

    /**
     * The first level icon in the selection of the level
     */
    public static final String FIRSTLEVELICON = "PrimoLivello.png";

    /**
     * The first level icon in the selection of the level while pressed
     */
    public static final String FIRSTLEVELICON_PRESSED = "PrimoLivelloPressed.png";

    /**
     * The second level icon in the selection of the level
     */
    public static final String SECONDLEVELICON = "SecondoLivello.png";

    /**
     * The second level icon in the selection of the level while pressed
     */
    public static final String SECONDLEVELICON_PRESSED = "SecondoLivelloPressed.png";

    /**
     * The third level icon in the selection of the level
     */
    public static final String THIRDLEVELICON = "TerzoLivello.png";

    /**
     * The third level icon in the selection of the level while pressed
     */
    public static final String THIRDLEVELICON_PRESSED = "TerzoLivelloPressed.png";

    /**
     * The fourth level icon in the selection of the level
     */
    public static final String FOURTHLEVELICON = "QuartoLivello.png";

    /**
     * The fourth level icon in the selection of the level while pressed
     */
    public static final String FOURTHLEVELICON_PRESSED = "QuartoLivelloPressed.png";

    /**
     * The fifth level icon in the selection of the level
     */
    public static final String FIFTHLEVELICON = "QuintoLivello.png";

    /**
     * The fifth level icon in the selection of the level while pressed
     */
    public static final String FIFTHLEVELICON_PRESSED = "QuintoLivelloPressed.png";

    /**
     * The sixth level icon in the selection of the level
     */
    public static final String SIXTHLEVELICON = "SestoLivello.png";

    /**
     * The sixth level icon in the selection of the level while pressed
     */
    public static final String SIXTHLEVELICON_PRESSED = "SestoLivelloPressed.png";

    /**
     * The animation frames of Toaster while it is idle
     */
    public static final String TOASTER_IDLE = "cards/toaster/idle.png";

    /**
     * Toaster’s animation frames while he’s running
     */
    public static final String TOASTER_RUN = "cards/toaster/run.png";

    /**
     * Toaster’s animation frames while he’s attacking
     */
    public static final String TOASTER_ATTACK = "cards/toaster/attack.png";

    /**
     * Toaster’s animation frames while he’s dying
     */
    public static final String TOASTER_DEATH = "cards/toaster/death.png";

    /**
     * Toaster’s animation frames while it was damaged
     */
    public static final String TOASTER_DAMAGED = "cards/toaster/damaged.png";

    /**
     * The animation frames of Storm Head while it is idle
     */
    public static final String STORMHEAD_IDLE = "cards/stormhead/idle.png";

    /**
     * Storm Head’s animation frames while he’s running
     */
    public static final String STORMHEAD_RUN = "cards/stormhead/run.png";

    /**
     * Storm Head’s animation frames while he’s attacking
     */
    public static final String STORMHEAD_ATTACK = "cards/stormhead/attack.png";

    /**
     * Storm Head’s animation frames while he’s dying
     */
    public static final String STORMHEAD_DEATH = "cards/stormhead/death.png";

    /**
     * Storm Head’s animation frames while it was damaged
     */
    public static final String STORMHEAD_DAMAGED = "cards/stormhead/damaged.png";

    /**
     * The animation frames of Spirit Boxer while it is idle
     */
    public static final String SPIRITBOXER_IDLE = "cards/spiritboxer/Idle.png";

    /**
     * Spirit Boxer’s animation frames while he’s running
     */
    public static final String SPIRITBOXER_RUN = "cards/spiritboxer/Run.png";

    /**
     * Spirit Boxer’s animation frames while he’s attacking
     */
    public static final String SPIRITBOXER_ATTACK = "cards/spiritboxer/attack.png";

    /**
     * Spirit Boxer’s animation frames while he’s dying
     */
    public static final String SPIRITBOXER_DEATH = "cards/spiritboxer/death.png";

    /**
     * Spirit Boxer’s animation frames while it was damaged
     */
    public static final String SPIRITBOXER_DAMAGED = "cards/spiritboxer/damaged.png";

    /**
     * The animated image of the coin
     */
    public static final String COIN = "coin-sheet.png";

    /**
     * The card image of a rock, used as bullet
     */
    public static final String ROCK = "bullet.png";

    /**
     * The card image of the fire tower
     */
    public static final String FIRETOWERCARD = "FireTower.png";

    /**
     * The card image of the electric tower
     */
    public static final String ELETTROTOWERCARD = "ElettroTower.png";

    /**
     * The card image of the laser tower
     */
    public static final String LASERTOWERCARD = "LaserTower.png";

    /**
     * The card image of the snow tower
     */
    public static final String SNOWTOWERCARD = "SnowTower.png";

    /**
     * The card image of the desert tower
     */
    public static final String DESERTTOWERCARD = "DesertTower.png";

    /**
     * The build image of the fire tower
     */
    public static final String FIRETOWERPLACED = "FireTowerPlaced.png";

    /**
     * The build image of the electric tower
     */
    public static final String ELETTROTOWERPLACED = "ElettroTowerPlaced.png";

    /**
     * The build image of the laser tower
     */
    public static final String LASERTOWERPLACED = "LaserTowerPlaced.png";

    /**
     * The build image of the snow tower
     */
    public static final String SNOWTOWERPLACED = "SnowTowerPlaced.png";

    /**
     * The build image of the desert tower
     */
    public static final String DESERTTOWERPLACED = "DesertTowerPlaced.png";

    /**
     * The animation frames of a snowball
     */
    public static final String SNOWBALLSHEETS = "snowball_sheet.png";

    /**
     * The animation frames of a fireball
     */
    public static final String FIREBALLSHEETS = "FireBall_sheet.png";

    /**
     * The image of an axe
     */
    public static final String AXE = "stone_axe.png";

    /**
     * The image background of a slider
     */
    public static final String SLIDER_BACKGROUND = "slider-Back.png";

    /**
     * The image knob of a slider
     */
    public static final String SLIDER_KNOB = "slider-dot.png";

    /**
     * The background music of the main menu
     */
    public static final String BACKGROUND_MUSIC = "Background.wav";

    /**
     * The background music of the first level
     */
    public static final String FIRST_MAP_BGMUSIC = "FirstMapSound.mp3";

    /**
     * The bottom layer of the health bar
     */
    public static final String HEALT_BAR_BOTTOMLAYED = "Sotto.png";

    /**
     * The middle layer of the health bar
     */
    public static final String HEALT_BAR_MIDLAYED = "mezzo.png";

    /**
     * The upper layer of the health bar
     */
    public static final String HEALT_BAR_UPPERLAYED = "Sopra.png";

    /**
     * The game over sound
     */
    public static final String GAMEOVERVOICE = "gameover.mp3";

    /**
     * A static instance of a blank card, it is used as placeholder
     */
    public static final Card BLANK_CARD;

    static {

        //load the black cards
        Texture i = CardTDGame.assetManager.get(CARDSLOT);
        BLANK_CARD = new Card("blank", i, null, 0);

    }

}
