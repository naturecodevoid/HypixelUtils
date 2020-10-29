package dev.naturecodevoid.forge.hypixelutils;

import dev.naturecodevoid.forge.hypixelutils.util.Vector2D;
import org.apache.commons.lang3.NotImplementedException;

public abstract class BaseFeature {
    public static BaseFeature instance;

    public BaseFeature() {
        instance = this;
    }

    public void render() {
        throw new NotImplementedException("Please override the render function! This runs when rendering features.");
    }

    public void renderEditor() {
        throw new NotImplementedException("Please override the renderEditor function! This runs when rendering features in the gui editor.");
    }

    public void resetPosition() {
        throw new NotImplementedException("Please override the resetPosition function! This runs when resetting the position in the gui editor.");
    }

    public Vector2D getPosition() {
        throw new NotImplementedException("Please override the getPosition function! This gets the feature position.");
    }

    public Vector2D getSize() {
        throw new NotImplementedException("Please override the getSize function! This gets the feature size.");
    }

    @Override
    public String toString() {
        String[] array = getClass().getName().split("\\.");
        return array[array.length - 1];
    }

    /*
    @SubscribeEvent
    public void onRender(RenderGameOverlayEvent event) {
        if (Util.getEnabled(event, HypixelUtils.config.CHANGEME_Enabled))
            return;

        this.render();
    }
     */
}
