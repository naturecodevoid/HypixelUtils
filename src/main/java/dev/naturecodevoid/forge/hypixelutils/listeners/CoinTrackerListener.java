package dev.naturecodevoid.forge.hypixelutils.listeners;

import dev.naturecodevoid.forge.hypixelutils.HypixelUtils;
import dev.naturecodevoid.forge.hypixelutils.features.CoinTracker;
import dev.naturecodevoid.forge.hypixelutils.util.Util;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CoinTrackerListener {
    // https://hypixel.net/threads/guide-how-to-start-create-coding-minecraft-forge-mods.551741/
    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        String message = event.message.getUnformattedText();
        if (message.startsWith("+") && message.contains("coins") && !message.endsWith("for being generous")) {
            String[] splittedMessage = message.split("coins");
            message = splittedMessage[0].replace("+", "");
            message = message.replace(" ", "");
            int coins = Integer.parseInt(message);
            HypixelUtils.totalCoins += coins;
        }
    }

    @SubscribeEvent
    public void onRender(RenderGameOverlayEvent event) {
        if (!(HypixelUtils.config.coinsEnabled) || !(Util.isModEnabled())) return;
        if (Util.hasGuiOpen()) return;
        if (event.isCancelable() || event.type != RenderGameOverlayEvent.ElementType.EXPERIENCE) return;

        CoinTracker.instance.render(event);
    }
}
