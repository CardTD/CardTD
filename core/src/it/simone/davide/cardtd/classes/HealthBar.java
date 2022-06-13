package it.simone.davide.cardtd.classes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import it.simone.davide.cardtd.CardTDGame;
import it.simone.davide.cardtd.StaticVariables;

/**
 * The class that manages the main tower’s life bar
 */
public class HealthBar extends Image implements Damageable {

    /**
     * The {@link NinePatchDrawable} of the background of the bar
     */
    private NinePatchDrawable loadingBarBackground;

    /**
     * The {@link NinePatchDrawable} of the top part of the bar
     */
    private NinePatchDrawable upSide;

    /**
     * The image of the red part of the bar
     */
    private TextureRegion loadingBarPatch;

    /**
     * The current health of the tower
     */
    private float health;

    /**
     * The max health of the tower
     */
    private float maxHealth;

    /**
     * The percentage of the bar
     * Values between {@code 0} and {@code 1}
     */
    private float progress = 1;

    /**
     * Create a new health bar
     *
     * @param maxHealth the max health of the tower
     */
    public HealthBar(float maxHealth) {

        super(new Texture(Gdx.files.internal("Sotto.png")));
        this.maxHealth = maxHealth;
        health = maxHealth;

        NinePatch loadingBarBackgroundPatch = new NinePatch(CardTDGame.ASSETSMANAGER.<Texture>get(StaticVariables.HEALT_BAR_BOTTOMLAYED), 5, 5, 4, 4);

        Texture texture = CardTDGame.ASSETSMANAGER.get(StaticVariables.HEALT_BAR_MIDLAYED);
        loadingBarPatch = new TextureRegion(texture);

        NinePatch sopra = new NinePatch(CardTDGame.ASSETSMANAGER.<Texture>get(StaticVariables.HEALT_BAR_UPPERLAYED), 5, 5, 4, 4);

        loadingBarBackground = new NinePatchDrawable(loadingBarBackgroundPatch);
        upSide = new NinePatchDrawable(sopra);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void draw(Batch batch, float parentAlpha) {

        loadingBarBackground.draw(batch, getX(), getY(), getWidth() * getScaleX(), getHeight() * getScaleY());

        TextureRegion[][] l = loadingBarPatch.split(1, (int) getHeight());

        for (int i = 0; i < getWidth() * progress; i++) {
            TextureRegion s = l[0][i];
            batch.draw(s, (s.getRegionX() + getX()), (s.getRegionY() + getY()), s.getRegionWidth(), s.getRegionHeight());

        }
        upSide.draw(batch, getX(), getY(), getWidth() * getScaleX(), getHeight() * getScaleY());

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean damage(float removelife) {
        health -= removelife;
        if (health < 0) {

            health = 0;
            return true;
        }
        progress = health / maxHealth;

        return false;
    }

    /**
     * Returns if the main tower id dead
     *
     * @return if the main tower id dead
     */
    public boolean isDead() {
        return health <= 0;
    }
}