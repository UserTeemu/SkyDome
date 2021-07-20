package dev.userteemu.skydome.config;

import gg.essential.vigilance.data.PropertyData;
import gg.essential.vigilance.data.SortingBehavior;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class SkyDomeConfigOrder extends SortingBehavior {
    @NotNull
    @Override
    public Comparator<? super Map.Entry<String, ? extends List<PropertyData>>> getSubcategoryComparator() {
        return new SubcategoryComparator();
    }

    public static class SubcategoryComparator implements Comparator<Map.Entry<String, ? extends List<PropertyData>>> {
        @Override
        public int compare(Map.Entry<String, ? extends List<PropertyData>> a, Map.Entry<String, ? extends List<PropertyData>> b) {
            int index1 = SkyDomeConfig.subcategoryOrder.indexOf(a.getKey());
            if (index1 == -1) return 0;

            int index2 = SkyDomeConfig.subcategoryOrder.indexOf(b.getKey());
            if (index2 == -1) return 0;

            return index1 - index2;
        }
    }
}
