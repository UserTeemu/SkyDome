package io.github.tivj.skydome;

import gg.essential.api.EssentialAPI;
import gg.essential.api.commands.Command;
import gg.essential.api.commands.DefaultHandler;

public class SkyDomeCommand extends Command {
    public SkyDomeCommand() {
        super("skydome");
    }

    @DefaultHandler
    public void handle() {
        EssentialAPI.getGuiUtil().openScreen(SkyDomeMain.INSTANCE.config.gui());
    }
}