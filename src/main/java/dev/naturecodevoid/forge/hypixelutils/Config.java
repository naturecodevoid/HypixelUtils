package dev.naturecodevoid.forge.hypixelutils;

import club.sk1er.vigilance.Vigilant;
import club.sk1er.vigilance.data.Property;
import club.sk1er.vigilance.data.PropertyType;

import java.io.File;

public class Config extends Vigilant {
    public final String[] colors = {"Black", "Gold", "Gray", "Blue", "Green", "Aqua", "Red", "Light Purple", "Yellow", "White", "Dark Blue", "Dark Green", "Dark Aqua", "Dark Red", "Dark Purple", "Dark Gray"};

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
            category = "Coin Tracker",
            description = "Toggle the coin tracker."
    )
    public boolean coinsEnabled = true;

    @Property(
            type = PropertyType.SELECTOR,
            name = "Coins Message",
            category = "Coin Tracker",
            description = "Change the coin tracker message.",
            options = {"Session Coins: 1234", "1234 Session Coins", "Coins: 1234", "1234 Coins", "Earned Coins: 1234", "1234 Earned Coins"}
    )
    public Integer coinsMessage = 1;
    public String[] coinsMessages = {"$2Session Coins: $3$1", "$3$1 $2Session Coins", "$2Coins: $3$1", "$3$1 $2Coins", "$2Earned Coins: $3$1", "$3$1 $2Earned Coins"};

    @Property(
            type = PropertyType.SWITCH,
            name = "Brackets",
            category = "Coin Tracker",
            description = "Toggle surrounding the coin tracker with brackets (example: '[Coins: 1234]')."
    )
    public boolean coinsBrackets = true;

    @Property(
            type = PropertyType.SELECTOR,
            name = "Coins Message Color",
            category = "Coin Tracker",
            description = "Change the coin tracker message color.",
            options = {"Black", "Gold", "Gray", "Blue", "Green", "Aqua", "Red", "Light Purple", "Yellow", "White", "Dark Blue", "Dark Green", "Dark Aqua", "Dark Red", "Dark Purple", "Dark Gray"}
    )
    public Integer coinsColor1 = 9;

    @Property(
            type = PropertyType.SELECTOR,
            name = "Coins Color",
            category = "Coin Tracker",
            description = "Change the coin color.",
            options = {"Black", "Gold", "Gray", "Blue", "Green", "Aqua", "Red", "Light Purple", "Yellow", "White", "Dark Blue", "Dark Green", "Dark Aqua", "Dark Red", "Dark Purple", "Dark Gray"}
    )
    public Integer coinsColor2 = 1;

    @Property(
            type = PropertyType.SLIDER,
            name = "Coin Tracker X",
            category = "Coin Tracker",
            description = "Manually change the coin tracker X position. /hutilsg is the recommended method. (value is percentage of screen)",
            hidden = true,
            max = 100
    )
    public int coinTrackerX = 0;

    @Property(
            type = PropertyType.SLIDER,
            name = "Coin Tracker Y",
            category = "Coin Tracker",
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
            category = "FPS Display",
            description = "Toggle the FPS display."
    )
    public boolean fpsEnabled = true;

    @Property(
            type = PropertyType.SELECTOR,
            name = "FPS Display Message",
            category = "FPS Display",
            description = "Change the FPS display message.",
            options = {"FPS: 999", "999 FPS"}
    )
    public Integer fpsMessage = 1;
    public String[] fpsMessages = {"FPS: $1", "$1 FPS"};

    @Property(
            type = PropertyType.SWITCH,
            name = "Brackets",
            category = "FPS Display",
            description = "Toggle surrounding the FPS display with brackets (example: '[999 FPS]')."
    )
    public boolean fpsBrackets = true;

    @Property(
            type = PropertyType.SELECTOR,
            name = "FPS Color",
            category = "FPS Display",
            description = "Change the FPS display color.",
            options = {"Black", "Gold", "Gray", "Blue", "Green", "Aqua", "Red", "Light Purple", "Yellow", "White", "Dark Blue", "Dark Green", "Dark Aqua", "Dark Red", "Dark Purple", "Dark Gray"}
    )
    public Integer fpsColor = 9;

    @Property(
            type = PropertyType.SLIDER,
            name = "FPS Display X",
            category = "FPS Display",
            description = "Manually change the FPS display X position. /hutilsg is the recommended method. (value is percentage of screen)",
            hidden = true,
            max = 100
    )
    public int fpsX = 30;

    @Property(
            type = PropertyType.SLIDER,
            name = "FPS Display Y",
            category = "FPS Display",
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
            category = "CPS Display",
            description = "Toggle the CPS display."
    )
    public boolean cpsEnabled = true;

    @Property(
            type = PropertyType.SELECTOR,
            name = "CPS Display Message",
            category = "CPS Display",
            description = "Change the CPS display message.",
            options = {"CPS: 10", "10 CPS", "10"}
    )
    public Integer cpsMessage = 1;
    public String[] cpsMessages = {"CPS: $1", "$1 CPS", "$1"};

    @Property(
            type = PropertyType.SWITCH,
            name = "Brackets",
            category = "CPS Display",
            description = "Toggle surrounding the CPS display with brackets (example: '[10 CPS]')."
    )
    public boolean cpsBrackets = true;

    @Property(
            type = PropertyType.SWITCH,
            name = "Right CPS",
            category = "CPS Display",
            description = "Toggle the right CPS display."
    )
    public boolean cpsRight = true;

    @Property(
            type = PropertyType.SELECTOR,
            name = "CPS Color",
            category = "CPS Display",
            description = "Change the CPS display color.",
            options = {"Black", "Gold", "Gray", "Blue", "Green", "Aqua", "Red", "Light Purple", "Yellow", "White", "Dark Blue", "Dark Green", "Dark Aqua", "Dark Red", "Dark Purple", "Dark Gray"}
    )
    public Integer cpsColor = 9;

    @Property(
            type = PropertyType.SLIDER,
            name = "CPS Display X",
            category = "CPS Display",
            description = "Manually change the CPS display X position. /hutilsg is the recommended method. (value is percentage of screen)",
            hidden = true,
            max = 100
    )
    public int cpsX = 30;

    @Property(
            type = PropertyType.SLIDER,
            name = "CPS Display Y",
            category = "CPS Display",
            description = "Manually change the CPS display Y position. /hutilsg is the recommended method. (value is percentage of screen)",
            hidden = true,
            max = 100
    )
    public int cpsY = 15;

    ////
    //// Clock
    ////

    @Property(
            type = PropertyType.SWITCH,
            name = "Enabled",
            category = "Clock",
            description = "Toggle the clock."
    )
    public boolean clockEnabled = true;

    @Property(
            type = PropertyType.SWITCH,
            name = "24 hour",
            category = "Clock",
            description = "Toggle 24 hour clock format."
    )
    public boolean clock24hr = false;

    @Property(
            type = PropertyType.SWITCH,
            name = "Show seconds",
            category = "Clock",
            description = "Toggle showing seconds."
    )
    public boolean clockSeconds = false;

    @Property(
            type = PropertyType.SWITCH,
            name = "Brackets",
            category = "Clock",
            description = "Toggle surrounding the clock with brackets (example: '[10:15 AM]')."
    )
    public boolean clockBrackets = true;

    @Property(
            type = PropertyType.SELECTOR,
            name = "Clock Color",
            category = "Clock",
            description = "Change the clock color.",
            options = {"Black", "Gold", "Gray", "Blue", "Green", "Aqua", "Red", "Light Purple", "Yellow", "White", "Dark Blue", "Dark Green", "Dark Aqua", "Dark Red", "Dark Purple", "Dark Gray"}
    )
    public Integer clockColor = 9;

    @Property(
            type = PropertyType.SLIDER,
            name = "Clock X",
            category = "Clock",
            description = "Manually change the clock X position. /hutilsg is the recommended method. (value is percentage of screen)",
            hidden = true,
            max = 100
    )
    public int clockX = 0;

    @Property(
            type = PropertyType.SLIDER,
            name = "Clock Y",
            category = "Clock",
            description = "Manually change the clock Y position. /hutilsg is the recommended method. (value is percentage of screen)",
            hidden = true,
            max = 100
    )
    public int clockY = 15;

    public Config() {
        super(new File("./config/hypixelutils.toml"));
        initialize();
        preload();
    }
}
