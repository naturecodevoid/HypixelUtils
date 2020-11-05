package dev.naturecodevoid.forge.hypixelutils.base;

import dev.naturecodevoid.forge.hypixelutils.util.Utils;
import dev.naturecodevoid.forge.hypixelutils.util.Vector2D;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.commons.lang3.NotImplementedException;

public abstract class GuiFeature extends BaseFeature {
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
        throw new NotImplementedException("Please override the getPosition function! This gets the feature position in percents.");
    }

    public Vector2D setPosition(Vector2D percent) {
        return this.setPosition(percent.x, percent.y);
    }

    public Vector2D setPosition(int xPercent, int yPercent) {
        throw new NotImplementedException("Please override the setPosition function! This sets the feature position in percents and returns getPosition().");
    }

    public Vector2D getSize() {
        throw new NotImplementedException("Please override the getSize function! This gets the feature size.");
    }

    @Override
    public boolean isGui() {
        return true;
    }

    @SubscribeEvent
    public void onRender(RenderGameOverlayEvent event) {
        if (Utils.getEnabled(event, this.isHypixel(), this.isEnabled()))
            return;

        this.render();
    }
}
