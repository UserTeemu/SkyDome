package io.github.tivj.skydome;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.util.glu.Sphere;

public class SkydomeRenderer {
    private static final ResourceLocation sky = new ResourceLocation("skydome", "sky.png");
    private final SkyDomeConfig config;
    private final Sphere s;

    public SkydomeRenderer(SkyDomeConfig config) {
        this.config = config;
        s = new Sphere();
        s.setTextureFlag(true);
    }

    private void renderSphere() {
        GlStateManager.pushMatrix();
        GlStateManager.disableLighting();
        GlStateManager.rotate(90, 1, 0, 0);
        GlStateManager.translate(0, 0, 2);
        s.draw(config.getRadius(), config.getDomeSlices(), config.getDomeStacks());
        GlStateManager.enableLighting();
        GlStateManager.popMatrix();
    }

    public void renderSky() {
        GlStateManager.disableCull();
        GlStateManager.depthMask(false);
        GlStateManager.disableLighting();
        Minecraft.getMinecraft().getTextureManager().bindTexture(sky);
        renderSphere();
        GlStateManager.enableLighting();
        GlStateManager.depthMask(true);
        GlStateManager.enableCull();
    }
}
