package it.simone.davide.cardtd;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
    public static void main(String[] arg) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setForegroundFPS(60);
        config.setResizable(true);
        config.setTitle("CardTD");
         config.setWindowedMode(Lwjgl3ApplicationConfiguration.getDisplayMode().width-100, Lwjgl3ApplicationConfiguration.getDisplayMode().height-100);
        // config.setFullscreenMode(Lwjgl3ApplicationConfiguration.getDisplayMode());
        new Lwjgl3Application(new CardTDGame(), config);
    }
}
