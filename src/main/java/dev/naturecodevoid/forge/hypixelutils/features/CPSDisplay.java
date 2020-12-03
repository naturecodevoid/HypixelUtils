package dev.naturecodevoid.forge.hypixelutils.features;

import dev.naturecodevoid.forge.hypixelutils.HypixelUtils;
import dev.naturecodevoid.forge.hypixelutils.base.TextFeature;
import dev.naturecodevoid.forge.hypixelutils.util.DrawUtils;
import dev.naturecodevoid.forge.hypixelutils.util.Timer;
import dev.naturecodevoid.forge.hypixelutils.util.Utils;
import dev.naturecodevoid.forge.hypixelutils.util.Vector2D;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CPSDisplay extends TextFeature {
    public static CPSDisplay instance;
    public static int cps = 0;
    public static int cpsRight = 0;

    public CPSDisplay() {
        this.init();
    }

    public String getText(boolean showActual) {
        String cpsText = String.valueOf(cps);
        if (HypixelUtils.config.cpsRight) cpsText += " | " + cpsRight;

        String cpsTextFake = "10";
        if (HypixelUtils.config.cpsRight) cpsTextFake += " | 10";

        String text = HypixelUtils.config.cpsMessages[HypixelUtils.config.cpsMessage].replace("$1", showActual ? cpsText : cpsTextFake);

        if (HypixelUtils.config.cpsBrackets) text = "[" + text + "]";

        return Utils.getColor(HypixelUtils.config.cpsColor) + text;
    }

    @Override
    public void resetPosition() {
        HypixelUtils.config.cpsX = 30;
        HypixelUtils.config.cpsY = 15;
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
    public boolean isEnabled() {
        return HypixelUtils.config.cpsEnabled;
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
                addClick();
                break;

            case 1:
                // Right click
                addClickRight();
                break;
        }
    }

    public void addClick() {
        cps++;
        new Timer(() -> cps--, 1000);
    }

    public void addClickRight() {
        cpsRight++;
        new Timer(() -> cpsRight--, 1000);
    }
}
