package dev.naturecodevoid.forge.hypixelutils;

import club.sk1er.vigilance.Vigilant;
import club.sk1er.vigilance.data.Property;
import club.sk1er.vigilance.data.PropertyType;

import java.io.File;

public class Config extends Vigilant {
    public final String[] colors = {
            "Black",
            "Gold",
            "Gray",
            "Blue",
            "Green",
            "Aqua",
            "Red",
            "Light Purple",
            "Yellow",
            "White",
            "Dark Blue",
            "Dark Green",
            "Dark Aqua",
            "Dark Red",
            "Dark Purple",
            "Dark Gray"
    };

    /*
            options = {
                    "§0Black",
                    "§6Gold",
                    "§7Gray",
                    "§9Blue",
                    "§aGreen",
                    "§bAqua",
                    "§cRed",
                    "§dLight Purple",
                    "§eYellow",
                    "§fWhite",
                    "§1Dark Blue",
                    "§2Dark Green",
                    "§3Dark Aqua",
                    "§4Dark Red",
                    "§5Dark Purple",
                    "§8Dark Gray"
            }
     */

    ////
    //// General
    ////

    @Property(
            type = PropertyType.SWITCH,
            name = "Enabled",
            category = "General",
            description = "Toggle HypixelUtils."
    )
    public boolean enabled = true;

    @Property(
            type = PropertyType.SWITCH,
            name = "Hypixel features enabled on other servers",
            category = "General",
            description = "Turn on to enable hypixel features on other servers/singleplayer. Turn off to only enable hypixel features on Hypixel."
    )
    public boolean otherServers = false;

    ////
    //// Chat Ping
    ////

    @Property(
            type = PropertyType.SWITCH,
            name = "Enabled",
            category = "Chat Ping",
            description = "Toggle chat ping."
    )
    public boolean chatPingEnabled = true;

    @Property(
            type = PropertyType.SWITCH,
            name = "Ping on username",
            category = "Chat Ping",
            description = "Toggle making a ping sound when your username is said in the chat."
    )
    public boolean chatPingUsername = false;

    @Property(
            type = PropertyType.SLIDER,
            name = "Ping Volume",
            category = "Chat Ping",
            description = "Change username ping volume. High values are not recommended.",
            max = 10
    )
    public int chatPingVolume = 2;

    @Property(
            type = PropertyType.SWITCH,
            name = "Username Highlight",
            category = "Chat Ping",
            description = "Toggle highlighting your name in the chat with colors, bold, etc."
    )
    public boolean chatPingHighlight = true;

    @Property(
            type = PropertyType.SWITCH,
            name = "Username Color Enabled",
            category = "Chat Ping",
            description = "Toggle highlighting your name in the chat with colors."
    )
    public boolean chatPingColorEnabled = false;

    @Property(
            type = PropertyType.SELECTOR,
            name = "Username Color",
            category = "Chat Ping",
            description = "Change username color.",
            options = {
                    "§0Black",
                    "§6Gold",
                    "§7Gray",
                    "§9Blue",
                    "§aGreen",
                    "§bAqua",
                    "§cRed",
                    "§dLight Purple",
                    "§eYellow",
                    "§fWhite",
                    "§1Dark Blue",
                    "§2Dark Green",
                    "§3Dark Aqua",
                    "§4Dark Red",
                    "§5Dark Purple",
                    "§8Dark Gray"
            }
    )
    public int chatPingColor = 4;

    @Property(
            type = PropertyType.SWITCH,
            name = "Username Bold",
            category = "Chat Ping",
            description = "Toggle highlighting your name in the chat with bold."
    )
    public boolean chatPingBold = false;

    @Property(
            type = PropertyType.SWITCH,
            name = "Username Italics",
            category = "Chat Ping",
            description = "Toggle highlighting your name in the chat with italics."
    )
    public boolean chatPingItalics = false;

    @Property(
            type = PropertyType.SWITCH,
            name = "Username Underline",
            category = "Chat Ping",
            description = "Toggle underlining your name in chat."
    )
    public boolean chatPingUnderline = true;

    ////
    //// Coin Tracker
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
    public int coinsMessage = 1;
    public String[] coinsMessages = {"$2Session Coins: $3$1", "$3$1 $2Session Coins", "$2Coins: $3$1", "$3$1 $2Coins", "$2Earned Coins: $3$1", "$3$1 $2Earned Coins"};
    public String[] coinsMessagesFriendly = {"Session Coins: 1234", "1234 Session Coins", "Coins: 1234", "1234 Coins", "Earned Coins: 1234", "1234 Earned Coins"};

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
            options = {
                    "§0Black",
                    "§6Gold",
                    "§7Gray",
                    "§9Blue",
                    "§aGreen",
                    "§bAqua",
                    "§cRed",
                    "§dLight Purple",
                    "§eYellow",
                    "§fWhite",
                    "§1Dark Blue",
                    "§2Dark Green",
                    "§3Dark Aqua",
                    "§4Dark Red",
                    "§5Dark Purple",
                    "§8Dark Gray"
            }
    )
    public int coinsColor1 = 9;

    @Property(
            type = PropertyType.SELECTOR,
            name = "Coins Color",
            category = "Coin Tracker",
            description = "Change the coin color.",
            options = {
                    "§0Black",
                    "§6Gold",
                    "§7Gray",
                    "§9Blue",
                    "§aGreen",
                    "§bAqua",
                    "§cRed",
                    "§dLight Purple",
                    "§eYellow",
                    "§fWhite",
                    "§1Dark Blue",
                    "§2Dark Green",
                    "§3Dark Aqua",
                    "§4Dark Red",
                    "§5Dark Purple",
                    "§8Dark Gray"
            }
    )
    public int coinsColor2 = 1;

    @Property(
            type = PropertyType.SLIDER,
            name = "Coin Tracker X",
            category = "Coin Tracker",
            description = "Manually change the coin tracker X position. /hutilsg is the recommended method. (value is percentage of screen)",
            hidden = true,
            max = 1000
    )
    public int coinTrackerX = 0;

    @Property(
            type = PropertyType.SLIDER,
            name = "Coin Tracker Y",
            category = "Coin Tracker",
            description = "Manually change the coin tracker Y position. /hutilsg is the recommended method. (value is percentage of screen)",
            hidden = true,
            max = 1000
    )
    public int coinTrackerY = 0;

    @Property(
            type = PropertyType.SLIDER,
            name = "Coin Tracker Coins",
            category = "Coin Tracker",
            description = "The amount of coins kept in the config",
            hidden = true
    )
    public int coinTrackerCoins = 0;

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
            options = {"FPS: 120", "120 FPS"}
    )
    public int fpsMessage = 1;
    public String[] fpsMessages = {"FPS: $1", "$1 FPS"};
    public String[] fpsMessagesFriendly = {"FPS: 120", "120 FPS"};

    @Property(
            type = PropertyType.SWITCH,
            name = "Brackets",
            category = "FPS Display",
            description = "Toggle surrounding the FPS display with brackets (example: '[120 FPS]')."
    )
    public boolean fpsBrackets = true;

    @Property(
            type = PropertyType.SELECTOR,
            name = "FPS Color",
            category = "FPS Display",
            description = "Change the FPS display color.",
            options = {
                    "§0Black",
                    "§6Gold",
                    "§7Gray",
                    "§9Blue",
                    "§aGreen",
                    "§bAqua",
                    "§cRed",
                    "§dLight Purple",
                    "§eYellow",
                    "§fWhite",
                    "§1Dark Blue",
                    "§2Dark Green",
                    "§3Dark Aqua",
                    "§4Dark Red",
                    "§5Dark Purple",
                    "§8Dark Gray"
            }
    )
    public int fpsColor = 9;

    @Property(
            type = PropertyType.SLIDER,
            name = "FPS Display X",
            category = "FPS Display",
            description = "Manually change the FPS display X position. /hutilsg is the recommended method. (value is percentage of screen)",
            hidden = true,
            max = 1000
    )
    public int fpsX = 300;

    @Property(
            type = PropertyType.SLIDER,
            name = "FPS Display Y",
            category = "FPS Display",
            description = "Manually change the FPS display Y position. /hutilsg is the recommended method. (value is percentage of screen)",
            hidden = true,
            max = 1000
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
    public int cpsMessage = 1;
    public String[] cpsMessages = {"CPS: $1", "$1 CPS", "$1"};
    public String[] cpsMessagesFriendly = {"CPS: 10", "10 CPS", "10"};

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
            options = {
                    "§0Black",
                    "§6Gold",
                    "§7Gray",
                    "§9Blue",
                    "§aGreen",
                    "§bAqua",
                    "§cRed",
                    "§dLight Purple",
                    "§eYellow",
                    "§fWhite",
                    "§1Dark Blue",
                    "§2Dark Green",
                    "§3Dark Aqua",
                    "§4Dark Red",
                    "§5Dark Purple",
                    "§8Dark Gray"
            }
    )
    public int cpsColor = 9;

    @Property(
            type = PropertyType.SLIDER,
            name = "CPS Display X",
            category = "CPS Display",
            description = "Manually change the CPS display X position. /hutilsg is the recommended method. (value is percentage of screen)",
            hidden = true,
            max = 1000
    )
    public int cpsX = 300;

    @Property(
            type = PropertyType.SLIDER,
            name = "CPS Display Y",
            category = "CPS Display",
            description = "Manually change the CPS display Y position. /hutilsg is the recommended method. (value is percentage of screen)",
            hidden = true,
            max = 1000
    )
    public int cpsY = 150;

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
            options = {
                    "§0Black",
                    "§6Gold",
                    "§7Gray",
                    "§9Blue",
                    "§aGreen",
                    "§bAqua",
                    "§cRed",
                    "§dLight Purple",
                    "§eYellow",
                    "§fWhite",
                    "§1Dark Blue",
                    "§2Dark Green",
                    "§3Dark Aqua",
                    "§4Dark Red",
                    "§5Dark Purple",
                    "§8Dark Gray"
            }
    )
    public int clockColor = 9;

    @Property(
            type = PropertyType.SLIDER,
            name = "Clock X",
            category = "Clock",
            description = "Manually change the clock X position. /hutilsg is the recommended method. (value is percentage of screen)",
            hidden = true,
            max = 1000
    )
    public int clockX = 0;

    @Property(
            type = PropertyType.SLIDER,
            name = "Clock Y",
            category = "Clock",
            description = "Manually change the clock Y position. /hutilsg is the recommended method. (value is percentage of screen)",
            hidden = true,
            max = 1000
    )
    public int clockY = 150;

    public Config() {
        super(new File("./config/hypixelutils.toml"));
        initialize();
        preload();
    }
}
