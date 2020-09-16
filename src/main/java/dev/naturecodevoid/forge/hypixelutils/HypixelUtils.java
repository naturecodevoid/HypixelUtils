package dev.naturecodevoid.forge.hypixelutils;

import dev.naturecodevoid.forge.hypixelutils.listeners.CoinListener;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Mod(modid = HypixelUtils.MODID, version = HypixelUtils.VERSION, name = HypixelUtils.NAME, clientSideOnly = true, acceptedMinecraftVersions = "@MOD_ACCEPTED@")
public class HypixelUtils {
    public static final String MODID = "@MOD_ID@";
    public static final String VERSION = "@VERSION@";
    public static final String NAME = "@MOD_NAME@";
    public static final String prefix = EnumChatFormatting.GREEN + "[HypixelUtils] " + EnumChatFormatting.RESET;
    public static HypixelUtilsConfig config;
    public static GuiScreen gui = null;
    public static int totalCoins = 0;
    @Mod.Instance(HypixelUtils.MODID)
    public static HypixelUtils instance;

    public static HypixelUtils get() {
        return instance;
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);

        MinecraftForge.EVENT_BUS.register(new CoinListener());

        ClientCommandHandler.instance.registerCommand(new HypixelUtilsCommand());

        config = new HypixelUtilsConfig();
    }

    @SubscribeEvent
    public void tick(TickEvent.ClientTickEvent event) {
        if (gui != null) {
            Minecraft.getMinecraft().displayGuiScreen(gui);
            gui = null;
        }
    }
}
