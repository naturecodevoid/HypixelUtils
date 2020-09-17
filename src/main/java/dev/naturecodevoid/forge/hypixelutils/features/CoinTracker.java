package dev.naturecodevoid.forge.hypixelutils.features;

import dev.naturecodevoid.forge.hypixelutils.HypixelUtils;
import dev.naturecodevoid.forge.hypixelutils.IFeature;
import dev.naturecodevoid.forge.hypixelutils.util.Coordinate2D;
import dev.naturecodevoid.forge.hypixelutils.util.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public class CoinTracker implements IFeature {
    public static CoinTracker instance;

    public CoinTracker() {
        instance = this;
    }

    @Override
    public void render(RenderGameOverlayEvent event) {
        String text = Util.getColorFromString(HypixelUtils.config.colors[HypixelUtils.config.coinsColor1]) + HypixelUtils.config.coinsMessages[HypixelUtils.config.coinsMessage] + ": " + Util.getColorFromString(HypixelUtils.config.colors[HypixelUtils.config.coinsColor2]) + HypixelUtils.totalCoins;
        FontRenderer fRender = Minecraft.getMinecraft().fontRendererObj;
        Coordinate2D pos = Util.getPosFromPercent(HypixelUtils.config.coinTrackerX, HypixelUtils.config.coinTrackerY);
        pos.x += 4;
        if (pos.y <= Minecraft.getMinecraft().displayHeight / 2 / 2) {
            pos.y += 16;
        }
        if (pos.x >= Minecraft.getMinecraft().displayWidth / 2 / 2) {
            pos.x -= fRender.getStringWidth(text) + 8;
        }
        fRender.drawStringWithShadow(text, pos.x, pos.y - 12, 0);
    }
}
