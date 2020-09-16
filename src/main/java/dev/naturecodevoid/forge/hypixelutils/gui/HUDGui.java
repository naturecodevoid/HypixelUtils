package dev.naturecodevoid.forge.hypixelutils.gui;

import dev.naturecodevoid.forge.hypixelutils.features.CoinTracker;
import net.minecraft.client.gui.GuiScreen;

public class HUDGui extends GuiScreen {
    private final boolean isDragging = false;
    private final int lastX = 0;
    private final int lastY = 0;

    @Override
    public void drawScreen(int x, int y, float partialTicks) {
        super.drawDefaultBackground();

        CoinTracker.instance.render(null);

        // TODO: make features draggable

        super.drawScreen(x, y, partialTicks);
    }
}
