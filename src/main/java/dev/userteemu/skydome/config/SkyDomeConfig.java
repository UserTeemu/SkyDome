package dev.userteemu.skydome.config;

import dev.userteemu.skydome.SkyDomeMain;
import gg.essential.vigilance.Vigilant;
import gg.essential.vigilance.data.JVMAnnotationPropertyCollector;
import gg.essential.vigilance.data.Property;
import gg.essential.vigilance.data.PropertyType;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class SkyDomeConfig extends Vigilant {
    public SkyDomeConfig() {
        super(new File("./config/skydome.toml"), "Settings", new JVMAnnotationPropertyCollector(), new SkyDomeConfigOrder());
        initialize();
    }

    static List<String> subcategoryOrder = Arrays.asList("Quality", "Rotation", "Offset");

    @Property(
            type = PropertyType.SWITCH,
            name = "Enabled",
            category = "SkyDome",
            subcategory = "Quality",
            description = "Toggle SkyDome behavior on or off"
    )
    private boolean enabled = true;

    @Property(
            type = PropertyType.SLIDER,
            name = "Radius",
            category = "SkyDome",
            subcategory = "Quality",
            description = "Radius of the skydome (Still rendered under the world)",
            max = 1000
    )
    private int radius = 100;

    @Property(
            type = PropertyType.SLIDER,
            name = "Dome Slices",
            category = "SkyDome",
            subcategory = "Quality",
            description = "Controls the horizontal quality of the skydome",
            min = 4,
            max = 400
    )
    private int domeSlices = 32;

    @Property(
            type = PropertyType.SLIDER,
            name = "Dome Stacks",
            category = "SkyDome",
            subcategory = "Quality",
            description = "Controls the vertical quality of the skydome",
            min = 4,
            max = 400
    )
    private int domeStacks = 32;

    @Property(
            type = PropertyType.SLIDER,
            name = "Dome X Rotation",
            category = "SkyDome",
            subcategory = "Rotation",
            description = "Rotates the dome",
            max = 360
    )
    private int domeXRotation = 0;

    @Property(
            type = PropertyType.SLIDER,
            name = "Dome Y Rotation",
            category = "SkyDome",
            subcategory = "Rotation",
            description = "Rotates the dome",
            max = 360
    )
    private int domeYRotation = 0;

    @Property(
            type = PropertyType.SLIDER,
            name = "Dome Z Rotation",
            category = "SkyDome",
            subcategory = "Rotation",
            description = "Rotates the dome",
            max = 360
    )
    private int domeZRotation = 0;

    @Property(
            type = PropertyType.SLIDER,
            name = "Dome X Offset",
            category = "SkyDome",
            subcategory = "Offset",
            description = "Move the center of the skydome, not recommended",
            min = -500,
            max = 500
    )
    private int domeXOffset = 0;

    @Property(
            type = PropertyType.SLIDER,
            name = "Dome Y Offset",
            category = "SkyDome",
            subcategory = "Offset",
            description = "Move the center of the skydome, not recommended",
            min = -500,
            max = 500
    )
    private int domeYOffset = 0;

    @Property(
            type = PropertyType.SLIDER,
            name = "Dome Z Offset",
            category = "SkyDome",
            subcategory = "Offset",
            description = "Move the center of the skydome, not recommended",
            min = -500,
            max = 500
    )
    private int domeZOffset = 0;

    public boolean isEnabled() {
        return enabled && SkyDomeMain.INSTANCE.renderer != null;
    }

    public int getRadius() {
        return radius;
    }
    public int getDomeSlices() {
        return domeSlices;
    }
    public int getDomeStacks() {
        return domeStacks;
    }

    public int getDomeXRotation() {
        return domeXRotation;
    }
    public int getDomeYRotation() {
        return domeYRotation;
    }
    public int getDomeZRotation() {
        return domeZRotation;
    }

    public int getDomeXOffset() {
        return domeXOffset;
    }
    public int getDomeYOffset() {
        return domeYOffset;
    }
    public int getDomeZOffset() {
        return domeZOffset;
    }
}