package dev.naturecodevoid.forge.hypixelutils.features;

/*
New feature todo:
- Search for 'template' and change the results
- Add the class to the feature list in HypixelUtils.java
- Add config values
- Code the feature + test
- See if getSize is good by moving to right side in gui editor, if not change textAddSmall to textAdd
- Remove this comment
 */

import dev.naturecodevoid.forge.hypixelutils.BaseFeature;
import dev.naturecodevoid.forge.hypixelutils.HypixelUtils;
import dev.naturecodevoid.forge.hypixelutils.util.Util;
import dev.naturecodevoid.forge.hypixelutils.util.Vector2D;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

public class ParticleChanger extends BaseFeature {
    public static ParticleChanger instance;

    public ParticleChanger() {
        this.init();
    }

    @Override
    public void render() {
    }

    @Override
    public void renderEditor() {
        this.render();
    }

    @Override
    public void resetPosition() {
    }

    @Override
    public Vector2D getPosition() {
        return new Vector2D(0, 0);
    }

    @Override
    public Vector2D setPosition(int xPercent, int yPercent) {
        return getPosition();
    }

    @Override
    public Vector2D getSize() {
        return new Vector2D(0, 0);
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
