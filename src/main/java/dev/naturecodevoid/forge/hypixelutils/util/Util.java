package dev.naturecodevoid.forge.hypixelutils.util;

import dev.naturecodevoid.forge.hypixelutils.HypixelUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

public class Util {
    public static boolean hasGuiOpen() {
        if (Minecraft.getMinecraft().gameSettings.showDebugInfo) return true;
        return Minecraft.getMinecraft().currentScreen != null && !(Minecraft.getMinecraft().currentScreen instanceof GuiChat);
    }

    public static void sendMessage(ICommandSender sender, String text) {
        sender.addChatMessage(new ChatComponentText(HypixelUtils.prefix + text));
    }

    public static Position getPosFromPercent(float percentX, float percentY) {
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
        return new Position(
                Math.floor(percentX / 100 * width),
                Math.floor(percentY / 100 * height)
        );
    }
}
