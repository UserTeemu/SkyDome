package io.github.tivj.skydome;

import io.github.tivj.skydome.modcore.ModCoreInstaller;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = SkyDomeMain.MODID, name = SkyDomeMain.NAME, version = SkyDomeMain.VERSION)
public class SkyDomeMain {
    public static final String MODID = "skydome";
    public static final String NAME = "SkyDome";
    public static final String VERSION = "@MOD_VERSION@";
    public static final Logger LOGGER = LogManager.getLogger("Skydome");

    public SkydomeRenderer renderer;
    public SkyDomeConfig config;

    @Mod.Instance(MODID)
    public static SkyDomeMain INSTANCE;

    public SkyDomeMain() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        ModCoreInstaller.initializeModCore(Minecraft.getMinecraft().mcDataDir);

        (config = new SkyDomeConfig()).preload();
        renderer = new SkydomeRenderer(config);

        ClientCommandHandler.instance.registerCommand(new SkyDomeCommand());
        MinecraftForge.EVENT_BUS.register(this);
    }
}