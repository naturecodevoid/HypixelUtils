package dev.naturecodevoid.forge.hypixelutils.commands;

import dev.naturecodevoid.forge.hypixelutils.HypixelUtils;
import dev.naturecodevoid.forge.hypixelutils.gui.GeneralGUI;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

public class GeneralCommand extends CommandBase {
    @Override
    public String getCommandName() {
        return "hypixelutils";
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
    public void processCommand(ICommandSender sender, String[] args) {
        HypixelUtils.gui = new GeneralGUI();
    }
}
