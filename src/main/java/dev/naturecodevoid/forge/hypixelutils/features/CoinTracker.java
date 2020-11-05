package dev.naturecodevoid.forge.hypixelutils.features;

import dev.naturecodevoid.forge.hypixelutils.HypixelUtils;
import dev.naturecodevoid.forge.hypixelutils.base.TextFeature;
import dev.naturecodevoid.forge.hypixelutils.util.Utils;
import dev.naturecodevoid.forge.hypixelutils.util.Vector2D;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CoinTracker extends TextFeature {
    public static CoinTracker instance;

    public CoinTracker() {
        this.init();
    }

    public String getText(boolean showActualCoins) {
        String text = HypixelUtils.config.coinsMessages[HypixelUtils.config.coinsMessage]
                .replace("$1", Utils.getColor(HypixelUtils.config.coinsColor2) + String.valueOf(showActualCoins ? HypixelUtils.totalCoins : 1234))
                .replace("$2", Utils.getColor(HypixelUtils.config.coinsColor1).toString())
                .replace("$3", Utils.getColor(HypixelUtils.config.coinsColor2).toString());

        if (HypixelUtils.config.coinsBrackets)
            text = "[" + text + Utils.getColor(HypixelUtils.config.coinsColor1) + "]";

        return Utils.getColor(HypixelUtils.config.coinsColor1) + text;
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
        return new Vector2D(Minecraft.getMinecraft().fontRendererObj.getStringWidth(getText(false)) + Utils.textAddSmall, 16);
    }

    @Override
    public boolean isEnabled() {
        return HypixelUtils.config.coinsEnabled;
    }

    @Override
    public boolean isHypixel() {
        return true;
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
