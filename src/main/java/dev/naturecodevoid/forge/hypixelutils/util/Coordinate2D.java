package dev.naturecodevoid.forge.hypixelutils.util;

public class Coordinate2D {
    public int x = 0;
    public int y = 0;

    public Coordinate2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coordinate2D(double x, double y) {
        this.x = (int) x;
        this.y = (int) y;
    }

    public Coordinate2D set(int x, int y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public Coordinate2D set(double x, double y) {
        this.x = (int) x;
        this.y = (int) y;
        return this;
    }
}
