package dev.naturecodevoid.hypixelutils.features;

import dev.naturecodevoid.hypixelutils.HypixelUtils;
import dev.naturecodevoid.hypixelutils.base.TextFeature;
import dev.naturecodevoid.hypixelutils.util.DrawUtils;
import dev.naturecodevoid.hypixelutils.util.Utils;
import dev.naturecodevoid.hypixelutils.util.Vector2;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.MinecraftForge;

public class FPSDisplay extends TextFeature.TextMethods implements TextFeature {
    private static FPSDisplay instance = null;

    public FPSDisplay() {
        instance = this;
        MinecraftForge.EVENT_BUS.register(this);
    }

    public static FPSDisplay get() {
        return instance;
    }

    public String displayName() {
        return "FPS Display";
    }

    public String getText(boolean showActualFPS) {
        // Replace the indicator with the FPS
        String text = HypixelUtils.config.fpsMessages[HypixelUtils.config.fpsMessage].replace("$1", showActualFPS ? "" + Minecraft.getDebugFPS() : "120");

        // Brackets
        if (HypixelUtils.config.fpsBrackets) text = "[" + text + "]";

        return Utils.getColor(HypixelUtils.config.fpsColor) + text;
    }

    @Override
    public void resetPosition() {
        HypixelUtils.config.fpsX = 300;
        HypixelUtils.config.fpsY = 0;
    }

    @Override
    public Vector2 getPosition() {
        return new Vector2(HypixelUtils.config.fpsX, HypixelUtils.config.fpsY);
    }

    @Override
    public Vector2 setPosition(int xPercent, int yPercent) {
        HypixelUtils.config.fpsX = xPercent;
        HypixelUtils.config.fpsY = yPercent;
        return getPosition();
    }

    @Override
    public Vector2 getSize() {
        return new Vector2(DrawUtils.getStringWidth(getText(false)) + Utils.textAddSmall, 16);
    }

    @Override
    public boolean getEnabled() {
        return HypixelUtils.config.fpsEnabled;
    }

    @Override
    public boolean setEnabled(boolean enabled) {
        HypixelUtils.config.fpsEnabled = enabled;
        return HypixelUtils.config.fpsEnabled;
    }

    @Override
    public boolean getBrackets() {
        return HypixelUtils.config.fpsBrackets;
    }

    @Override
    public boolean setBrackets(boolean brackets) {
        HypixelUtils.config.fpsBrackets = brackets;
        return HypixelUtils.config.fpsBrackets;
    }

    @Override
    public int getMessageIndex() {
        return HypixelUtils.config.fpsMessage;
    }

    @Override
    public int setMessageIndex(int messageIndex) {
        if (messageIndex > getMessages().length - 1) {
            messageIndex = 0;
        }
        HypixelUtils.config.fpsMessage = messageIndex;
        return HypixelUtils.config.fpsMessage;
    }

    @Override
    public String[] getMessages() {
        return HypixelUtils.config.fpsMessages;
    }

    @Override
    public String[] getMessagesFriendly() {
        return HypixelUtils.config.fpsMessagesFriendly;
    }

    @Override
    public int getColor() {
        return HypixelUtils.config.fpsColor;
    }

    @Override
    public int setColor(int color) {
        color = Utils.limitArrayIndexToArraySize(HypixelUtils.config.colors, color);
        HypixelUtils.config.fpsColor = color;
        return HypixelUtils.config.fpsColor;
    }

    @Override
    public boolean isHypixel() {
        return false;
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
            return FPSDisplay.get();
        }

        @Override
        public void init() {
            this.maxPerColumn = 3;

            super.init();
        }
    }
}
