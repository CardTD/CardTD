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
    public static final String PAUSEBUTTON = "pause.png";
    public static final String PAUSEBUTTON_PRESSED = "pausepressed.png";
    public static final String RESUMEBUTTON = "resume.png";
    public static final String RESUMEBUTTON_PRESSED = "resumepressed.png";
    public static final String RELOADBUTTON = "reload.png";
    public static final String RELOADBUTTON_PRESSED = "reloadpressed.png";
    public static final String OPTIONBUTTON = "optionbutton.png";
    public static final String OPTIONBUTTON_PRESSED = "optionbuttonpressed.png";
    public static final String HOMEBUTTON = "home.png";
    public static final String HOMEBUTTON_PRESSED = "homepressend.png";


    public static final String FIRSTMAP = "mappa1.png";
    public static final String TMXMAP = "mappa1.tmx";
    public static final String MAP_OVERLAY = "Overlay Mappa.png";

    public static final String PRIMOLIVELLOICO = "PrimoLivello.png";
    public static final String PRIMOLIVELLOICOP = "PrimoLivelloPressed.png";
    public static final String SECONDOLIVELLOICO = "SecondoLivello.png";
    public static final String SECONDOLIVELLOICOP = "SecondoLivelloPressed.png";
    public static final String TERZOLIVELLOICO = "TerzoLivello.png";
    public static final String TERZOLIVELLOICOP = "TerzoLivelloPressed.png";
    public static final String QUARTOLIVELLOICO = "QuartoLivello.png";
    public static final String QUARTOLIVELLOICOP = "QuartoLivelloPressed.png";
    public static final String QUINTOLIVELLOICO = "QuintoLivello.png";
    public static final String QUINTOLIVELLOICOP = "QuintoLivelloPressed.png";
    public static final String SESTOLIVELLOICO = "SestoLivello.png";
    public static final String SESTOLIVELLOICOP = "SestoLivelloPressed.png";

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

    public static final String SpiritBoxerIDLE = "cards/spiritboxer/Idle.png";
    public static final String SpiritBoxerRun = "cards/spiritboxer/Run.png";
    public static final String SpiritBoxerAttack = "cards/spiritboxer/attack.png";
    public static final String SpiritBoxerDeath = "cards/spiritboxer/death.png";
    public static final String SpiritBoxerDamaged = "cards/spiritboxer/damaged.png";
    public static final String COIN = "coin-sheet.png";

    public static final String ROCK = "bullet.png";

    public static final String FIRETOWERCARD = "FireTower.png";
    public static final String ELETTROTOWERCARD = "ElettroTower.png";
    public static final String LASERTOWERCARD = "LaserTower.png";
    public static final String SNOWTOWERCARD = "SnowTower.png";
    public static final String DESERTTOWERCARD = "DesertTower.png";

    public static final String FIRETOWERPLACED = "FireTowerPlaced.png";
    public static final String ELETTROTOWERPLACED = "ElettroTowerPlaced.png";
    public static final String LASERTOWERPLACED = "LaserTowerPlaced.png";
    public static final String SNOWTOWERPLACED = "SnowTowerPlaced.png";
    public static final String DESERTTOWERPLACED = "DesertTowerPlaced.png";

    public static final String SNOWBALLSHEETS = "snowball_sheet.png";
    public static final String FIREBALLSHEETS = "FireBall_sheet.png";
    public static final String AXE = "stone_axe.png";

    public static final String SliderBackground = "slider-Back.png";
    public static final String SliderKnob = "slider-dot.png";
    public static final String BackgroundMusic = "Background.wav";
    public static final String FirstMapSound = "FirstMapSound.mp3";
    public static final String HealtBarD = "Sotto.png";
    public static final String HealtBarM = "mezzo.png";
    public static final String HealtBarL = "Sopra.png";
    public static final String GAMEOVERVOICE = "gameover.mp3";

    public static final Card BLANK_CARD;

    //get card by name

    static {
        Texture i = CardTDGame.assetManager.get(CARDSLOT);
        BLANK_CARD = new Card("blank", i, null, 0);

    }

}
