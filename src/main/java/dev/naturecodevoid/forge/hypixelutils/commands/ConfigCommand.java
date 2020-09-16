package dev.naturecodevoid.forge.hypixelutils.commands;

import dev.naturecodevoid.forge.hypixelutils.HypixelUtils;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

import java.util.Collections;
import java.util.List;

public class ConfigCommand extends CommandBase {
    @Override
    public String getCommandName() {
        return "hypixelutilsconfig";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/" + getCommandName();
    }

    @Override
    public int getRequiredPermissionLevel() {
        return -1;
    }

    @Override
    public List<String> getCommandAliases() {
        return Collections.singletonList("hutilsc");
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        HypixelUtils.gui = HypixelUtils.config.gui();
    }
}
