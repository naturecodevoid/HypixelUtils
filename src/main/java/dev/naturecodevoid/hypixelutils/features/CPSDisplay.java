package dev.naturecodevoid.hypixelutils.features;

import dev.naturecodevoid.hypixelutils.HypixelUtils;
import dev.naturecodevoid.hypixelutils.base.TextFeature;
import dev.naturecodevoid.hypixelutils.util.DrawUtils;
import dev.naturecodevoid.hypixelutils.util.Timer;
import dev.naturecodevoid.hypixelutils.util.Utils;
import dev.naturecodevoid.hypixelutils.util.Vector2;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CPSDisplay extends TextFeature.TextMethods implements TextFeature {
    /**
     * Left CPS counter
     */
    protected static int cpsLeft = 0;
    /**
     * Right CPS counter
     */
    protected static int cpsRight = 0;
    private static CPSDisplay instance = null;

    public CPSDisplay() {
        instance = this;
        MinecraftForge.EVENT_BUS.register(this);
    }

    public static CPSDisplay get() {
        return instance;
    }

    public String displayName() {
        return "CPS Display";
    }

    public String getText(boolean showActual) {
        final String straightLine = "\u23B9";
        final String otherLine = "|";

        // Left CPS text
        String cpsText = "" + this.cpsLeft;
        // Right CPS text
        if (HypixelUtils.config.cpsRight)
            cpsText += " " + (HypixelUtils.config.cpsStraightLine ? straightLine : otherLine) + " " + this.cpsRight;

        // Fake left CPS text
        String cpsTextFake = "10";
        // Fake right CPS text
        if (HypixelUtils.config.cpsRight)
            cpsTextFake += " " + (HypixelUtils.config.cpsStraightLine ? straightLine : otherLine) + " 10";

        // Replace the indicator in the selected message with the CPS text
        String text = HypixelUtils.config.cpsMessages[HypixelUtils.config.cpsMessage].replace("$1", showActual ? cpsText : cpsTextFake);

        // Brackets
        if (HypixelUtils.config.cpsBrackets) text = "[" + text + "]";

        return Utils.getColor(HypixelUtils.config.cpsColor) + text;
    }

    @Override
    public void resetPosition() {
        HypixelUtils.config.cpsX = 300;
        HypixelUtils.config.cpsY = 150;
    }

    @Override
    public Vector2 getPosition() {
        return new Vector2(HypixelUtils.config.cpsX, HypixelUtils.config.cpsY);
    }

    @Override
    public Vector2 setPosition(int xPercent, int yPercent) {
        HypixelUtils.config.cpsX = xPercent;
        HypixelUtils.config.cpsY = yPercent;
        return getPosition();
    }

    @Override
    public Vector2 getSize() {
        return new Vector2(DrawUtils.getStringWidth(getText()) + Utils.textAdd + 1, 16);
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
        CPSDisplay.cpsLeft++;
        new Timer(() -> cpsLeft--, 1000);
    }

    public void addRightClick() {
        CPSDisplay.cpsRight++;
        new Timer(() -> cpsRight--, 1000);
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
            return CPSDisplay.get();
        }

        @Override
        public void init() {
            this.maxPerColumn = 4;

            super.init();

            // Adds all the special config buttons

            // Right CPS

            this.makeButton(
                    "Show right CPS: " + Utils.getBooleanText(HypixelUtils.config.cpsRight),
                    (GuiButton btn) -> {
                        HypixelUtils.config.cpsRight = !(HypixelUtils.config.cpsRight);
                        btn.displayString = "Show right CPS: " + Utils.getBooleanText(HypixelUtils.config.cpsRight);
                        return null;
                    },
                    (GuiButton btn) -> {
                        btn.displayString = "Show right CPS: " + Utils.getBooleanText(HypixelUtils.config.cpsRight);
                        return null;
                    }
            );

            // Special separator

            this.makeButton(
                    "Use special separator: " + Utils.getBooleanText(HypixelUtils.config.cpsStraightLine),
                    (GuiButton btn) -> {
                        HypixelUtils.config.cpsStraightLine = !(HypixelUtils.config.cpsStraightLine);
                        btn.displayString = "Use special separator: " + Utils.getBooleanText(HypixelUtils.config.cpsStraightLine);
                        return null;
                    },
                    (GuiButton btn) -> {
                        btn.displayString = "Use special separator: " + Utils.getBooleanText(HypixelUtils.config.cpsStraightLine);
                        return null;
                    }
            );
        }
    }
}
