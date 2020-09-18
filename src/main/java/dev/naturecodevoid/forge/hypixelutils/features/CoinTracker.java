package dev.naturecodevoid.forge.hypixelutils.features;

import dev.naturecodevoid.forge.hypixelutils.BaseFeature;
import dev.naturecodevoid.forge.hypixelutils.HypixelUtils;
import dev.naturecodevoid.forge.hypixelutils.util.Vector2D;
import dev.naturecodevoid.forge.hypixelutils.util.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CoinTracker extends BaseFeature {
    public CoinTracker() {
        super();
        MinecraftForge.EVENT_BUS.register(this);
    }

    private String getText() {
        return Util.getColorFromString(HypixelUtils.config.colors[HypixelUtils.config.coinsColor1]) + HypixelUtils.config.coinsMessages[HypixelUtils.config.coinsMessage] + ": " + Util.getColorFromString(HypixelUtils.config.colors[HypixelUtils.config.coinsColor2]) + HypixelUtils.totalCoins;
    }

    @Override
    public void render() {
        ScaledResolution res = new ScaledResolution(Minecraft.getMinecraft());
        int width = res.getScaledWidth();
        int height = res.getScaledHeight();

        FontRenderer fRender = Minecraft.getMinecraft().fontRendererObj;

        String text = getText();
        Vector2D pos = Util.getPosFromPercent(HypixelUtils.config.coinTrackerX, HypixelUtils.config.coinTrackerY);

        pos.x += 4;
        if (pos.y <= height / 2) {
            pos.y += 16;
        }
        if (pos.x >= width / 2) {
            pos.x -= fRender.getStringWidth(text) + 8;
        }

        fRender.drawStringWithShadow(text, pos.x, pos.y - 12, 0);
    }

    @Override
    public void resetPosition() {
        HypixelUtils.config.coinTrackerX = 0;
        HypixelUtils.config.coinTrackerY = 0;
    }

    @Override
    public Vector2D getPosition() {
        return new Vector2D(HypixelUtils.config.coinTrackerX, HypixelUtils.config.coinTrackerY);
    }

    @Override
    public Vector2D getSize() {
        return new Vector2D(Minecraft.getMinecraft().fontRendererObj.getStringWidth(getText()), 12);
    }

    // https://hypixel.net/threads/guide-how-to-start-create-coding-minecraft-forge-mods.551741/
    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        String message = event.message.getUnformattedText();
        if (message.startsWith("+") && message.contains("coins") && !message.endsWith("for being generous")) {
            String[] splittedMessage = message.split("coins");
            message = splittedMessage[0].replace("+", "");
            message = message.replace(" ", "");
            int coins = Integer.parseInt(message);
            HypixelUtils.totalCoins += coins;
        }
    }

    @SubscribeEvent
    public void onRender(RenderGameOverlayEvent event) {
        if (!(HypixelUtils.config.coinsEnabled) || !(Util.isModEnabled()) || Util.hasGuiOpen() || event.isCancelable() || event.type != RenderGameOverlayEvent.ElementType.TEXT)
            return;

        this.render();
    }
}
