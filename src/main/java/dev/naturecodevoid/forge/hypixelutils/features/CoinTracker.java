package dev.naturecodevoid.forge.hypixelutils.features;

import dev.naturecodevoid.forge.hypixelutils.BaseFeature;
import dev.naturecodevoid.forge.hypixelutils.HypixelUtils;
import dev.naturecodevoid.forge.hypixelutils.util.Util;
import dev.naturecodevoid.forge.hypixelutils.util.Vector2D;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CoinTracker extends BaseFeature {
    public static CoinTracker instance;

    public CoinTracker() {
        this.init();
    }

    public String getText() {
        return getText(true);
    }

    public String getText(boolean showActualCoins) {
        return Util.getColor(HypixelUtils.config.coinsColor1) + HypixelUtils.config.coinsMessages[HypixelUtils.config.coinsMessage].replace("$1", Util.getColor(HypixelUtils.config.coinsColor2) + String.valueOf(showActualCoins ? HypixelUtils.totalCoins : 1234));
    }

    @Override
    public void render() {
        this.render(true);
    }

    public void render(boolean showActualCoins) {
        FontRenderer fRender = Minecraft.getMinecraft().fontRendererObj;

        String text = getText(showActualCoins);
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
        HypixelUtils.config.coinTrackerX = 0;
        HypixelUtils.config.coinTrackerY = 0;
    }

    @Override
    public Vector2D getPosition() {
        return new Vector2D(HypixelUtils.config.coinTrackerX, HypixelUtils.config.coinTrackerY);
    }

    @Override
    public Vector2D setPosition(int xPercent, int yPercent) {
        HypixelUtils.config.coinTrackerX = xPercent;
        HypixelUtils.config.coinTrackerY = yPercent;
        return getPosition();
    }

    @Override
    public Vector2D getSize() {
        return new Vector2D(Minecraft.getMinecraft().fontRendererObj.getStringWidth(getText(false)) + 7, 16);
    }

    @Override
    public boolean isEnabled() {
        return HypixelUtils.config.coinsEnabled;
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
}
