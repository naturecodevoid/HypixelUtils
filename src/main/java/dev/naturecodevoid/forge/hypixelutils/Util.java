package dev.naturecodevoid.forge.hypixelutils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;

public class Util {
    public static boolean hasGuiOpen() {
        if (Minecraft.getMinecraft().gameSettings.showDebugInfo) return true;
        return Minecraft.getMinecraft().currentScreen != null && !(Minecraft.getMinecraft().currentScreen instanceof GuiChat);
    }
}
