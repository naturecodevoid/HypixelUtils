package dev.naturecodevoid.forge.hypixelutils;


import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.Mixins;

import java.util.Map;

// ONLY needed in development environment, the mixin tweaker handles this in prod.
@IFMLLoadingPlugin.MCVersion(ForgeVersion.mcVersion)
public class HypixelUtilsLoadingPlugin implements IFMLLoadingPlugin {

    public HypixelUtilsLoadingPlugin() {
        MixinBootstrap.init();

        Mixins.addConfiguration("mixins." + HypixelUtils.MODID + ".json");
    }

    @Override
    public String[] getASMTransformerClass() {
        return new String[0];
    }

    @Override
    public String getModContainerClass() {
        return null;
    }

    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {

    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }
}