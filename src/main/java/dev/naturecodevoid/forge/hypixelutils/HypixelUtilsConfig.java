package dev.naturecodevoid.forge.hypixelutils;

import club.sk1er.vigilance.Vigilant;
import club.sk1er.vigilance.data.Property;
import club.sk1er.vigilance.data.PropertyType;

import java.io.File;

public class HypixelUtilsConfig extends Vigilant {
    @Property(
            type = PropertyType.SWITCH,
            name = "HypixelUtils",
            category = "General",
            subcategory = "General",
            description = "Toggle HypixelUtils."
    )
    public boolean enabled = true;

    @Property(
            type = PropertyType.SWITCH,
            name = "Coin Tracker",
            category = "General",
            subcategory = "General",
            description = "Toggle the coin tracker."
    )
    public boolean coinsEnabled = true;

    public HypixelUtilsConfig() {
        super(new File("./config/hypixelutils.toml"));
        initialize();
    }
}
