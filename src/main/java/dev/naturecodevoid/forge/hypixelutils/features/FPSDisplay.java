package dev.naturecodevoid.forge.hypixelutils.features;

import dev.naturecodevoid.forge.hypixelutils.BaseFeature;
import dev.naturecodevoid.forge.hypixelutils.HypixelUtils;
import dev.naturecodevoid.forge.hypixelutils.util.Util;
import dev.naturecodevoid.forge.hypixelutils.util.Vector2D;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class FPSDisplay extends BaseFeature {
    public FPSDisplay() {
        super();
        MinecraftForge.EVENT_BUS.register(this);
    }

    public String getText() {
        return getText(true);
    }

    public String getText(boolean showActualFPS) {
        String text = HypixelUtils.config.fpsMessages[HypixelUtils.config.fpsMessage].replace("$1", showActualFPS ? String.valueOf(Minecraft.getDebugFPS()) : "999");
        return Util.getColorFromString(HypixelUtils.config.colors[HypixelUtils.config.fpsColor]) + text;
    }

    @Override
    public void render() {
        this.render(true);
    }

    public void render(boolean showActualFPS) {
        FontRenderer fRender = Minecraft.getMinecraft().fontRendererObj;

        String text = getText(showActualFPS);
        Vector2D pos = Util.getPosFromPercent(HypixelUtils.config.coinTrackerX, HypixelUtils.config.coinTrackerY);

        pos.x += 4;
        pos.y += 16;

        fRender.drawStringWithShadow(text, pos.x, pos.y - 12, 0);
    }

    @Override
    public void renderEditor() {
        this.render(false);
    }

    @Override
    public void resetPosition() {
        HypixelUtils.config.fpsX = 0;
        HypixelUtils.config.fpsY = 0;
    }

    @Override
    public Vector2D getPosition() {
        return new Vector2D(HypixelUtils.config.fpsX, HypixelUtils.config.fpsY);
    }

    @Override
    public Vector2D getSize() {
        return new Vector2D(Minecraft.getMinecraft().fontRendererObj.getStringWidth(getText(false)) + 7, 16);
    }

    @SubscribeEvent
    public void onRender(RenderGameOverlayEvent event) {
        if (Util.getEnabled(event, HypixelUtils.config.fpsEnabled))
            return;

        this.render();
    }
}