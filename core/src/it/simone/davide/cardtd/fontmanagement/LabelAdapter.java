package it.simone.davide.cardtd.fontmanagement;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

/**
 * Utility class to simplify the creation of labels
 */
public class LabelAdapter extends Label {

    /**
     * The text of the label
     */
    private final String text;

    /**
     * The font type of the label
     */
    private final FontType fontType;

    /**
     * Create a new label
     *
     * @param text     the text of the label
     * @param fontType the font type of the label
     */
    public LabelAdapter(String text, FontType fontType) {

        super(text, new LabelStyle(fontType.getBitMapFont(), fontType.getColor()));
        this.text = text;
        this.fontType = fontType;
    }

    /**
     * In which stage must be added
     *
     * @param stage the stage
     * @param x     the coordinate x
     * @param y     the coordinate y
     */
    public void toStage(Stage stage, float x, float y) {

        stage.addActor(this);
        setPosition(x, y);

    }

    /**
     * Update the state of the label
     */
    public void reset() {

        setText(text);
        setColor(fontType.getColor());
    }

}
