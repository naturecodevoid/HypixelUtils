package dev.naturecodevoid.forge.hypixelutils.util;

public class Vector2D {
    public int x = 0;
    public int y = 0;

    public Vector2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vector2D(double x, double y) {
        this.x = (int) x;
        this.y = (int) y;
    }

    public Vector2D set(int x, int y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public Vector2D set(double x, double y) {
        this.x = (int) x;
        this.y = (int) y;
        return this;
    }

    public double length() {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    @Override
    public String toString() {
        return "X: " + x + " | Y: " + y;
    }
}
