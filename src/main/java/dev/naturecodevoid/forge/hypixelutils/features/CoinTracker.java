package dev.naturecodevoid.forge.hypixelutils.features;

import dev.naturecodevoid.forge.hypixelutils.BaseFeature;
import dev.naturecodevoid.forge.hypixelutils.HypixelUtils;
import dev.naturecodevoid.forge.hypixelutils.util.Util;
import dev.naturecodevoid.forge.hypixelutils.util.Vector2D;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CoinTracker extends BaseFeature {
    public CoinTracker() {
        super();
        MinecraftForge.EVENT_BUS.register(this);
    }

    public String getText() {
        return getText(true);
    }

    public String getText(boolean showActualCoins) {
        return getText(showActualCoins, 0);
    }

    public String getText(boolean showActualCoins, int coins) {
        return Util.getColorFromString(HypixelUtils.config.colors[HypixelUtils.config.coinsColor1]) + HypixelUtils.config.coinsMessages[HypixelUtils.config.coinsMessage] + ": " + Util.getColorFromString(HypixelUtils.config.colors[HypixelUtils.config.coinsColor2]) + (showActualCoins ? HypixelUtils.totalCoins : coins);
    }

    @Override
    public void render() {
        FontRenderer fRender = Minecraft.getMinecraft().fontRendererObj;

        String text = getText();
        Vector2D pos = Util.getPosFromPercent(HypixelUtils.config.coinTrackerX, HypixelUtils.config.coinTrackerY);

        pos.x += 4;
        pos.y += 16;

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
        return new Vector2D(Minecraft.getMinecraft().fontRendererObj.getStringWidth(getText(false, 30)) + 7, 12);
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
