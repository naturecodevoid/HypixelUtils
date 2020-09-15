package dev.naturecodevoid.forge.hypixelutils;

import dev.naturecodevoid.forge.hypixelutils.listeners.CoinListener;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = HypixelUtils.MODID, version = HypixelUtils.VERSION, name = HypixelUtils.NAME, clientSideOnly = true, acceptedMinecraftVersions = "@MOD_ACCEPTED@")
public class HypixelUtils {
    public static final String MODID = "@MOD_ID@";
    public static final String VERSION = "@VERSION@";
    public static final String NAME = "@MOD_NAME@";
    public static HypixelUtilsConfig config;
    public static Integer totalCoins = 0;
    public static final String prefix = EnumChatFormatting.GREEN + "[HypixelUtils] " + EnumChatFormatting.RESET;

    @Mod.Instance(HypixelUtils.MODID)
    public static HypixelUtils instance;

    public static HypixelUtils get() {
        return instance;
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        // FMLCommonHandler.instance().bus().register(this);
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new CoinListener());
        ClientCommandHandler.instance.registerCommand(new HypixelUtilsCommand());

        config = new HypixelUtilsConfig();
        config.preload();
    }
}
