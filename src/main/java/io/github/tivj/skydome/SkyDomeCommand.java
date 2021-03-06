package io.github.tivj.skydome;

import club.sk1er.mods.core.ModCore;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

public class SkyDomeCommand extends CommandBase {
    /**
     * Gets the name of the command
     */
    @Override
    public String getCommandName() {
        return "skydome";
    }

    /**
     * Gets the usage string for the command.
     *
     * @param sender user the command is being sent from
     */
    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/" + getCommandName();
    }

    /**
     * Callback when the command is invoked
     *
     * @param sender user the command is being sent from
     * @param args   arguments provided via command
     */
    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        ModCore.getInstance().getGuiHandler().open(SkyDomeMain.INSTANCE.config.gui());
    }

    @Override
    public int getRequiredPermissionLevel() {
        return -1;
    }
}