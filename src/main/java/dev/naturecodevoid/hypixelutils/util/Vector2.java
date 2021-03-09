package dev.naturecodevoid.hypixelutils.util;

public class Vector2 {
    public int x = 0;
    public int y = 0;

    public Vector2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vector2(double x, double y) {
        this.x = (int) x;
        this.y = (int) y;
    }

    public Vector2(float x, float y) {
        this.x = (int) x;
        this.y = (int) y;
    }

    public Vector2 copy() {
        return new Vector2(this.x, this.y);
    }

    public Vector2 set(int x, int y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public Vector2 set(double x, double y) {
        this.x = (int) x;
        this.y = (int) y;
        return this;
    }

    public Vector2 set(float x, float y) {
        this.x = (int) x;
        this.y = (int) y;
        return this;
    }

    public Vector2 setX(int x) {
        this.x = x;
        return this;
    }

    public Vector2 setX(double x) {
        this.x = (int) x;
        return this;
    }

    public Vector2 setX(float x) {
        this.x = (int) x;
        return this;
    }

    public Vector2 setY(int y) {
        this.y = y;
        return this;
    }

    public Vector2 setY(double y) {
        this.y = (int) y;
        return this;
    }

    public Vector2 setY(float y) {
        this.y = (int) y;
        return this;
    }

    public double length() {
        return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2));
    }

    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }
}
