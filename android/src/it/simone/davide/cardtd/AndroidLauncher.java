package it.simone.davide.cardtd;

import android.os.Build;
import android.os.Bundle;

import android.view.Window;
import android.view.WindowManager;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import it.simone.davide.cardtd.CardTDGame;

public class AndroidLauncher extends AndroidApplication {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Notch Eliminata
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            Window applicationWindow = getApplicationWindow();
            WindowManager.LayoutParams attrib = applicationWindow.getAttributes();
            attrib.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        }

        super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        config.useWakelock = true;
        config.useImmersiveMode = true;
        config.hideStatusBar = true;
        initialize(new CardTDGame(), config);
    }
}
