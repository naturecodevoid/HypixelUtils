package dev.naturecodevoid.forge.hypixelutils;

import dev.naturecodevoid.forge.hypixelutils.commands.ConfigCommand;
import dev.naturecodevoid.forge.hypixelutils.commands.GeneralCommand;
import dev.naturecodevoid.forge.hypixelutils.commands.HUDCommand;
import dev.naturecodevoid.forge.hypixelutils.features.CoinTracker;
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

import java.util.ArrayList;

@Mod(modid = HypixelUtils.MODID, version = HypixelUtils.VERSION, name = HypixelUtils.NAME, clientSideOnly = true, acceptedMinecraftVersions = "@MOD_ACCEPTED@")
public class HypixelUtils {
    public static final String MODID = "@MOD_ID@";
    public static final String VERSION = "@VERSION@";
    public static final String NAME = "@MOD_NAME@";
    public static final String prefix = EnumChatFormatting.GREEN + "[HypixelUtils] " + EnumChatFormatting.RESET;
    public static ArrayList<IFeature> features = new ArrayList<IFeature>();
    public static Config config;
    public static GuiScreen gui = null;
    public static int totalCoins = 0;

    @Mod.Instance(HypixelUtils.MODID)
    public static HypixelUtils instance;

    @EventHandler

    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
        config = new Config();

        // Features
        features.add(new CoinTracker());

        // Listeners
        MinecraftForge.EVENT_BUS.register(new CoinListener());

        // Commands
        ClientCommandHandler.instance.registerCommand(new ConfigCommand());
        ClientCommandHandler.instance.registerCommand(new HUDCommand());
        ClientCommandHandler.instance.registerCommand(new GeneralCommand());
    }

    @SubscribeEvent
    public void tick(TickEvent.ClientTickEvent event) {
        if (gui != null) {
            Minecraft.getMinecraft().displayGuiScreen(gui);
            gui = null;
        }
    }
}
