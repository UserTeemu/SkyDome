package io.github.tivj.skydome.asm.tweaker;

import io.github.tivj.skydome.modcore.ModCoreInstaller;
import net.minecraft.launchwrapper.Launch;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;

import java.util.Map;

@IFMLLoadingPlugin.SortingIndex(1005)
public class Tweaker implements IFMLLoadingPlugin {

    @Override
    public String[] getASMTransformerClass() {
        //INIT MODCORE
        int initialize = ModCoreInstaller.initialize(Launch.minecraftHome, "1.8.9");

        if (ModCoreInstaller.isErrored() || initialize != 0 && initialize != -1) {
            // Technically wouldn't happen in simulated installed but is important for actual impl
            System.out.println("Failed to load Sk1er Modcore - " + initialize + " - " + ModCoreInstaller.getError());
        }

        if (ModCoreInstaller.isIsRunningModCore())  // If true the classes are loaded
            return new String[]{"club.sk1er.mods.core.forge.ClassTransformer", ClassTransformer.class.getName()};
        else return new String[]{ClassTransformer.class.getName()};
    }

    @Override
    public String getModContainerClass() {
        return null;
    }

    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {

    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }
}