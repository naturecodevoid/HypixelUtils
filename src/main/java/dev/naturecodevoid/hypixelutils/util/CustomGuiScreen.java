package dev.naturecodevoid.hypixelutils.util;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.common.MinecraftForge;

public class CustomGuiScreen extends GuiScreen {
    @Override
    public void drawScreen(int x, int y, float partialTicks) {
        if (mc.theWorld != null) {
            this.drawGradientRect(0, 0, this.width, this.height, Utils.toHex("202020", 200), Utils.toHex("202020", 200));
            MinecraftForge.EVENT_BUS.post(new GuiScreenEvent.BackgroundDrawnEvent(this));
        }
        else this.drawDefaultBackground();

        super.drawScreen(x, y, partialTicks);
    }

    @Override
    public String toString() {
        String[] array = getClass().getName().split("\\.");
        return array[array.length - 1];
    }
}
