package dev.naturecodevoid.forge.hypixelutils;

import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = HypixelUtils.MODID, version = HypixelUtils.VERSION, name = HypixelUtils.NAME, clientSideOnly = true, acceptedMinecraftVersions = "@MOD_ACCEPTED@")
public class HypixelUtils {
    public static final String MODID = "@MOD_ID@";
    public static final String VERSION = "@VERSION@";
    public static final String NAME = "@MOD_NAME@";

    @EventHandler
    public void init(FMLInitializationEvent event) {
    }
}
