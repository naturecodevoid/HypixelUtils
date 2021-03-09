package dev.naturecodevoid.hypixelutils.util;

import net.minecraft.client.Minecraft;

public class DrawUtils {
    ////
    //// TEXT DRAWING
    ////

    /**
     * Returns FontRenderer.getStringWidth(text); utility method
     */
    public static int getStringWidth(String text) {
        return Minecraft.getMinecraft().fontRendererObj.getStringWidth(text);
    }

    /**
     * WIP method for special text alignment
     */
    public static int getPositionDifference(String text, TextAlignment alignment) {
        int width = Minecraft.getMinecraft().fontRendererObj.getStringWidth(text);
        int move = 0;

        // TODO: fix
        switch(alignment) {
            case Middle:
                move = width / 2;
                break;
            case Right:
                move = width;
                break;
        }

        return move;
    }

    /**
     * Draws a string.
     */
    public static int drawString(String text, int x, int y) {
        return DrawUtils.drawString(text, x, y, "ffffff", true);
    }

    /**
     * Draws a string.
     */
    public static int drawString(String text, int x, int y, String hex) {
        return DrawUtils.drawString(text, x, y, hex, true);
    }

    /**
     * Draws a string.
     */
    public static int drawString(String text, int x, int y, boolean shadow) {
        return DrawUtils.drawString(text, x, y, "ffffff", shadow);
    }

    /**
     * Draws a string.
     */
    public static int drawString(String text, int x, int y, String hex, boolean shadow) {
        return Minecraft.getMinecraft().fontRendererObj.drawString(text, x, y, Utils.toHex(hex), shadow);
    }

    /**
     * Stores the type of text alignment
     */
    public enum TextAlignment {
        Left(1),
        Middle(2),
        Right(3);

        public final int value;

        TextAlignment(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return String.valueOf(this.value);
        }
    }
}
