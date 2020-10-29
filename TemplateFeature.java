package dev.naturecodevoid.forge.hypixelutils.features;

/*
New feature todo:
- Copy this to the features directory
- Rename
- Search for 'template' and change the results
- Add the class to the feature list in HypixelUtils.java
- Add config values
- Code the feature + test
- Remove this comment
 */

import dev.naturecodevoid.forge.hypixelutils.BaseFeature;
import dev.naturecodevoid.forge.hypixelutils.HypixelUtils;
import dev.naturecodevoid.forge.hypixelutils.util.Util;
import dev.naturecodevoid.forge.hypixelutils.util.Vector2D;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

public class TemplateFeature extends BaseFeature {
    public static TemplateFeature instance;

    public TemplateFeature() {
        this.init();
    }

    public String getText() {
        return getText(true);
    }

    public String getText(boolean showActual) {
        return "TemplateFeature";
    }

    @Override
    public void render() {
        this.render(true);
    }

    public void render(boolean showActual) {
        FontRenderer fRender = Minecraft.getMinecraft().fontRendererObj;

        String text = getText(showActual);
        Vector2D pos = Util.getPosFromPercent(HypixelUtils.config.templateX, HypixelUtils.config.templateY);

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
        HypixelUtils.config.templateX = 0;
        HypixelUtils.config.templateY = 0;
    }

    @Override
    public Vector2D getPosition() {
        return new Vector2D(HypixelUtils.config.templateX, HypixelUtils.config.templateY);
    }

    @Override
    public Vector2D setPosition(int xPercent, int yPercent) {
        HypixelUtils.config.templateX = xPercent;
        HypixelUtils.config.templateY = yPercent;
        return getPosition();
    }

    @Override
    public Vector2D getSize() {
        return new Vector2D(Minecraft.getMinecraft().fontRendererObj.getStringWidth(getText()) + Util.textAddSmall /* + Util.textAdd */, 16);
    }

    @Override
    public boolean isEnabled() {
        return HypixelUtils.config.templateEnabled;
    }
}
