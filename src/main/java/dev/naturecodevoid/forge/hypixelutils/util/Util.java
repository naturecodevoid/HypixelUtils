package dev.naturecodevoid.forge.hypixelutils.util;

import dev.naturecodevoid.forge.hypixelutils.HypixelUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {
    public static final int textAdd = 18;
    public static final int textAddSmall = 7;

    public static int clamp(int value, int min, int max) {
        return Math.max(min, Math.min(max, value));
    }

    public static boolean hasGuiOpen() {
        if (Minecraft.getMinecraft().gameSettings.showDebugInfo) return true;
        return Minecraft.getMinecraft().currentScreen != null && !(Minecraft.getMinecraft().currentScreen instanceof GuiChat);
    }

    public static void sendMessage(ICommandSender sender, String text) {
        sender.addChatMessage(new ChatComponentText(HypixelUtils.prefix + text));
    }

    public static Vector2D getScreenSize() {
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
        return new Vector2D(width, height);
    }

    public static Vector2D getPosFromPercent(float percentX, float percentY) {
        Vector2D size = getScreenSize();
        return new Vector2D(
                Math.round((percentX / 100) * size.x),
                Math.round((percentY / 100) * size.y)
        );
    }

    public static Vector2D getPercentFromPos(float posX, float posY) {
        Vector2D size = getScreenSize();
        return new Vector2D(
                clamp(Math.round((posX * 100) / size.x), 0, 100),
                clamp(Math.round((posY * 100) / size.y), 0, 100)
        );
    }

    public static EnumChatFormatting getColor(String str) {
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

    public static EnumChatFormatting getColor(int index) {
        return Util.getColor(HypixelUtils.config.colors[index]);
    }

    // https://github.com/BiscuitDevelopment/SkyblockAddons/blob/master/src/main/java/codes/biscuit/skyblockaddons/utils/Utils.java#L189
    public static boolean isOnHypixel() {
        final Pattern SERVER_BRAND_PATTERN = Pattern.compile("(.+) <- (?:.+)");
        final String HYPIXEL_SERVER_BRAND = "BungeeCord (Hypixel)";

        Minecraft mc = Minecraft.getMinecraft();

        if (!mc.isSingleplayer() && mc.thePlayer.getClientBrand() != null) {
            Matcher matcher = SERVER_BRAND_PATTERN.matcher(mc.thePlayer.getClientBrand());

            if (matcher.find()) {
                // Group 1 is the server brand.
                return matcher.group(1).equals(HYPIXEL_SERVER_BRAND);
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public static boolean isModEnabled() {
        if (!HypixelUtils.config.otherServers) return HypixelUtils.config.enabled && isOnHypixel();
        return HypixelUtils.config.enabled;
    }

    public static double distance(Vector2D pos1, Vector2D pos2) {
        int x = pos1.x - pos2.x;
        int y = pos1.y - pos2.y;
        return new Vector2D(x, y).length();
    }

    public static boolean getEnabled(RenderGameOverlayEvent event) {
        return getEnabled(event, true);
    }

    public static boolean getEnabled(RenderGameOverlayEvent event, boolean featureEnabled) {
        return !(featureEnabled) || !(Util.isModEnabled()) || Util.hasGuiOpen() || event.isCancelable() || event.type != RenderGameOverlayEvent.ElementType.TEXT;
    }
}
