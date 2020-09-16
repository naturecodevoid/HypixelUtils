package dev.naturecodevoid.forge.hypixelutils;

import club.sk1er.vigilance.Vigilant;
import club.sk1er.vigilance.data.Property;
import club.sk1er.vigilance.data.PropertyType;

import java.io.File;

public class Config extends Vigilant {
    @Property(
            type = PropertyType.SWITCH,
            name = "HypixelUtils",
            category = "General",
            description = "Toggle HypixelUtils."
    )
    public boolean enabled = true;

    @Property(
            type = PropertyType.SWITCH,
            name = "Coin Tracker",
            category = "Features",
            description = "Toggle the coin tracker."
    )
    public boolean coinsEnabled = true;

    /*
    @Property(
            type = PropertyType.NUMBER,
            name = "Coin Tracker X",
            category = "gui",
            hidden = true
    )
    public int coinTrackerX = 10;

    @Property(
            type = PropertyType.NUMBER,
            name = "Coin Tracker Y",
            category = "gui",
            hidden = true
    )
    public int coinTrackerY = 5;
     */

    public Config() {
        super(new File("./config/hypixelutils.toml"));
        initialize();
    }
}
