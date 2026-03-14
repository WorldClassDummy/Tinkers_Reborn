package tinkers.dummy.item.attribute;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import tinkers.dummy.tag.ModItemTags;

public class ToolMaterials extends PartMaterial {
    public ToolMaterials(ResourceLocation id) {
        super(id);
    }

    public static final PartMaterial WOOD = register("wood");
    public static final PartMaterial STONE = register("stone");
    public static final PartMaterial IRON = register("iron");
    public static final PartMaterial GOLD = register("gold");
    public static final PartMaterial DIAMOND = register("diamond");
    public static final PartMaterial NETHERITE = register("netherite");

    public static PartMaterial getIngredientMaterial(ItemStack stack) {
        if (stack.is(ItemTags.PLANKS)) return WOOD;
        if (stack.is(ItemTags.STONE_TOOL_MATERIALS)) return STONE;
        if (stack.is(ModItemTags.IRON_TOOL_MATERIALS)) return IRON;
        if (stack.is(ModItemTags.GOLD_TOOL_MATERIALS)) return GOLD;
        if (stack.is(ModItemTags.DIAMOND_TOOL_MATERIALS)) return DIAMOND;
        if (stack.is(ModItemTags.NETHERITE_TOOL_MATERIALS)) return NETHERITE;
        else return STONE;
    }
}
