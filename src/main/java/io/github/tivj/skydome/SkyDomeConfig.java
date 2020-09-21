package io.github.tivj.skydome;

import club.sk1er.vigilance.Vigilant;
import club.sk1er.vigilance.data.Property;
import club.sk1er.vigilance.data.PropertyType;

import java.io.File;

public class SkyDomeConfig extends Vigilant {
    public SkyDomeConfig() {
        super(new File("./config/skydome.toml"));
        initialize();
    }

    @Property(
            type = PropertyType.SLIDER,
            name = "Radius",
            category = "SkyDome",
            subcategory = "Settings",
            description = "Radius of the skydome (Still rendered under the world)",
            max = 1000
    )
    private int radius = 100;

    @Property(
            type = PropertyType.SLIDER,
            name = "Dome Slices",
            category = "SkyDome",
            subcategory = "Settings",
            description = "Controls the horizontal quality of the skydome",
            min = 4,
            max = 400
    )
    private int domeSlices = 32;

    @Property(
            type = PropertyType.SLIDER,
            name = "Dome Stacks",
            category = "SkyDome",
            subcategory = "Settings",
            description = "Controls the vertical quality of the skydome",
            min = 4,
            max = 400
    )
    private int domeStacks = 32;

    @Property(
            type = PropertyType.SWITCH,
            name = "Enabled",
            category = "SkyDome",
            subcategory = "Settings",
            description = "Toggle SkyDome behavior on or off"
    )
    private boolean enabled = true;

    public int getRadius() {
        return radius;
    }

    public int getDomeSlices() {
        return domeSlices;
    }

    public int getDomeStacks() {
        return domeStacks;
    }

    public boolean isEnabled() {
        return enabled && SkyDomeMain.INSTANCE.renderer != null;
    }
}