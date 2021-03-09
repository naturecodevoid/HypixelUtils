package dev.naturecodevoid.hypixelutils.commands;

import dev.naturecodevoid.hypixelutils.HypixelUtils;
import dev.naturecodevoid.hypixelutils.gui.GeneralGui;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

import java.util.Collections;
import java.util.List;

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
    public List<String> getCommandAliases() {
        return Collections.singletonList("hutils");
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        HypixelUtils.gui = new GeneralGui();
    }
}
