package dev.naturecodevoid.forge.hypixelutils.features;

import dev.naturecodevoid.forge.hypixelutils.HypixelUtils;
import dev.naturecodevoid.forge.hypixelutils.base.GuiFeatureEditor;
import dev.naturecodevoid.forge.hypixelutils.base.TextFeature;
import dev.naturecodevoid.forge.hypixelutils.base.TextFeatureEditor;
import dev.naturecodevoid.forge.hypixelutils.util.DrawUtils;
import dev.naturecodevoid.forge.hypixelutils.util.Timer;
import dev.naturecodevoid.forge.hypixelutils.util.Utils;
import dev.naturecodevoid.forge.hypixelutils.util.Vector2D;
import net.minecraft.client.gui.GuiButton;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CPSDisplay extends TextFeature.TextMethods implements TextFeature {
    public static int cpsLeft = 0;
    public static int cpsRight = 0;
    private static CPSDisplay instance = null;

    public CPSDisplay() {
        instance = this;
        MinecraftForge.EVENT_BUS.register(this);
    }

    public static CPSDisplay get() {
        return instance;
    }

    public String getText(boolean showActual) {
        String cpsText = String.valueOf(cpsLeft);
        if (HypixelUtils.config.cpsRight) cpsText += " | " + cpsRight;

        String cpsTextFake = "10";
        if (HypixelUtils.config.cpsRight) cpsTextFake += " | 10";

        String text = HypixelUtils.config.cpsMessages[HypixelUtils.config.cpsMessage].replace("$1", showActual ? cpsText : cpsTextFake);

        if (HypixelUtils.config.cpsBrackets) text = "[" + text + "]";

        return Utils.getColor(HypixelUtils.config.cpsColor) + text;
    }

    @Override
    public void resetPosition() {
        HypixelUtils.config.cpsX = 300;
        HypixelUtils.config.cpsY = 150;
    }

    @Override
    public Vector2D getPosition() {
        return new Vector2D(HypixelUtils.config.cpsX, HypixelUtils.config.cpsY);
    }

    @Override
    public Vector2D setPosition(int xPercent, int yPercent) {
        HypixelUtils.config.cpsX = xPercent;
        HypixelUtils.config.cpsY = yPercent;
        return getPosition();
    }

    @Override
    public Vector2D getSize() {
        return new Vector2D(DrawUtils.getStringWidth(getText()) + Utils.textAdd + 1, 16);
    }

    @Override
    public GuiFeatureEditor getEditor() {
        return new Editor();
    }

    @Override
    public boolean getEnabled() {
        return HypixelUtils.config.cpsEnabled;
    }

    @Override
    public boolean setEnabled(boolean enabled) {
        HypixelUtils.config.cpsEnabled = enabled;
        return HypixelUtils.config.cpsEnabled;
    }

    @Override
    public boolean getBrackets() {
        return HypixelUtils.config.cpsBrackets;
    }

    @Override
    public boolean setBrackets(boolean brackets) {
        HypixelUtils.config.cpsBrackets = brackets;
        return HypixelUtils.config.cpsBrackets;
    }

    @Override
    public int getMessageIndex() {
        return HypixelUtils.config.cpsMessage;
    }

    @Override
    public int setMessageIndex(int messageIndex) {
        messageIndex = Utils.limitArrayIndexToArraySize(getMessages(), messageIndex);
        HypixelUtils.config.cpsMessage = messageIndex;
        return HypixelUtils.config.cpsMessage;
    }

    @Override
    public String[] getMessages() {
        return HypixelUtils.config.cpsMessages;
    }

    @Override
    public String[] getMessagesFriendly() {
        return HypixelUtils.config.cpsMessagesFriendly;
    }

    @Override
    public int getColor() {
        return HypixelUtils.config.cpsColor;
    }

    @Override
    public int setColor(int color) {
        color = Utils.limitArrayIndexToArraySize(HypixelUtils.config.colors, color);
        HypixelUtils.config.cpsColor = color;
        return HypixelUtils.config.cpsColor;
    }

    @Override
    public boolean isHypixel() {
        return false;
    }

    @SubscribeEvent
    public void onMouseClick(MouseEvent event) {
        if (event.buttonstate) switch (event.button) {
            case 0:
                // Left click
                addLeftClick();
                break;

            case 1:
                // Right click
                addRightClick();
                break;
        }
    }

    public void addLeftClick() {
        cpsLeft++;
        new Timer(() -> cpsLeft--, 1000);
    }

    public void addRightClick() {
        cpsRight++;
        new Timer(() -> cpsRight--, 1000);
    }

    private static class Editor extends TextFeatureEditor {
        @Override
        public TextFeature getFeature() {
            return CPSDisplay.get();
        }

        @Override
        public void init() {
            this.title = "CPS Display";

            super.init();

            makeButton(
                    "Show right cps: " + Utils.getBooleanText(HypixelUtils.config.cpsRight),
                    (GuiButton btn) -> {
                        HypixelUtils.config.cpsRight = !(HypixelUtils.config.cpsRight);
                        btn.displayString = "Show right cps: " + Utils.getBooleanText(HypixelUtils.config.cpsRight);
                        return null;
                    },
                    (GuiButton btn) -> {
                        btn.displayString = "Show right cps: " + Utils.getBooleanText(HypixelUtils.config.cpsRight);
                        return null;
                    }
            );
        }
    }
}
