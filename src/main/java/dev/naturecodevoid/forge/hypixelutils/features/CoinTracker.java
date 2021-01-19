package dev.naturecodevoid.forge.hypixelutils.features;

import dev.naturecodevoid.forge.hypixelutils.HypixelUtils;
import dev.naturecodevoid.forge.hypixelutils.base.GuiFeatureEditor;
import dev.naturecodevoid.forge.hypixelutils.base.TextFeature;
import dev.naturecodevoid.forge.hypixelutils.base.TextFeatureEditor;
import dev.naturecodevoid.forge.hypixelutils.util.DrawUtils;
import dev.naturecodevoid.forge.hypixelutils.util.Utils;
import dev.naturecodevoid.forge.hypixelutils.util.Vector2D;
import net.minecraft.client.gui.GuiButton;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CoinTracker extends TextFeature.TextMethods implements TextFeature {
    public static int coins = 0;
    private static CoinTracker instance = null;

    public CoinTracker() {
        instance = this;
        MinecraftForge.EVENT_BUS.register(this);
    }

    public static CoinTracker get() {
        return instance;
    }

    public String getText(boolean showActualCoins) {
        String text = HypixelUtils.config.coinsMessages[HypixelUtils.config.coinsMessage]
                .replace("$1", Utils.getColor(HypixelUtils.config.coinsColor2) + String.valueOf(showActualCoins ? coins : 1234))
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
        return new Vector2D(DrawUtils.getStringWidth(getText(false)) + Utils.textAddSmall, 16);
    }

    @Override
    public GuiFeatureEditor getEditor() {
        return new Editor();
    }

    @Override
    public boolean getEnabled() {
        return HypixelUtils.config.coinsEnabled;
    }

    @Override
    public boolean setEnabled(boolean enabled) {
        HypixelUtils.config.coinsEnabled = enabled;
        return HypixelUtils.config.coinsEnabled;
    }

    @Override
    public boolean getBrackets() {
        return HypixelUtils.config.coinsBrackets;
    }

    @Override
    public boolean setBrackets(boolean brackets) {
        HypixelUtils.config.coinsBrackets = brackets;
        return HypixelUtils.config.coinsBrackets;
    }

    @Override
    public int getMessageIndex() {
        return HypixelUtils.config.coinsMessage;
    }

    @Override
    public int setMessageIndex(int messageIndex) {
        if (messageIndex > getMessages().length - 1) {
            messageIndex = 0;
        }
        HypixelUtils.config.coinsMessage = messageIndex;
        return HypixelUtils.config.coinsMessage;
    }

    @Override
    public String[] getMessages() {
        return HypixelUtils.config.coinsMessages;
    }

    @Override
    public String[] getMessagesFriendly() {
        return HypixelUtils.config.coinsMessagesFriendly;
    }

    @Override
    public int getColor() {
        return 0;
    }

    @Override
    public int setColor(int color) {
        return 0;
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
            CoinTracker.coins += coins;
            HypixelUtils.config.coinTrackerCoins = CoinTracker.coins;
            Utils.saveConfig();
        }
    }

    private static class Editor extends TextFeatureEditor {
        @Override
        public TextFeature getFeature() {
            return CoinTracker.get();
        }

        @Override
        public void init() {
            this.title = "Coin Tracker";
            this.showColor = false;

            super.init();

            makeButton(
                    "Message color: " + Utils.getColorText(HypixelUtils.config.colors[HypixelUtils.config.coinsColor1]),
                    (GuiButton btn) -> {
                        HypixelUtils.config.coinsColor1 = Utils.limitArrayIndexToArraySize(HypixelUtils.config.colors, HypixelUtils.config.coinsColor1 + 1);
                        btn.displayString = "Message color: " + Utils.getColorText(HypixelUtils.config.colors[HypixelUtils.config.coinsColor1]);
                        return null;
                    },
                    (GuiButton btn) -> {
                        btn.displayString = "Message color: " + Utils.getColorText(HypixelUtils.config.colors[HypixelUtils.config.coinsColor1]);
                        return null;
                    }
            );

            makeButton(
                    "Coin color: " + Utils.getColorText(HypixelUtils.config.colors[HypixelUtils.config.coinsColor2]),
                    (GuiButton btn) -> {
                        HypixelUtils.config.coinsColor2 = Utils.limitArrayIndexToArraySize(HypixelUtils.config.colors, HypixelUtils.config.coinsColor2 + 1);
                        btn.displayString = "Coin color: " + Utils.getColorText(HypixelUtils.config.colors[HypixelUtils.config.coinsColor2]);
                        return null;
                    },
                    (GuiButton btn) -> {
                        btn.displayString = "Coin color: " + Utils.getColorText(HypixelUtils.config.colors[HypixelUtils.config.coinsColor2]);
                        return null;
                    }
            );
        }
    }
}
