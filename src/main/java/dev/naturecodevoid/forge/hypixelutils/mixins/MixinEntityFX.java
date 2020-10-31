package dev.naturecodevoid.forge.hypixelutils.mixins;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityFX.class)
public class MixinEntityFX {
    @Inject(method = "renderParticle", at = @At(value = "HEAD"))
    public void onRenderParticle(CallbackInfo ci) {
        System.out.println("test");
    }
}
