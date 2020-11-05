package dev.naturecodevoid.forge.hypixelutils.base;

import net.minecraftforge.common.MinecraftForge;
import org.apache.commons.lang3.NotImplementedException;

public abstract class BaseFeature {
    public BaseFeature instance;

    /*
    public static CHANGEME instance;

    public CHANGEME() {
        this.init();
    }
    */

    public void init() {
        instance = this;
        MinecraftForge.EVENT_BUS.register(this);
    }

    public boolean isEnabled() {
        throw new NotImplementedException("Please override the isEnabled function! This checks if the feature is enabled.");
    }

    public boolean isHypixel() {
        throw new NotImplementedException("Please override the isHypixel function! This checks if the feature is hypixel only.");
    }

    public boolean isGui() {
        return false;
    }

    @Override
    public String toString() {
        String[] array = getClass().getName().split("\\.");
        return array[array.length - 1];
    }
}
