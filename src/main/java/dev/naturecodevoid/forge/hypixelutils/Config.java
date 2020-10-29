package dev.naturecodevoid.forge.hypixelutils;

import club.sk1er.vigilance.Vigilant;
import club.sk1er.vigilance.data.Property;
import club.sk1er.vigilance.data.PropertyType;

import java.io.File;

public class Config extends Vigilant {
    public final String[] colors = {"Black", "Gold", "Gray", "Blue", "Green", "Aqua", "Red", "Light Purple", "Yellow", "White" /*"Dark Blue", "Dark Green", "Dark Aqua", "Dark Red", "Dark Purple", "Dark Gray",*/};

    @Property(
            type = PropertyType.SWITCH,
            name = "Enabled",
            category = "General",
            description = "Toggle HypixelUtils."
    )
    public boolean enabled = true;

    @Property(
            type = PropertyType.SWITCH,
            name = "Enabled on other servers",
            category = "General",
            description = "Turn on to enable on other servers/singleplayer. Turn off to only enable on Hypixel."
    )
    public boolean otherServers = false;

    ////
    //// COIN TRACKER
    ////

    @Property(
            type = PropertyType.SWITCH,
            name = "Enabled",
            category = "Features",
            subcategory = "Coin Tracker",
            description = "Toggle the coin tracker."
    )
    public boolean coinsEnabled = true;

    @Property(
            type = PropertyType.SELECTOR,
            name = "Coins Message",
            category = "Features",
            subcategory = "Coin Tracker",
            description = "Change the coin tracker message.",
            options = {"Session Coins", "Coins", "Earned Coins"}
    )
    public Integer coinsMessage = 1;
    public String[] coinsMessages = {"Session Coins", "Coins", "Earned Coins"};

    @Property(
            type = PropertyType.SELECTOR,
            name = "Coins Message Color",
            category = "Features",
            subcategory = "Coin Tracker",
            description = "Change the coin tracker message color.",
            options = {"Black", "Gold", "Gray", "Blue", "Green", "Aqua", "Red", "Light Purple", "Yellow", "White" /*"Dark Blue", "Dark Green", "Dark Aqua", "Dark Red", "Dark Purple", "Dark Gray",*/}
    )
    public Integer coinsColor1 = 9;

    @Property(
            type = PropertyType.SELECTOR,
            name = "Coins Color",
            category = "Features",
            subcategory = "Coin Tracker",
            description = "Change the coin color.",
            options = {"Black", "Gold", "Gray", "Blue", "Green", "Aqua", "Red", "Light Purple", "Yellow", "White" /*"Dark Blue", "Dark Green", "Dark Aqua", "Dark Red", "Dark Purple", "Dark Gray",*/}
    )
    public Integer coinsColor2 = 1;

    @Property(
            type = PropertyType.SLIDER,
            name = "Coin Tracker X",
            category = "Features",
            subcategory = "Coin Tracker",
            description = "Manually change the coin tracker X position. /hutilsg is the recommended method. (value is percentage of screen)",
            hidden = true,
            max = 100
    )
    public int coinTrackerX = 0;

    @Property(
            type = PropertyType.SLIDER,
            name = "Coin Tracker Y",
            category = "Features",
            subcategory = "Coin Tracker",
            description = "Manually change the coin tracker Y position. /hutilsg is the recommended method. (value is percentage of screen)",
            hidden = true,
            max = 100
    )
    public int coinTrackerY = 0;

    ////
    //// FPS Display
    ////

    @Property(
            type = PropertyType.SWITCH,
            name = "Enabled",
            category = "Features",
            subcategory = "FPS Display",
            description = "Toggle the FPS display."
    )
    public boolean fpsEnabled = true;

    @Property(
            type = PropertyType.SELECTOR,
            name = "FPS Display Message",
            category = "Features",
            subcategory = "FPS Display",
            description = "Change the FPS display message.",
            options = {"FPS: 999", "999 FPS"}
    )
    public Integer fpsMessage = 1;
    public String[] fpsMessages = {"FPS: $1", "$1 FPS"};

    @Property(
            type = PropertyType.SELECTOR,
            name = "FPS Color",
            category = "Features",
            subcategory = "FPS Display",
            description = "Change the FPS display color.",
            options = {"Black", "Gold", "Gray", "Blue", "Green", "Aqua", "Red", "Light Purple", "Yellow", "White" /*"Dark Blue", "Dark Green", "Dark Aqua", "Dark Red", "Dark Purple", "Dark Gray",*/}
    )
    public Integer fpsColor = 9;

    @Property(
            type = PropertyType.SLIDER,
            name = "FPS Display X",
            category = "Features",
            subcategory = "FPS Display",
            description = "Manually change the FPS display X position. /hutilsg is the recommended method. (value is percentage of screen)",
            hidden = true,
            max = 100
    )
    public int fpsX = 30;

    @Property(
            type = PropertyType.SLIDER,
            name = "FPS Display Y",
            category = "Features",
            subcategory = "FPS Display",
            description = "Manually change the FPS display Y position. /hutilsg is the recommended method. (value is percentage of screen)",
            hidden = true,
            max = 100
    )
    public int fpsY = 0;

    ////
    //// CPS Display
    ////

    @Property(
            type = PropertyType.SWITCH,
            name = "Enabled",
            category = "Features",
            subcategory = "CPS Display",
            description = "Toggle the CPS display."
    )
    public boolean cpsEnabled = true;

    @Property(
            type = PropertyType.SELECTOR,
            name = "CPS Display Message",
            category = "Features",
            subcategory = "CPS Display",
            description = "Change the CPS display message.",
            options = {"CPS: 10", "10 CPS"}
    )
    public Integer cpsMessage = 1;
    public String[] cpsMessages = {"CPS: $1", "$1 CPS"};

    @Property(
            type = PropertyType.SWITCH,
            name = "Right CPS",
            category = "Features",
            subcategory = "CPS Display",
            description = "Toggle the right CPS display."
    )
    public boolean cpsRight = true;

    @Property(
            type = PropertyType.SELECTOR,
            name = "CPS Color",
            category = "Features",
            subcategory = "CPS Display",
            description = "Change the CPS display color.",
            options = {"Black", "Gold", "Gray", "Blue", "Green", "Aqua", "Red", "Light Purple", "Yellow", "White" /*"Dark Blue", "Dark Green", "Dark Aqua", "Dark Red", "Dark Purple", "Dark Gray",*/}
    )
    public Integer cpsColor = 9;

    @Property(
            type = PropertyType.SLIDER,
            name = "CPS Display X",
            category = "Features",
            subcategory = "CPS Display",
            description = "Manually change the CPS display X position. /hutilsg is the recommended method. (value is percentage of screen)",
            hidden = true,
            max = 100
    )
    public int cpsX = 30;

    @Property(
            type = PropertyType.SLIDER,
            name = "CPS Display Y",
            category = "Features",
            subcategory = "CPS Display",
            description = "Manually change the CPS display Y position. /hutilsg is the recommended method. (value is percentage of screen)",
            hidden = true,
            max = 100
    )
    public int cpsY = 0;

    public Config() {
        super(new File("./config/hypixelutils.toml"));
        initialize();
        preload();
    }
}
