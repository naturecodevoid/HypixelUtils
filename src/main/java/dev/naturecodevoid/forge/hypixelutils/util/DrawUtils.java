package dev.naturecodevoid.forge.hypixelutils.util;

import net.minecraft.client.Minecraft;

public class DrawUtils {
    //
    // STRING DRAWING
    //

    public static int drawStringWithShadow(String text, int x, int y) {
        return DrawUtils.drawString(text, x, y, "ffffff", true);
    }

    public static int drawStringWithShadow(String text, int x, int y, String hex) {
        return DrawUtils.drawString(text, x, y, hex, true);
    }

    public static int drawStringWithoutShadow(String text, int x, int y) {
        return DrawUtils.drawString(text, x, y, "ffffff", false);
    }

    public static int drawStringWithoutShadow(String text, int x, int y, String hex) {
        return DrawUtils.drawString(text, x, y, hex, false);
    }

    public static int drawString(String text, int x, int y) {
        return DrawUtils.drawString(text, x, y, "ffffff", true);
    }

    public static int drawString(String text, int x, int y, String hex) {
        return DrawUtils.drawString(text, x, y, hex, true);
    }

    public static int drawString(String text, int x, int y, boolean shadow) {
        return DrawUtils.drawString(text, x, y, "ffffff", shadow);
    }

    public static int drawString(String text, int x, int y, String hex, boolean shadow) {
        return Minecraft.getMinecraft().fontRendererObj.drawString(text, x, y, Utils.toHex(hex), shadow);
    }
}
