package dev.naturecodevoid.forge.hypixelutils.features;

import dev.naturecodevoid.forge.hypixelutils.HypixelUtils;
import dev.naturecodevoid.forge.hypixelutils.base.TextFeature;
import dev.naturecodevoid.forge.hypixelutils.util.DrawUtils;
import dev.naturecodevoid.forge.hypixelutils.util.Utils;
import dev.naturecodevoid.forge.hypixelutils.util.Vector2D;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Clock extends TextFeature {
    public static Clock instance;

    public Clock() {
        this.init();
    }

    public String getText(boolean showActual) {
        String text = "";
        if (HypixelUtils.config.clock24hr) {
            Date date = new Date();
            Calendar calendar = GregorianCalendar.getInstance();
            calendar.setTime(date);

            text += Utils.addZeros(calendar.get(Calendar.HOUR_OF_DAY));

            text += ":" + Utils.addZeros(calendar.get(Calendar.MINUTE));

            if (HypixelUtils.config.clockSeconds) text += ":" + Utils.addZeros(calendar.get(Calendar.SECOND));
        } else {
            Date date = new Date();
            Calendar calendar = GregorianCalendar.getInstance();
            calendar.setTime(date);

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
        HypixelUtils.config.clockY = 15;
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
    public boolean isEnabled() {
        return HypixelUtils.config.clockEnabled;
    }

    @Override
    public boolean isHypixel() {
        return false;
    }
}
