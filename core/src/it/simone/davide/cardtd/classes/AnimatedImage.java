package it.simone.davide.cardtd.classes;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * Manage the animations of the images (es. coin)
 *
 * @see Image
 */
public class AnimatedImage extends Image {

    /**
     * The animation that is going to be animated, the frame of the animation
     */
    protected Animation animation;

    /**
     * The initial stateTime of the animation
     */
    private float stateTime = 0;

    /**
     * Creating a new AnimatedBullet
     *
     * @param animation the frame of the animation
     * @see Animation
     */
    public AnimatedImage(Animation animation) {
        super((TextureRegion) animation.getKeyFrame(0));
        this.animation = animation;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void act(float delta) {
        ((TextureRegionDrawable) getDrawable()).setRegion((TextureRegion) animation.getKeyFrame(stateTime += delta * 2, true));
        super.act(delta);
    }
}
