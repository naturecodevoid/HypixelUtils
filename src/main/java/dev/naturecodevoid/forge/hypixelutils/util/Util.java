package dev.naturecodevoid.forge.hypixelutils.util;

import dev.naturecodevoid.forge.hypixelutils.HypixelUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class Util {
    public static boolean hasGuiOpen() {
        if (Minecraft.getMinecraft().gameSettings.showDebugInfo) return true;
        return Minecraft.getMinecraft().currentScreen != null && !(Minecraft.getMinecraft().currentScreen instanceof GuiChat);
    }

    public static void sendMessage(ICommandSender sender, String text) {
        sender.addChatMessage(new ChatComponentText(HypixelUtils.prefix + text));
    }

    public static boolean isFullscreen() {
        try {
            // TODO: add code here that checks if mac fullscreen is on, if so return true
        } catch (Exception ignored) {
        }
        return Minecraft.getMinecraft().isFullScreen();
    }

    public static Coordinate2D getPosFromPercent(float percentX, float percentY) {
        int width;
        int height;
        try {
            ScaledResolution res = new ScaledResolution(Minecraft.getMinecraft());
            width = res.getScaledWidth();
            height = res.getScaledHeight();
            if (isFullscreen()) {
                percentX -= 20;
                percentY -= 6;
            }
        } catch (Exception e) {
            try {
                width = Minecraft.getMinecraft().displayWidth / 2;
                height = Minecraft.getMinecraft().displayHeight / 2;
            } catch (Exception e2) {
                width = 0;
                height = 0;
            }
        }
        return new Coordinate2D(
                Math.floor((percentX / 100) * width),
                Math.floor((percentY / 100) * height)
        );
    }

    public static Coordinate2D getPercentFromPos(float posX, float posY) {
        int width;
        int height;
        try {
            ScaledResolution res = new ScaledResolution(Minecraft.getMinecraft());
            width = res.getScaledWidth();
            height = res.getScaledHeight();
        } catch (Exception e) {
            try {
                width = Minecraft.getMinecraft().displayWidth / 2;
                height = Minecraft.getMinecraft().displayHeight / 2;
            } catch (Exception e2) {
                width = 0;
                height = 0;
            }
        }
        return new Coordinate2D(
                Math.floor((posX * 100) / width),
                Math.floor((posY * 100) / height)
        );
    }

    public static EnumChatFormatting getColorFromString(String str) {
        String string = str.toUpperCase().replaceAll(" ", "_");

        switch (string) {
            case "BLACK":
                return EnumChatFormatting.BLACK;
            case "DARK_BLUE":
                return EnumChatFormatting.DARK_BLUE;
            case "DARK_AQUA":
                return EnumChatFormatting.DARK_AQUA;
            case "DARK_RED":
                return EnumChatFormatting.DARK_RED;
            case "DARK_PURPLE":
                return EnumChatFormatting.DARK_PURPLE;
            case "GOLD":
                return EnumChatFormatting.GOLD;
            case "GRAY":
                return EnumChatFormatting.GRAY;
            case "DARK_GRAY":
                return EnumChatFormatting.DARK_GRAY;
            case "BLUE":
                return EnumChatFormatting.BLUE;
            case "GREEN":
                return EnumChatFormatting.GREEN;
            case "AQUA":
                return EnumChatFormatting.AQUA;
            case "RED":
                return EnumChatFormatting.RED;
            case "LIGHT_PURPLE":
                return EnumChatFormatting.LIGHT_PURPLE;
            case "YELLOW":
                return EnumChatFormatting.YELLOW;
            case "WHITE":
                return EnumChatFormatting.WHITE;
            default:
                return EnumChatFormatting.RESET;

        }
    }
}
