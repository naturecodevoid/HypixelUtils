package dev.naturecodevoid.forge.hypixelutils.features;

import dev.naturecodevoid.forge.hypixelutils.HypixelUtils;
import dev.naturecodevoid.forge.hypixelutils.base.BaseFeature;
import dev.naturecodevoid.forge.hypixelutils.misc.OrbSound;
import dev.naturecodevoid.forge.hypixelutils.util.Utils;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ChatPing extends BaseFeature {
    public static ChatPing instance;

    public ChatPing() {
        this.init();
    }

    @Override
    public boolean isEnabled() {
        return HypixelUtils.config.chatPingEnabled;
    }

    @Override
    public boolean isHypixel() {
        return false;
    }

    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        if (!(this.isEnabled())) return;

        Minecraft mc = Minecraft.getMinecraft();

        String text = event.message
                .getUnformattedText()
                .toLowerCase()
                .replaceFirst("<.+>", "")
                .replaceFirst(".+:", "");

        if (text.contains(mc.thePlayer.getName().toLowerCase())) {
            if (HypixelUtils.config.chatPingUsername) mc.getSoundHandler().playSound(new OrbSound());
            if (HypixelUtils.config.chatPingHighlight) {
                String text2 = event.message.getFormattedText();

                text2 = text2.replaceAll(mc.thePlayer.getName(), Utils.getColor(HypixelUtils.config.chatPingColor) + mc.thePlayer.getName());

                Utils.sendMessageNoPrefix(mc.thePlayer, text2);

                event.setCanceled(true);
            }
        }
    }
}
