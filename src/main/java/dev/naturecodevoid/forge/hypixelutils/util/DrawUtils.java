package dev.naturecodevoid.forge.hypixelutils.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

public class DrawUtils {
    /**
     * ALPHA IS OUT OF 255
     */
    public static void drawString(String text, int x, int y, String hex, boolean shadow) {
        drawString(text, x, y, hex, 255, shadow);
    }

    /**
     * ALPHA IS OUT OF 255
     */
    public static void drawString(String text, int x, int y, String hex) {
        drawString(text, x, y, hex, 255, true);
    }

    /**
     * ALPHA IS OUT OF 255
     */
    public static void drawString(String text, int x, int y, String hex, int alpha) {
        drawString(text, x, y, hex, alpha, true);
    }

    /**
     * ALPHA IS OUT OF 255
     */
    public static void drawString(String text, int x, int y, String hex, int alpha, boolean shadow) {
        FontRenderer fRender = Minecraft.getMinecraft().fontRendererObj;

        fRender.drawString(text, x, y, Utils.toHex(hex, alpha), shadow);
    }
}
