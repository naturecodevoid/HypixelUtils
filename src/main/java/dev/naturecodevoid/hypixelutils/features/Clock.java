package dev.naturecodevoid.hypixelutils.features;

import dev.naturecodevoid.hypixelutils.HypixelUtils;
import dev.naturecodevoid.hypixelutils.base.TextFeature;
import dev.naturecodevoid.hypixelutils.util.DrawUtils;
import dev.naturecodevoid.hypixelutils.util.Utils;
import dev.naturecodevoid.hypixelutils.util.Vector2;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.MinecraftForge;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Clock extends TextFeature.TextMethods implements TextFeature {
    private static Clock instance = null;

    public Clock() {
        instance = this;
        MinecraftForge.EVENT_BUS.register(this);
    }

    public static Clock get() {
        return instance;
    }

    public String displayName() {
        return "Clock";
    }

    public String getText(boolean showActual) {
        // Get the date
        Date date = new Date();
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);

        // if (!showActual) {
        //     calendar.set(Calendar.HOUR_OF_DAY, 12);
        //     calendar.set(Calendar.MINUTE, 34);
        //     calendar.set(Calendar.SECOND, 56);
        // }

        String text = "";

        if (HypixelUtils.config.clock24hr) {
            // 24 hr version
            text += Utils.addZeros(calendar.get(Calendar.HOUR_OF_DAY));

            text += ":" + Utils.addZeros(calendar.get(Calendar.MINUTE));

            if (HypixelUtils.config.clockSeconds) text += ":" + Utils.addZeros(calendar.get(Calendar.SECOND));
        } else {
            // 12 hr version
            int hour = calendar.get(Calendar.HOUR);
            if (hour == 0) hour = 12;
            text += hour;

            text += ":" + Utils.addZeros(calendar.get(Calendar.MINUTE));

            if (HypixelUtils.config.clockSeconds) text += ":" + Utils.addZeros(calendar.get(Calendar.SECOND));

            text += " " + (calendar.get(Calendar.AM_PM) == Calendar.AM ? "AM" : "PM");
        }

        // Brackets
        if (HypixelUtils.config.clockBrackets) text = "[" + text + "]";

        return Utils.getColor(HypixelUtils.config.clockColor) + text;
    }

    @Override
    public void resetPosition() {
        HypixelUtils.config.clockX = 0;
        HypixelUtils.config.clockY = 150;
    }

    @Override
    public Vector2 getPosition() {
        return new Vector2(HypixelUtils.config.clockX, HypixelUtils.config.clockY);
    }

    @Override
    public Vector2 setPosition(int xPercent, int yPercent) {
        HypixelUtils.config.clockX = xPercent;
        HypixelUtils.config.clockY = yPercent;
        return getPosition();
    }

    @Override
    public Vector2 getSize() {
        return new Vector2(DrawUtils.getStringWidth(getText()) + Utils.textAddSmall, 16);
    }

    @Override
    public boolean getEnabled() {
        return HypixelUtils.config.clockEnabled;
    }

    @Override
    public boolean setEnabled(boolean enabled) {
        HypixelUtils.config.clockEnabled = enabled;
        return HypixelUtils.config.clockEnabled;
    }

    @Override
    public boolean getBrackets() {
        return HypixelUtils.config.clockBrackets;
    }

    @Override
    public boolean setBrackets(boolean brackets) {
        HypixelUtils.config.clockBrackets = brackets;
        return HypixelUtils.config.clockBrackets;
    }

    @Override
    public int getMessageIndex() {
        return 0;
    }

    @Override
    public int setMessageIndex(int messageIndex) {
        return 0;
    }

    @Override
    public String[] getMessages() {
        return new String[]{};
    }

    @Override
    public String[] getMessagesFriendly() {
        return new String[]{};
    }

    @Override
    public int getColor() {
        return HypixelUtils.config.clockColor;
    }

    @Override
    public int setColor(int color) {
        color = Utils.limitArrayIndexToArraySize(HypixelUtils.config.colors, color);
        HypixelUtils.config.clockColor = color;
        return HypixelUtils.config.clockColor;
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
            return Clock.get();
        }

        @Override
        public void init() {
            this.showMessage = false;
            this.maxPerColumn = 3;

            super.init();

            // Adds all the special config buttons

            // 24 hr format

            this.makeButton(
                    "Show in 24 hour format: " + Utils.getBooleanText(HypixelUtils.config.clock24hr),
                    (GuiButton btn) -> {
                        HypixelUtils.config.clock24hr = !(HypixelUtils.config.clock24hr);
                        btn.displayString = "Show in 24 hour format: " + Utils.getBooleanText(HypixelUtils.config.clock24hr);
                        return null;
                    },
                    (GuiButton btn) -> {
                        btn.displayString = "Show in 24 hour format: " + Utils.getBooleanText(HypixelUtils.config.clock24hr);
                        return null;
                    }
            );

            // Show seconds

            this.makeButton(
                    "Show seconds: " + Utils.getBooleanText(HypixelUtils.config.clockSeconds),
                    (GuiButton btn) -> {
                        HypixelUtils.config.clockSeconds = !(HypixelUtils.config.clockSeconds);
                        btn.displayString = "Show seconds: " + Utils.getBooleanText(HypixelUtils.config.clockSeconds);
                        return null;
                    },
                    (GuiButton btn) -> {
                        btn.displayString = "Show seconds: " + Utils.getBooleanText(HypixelUtils.config.clockSeconds);
                        return null;
                    }
            );
        }
    }
}
