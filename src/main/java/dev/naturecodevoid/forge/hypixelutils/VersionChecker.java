package dev.naturecodevoid.forge.hypixelutils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.naturecodevoid.forge.hypixelutils.util.Timer;
import net.minecraft.client.Minecraft;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.HoverEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;

// https://stackoverflow.com/a/4308662
public class VersionChecker {
    public URL latestReleaseUrl;
    public JsonElement downloadUrl;
    public ChatComponentText message = null;
    public boolean sentMessage = false;

    public VersionChecker() {
        try {
            latestReleaseUrl = new URL("https://api.github.com/repos/naturecodevoiddev/HypixelUtils/releases/latest");

            InputStream is = latestReleaseUrl.openStream();
            JsonObject json;
            try {
                BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
                String jsonText = readAll(rd);
                json = new Gson().fromJson(jsonText, JsonObject.class);
            } finally {
                is.close();
            }

            JsonArray assets = json.get("assets").getAsJsonArray();

            JsonObject latestAsset = (JsonObject) assets.get(0);

            int latestAssetVersion = Integer.parseInt(
                    latestAsset.get("name").toString()
                            .replace("HypixelUtils-", "")
                            .replace(".jar", "")
                            .replace(".", "")
                            .replace("\"", "")
            );

            String latestAssetVersionString =
                    latestAsset.get("name").toString()
                            .replace("HypixelUtils-", "")
                            .replace(".jar", "")
                            .replace("\"", "");

            int currentVersion = Integer.parseInt(
                    HypixelUtils.VERSION
                            .replace(".", "")
            );

            if (latestAssetVersion > currentVersion) {
                downloadUrl = latestAsset.get("browser_download_url");

                // https://github.com/BiscuitDevelopment/SkyblockAddons/blob/master/src/main/java/codes/biscuit/skyblockaddons/misc/Updater.java
                message = new ChatComponentText(HypixelUtils.PREFIX + EnumChatFormatting.AQUA + "A new version (v" + latestAssetVersionString + ") is available! Click this message to download!");
                message
                        .setChatStyle(message.getChatStyle().setChatClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, downloadUrl.getAsString()))
                                .setChatHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ChatComponentText(EnumChatFormatting.AQUA + "Click to download!"))));

                MinecraftForge.EVENT_BUS.register(this);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("[HypixelUtils] Failed to get release info.");
        }
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    @SubscribeEvent
    public void onRender(RenderGameOverlayEvent event) {
        if (event.type == RenderGameOverlayEvent.ElementType.TEXT && !sentMessage && message != null) {
            sentMessage = true;
            new Timer(() -> {
                // https://github.com/BiscuitDevelopment/SkyblockAddons/blob/master/src/main/java/codes/biscuit/skyblockaddons/utils/Utils.java#L154
                ClientChatReceivedEvent event2 = new ClientChatReceivedEvent((byte) 1, message);
                MinecraftForge.EVENT_BUS.post(event2); // Let other mods pick up the new message
                if (!event2.isCanceled()) {
                    Minecraft.getMinecraft().thePlayer.addChatMessage(event2.message); // Just for logs
                }
            }, 1000);
        }
    }
}
