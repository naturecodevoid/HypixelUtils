package dev.naturecodevoid.forge.hypixelutils.features;

import dev.naturecodevoid.forge.hypixelutils.HypixelUtils;
import dev.naturecodevoid.forge.hypixelutils.base.GuiFeatureEditor;
import dev.naturecodevoid.forge.hypixelutils.base.TextFeature;
import dev.naturecodevoid.forge.hypixelutils.base.TextFeatureEditor;
import dev.naturecodevoid.forge.hypixelutils.util.DrawUtils;
import dev.naturecodevoid.forge.hypixelutils.util.Utils;
import dev.naturecodevoid.forge.hypixelutils.util.Vector2D;
import net.minecraft.client.Minecraft;
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

    public String getText(boolean showActualFPS) {
        String text = HypixelUtils.config.fpsMessages[HypixelUtils.config.fpsMessage].replace("$1", showActualFPS ? String.valueOf(Minecraft.getDebugFPS()) : "120");

        if (HypixelUtils.config.fpsBrackets) text = "[" + text + "]";

        return Utils.getColor(HypixelUtils.config.fpsColor) + text;
    }

    @Override
    public void resetPosition() {
        HypixelUtils.config.fpsX = 300;
        HypixelUtils.config.fpsY = 0;
    }

    @Override
    public Vector2D getPosition() {
        return new Vector2D(HypixelUtils.config.fpsX, HypixelUtils.config.fpsY);
    }

    @Override
    public Vector2D setPosition(int xPercent, int yPercent) {
        HypixelUtils.config.fpsX = xPercent;
        HypixelUtils.config.fpsY = yPercent;
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

    private static class Editor extends TextFeatureEditor {
        @Override
        public TextFeature getFeature() {
            return FPSDisplay.get();
        }

        @Override
        public void init() {
            this.title = "FPS Display";

            super.init();
        }
    }
}
