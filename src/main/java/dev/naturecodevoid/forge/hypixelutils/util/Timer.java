package dev.naturecodevoid.forge.hypixelutils.util;

import dev.naturecodevoid.forge.hypixelutils.HypixelUtils;

public class Timer {
    public final int index;
    private final long startTime;
    private final double length;
    private final Runnable run;
    public boolean done = false;

    /**
     * Custom timer
     *
     * @param run    Runnable to run when it finishes, can be lambda
     * @param length Milliseconds until finish
     */
    public Timer(Runnable run, int length) {
        this(run, (double) length);
    }

    /**
     * Custom timer
     *
     * @param run    Runnable to run when it finishes, can be lambda
     * @param length Milliseconds until finish
     */
    public Timer(Runnable run, double length) {
        this(run, length, System.currentTimeMillis());
    }

    /**
     * Custom timer
     *
     * @param run    Runnable to run when it finishes, can be lambda
     * @param length Milliseconds until finish
     */
    public Timer(Runnable run, double length, long startTime) {
        this.startTime = startTime;
        this.length = length;
        this.run = run;
        HypixelUtils.timers.add(this);
        this.index = HypixelUtils.timers.size() - 1;
    }

    public void update() {
        if (System.currentTimeMillis() - this.length >= this.startTime) {
            this.run.run();
            this.done = true;
        }
    }
}
