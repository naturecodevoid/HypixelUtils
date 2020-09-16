package dev.naturecodevoid.forge.hypixelutils.util;

public class Position {
    public int x = 0;
    public int y = 0;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position(double x, double y) {
        this.x = (int) x;
        this.y = (int) y;
    }
}
