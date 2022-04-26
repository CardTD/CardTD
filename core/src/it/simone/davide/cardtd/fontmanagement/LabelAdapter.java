package it.simone.davide.cardtd.fontmanagement;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class LabelAdapter extends Label {

    private final String text;
    private final FontType fontType;

    public LabelAdapter(String text, FontType fontType) {

        super(text, new LabelStyle(fontType.getBitMapFont(), fontType.getColor()));
        this.text = text;
        this.fontType = fontType;
    }

    public void toStage(Stage stage, float x, float y) {

        stage.addActor(this);
        setPosition(x, y);

    }

    public void reset() {

        setText(text);
        setColor(fontType.getColor());
    }

}
