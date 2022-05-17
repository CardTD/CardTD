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

public class HealthBar extends Image {

    private NinePatchDrawable loadingBarBackground, sopraB;
    private TextureRegion loadingBarPatch;

    private float health;
    private float maxHealth;
    private float progress = 1;

    public HealthBar(float maxHealth) {

        super(new Texture(Gdx.files.internal("Sotto.png")));
        this.maxHealth = maxHealth;
        health = maxHealth;

        NinePatch loadingBarBackgroundPatch = new NinePatch(CardTDGame.assetManager.<Texture>get(StaticVariables.HealtBarD), 5, 5, 4, 4);

        Texture texture = CardTDGame.assetManager.<Texture>get(StaticVariables.HealtBarM);
        loadingBarPatch = new TextureRegion(texture);

        NinePatch sopra = new NinePatch(CardTDGame.assetManager.<Texture>get(StaticVariables.HealtBarL), 5, 5, 4, 4);

        loadingBarBackground = new NinePatchDrawable(loadingBarBackgroundPatch);
        sopraB = new NinePatchDrawable(sopra);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        loadingBarBackground.draw(batch, getX(), getY(), getWidth() * getScaleX(), getHeight() * getScaleY());

        TextureRegion[][] l = loadingBarPatch.split(1, (int) getHeight());

        for (int i = 0; i < getWidth() * progress; i++) {
            TextureRegion s = l[0][i];
            batch.draw(s, (s.getRegionX() + getX()), (s.getRegionY() + getY()), s.getRegionWidth(), s.getRegionHeight());

        }
        sopraB.draw(batch, getX(), getY(), getWidth() * getScaleX(), getHeight() * getScaleY());

    }

    public void damage(float removelife) {
        health -= removelife;
        if (health < 0) {
            health = 0;
        }
        progress = health / maxHealth;

    }

    public boolean isDead() {
        return health <= 0;
    }
}