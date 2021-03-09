package dev.naturecodevoid.hypixelutils.features;

import dev.naturecodevoid.hypixelutils.HypixelUtils;
import dev.naturecodevoid.hypixelutils.base.BaseFeature;
import dev.naturecodevoid.hypixelutils.misc.OrbSound;
import dev.naturecodevoid.hypixelutils.util.DrawUtils;
import dev.naturecodevoid.hypixelutils.util.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.Session;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChatPing extends BaseFeature.BaseMethods implements BaseFeature {
    private static ChatPing instance = null;
    /**
     * The pattern for finding formatting codes; used later
     */
    public final Pattern formattingCodesPattern = Pattern.compile("§([a-z]|[0-9])");

    public ChatPing() {
        instance = this;
        MinecraftForge.EVENT_BUS.register(this);
    }

    public static ChatPing get() {
        return instance;
    }

    public String displayName() {
        return "Chat Ping";
    }

    @Override
    public boolean getEnabled() {
        return HypixelUtils.config.chatPingEnabled;
    }

    @Override
    public boolean setEnabled(boolean enabled) {
        HypixelUtils.config.chatPingEnabled = enabled;
        return HypixelUtils.config.chatPingEnabled;
    }

    @Override
    public boolean isHypixel() {
        return false;
    }

    /**
     * Separated from the chat event to be usable in the Editor.
     */
    private String changeText(String formattedText) {
        // Get all the formatting codes from the first part of the message.

        Matcher formattingCodesMatcher = this.formattingCodesPattern.matcher(formattedText.split(Utils.getPlayerName())[0]);
        String formattingCodes = "";

        while (formattingCodesMatcher.find()) {
            formattingCodes += formattingCodesMatcher.group();
        }

        String text2 = formattedText;

        if (HypixelUtils.config.chatPingHighlight) {
            // Adds the highlighting/special effects to the username text
            String nameText = Utils.getPlayerName() + EnumChatFormatting.RESET + formattingCodes;

            if (HypixelUtils.config.chatPingUnderline) nameText = EnumChatFormatting.UNDERLINE + nameText;

            if (HypixelUtils.config.chatPingBold) nameText = EnumChatFormatting.BOLD + nameText;

            if (HypixelUtils.config.chatPingItalics) nameText = EnumChatFormatting.ITALIC + nameText;

            if (HypixelUtils.config.chatPingColorEnabled)
                nameText = Utils.getColor(HypixelUtils.config.chatPingColor) + nameText;

            text2 = text2
                    .replaceAll(Utils.getPlayerName(), nameText);

            String[] tmp = text2.split(" ");
            int count = 0;
            for (String s : tmp) {
                if (s.contains(Utils.getPlayerName()))
                    count++;
            }

            if (count >= 2)
                text2 = text2.replaceFirst(nameText, Utils.getPlayerName());
        }

        return text2;
    }

    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        if (!(this.getEnabled())) return;

        Minecraft mc = Minecraft.getMinecraft();

        String text = event.message
                .getUnformattedText()
                .toLowerCase()
                .replaceFirst("<.+>", "")
                .replaceFirst(".+:", "");

        if (text.contains(Utils.getPlayerName().toLowerCase())) {
            // Play the ping sound.
            if (HypixelUtils.config.chatPingUsername) mc.getSoundHandler().playSound(new OrbSound());

            // Changes the text to highlight the username if needed.
            String text2 = this.changeText(event.message.getFormattedText());

            // Cancel the event and resend the message.

            // Possible bug: all click/hover events go bye-bye

            event.setCanceled(true);

            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(text2));
        }
    }

    @Override
    public BaseEditor getEditor(GuiScreen backScreen) {
        return new Editor(backScreen);
    }

    private static class Editor extends BaseEditor {
        public Editor(GuiScreen backScreen) {
            super(backScreen);
        }

        @Override
        public BaseFeature getFeature() {
            return ChatPing.get();
        }

        @Override
        public void init() {
            this.maxPerColumn = 5;

            super.init();

            // Adds all the special config buttons

            // Ping sound on username

            this.makeButton(
                    "Ping on username: " + Utils.getBooleanText(HypixelUtils.config.chatPingUsername),
                    (GuiButton btn) -> {
                        HypixelUtils.config.chatPingUsername = !(HypixelUtils.config.chatPingUsername);
                        btn.displayString = "Ping on username: " + Utils.getBooleanText(HypixelUtils.config.chatPingUsername);
                        return null;
                    },
                    (GuiButton btn) -> {
                        btn.displayString = "Ping on username: " + Utils.getBooleanText(HypixelUtils.config.chatPingUsername);
                        return null;
                    }
            );

            // Ping sound volume

            this.makeButton(
                    "Ping volume: " + HypixelUtils.config.chatPingVolume + "/10",
                    (GuiButton btn) -> {
                        int volume = HypixelUtils.config.chatPingVolume + 1;
                        if (volume > 10) volume = 0;
                        HypixelUtils.config.chatPingVolume = volume;
                        btn.displayString = "Ping volume: " + HypixelUtils.config.chatPingVolume + "/10";
                        return null;
                    },
                    (GuiButton btn) -> {
                        btn.displayString = "Ping volume: " + HypixelUtils.config.chatPingVolume + "/10";
                        return null;
                    }
            );

            // If it should highlight your username

            this.makeButton(
                    "Highlight username: " + Utils.getBooleanText(HypixelUtils.config.chatPingHighlight),
                    (GuiButton btn) -> {
                        HypixelUtils.config.chatPingHighlight = !(HypixelUtils.config.chatPingHighlight);
                        btn.displayString = "Highlight username: " + Utils.getBooleanText(HypixelUtils.config.chatPingHighlight);
                        return null;
                    },
                    (GuiButton btn) -> {
                        btn.displayString = "Highlight username: " + Utils.getBooleanText(HypixelUtils.config.chatPingHighlight);
                        return null;
                    }
            );

            // If it should use color

            this.makeButton(
                    "Enable color: " + Utils.getBooleanText(HypixelUtils.config.chatPingColorEnabled),
                    (GuiButton btn) -> {
                        HypixelUtils.config.chatPingColorEnabled = !(HypixelUtils.config.chatPingColorEnabled);
                        btn.displayString = "Enable color: " + Utils.getBooleanText(HypixelUtils.config.chatPingColorEnabled);
                        return null;
                    },
                    (GuiButton btn) -> {
                        btn.displayString = "Enable color: " + Utils.getBooleanText(HypixelUtils.config.chatPingColorEnabled);
                        return null;
                    }
            );

            // Color switcher

            this.makeButton(
                    "Color: " + Utils.getColorText(Utils.getColor(HypixelUtils.config.chatPingColor)),
                    (GuiButton btn) -> {
                        int color = HypixelUtils.config.chatPingColor + 1;
                        color = Utils.limitArrayIndexToArraySize(HypixelUtils.config.colors, color);
                        HypixelUtils.config.chatPingColor = color;
                        btn.displayString = "Color: " + Utils.getColorText(Utils.getColor(HypixelUtils.config.chatPingColor));
                        return null;
                    },
                    (GuiButton btn) -> {
                        btn.displayString = "Color: " + Utils.getColorText(Utils.getColor(HypixelUtils.config.chatPingColor));
                        return null;
                    }
            );

            // If it should use bold

            this.makeButton(
                    "Bold: " + Utils.getBooleanText(HypixelUtils.config.chatPingBold),
                    (GuiButton btn) -> {
                        HypixelUtils.config.chatPingBold = !(HypixelUtils.config.chatPingBold);
                        btn.displayString = "Bold: " + Utils.getBooleanText(HypixelUtils.config.chatPingBold);
                        return null;
                    },
                    (GuiButton btn) -> {
                        btn.displayString = "Bold: " + Utils.getBooleanText(HypixelUtils.config.chatPingBold);
                        return null;
                    }
            );

            // If it should use italics

            this.makeButton(
                    "Italics: " + Utils.getBooleanText(HypixelUtils.config.chatPingItalics),
                    (GuiButton btn) -> {
                        HypixelUtils.config.chatPingItalics = !(HypixelUtils.config.chatPingItalics);
                        btn.displayString = "Italics: " + Utils.getBooleanText(HypixelUtils.config.chatPingItalics);
                        return null;
                    },
                    (GuiButton btn) -> {
                        btn.displayString = "Italics: " + Utils.getBooleanText(HypixelUtils.config.chatPingItalics);
                        return null;
                    }
            );

            // If it should be underlined

            this.makeButton(
                    "Underline username: " + Utils.getBooleanText(HypixelUtils.config.chatPingUnderline),
                    (GuiButton btn) -> {
                        HypixelUtils.config.chatPingUnderline = !(HypixelUtils.config.chatPingUnderline);
                        btn.displayString = "Underline: " + Utils.getBooleanText(HypixelUtils.config.chatPingUnderline);
                        return null;
                    },
                    (GuiButton btn) -> {
                        btn.displayString = "Underline: " + Utils.getBooleanText(HypixelUtils.config.chatPingUnderline);
                        return null;
                    }
            );
        }

        @Override
        public void drawScreen(int x, int y, float partialTicks) {
            super.drawScreen(x, y, partialTicks);

            // Custom preview

            String text = "Preview: " + ChatPing.get().changeText(EnumChatFormatting.WHITE + "Hey " + Utils.getPlayerName() + "! " + EnumChatFormatting.GREEN + "Want to play " + EnumChatFormatting.GOLD + "some minecraft? :D");
            DrawUtils.drawString(text, this.width / 2 - DrawUtils.getPositionDifference(text, DrawUtils.TextAlignment.Middle), this.startHeight + this.startHeightButtonAdd);
        }
    }
}
