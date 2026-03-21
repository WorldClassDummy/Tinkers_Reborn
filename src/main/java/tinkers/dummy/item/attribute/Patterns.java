package tinkers.dummy.item.attribute;

import net.minecraft.resources.ResourceLocation;

public class Patterns extends Pattern {
    public Patterns(ResourceLocation id) {
        super(id);
    }

    public static final Pattern PICKAXE_HEAD = register("pickaxe_head");
    public static final Pattern SHOVEL_HEAD = register("shovel_head");
    public static final Pattern AXE_HEAD = register("axe_head");
    public static final Pattern HOE_HEAD = register("hoe_head");
    public static final Pattern SMALL_BLADE = register("small_blade");
    public static final Pattern LARGE_BLADE = register("large_blade");
    public static final Pattern PLATE = register("plate");
    public static final Pattern ROD = register("rod");
    public static final Pattern BINDING = register("binding");
    public static final Pattern TOUGH_ROD = register("tough_binding");
    public static final Pattern TOUGH_BINDING = register("tough_binding");
}
