package dev.naturecodevoid.forge.hypixelutils;

import club.sk1er.vigilance.Vigilant;

import java.io.File;

public class Config extends Vigilant {
    public Config() {
        super(new File("./config/hypixelutils.toml"));
    }
}
