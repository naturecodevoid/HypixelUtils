package dev.naturecodevoid.forge.hypixelutils.base;

import dev.naturecodevoid.forge.hypixelutils.util.DrawUtils;
import dev.naturecodevoid.forge.hypixelutils.util.Utils;
import dev.naturecodevoid.forge.hypixelutils.util.Vector2D;
import org.apache.commons.lang3.NotImplementedException;

public abstract class TextFeature extends GuiFeature {
    public String getText() {
        return getText(true);
    }

    public String getText(boolean showActual) {
        throw new NotImplementedException("Please override the getText function! This gets the text for TextFeatures.");
    }

    @Override
    public void render() {
        this.render(true);
    }

    public void render(boolean showActual) {
        String text = this.getText(showActual);
        Vector2D pos_ = this.getPosition();
        Vector2D pos = Utils.getPosFromPercent(pos_.x, pos_.y);

        pos.x += 4;
        pos.y += 4;

        DrawUtils.drawStringWithShadow(text, pos.x, pos.y);
    }

    @Override
    public void renderEditor() {
        this.render(false);
    }
}
