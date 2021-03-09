package dev.naturecodevoid.hypixelutils.util;

import dev.naturecodevoid.hypixelutils.HypixelUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;

import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    public static final int textAdd = 18;
    public static final int textAddSmall = 7;

    /**
     * Sends a message with the prefix.
     */
    public static void sendMessage(String text) {
        // https://github.com/BiscuitDevelopment/SkyblockAddons/blob/master/src/main/java/codes/biscuit/skyblockaddons/utils/Utils.java#L154
        ClientChatReceivedEvent event = new ClientChatReceivedEvent((byte) 1, new ChatComponentText(HypixelUtils.PREFIX + text));
        MinecraftForge.EVENT_BUS.post(event); // Let other mods pick up the new message
        if (!event.isCanceled()) {
            Minecraft.getMinecraft().thePlayer.addChatMessage(event.message); // Just for logs
        }
    }

    /**
     * Sends a message without the prefix.
     */
    public static void sendMessageNoPrefix(String text) {
        // https://github.com/BiscuitDevelopment/SkyblockAddons/blob/master/src/main/java/codes/biscuit/skyblockaddons/utils/Utils.java#L154
        ClientChatReceivedEvent event = new ClientChatReceivedEvent((byte) 1, new ChatComponentText(text));
        MinecraftForge.EVENT_BUS.post(event); // Let other mods pick up the new message
        if (!event.isCanceled()) {
            Minecraft.getMinecraft().thePlayer.addChatMessage(event.message); // Just for logs
        }
    }

    /**
     * Sends a message without the prefix.
     */
    public static void sendMessageNoPrefix(ChatComponentText text) {
        // https://github.com/BiscuitDevelopment/SkyblockAddons/blob/master/src/main/java/codes/biscuit/skyblockaddons/utils/Utils.java#L154
        ClientChatReceivedEvent event = new ClientChatReceivedEvent((byte) 1, text);
        MinecraftForge.EVENT_BUS.post(event); // Let other mods pick up the new message
        if (!event.isCanceled()) {
            Minecraft.getMinecraft().thePlayer.addChatMessage(event.message); // Just for logs
        }
    }

    /**
     * Sends a message without the prefix.
     */
    public static void sendMessageNoPrefix(ClientChatReceivedEvent msgEvent) {
        // https://github.com/BiscuitDevelopment/SkyblockAddons/blob/master/src/main/java/codes/biscuit/skyblockaddons/utils/Utils.java#L154
        MinecraftForge.EVENT_BUS.post(msgEvent); // Let other mods pick up the new message
        if (!msgEvent.isCanceled()) {
            Minecraft.getMinecraft().thePlayer.addChatMessage(msgEvent.message); // Just for logs
        }
    }

    /**
     * Gets the screen size.
     */
    public static Vector2 getScreenSize() {
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
        return new Vector2(width, height);
    }

    /**
     * Converts a percent to position. (max: 1000%)
     */
    public static int getPosFromPercent(int percent) {
        return getPosFromPercent(percent, 0).x;
    }

    /**
     * Converts a percent to position. (max: 1000%)
     */
    public static Vector2 getPosFromPercent(Vector2 percent) {
        return getPosFromPercent(percent.x, percent.y);
    }

    /**
     * Converts a percent to position. (max: 1000%)
     */
    public static Vector2 getPosFromPercent(float percentX, float percentY) {
        Vector2 size = getScreenSize();
        return new Vector2(
                Math.round((percentX / 1000) * size.x),
                Math.round((percentY / 1000) * size.y)
        );
    }

    /**
     * Converts a position to percent. (max: 1000%)
     */
    public static int getPercentFromPos(int pos) {
        return getPercentFromPos(pos, 0).x;
    }

    /**
     * Converts a position to percent. (max: 1000%)
     */
    public static Vector2 getPercentFromPos(Vector2 pos) {
        return getPercentFromPos(pos.x, pos.y);
    }

    /**
     * Converts a position to percent. (max: 1000%)
     */
    public static Vector2 getPercentFromPos(float posX, float posY) {
        Vector2 size = getScreenSize();
        return new Vector2(
                clamp(Math.round((posX * 1000) / size.x), 0, 1000),
                clamp(Math.round((posY * 1000) / size.y), 0, 1000)
        );
    }

    /**
     * Gets a EnumChatFormatting from a string.
     */
    public static EnumChatFormatting getColor(String str) {
        String string = str.toUpperCase().replaceAll(" ", "_").trim();

        switch (string) {
            case "DARK_BLUE":
                return EnumChatFormatting.DARK_BLUE;
            case "DARK_GREEN":
                return EnumChatFormatting.DARK_GREEN;
            case "DARK_AQUA":
                return EnumChatFormatting.DARK_AQUA;
            case "DARK_RED":
                return EnumChatFormatting.DARK_RED;
            case "DARK_PURPLE":
                return EnumChatFormatting.DARK_PURPLE;
            case "DARK_GRAY":
                return EnumChatFormatting.DARK_GRAY;
            case "BLACK":
                return EnumChatFormatting.BLACK;
            case "GOLD":
                return EnumChatFormatting.GOLD;
            case "GRAY":
                return EnumChatFormatting.GRAY;
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

    /**
     * Gets a EnumChatFormatting from a int.
     */
    public static EnumChatFormatting getColor(int index) {
        return Utils.getColor(HypixelUtils.config.colors[index]);
    }

    /**
     * Gets formatted text from a boolean.
     */
    public static String getBooleanText(boolean bool) {
        return bool ? EnumChatFormatting.GREEN + "Yes" : EnumChatFormatting.RED + "No";
    }

    /**
     * Gets formatted text from an EnumChatFormatting.
     */
    public static String getColorText(EnumChatFormatting color) {
        switch (color) {
            case DARK_BLUE:
                return color + "Dark Blue" + EnumChatFormatting.RESET;
            case DARK_GREEN:
                return color + "Dark Green" + EnumChatFormatting.RESET;
            case DARK_AQUA:
                return color + "Dark Aqua" + EnumChatFormatting.RESET;
            case DARK_RED:
                return color + "Dark Red" + EnumChatFormatting.RESET;
            case DARK_PURPLE:
                return color + "Dark Purple" + EnumChatFormatting.RESET;
            case DARK_GRAY:
                return color + "Dark Gray" + EnumChatFormatting.RESET;
            case BLACK:
                return color + "Black" + EnumChatFormatting.RESET;
            case GOLD:
                return color + "Gold" + EnumChatFormatting.RESET;
            case GRAY:
                return color + "Gray" + EnumChatFormatting.RESET;
            case BLUE:
                return color + "Blue" + EnumChatFormatting.RESET;
            case GREEN:
                return color + "Green" + EnumChatFormatting.RESET;
            case AQUA:
                return color + "Aqua" + EnumChatFormatting.RESET;
            case RED:
                return color + "Red" + EnumChatFormatting.RESET;
            case LIGHT_PURPLE:
                return color + "Light Purple" + EnumChatFormatting.RESET;
            case YELLOW:
                return color + "Yellow" + EnumChatFormatting.RESET;
            case WHITE:
                return color + "White" + EnumChatFormatting.RESET;
            default:
                return EnumChatFormatting.RESET + "None (this might be a bug)";
        }
    }

    /**
     * Gets formatted text from a color (string).
     */
    public static String getColorText(String color) {
        color = color.toUpperCase().replaceAll(" ", "_").trim();

        switch (color) {
            case "DARK_BLUE":
                return EnumChatFormatting.DARK_BLUE + "Dark Blue" + EnumChatFormatting.RESET;
            case "DARK_GREEN":
                return EnumChatFormatting.DARK_GREEN + "Dark Green" + EnumChatFormatting.RESET;
            case "DARK_AQUA":
                return EnumChatFormatting.DARK_AQUA + "Dark Aqua" + EnumChatFormatting.RESET;
            case "DARK_RED":
                return EnumChatFormatting.DARK_RED + "Dark Red" + EnumChatFormatting.RESET;
            case "DARK_PURPLE":
                return EnumChatFormatting.DARK_PURPLE + "Dark Purple" + EnumChatFormatting.RESET;
            case "DARK_GRAY":
                return EnumChatFormatting.DARK_GRAY + "Dark Gray" + EnumChatFormatting.RESET;
            case "BLACK":
                return EnumChatFormatting.BLACK + "Black" + EnumChatFormatting.RESET;
            case "GOLD":
                return EnumChatFormatting.GOLD + "Gold" + EnumChatFormatting.RESET;
            case "GRAY":
                return EnumChatFormatting.GRAY + "Gray" + EnumChatFormatting.RESET;
            case "BLUE":
                return EnumChatFormatting.BLUE + "Blue" + EnumChatFormatting.RESET;
            case "GREEN":
                return EnumChatFormatting.GREEN + "Green" + EnumChatFormatting.RESET;
            case "AQUA":
                return EnumChatFormatting.AQUA + "Aqua" + EnumChatFormatting.RESET;
            case "RED":
                return EnumChatFormatting.RED + "Red" + EnumChatFormatting.RESET;
            case "LIGHT_PURPLE":
                return EnumChatFormatting.LIGHT_PURPLE + "Light Purple" + EnumChatFormatting.RESET;
            case "YELLOW":
                return EnumChatFormatting.YELLOW + "Yellow" + EnumChatFormatting.RESET;
            case "WHITE":
                return EnumChatFormatting.WHITE + "White" + EnumChatFormatting.RESET;
            default:
                return EnumChatFormatting.RESET + "None (this might be a bug)";
        }
    }

    /**
     * Gets if the mod is not enabled.
     */
    // Don't ask why
    public static boolean getNotEnabled(RenderGameOverlayEvent event, boolean hypixel) {
        return getNotEnabled(event, hypixel, true);
    }

    /**
     * Gets if the mod/feature is not enabled.
     */
    // Don't ask why
    public static boolean getNotEnabled(RenderGameOverlayEvent event, boolean hypixel, boolean featureEnabled) {
        return !(featureEnabled) || !(Utils.isModEnabled(hypixel)) || Utils.hasGuiOpen() || event.isCancelable() || event.type != RenderGameOverlayEvent.ElementType.TEXT;
    }

    /**
     * Prefixes zeros to a number (default amount: 1)
     */
    public static String addZeros(int num) {
        return Utils.addZeros(String.valueOf(num), 1);
    }

    /**
     * Prefixes zeros to a number (default amount: 1)
     */
    public static String addZeros(int num, int amount) {
        return Utils.addZeros(String.valueOf(num), amount);
    }

    /**
     * Prefixes zeros to a number (default amount: 1)
     */
    public static String addZeros(String num) {
        return Utils.addZeros(num, 1);
    }

    /**
     * Prefixes zeros to a number (default amount: 1)
     */
    public static String addZeros(String num, int amount) {
        String tmp = "" + num;
        int length = num.length();
        for (int i = 0; i < (amount + 1) - length; i++) {
            tmp = "0" + tmp;
        }
        return tmp;
    }

    // https://stackoverflow.com/a/17853589
    // https://stackoverflow.com/a/18194652
    // https://stackoverflow.com/a/32530035

    /**
     * Converts a RGB color to a hex decimal
     */
    public static int toHex(String red, String green, String blue) {
        Color color = new Color(Integer.parseInt(red), Integer.parseInt(green), Integer.parseInt(blue));

        String buf = Integer.toHexString(color.getRGB());
        String hex = buf.substring(buf.length() - 6);

        String red2 = hex.substring(0, hex.length() / 3);
        String green2 = hex.substring(hex.length() / 3, hex.length() / 3 * 2);
        String blue2 = hex.substring(hex.length() / 3 * 2);

        String hexnum = "FF" + addZeros(red2) + addZeros(green2) + addZeros(blue2);
        return (int) Long.parseLong(hexnum, 16);
        //return Integer.parseInt(hexnum, 16);
    }

    /**
     * Converts a RGB color to a hex decimal
     */
    public static int toHex(int red, int green, int blue) {
        return Utils.toHex(String.valueOf(red), String.valueOf(green), String.valueOf(blue));
    }

    /**
     * Converts a color to a hex decimal
     */
    public static int toHex(Color color) {
        return Utils.toHex(color.getRed(), color.getGreen(), color.getBlue());
    }

    /**
     * Converts a hex string to a hex decimal
     */
    public static int toHex(String hex) {
        return toHex(hex, 255);
    }

    /**
     * Converts a hex string to a hex decimal (alpha/255)
     */
    public static int toHex(String hex, int alpha) {
        hex = hex.replaceAll("#", "");
        hex = Integer.toHexString(alpha) + hex;
        return (int) Long.parseLong(hex, 16);
        //return Integer.parseInt(hex, 16);
    }

    /**
     * Saves the config
     */
    public static void saveConfig() {
        HypixelUtils.config.markDirty();
        HypixelUtils.config.writeData();

        // Disabled because possible bug
        //Keyboard.enableRepeatEvents(false);
    }

    /**
     * Limits an number to a array size
     */
    public static int limitArrayIndexToArraySize(Object[] array, int index) {
        if (index > array.length - 1) index = 0;
        return index;
    }

    /**
     * Checks if a position is in a rectangle
     */
    public static boolean checkIfPosInsideRect(Vector2 pos, Vector2 startRect, Vector2 endRect) {
        return ((pos.x >= startRect.x) && (pos.x <= endRect.x)) &&
                ((pos.y >= startRect.y) && (pos.y <= endRect.y));
    }

    // https://github.com/BiscuitDevelopment/SkyblockAddons/blob/master/src/main/java/codes/biscuit/skyblockaddons/utils/Utils.java#L189

    /**
     * Checks if the player is currently on Hypixel
     */
    public static boolean isOnHypixel() {
        final Pattern SERVER_BRAND_PATTERN = Pattern.compile("(.+) <- (?:.+)");
        final String HYPIXEL_SERVER_BRAND = "Hypixel BungeeCord";

        Minecraft mc = Minecraft.getMinecraft();

        if (!mc.isSingleplayer() && mc.thePlayer.getClientBrand() != null) {
            Matcher matcher = SERVER_BRAND_PATTERN.matcher(mc.thePlayer.getClientBrand());

            if (matcher.find()) {
                // Group 1 is the server brand.
                return matcher.group(1).startsWith(HYPIXEL_SERVER_BRAND);
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Checks if the mod is enabled
     */
    public static boolean isModEnabled(boolean hypixel) {
        if (!HypixelUtils.config.otherServers && hypixel) return HypixelUtils.config.enabled && isOnHypixel();
        return HypixelUtils.config.enabled;
    }

    /**
     * Gets the distance between 2 position
     */
    public static double distance(Vector2 pos1, Vector2 pos2) {
        int x = pos1.x - pos2.x;
        int y = pos1.y - pos2.y;
        return new Vector2(x, y).length();
    }

    /**
     * Clamps a value between a minimum value and a maximum value
     */
    public static int clamp(int value, int min, int max) {
        return Math.max(min, Math.min(max, value));
    }

    /**
     * Checks if the game has a GUI open
     */
    public static boolean hasGuiOpen() {
        if (Minecraft.getMinecraft().gameSettings.showDebugInfo) return true;
        return
                Minecraft.getMinecraft().currentScreen != null &&
                        !(Minecraft.getMinecraft().currentScreen instanceof GuiChat) &&
                        !(Minecraft.getMinecraft().currentScreen instanceof GuiInventory) &&
                        !(Minecraft.getMinecraft().currentScreen instanceof GuiContainer);
    }
}
