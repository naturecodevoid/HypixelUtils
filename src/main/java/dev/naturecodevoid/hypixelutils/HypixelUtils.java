package dev.naturecodevoid.hypixelutils;

import dev.naturecodevoid.hypixelutils.base.BaseFeature;
import dev.naturecodevoid.hypixelutils.base.GuiFeature;
import dev.naturecodevoid.hypixelutils.commands.GeneralCommand;
import dev.naturecodevoid.hypixelutils.features.*;
import dev.naturecodevoid.hypixelutils.util.Timer;
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

@Mod(
        modid = HypixelUtils.MODID,
        version = HypixelUtils.VERSION,
        name = HypixelUtils.NAME,
        clientSideOnly = true,
        acceptedMinecraftVersions = "@MOD_ACCEPTED@",
        guiFactory = "dev.naturecodevoid.hypixelutils.HypixelUtilsGuiFactory"
)
public class HypixelUtils {
    public static final String MODID = "@MOD_ID@";
    public static final String VERSION = "@VERSION@";
    public static final String NAME = "@MOD_NAME@";
    public static final String PREFIX = EnumChatFormatting.GREEN + "[HypixelUtils] " + EnumChatFormatting.RESET;
    public static final ArrayList<GuiFeature> guiFeatures = new ArrayList<GuiFeature>();
    public static final ArrayList<BaseFeature> features = new ArrayList<BaseFeature>();
    public static final ArrayList<Timer> timers = new ArrayList<Timer>();
    public static Config config;
    public static GuiScreen gui = null;

    private static HypixelUtils instance = null;

    public HypixelUtils() {
        instance = this;
    }

    public static HypixelUtils get() {
        return instance;
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        new VersionChecker();

        MinecraftForge.EVENT_BUS.register(this);
        HypixelUtils.config = new Config();

        ////
        //// Features
        ////

        //// Gui
        HypixelUtils.guiFeatures.add(new CoinTracker());
        HypixelUtils.guiFeatures.add(new FPSDisplay());
        HypixelUtils.guiFeatures.add(new CPSDisplay());
        HypixelUtils.guiFeatures.add(new Clock());

        //// Other
        HypixelUtils.features.add(new ChatPing());

        ////
        //// Commands
        ////
        ClientCommandHandler.instance.registerCommand(new GeneralCommand());
    }

    @SubscribeEvent
    public void tick(TickEvent.ClientTickEvent event) {
        if (HypixelUtils.gui != null) {
            Minecraft.getMinecraft().displayGuiScreen(HypixelUtils.gui);
            HypixelUtils.gui = null;
        }

        // Update timers here
        ArrayList<Timer> done = new ArrayList<Timer>();

        for (Timer timer : HypixelUtils.timers) {
            if (!timer.done) timer.update();
            else done.add(timer);
        }

        for (Timer timer : done) {
            if (HypixelUtils.timers.size() - 1 <= timer.index && !HypixelUtils.timers.isEmpty())
                HypixelUtils.timers.remove(timer.index);
        }
    }
}
