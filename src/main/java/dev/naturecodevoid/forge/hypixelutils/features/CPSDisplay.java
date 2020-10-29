package dev.naturecodevoid.forge.hypixelutils.features;

import dev.naturecodevoid.forge.hypixelutils.BaseFeature;
import dev.naturecodevoid.forge.hypixelutils.HypixelUtils;
import dev.naturecodevoid.forge.hypixelutils.util.Util;
import dev.naturecodevoid.forge.hypixelutils.util.Vector2D;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Timer;
import java.util.TimerTask;

public class CPSDisplay extends BaseFeature {
    public static CPSDisplay instance;
    public static int cps = 0;
    public static int cpsRight = 0;

    public CPSDisplay() {
        this.init();
    }

    public String getText() {
        return getText(true);
    }

    public String getText(boolean showActual) {
        String cpsText = String.valueOf(cps);
        if (HypixelUtils.config.cpsRight) cpsText += " | " + cpsRight;

        String cpsTextFake = "10";
        if (HypixelUtils.config.cpsRight) cpsTextFake += " | 10";

        String text = HypixelUtils.config.cpsMessages[HypixelUtils.config.cpsMessage].replace("$1", showActual ? cpsText : cpsTextFake);
        return Util.getColor(HypixelUtils.config.cpsColor) + text;
    }

    @Override
    public void render() {
        this.render(true);
    }

    public void render(boolean showActual) {
        FontRenderer fRender = Minecraft.getMinecraft().fontRendererObj;

        String text = getText(showActual);
        Vector2D pos = Util.getPosFromPercent(HypixelUtils.config.cpsX, HypixelUtils.config.cpsY);

        pos.x += 4;
        pos.y += 16;

        fRender.drawStringWithShadow(text, pos.x, pos.y - 12, 0);
    }

    @Override
    public void renderEditor() {
        this.render(false);
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
        return new Vector2D(Minecraft.getMinecraft().fontRendererObj.getStringWidth(getText()) + Util.textAdd, 16);
    }

    @Override
    public boolean isEnabled() {
        return HypixelUtils.config.cpsEnabled;
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
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                cps--;
            }
        }, 1000);
    }

    public void addClickRight() {
        cpsRight++;
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                cpsRight--;
            }
        }, 1000);
    }
}
