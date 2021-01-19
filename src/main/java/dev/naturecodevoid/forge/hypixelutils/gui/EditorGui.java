package dev.naturecodevoid.forge.hypixelutils.gui;

import dev.naturecodevoid.forge.hypixelutils.HypixelUtils;
import dev.naturecodevoid.forge.hypixelutils.base.GuiFeature;
import dev.naturecodevoid.forge.hypixelutils.util.Utils;
import dev.naturecodevoid.forge.hypixelutils.util.Vector2D;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.common.MinecraftForge;
import org.apache.commons.lang3.NotImplementedException;

import java.io.IOException;

public class EditorGui extends GuiScreen {
    private boolean isDragging = false;
    private GuiFeature currentDragging = null;
    private GuiFeature lastDragging = null;
    private Vector2D anchor = null;
    private int lastX = 0;
    private int lastY = 0;

    public void initGui() {
        this.buttonList.add(new GuiButton(1,
                this.width / 2 - 75,
                this.height - 20,
                150,
                20,
                "Reset Positions"
        ));
        this.buttonList.add(new GuiButton(2,
                -100,
                -100,
                30,
                20,
                "Edit"
        ));
        this.buttonList.get(1).visible = false;
    }

    public void actionPerformed(GuiButton button) {
        switch (button.id) {
            case 1:
                HypixelUtils.guiFeatures.forEach(GuiFeature::resetPosition);
                return;
            case 2:
                HypixelUtils.gui = lastDragging.getEditor();
                return;
        }
    }

    public void checkForDragging(int x, int y) {
        GuiFeature closestFeature = null;
        Vector2D anchor2 = null;

        if (currentDragging == null || Utils.distance(new Vector2D(x, y), new Vector2D(lastX, lastY)) >= 5) {
            for (GuiFeature f : HypixelUtils.guiFeatures) {
                Vector2D pos = Utils.getPosFromPercent(f.getPosition());
                Vector2D size = f.getSize();

                if (Utils.checkIfPosInsideRect(new Vector2D(x, y), pos, new Vector2D(pos.x + size.x, pos.y + size.y))) {
                    closestFeature = f;
                    anchor2 = new Vector2D(x - pos.x, y - pos.y);
                    break;
                }
            }

            if (closestFeature != null) {
                lastDragging = closestFeature;
            }
            currentDragging = closestFeature;
            anchor = anchor2;
        }

        if (currentDragging != null) {
            Vector2D screenSize = Utils.getScreenSize();

            Vector2D anchor3;

            if (anchor != null)
                anchor3 = anchor;
            else anchor3 = new Vector2D(currentDragging.getSize().x / 2, currentDragging.getSize().y / 2);

            Vector2D percent = Utils.getPercentFromPos(
                    Math.min(x - anchor3.x, screenSize.x - currentDragging.getSize().x),
                    Utils.clamp(y - anchor3.y, 0, screenSize.y - currentDragging.getSize().y)
            );

            currentDragging.setPosition(percent);

            try {
                currentDragging.getEditor();

                Vector2D newPos = Utils.getPosFromPercent(currentDragging.getPosition().x, currentDragging.getPosition().y);
                this.buttonList.get(1).xPosition = newPos.x + 3;
                this.buttonList.get(1).yPosition = newPos.y + currentDragging.getSize().y - 2;
                this.buttonList.get(1).visible = true;
                this.buttonList.get(1).enabled = true;
            } catch (NotImplementedException ignored) {
                this.buttonList.get(1).visible = false;
                this.buttonList.get(1).enabled = false;
            }
        }
    }

    @Override
    public void mouseClicked(int x, int y, int time) throws IOException {
        if ((y != lastY) || (x != lastX)) {
            this.isDragging = true;

            checkForDragging(x, y);

            this.lastX = x;
            this.lastY = y;
        } else {
            this.isDragging = false;
            currentDragging = null;
            this.buttonList.get(1).visible = false;
            this.buttonList.get(1).enabled = false;
        }
        super.mouseClicked(x, y, time);
    }

    @Override
    public void mouseClickMove(int x, int y, int lastButtonClicked, long timeSinceClick) {
        if (this.isDragging) {
            checkForDragging(x, y);

            this.lastX = x;
            this.lastY = y;
        } else {
            currentDragging = null;
            this.buttonList.get(1).visible = false;
            this.buttonList.get(1).enabled = false;
        }
        super.mouseClickMove(x, y, lastButtonClicked, timeSinceClick);
    }

    @Override
    public void drawScreen(int x, int y, float partialTicks) {
        if (mc.theWorld != null) this.drawGradientRect(0, 0, this.width, this.height, -1608507360, -1608507360);
        MinecraftForge.EVENT_BUS.post(new GuiScreenEvent.BackgroundDrawnEvent(this));

        super.drawScreen(x, y, partialTicks);

        HypixelUtils.guiFeatures.forEach(GuiFeature::renderEditor);
    }

    @Override
    public void onGuiClosed() {
        super.onGuiClosed();
        Utils.saveConfig();
    }
}
