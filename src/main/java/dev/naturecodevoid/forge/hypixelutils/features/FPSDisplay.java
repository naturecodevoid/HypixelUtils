package dev.naturecodevoid.forge.hypixelutils.features;

import dev.naturecodevoid.forge.hypixelutils.BaseFeature;
import dev.naturecodevoid.forge.hypixelutils.HypixelUtils;
import dev.naturecodevoid.forge.hypixelutils.util.Utils;
import dev.naturecodevoid.forge.hypixelutils.util.Vector2D;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

public class FPSDisplay extends BaseFeature {
    public static FPSDisplay instance;

    public FPSDisplay() {
        this.init();
    }

    public String getText() {
        return getText(true);
    }

    public String getText(boolean showActualFPS) {
        String text = HypixelUtils.config.fpsMessages[HypixelUtils.config.fpsMessage].replace("$1", showActualFPS ? String.valueOf(Minecraft.getDebugFPS()) : "999");

        if (HypixelUtils.config.clockBrackets) text = "[" + text + "]";

        return Utils.getColor(HypixelUtils.config.fpsColor) + text;
    }

    @Override
    public void render() {
        this.render(true);
    }

    public void render(boolean showActualFPS) {
        FontRenderer fRender = Minecraft.getMinecraft().fontRendererObj;

        String text = getText(showActualFPS);
        Vector2D pos = Utils.getPosFromPercent(HypixelUtils.config.fpsX, HypixelUtils.config.fpsY);

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
        HypixelUtils.config.fpsX = 30;
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
        return new Vector2D(Minecraft.getMinecraft().fontRendererObj.getStringWidth(getText(false)) + Utils.textAddSmall, 16);
    }

    @Override
    public boolean isEnabled() {
        return HypixelUtils.config.fpsEnabled;
    }
}
