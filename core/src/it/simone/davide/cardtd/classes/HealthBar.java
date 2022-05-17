package it.simone.davide.cardtd.classes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;

public class HealthBar extends Image {

    private NinePatchDrawable loadingBarBackground, loadingBar, sopraB;

    private float health;
    private float maxHealth;
    private float progress = 1;

    public HealthBar(float maxHealth) {

        super(new Texture(Gdx.files.internal("Sotto.png")));
        this.maxHealth = maxHealth;
        health = maxHealth;

        NinePatch loadingBarBackgroundPatch = new NinePatch(new Texture(Gdx.files.internal("Sotto.png")), 5, 5, 4, 4);
        NinePatch loadingBarPatch = new NinePatch(new Texture(Gdx.files.internal("mezzo.png")), 5, 5, 4, 4);
        NinePatch sopra = new NinePatch(new Texture(Gdx.files.internal("Sopra.png")), 5, 5, 4, 4);
        loadingBar = new NinePatchDrawable(loadingBarPatch);
        loadingBarBackground = new NinePatchDrawable(loadingBarBackgroundPatch);
        sopraB = new NinePatchDrawable(sopra);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        loadingBarBackground.draw(batch, getX(), getY(), getWidth() * getScaleX(), getHeight() * getScaleY());

        loadingBar.draw(batch, getX(), getY(), progress * getWidth() * getScaleX(), getHeight() * getScaleY());
        sopraB.draw(batch, getX(), getY(), getWidth() * getScaleX(), getHeight() * getScaleY());

    }

    public void damage(float removelife) {
        health -= removelife;
        if (health < 0) {
            health = 0;
        }
        progress = health / maxHealth;
        if(progress==0)
            progress=0.01f;

    }

    public boolean isDead() {
        return health <= 0;
    }
}