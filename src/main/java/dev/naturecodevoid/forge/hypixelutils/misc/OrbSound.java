package dev.naturecodevoid.forge.hypixelutils.misc;

import dev.naturecodevoid.forge.hypixelutils.HypixelUtils;
import net.minecraft.client.audio.ISound;
import net.minecraft.util.ResourceLocation;

public class OrbSound implements ISound {
    public final ResourceLocation loc;

    public OrbSound() {
        this.loc = new ResourceLocation("", "random.orb");
    }

    @Override
    public ResourceLocation getSoundLocation() {
        return this.loc;
    }

    @Override
    public boolean canRepeat() {
        return false;
    }

    @Override
    public int getRepeatDelay() {
        return 0;
    }

    @Override
    public float getVolume() {
        return (float) HypixelUtils.config.chatPingVolume / 10;
    }

    @Override
    public float getPitch() {
        return 1.0F;
    }

    @Override
    public float getXPosF() {
        return 0;
    }

    @Override
    public float getYPosF() {
        return 0;
    }

    @Override
    public float getZPosF() {
        return 0;
    }

    @Override
    public AttenuationType getAttenuationType() {
        return AttenuationType.NONE;
    }
}
