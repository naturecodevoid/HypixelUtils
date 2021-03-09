package dev.naturecodevoid.hypixelutils.features;

import dev.naturecodevoid.hypixelutils.HypixelUtils;
import dev.naturecodevoid.hypixelutils.base.TextFeature;
import dev.naturecodevoid.hypixelutils.util.DrawUtils;
import dev.naturecodevoid.hypixelutils.util.Utils;
import dev.naturecodevoid.hypixelutils.util.Vector2;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CoinTracker extends TextFeature.TextMethods implements TextFeature {
    /**
     * Amount of coins that should be displayed. Separate from the amount of coins stored in Config.
     */
    public static int coins = 0;
    private static CoinTracker instance = null;

    public CoinTracker() {
        instance = this;
        MinecraftForge.EVENT_BUS.register(this);
    }

    public static CoinTracker get() {
        return instance;
    }

    public String displayName() {
        return "Coin Tracker";
    }

    public String getText(boolean showActualCoins) {
        // Replace all the indicators with the proper values
        String text = HypixelUtils.config.coinsMessages[HypixelUtils.config.coinsMessage]
                // Coins
                .replace("$1", Utils.getColor(HypixelUtils.config.coinsColor2) + String.valueOf(showActualCoins ? CoinTracker.coins : 1234))
                // Message color
                .replace("$2", Utils.getColor(HypixelUtils.config.coinsColor1).toString())
                // Coin color
                .replace("$3", Utils.getColor(HypixelUtils.config.coinsColor2).toString());

        // Brackets
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
    public Vector2 getPosition() {
        return new Vector2(HypixelUtils.config.coinTrackerX, HypixelUtils.config.coinTrackerY);
    }

    @Override
    public Vector2 setPosition(int xPercent, int yPercent) {
        HypixelUtils.config.coinTrackerX = xPercent;
        HypixelUtils.config.coinTrackerY = yPercent;
        return getPosition();
    }

    @Override
    public Vector2 getSize() {
        return new Vector2(DrawUtils.getStringWidth(getText(false)) + Utils.textAddSmall, 16);
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

        // If the message looks like a coins message
        if (message.startsWith("+") && message.contains("coins") && !message.endsWith("for being generous")) {
            // Split the message
            String[] splittedMessage = message.split("coins");

            // Remove unnecessary stuff
            message = splittedMessage[0].replace("+", "");
            message = message.replace(" ", "");

            // Parse amount of coins
            int coins = Integer.parseInt(message);

            // Add to coins count and Config coins count
            CoinTracker.coins += coins;
            HypixelUtils.config.coinTrackerCoins = CoinTracker.coins;

            // Save the config
            Utils.saveConfig();
        }
    }

    @Override
    public TextEditor getEditor(GuiScreen backScreen) {
        return new Editor(backScreen);
    }

    private static class Editor extends TextEditor {
        public Editor(GuiScreen backScreen) {
            super(backScreen);
        }

        @Override
        public TextFeature getFeature() {
            return CoinTracker.get();
        }

        @Override
        public void init() {
            this.showColor = false;
            this.maxPerColumn = 4;

            super.init();

            // Adds all the special config buttons

            // Message color switcher

            this.makeButton(
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

            // Coin color switcher

            this.makeButton(
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

            // Grabs coins from config

            this.makeButton(
                    "Use coins from last session",
                    (GuiButton btn) -> {
                        CoinTracker.coins = HypixelUtils.config.coinTrackerCoins;
                        return null;
                    },
                    (GuiButton btn) -> null
            );

            // Resets coins - doesn't write to config

            this.makeButton(
                    "Reset coin tracker coins",
                    (GuiButton btn) -> {
                        CoinTracker.coins = 0;
                        return null;
                    },
                    (GuiButton btn) -> null
            );
        }
    }
}
