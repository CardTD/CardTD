package it.simone.davide.cardtd;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class LabelAdapter extends Label {

    private String text;
    private FontType fontType;

    public LabelAdapter(String text, FontType fontType) {

        super(text, new LabelStyle(fontType.getBitMapFont(), fontType.getColor()));

    }

    public void toStage(Stage stage, float x, float y) {

        stage.addActor(this);
        setPosition(x, y);

    }

}
