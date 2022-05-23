package it.simone.davide.cardtd.classes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;


public abstract class AnimatedBullet extends Bullet{

    protected Animation animation = null;
    private float stateTime = 0;

    public AnimatedBullet(Animation animation, float speed) {
        super(((TextureRegion) animation.getKeyFrame(0)).getTexture(), speed);
        this.animation = animation;

    }

    @Override
    public void act(float delta) {

        ((TextureRegionDrawable) getDrawable()).setRegion((TextureRegion) animation.getKeyFrame(stateTime += delta * 2, true));
        super.act(delta);
    }


}
