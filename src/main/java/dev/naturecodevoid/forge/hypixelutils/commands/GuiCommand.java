package dev.naturecodevoid.forge.hypixelutils.commands;

import dev.naturecodevoid.forge.hypixelutils.HypixelUtils;
import dev.naturecodevoid.forge.hypixelutils.gui.EditorGui;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

import java.util.Collections;
import java.util.List;

public class GuiCommand extends CommandBase {
    @Override
    public String getCommandName() {
        return "hypixelutilsgui";
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
        return Collections.singletonList("hutilsg");
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        HypixelUtils.gui = new EditorGui();
    }
}
