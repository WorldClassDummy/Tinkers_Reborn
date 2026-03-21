package tinkers.dummy.item.attribute;

import net.minecraft.resources.ResourceLocation;

public class Patterns extends Pattern {
    public Patterns(ResourceLocation id, int materialAmount) {
        super(id, materialAmount);
    }

    public static final Pattern PICKAXE_HEAD = register("pickaxe_head", 2);
    public static final Pattern SHOVEL_HEAD = register("shovel_head", 1);
    public static final Pattern AXE_HEAD = register("axe_head", 2);
    public static final Pattern HOE_HEAD = register("hoe_head", 2);
    public static final Pattern SMALL_BLADE = register("small_blade", 2);
    public static final Pattern LARGE_BLADE = register("large_blade", 3);
    public static final Pattern PLATE = register("plate", 3);
    public static final Pattern ROD = register("rod", 1);
    public static final Pattern BINDING = register("binding", 1);
    public static final Pattern TOUGH_ROD = register("tough_binding", 2);
    public static final Pattern TOUGH_BINDING = register("tough_binding", 2);
}
