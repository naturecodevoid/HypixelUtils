package dev.naturecodevoid.forge.hypixelutils.base;

import dev.naturecodevoid.forge.hypixelutils.util.DrawUtils;
import dev.naturecodevoid.forge.hypixelutils.util.Utils;
import net.minecraft.client.gui.GuiButton;
import org.apache.commons.lang3.NotImplementedException;

public abstract class TextFeatureEditor extends GuiFeatureEditor {
    public boolean showBrackets = true;
    public boolean showColor = true;
    public boolean showMessage = true;

    @Override
    public TextFeature getFeature() {
        throw new NotImplementedException("Please override the getFeature function!");
    }

    @Override
    public void init() {
        super.init();

        TextFeature f = getFeature();

        if (showBrackets) {
            makeButton(
                    "Brackets: " + Utils.getBooleanText(f.getBrackets()),
                    (GuiButton btn) -> {
                        f.setBrackets(!(f.getBrackets()));
                        btn.displayString = "Brackets: " + Utils.getBooleanText(f.getBrackets());
                        return null;
                    },
                    (GuiButton btn) -> {
                        btn.displayString = "Brackets: " + Utils.getBooleanText(f.getBrackets());
                        return null;
                    }
            );
        }

        if (showMessage) {
            makeButton(
                    "Message: " + f.getMessagesFriendly()[f.getMessageIndex()],
                    (GuiButton btn) -> {
                        f.setMessageIndex(f.getMessageIndex() + 1);
                        btn.displayString = "Message: " + f.getMessagesFriendly()[f.getMessageIndex()];
                        return null;
                    },
                    (GuiButton btn) -> {
                        btn.displayString = "Message: " + f.getMessagesFriendly()[f.getMessageIndex()];
                        return null;
                    }
            );
        }

        if (showColor) {
            makeButton(
                    "Color: " + Utils.getColorText(Utils.getColor(f.getColor())),
                    (GuiButton btn) -> {
                        f.setColor(f.getColor() + 1);
                        btn.displayString = "Color: " + Utils.getColorText(Utils.getColor(f.getColor()));
                        return null;
                    },
                    (GuiButton btn) -> {
                        btn.displayString = "Color: " + Utils.getColorText(Utils.getColor(f.getColor()));
                        return null;
                    }
            );
        }
    }


    @Override
    public void drawScreen(int x, int y, float partialTicks) {
        super.drawScreen(x, y, partialTicks);

        DrawUtils.drawStringWithShadow("Preview: " + getFeature().getText(false), this.width / 2 - DrawUtils.getStringWidth("Preview: " + getFeature().getText(false)) / 2, startHeight - 5);
    }
}
