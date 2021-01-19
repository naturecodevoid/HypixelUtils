package dev.naturecodevoid.forge.hypixelutils.features;

import dev.naturecodevoid.forge.hypixelutils.HypixelUtils;
import dev.naturecodevoid.forge.hypixelutils.base.GuiFeatureEditor;
import dev.naturecodevoid.forge.hypixelutils.base.TextFeature;
import dev.naturecodevoid.forge.hypixelutils.base.TextFeatureEditor;
import dev.naturecodevoid.forge.hypixelutils.util.DrawUtils;
import dev.naturecodevoid.forge.hypixelutils.util.Utils;
import dev.naturecodevoid.forge.hypixelutils.util.Vector2D;
import net.minecraft.client.gui.GuiButton;
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

    public String getText(boolean showActual) {
        Date date = new Date();
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);

        if (!showActual) {
            calendar.set(Calendar.HOUR_OF_DAY, 12);
            calendar.set(Calendar.MINUTE, 34);
            calendar.set(Calendar.SECOND, 56);
        }

        String text = "";

        if (HypixelUtils.config.clock24hr) {
            text += Utils.addZeros(calendar.get(Calendar.HOUR_OF_DAY));

            text += ":" + Utils.addZeros(calendar.get(Calendar.MINUTE));

            if (HypixelUtils.config.clockSeconds) text += ":" + Utils.addZeros(calendar.get(Calendar.SECOND));
        } else {
            int hour = calendar.get(Calendar.HOUR);
            if (hour == 0) hour = 12;
            text += hour;

            text += ":" + Utils.addZeros(calendar.get(Calendar.MINUTE));

            if (HypixelUtils.config.clockSeconds) text += ":" + Utils.addZeros(calendar.get(Calendar.SECOND));

            text += " " + (calendar.get(Calendar.AM_PM) == Calendar.AM ? "AM" : "PM");
        }

        if (HypixelUtils.config.clockBrackets) text = "[" + text + "]";

        return Utils.getColor(HypixelUtils.config.clockColor) + text;
    }

    @Override
    public void resetPosition() {
        HypixelUtils.config.clockX = 0;
        HypixelUtils.config.clockY = 150;
    }

    @Override
    public Vector2D getPosition() {
        return new Vector2D(HypixelUtils.config.clockX, HypixelUtils.config.clockY);
    }

    @Override
    public Vector2D setPosition(int xPercent, int yPercent) {
        HypixelUtils.config.clockX = xPercent;
        HypixelUtils.config.clockY = yPercent;
        return getPosition();
    }

    @Override
    public Vector2D getSize() {
        return new Vector2D(DrawUtils.getStringWidth(getText()) + Utils.textAddSmall, 16);
    }

    @Override
    public GuiFeatureEditor getEditor() {
        return new Editor();
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

    private static class Editor extends TextFeatureEditor {
        @Override
        public TextFeature getFeature() {
            return Clock.get();
        }

        @Override
        public void init() {
            this.title = "Clock";
            this.showMessage = false;

            super.init();

            makeButton(
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

            makeButton(
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
