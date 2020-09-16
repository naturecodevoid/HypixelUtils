package dev.naturecodevoid.forge.hypixelutils.features;

import dev.naturecodevoid.forge.hypixelutils.HypixelUtils;
import dev.naturecodevoid.forge.hypixelutils.IFeature;
import dev.naturecodevoid.forge.hypixelutils.util.Position;
import dev.naturecodevoid.forge.hypixelutils.util.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public class CoinTracker implements IFeature {
    public static CoinTracker instance;

    public CoinTracker() {
        instance = this;
    }

    @Override
    public void render(RenderGameOverlayEvent event) {
        Position pos = Util.getPosFromPercent(HypixelUtils.config.coinTrackerX, HypixelUtils.config.coinTrackerY);
        pos.x += 4;
        if (pos.y <= Minecraft.getMinecraft().displayHeight / 2 / 2) {
            pos.y += 16;
        }
        if (pos.x >= Minecraft.getMinecraft().displayWidth / 2 / 2) {
            pos.x -= 29 * 3;
        }
        FontRenderer fRender = Minecraft.getMinecraft().fontRendererObj;
        fRender.drawStringWithShadow(EnumChatFormatting.GREEN + "Session Coins: " + EnumChatFormatting.WHITE + HypixelUtils.totalCoins, pos.x, pos.y - 12, 0);
    }
}
