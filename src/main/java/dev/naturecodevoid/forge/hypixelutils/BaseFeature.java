package dev.naturecodevoid.forge.hypixelutils;

import org.apache.commons.lang3.NotImplementedException;

public abstract class BaseFeature {
    public static BaseFeature instance;

    public BaseFeature() {
        instance = this;
    }

    public void render() {
        throw new NotImplementedException("Please override the render function!");
    }

    public void resetPosition() {
        throw new NotImplementedException("Please override the resetPosition function!");
    }
}
