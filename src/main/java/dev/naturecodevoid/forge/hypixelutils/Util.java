package dev.naturecodevoid.forge.hypixelutils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
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
}
