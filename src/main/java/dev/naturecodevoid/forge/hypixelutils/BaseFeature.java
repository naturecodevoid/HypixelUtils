package dev.naturecodevoid.forge.hypixelutils;

import dev.naturecodevoid.forge.hypixelutils.util.Coordinate2D;
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

    public Coordinate2D getPosition() {
        throw new NotImplementedException("Please override the getPosition function!");
    }

    public Coordinate2D getSize() {
        throw new NotImplementedException("Please override the getSize function!");
    }

    @Override
    public String toString() {
        String[] array = getClass().getName().split("\\.");
        return array[array.length - 1];
    }
}
