package dev.naturecodevoid.forge.hypixelutils.gui;

import dev.naturecodevoid.forge.hypixelutils.BaseFeature;
import dev.naturecodevoid.forge.hypixelutils.HypixelUtils;
import dev.naturecodevoid.forge.hypixelutils.util.Utils;
import dev.naturecodevoid.forge.hypixelutils.util.Vector2D;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.common.MinecraftForge;
import org.lwjgl.input.Keyboard;

import java.io.IOException;

public class EditorGui extends GuiScreen {
    private boolean isDragging = false;
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
    }

    public void actionPerformed(GuiButton button) {
        switch (button.id) {
            case 1:
                HypixelUtils.features.forEach(BaseFeature::resetPosition);
                return;
        }
    }

    @Override
    public void drawScreen(int x, int y, float partialTicks) {
        if (mc.theWorld != null) this.drawGradientRect(0, 0, this.width, this.height, -1608507360, -1608507360);
        MinecraftForge.EVENT_BUS.post(new GuiScreenEvent.BackgroundDrawnEvent(this));

        super.drawScreen(x, y, partialTicks);

        HypixelUtils.features.forEach(BaseFeature::renderEditor);
    }

    @Override
    public void mouseClicked(int x, int y, int time) throws IOException {
        if ((y != lastY) || (x != lastX)) {
            this.isDragging = true;
            this.lastX = x;
            this.lastY = y;
        } else {
            this.isDragging = false;
        }
        super.mouseClicked(x, y, time);
    }

    @Override
    public void mouseClickMove(int x, int y, int lastButtonClicked, long timeSinceClick) {
        if (this.isDragging) {
            BaseFeature closestFeature = null;
            double closestDist = 999999;

            Vector2D screenSize = Utils.getScreenSize();

            for (BaseFeature feature : HypixelUtils.features) {
                Vector2D pos = feature.getPosition();

                double dist = Utils.distance(
                        new Vector2D(x, y),
                        Utils.getPosFromPercent(pos.x, pos.y)
                );

                if (dist <= closestDist && dist <= 25) {
                    closestFeature = feature;
                    closestDist = dist;
                }
            }

            if (closestDist != 999999) {
                Vector2D percent = Utils.getPercentFromPos(Math.min(x, screenSize.x - closestFeature.getSize().x), Utils.clamp(y, 0, screenSize.y - closestFeature.getSize().y));
                closestFeature.setPosition(percent);
            }

            this.lastX = x;
            this.lastY = y;
        }
        super.mouseClickMove(x, y, lastButtonClicked, timeSinceClick);
    }

    @Override
    public void onGuiClosed() {
        super.onGuiClosed();
        HypixelUtils.config.markDirty();
        HypixelUtils.config.writeData();
        Keyboard.enableRepeatEvents(false);
    }
}
