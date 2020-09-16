package dev.naturecodevoid.forge.hypixelutils;

import net.minecraftforge.client.event.RenderGameOverlayEvent;

public interface IFeature {
    IFeature instance = null;

    void render(RenderGameOverlayEvent event);
}
