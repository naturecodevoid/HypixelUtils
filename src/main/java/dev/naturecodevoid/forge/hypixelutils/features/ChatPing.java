package dev.naturecodevoid.forge.hypixelutils.features;

import dev.naturecodevoid.forge.hypixelutils.HypixelUtils;
import dev.naturecodevoid.forge.hypixelutils.base.BaseFeature;
import dev.naturecodevoid.forge.hypixelutils.misc.OrbSound;
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
        return HypixelUtils.config.coinsEnabled;
    }

    @Override
    public boolean isHypixel() {
        return true;
    }

    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        if (!(HypixelUtils.config.chatPingEnabled)) return;

        Minecraft mc = Minecraft.getMinecraft();

        String text = event.message
                .getUnformattedText()
                .toLowerCase()
                .replaceFirst("<.+>", "")
                .replaceFirst(".+:", "");

        if (text.contains(mc.thePlayer
                .getName()
                .toLowerCase())
                && HypixelUtils.config.chatPingUsername)
            mc.getSoundHandler().playSound(new OrbSound());
    }
}
