package dev.naturecodevoid.forge.hypixelutils;

import dev.naturecodevoid.forge.hypixelutils.util.Util;
import dev.naturecodevoid.forge.hypixelutils.util.Vector2D;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.commons.lang3.NotImplementedException;

public abstract class BaseFeature {
    public BaseFeature instance;

    /*
    public static CHANGEME instance;

    public CHANGEME() {
        this.init();
    }
    */

    public void init() {
        instance = this;
        MinecraftForge.EVENT_BUS.register(this);
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

    public boolean isEnabled() {
        throw new NotImplementedException("Please override the isEnabled function! This checks if the feature is enabled.");
    }

    @Override
    public String toString() {
        String[] array = getClass().getName().split("\\.");
        return array[array.length - 1];
    }

    @SubscribeEvent
    public void onRender(RenderGameOverlayEvent event) {
        if (Util.getEnabled(event, this.isEnabled()))
            return;

        this.render();
    }
}
