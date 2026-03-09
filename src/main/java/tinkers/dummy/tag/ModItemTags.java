package tinkers.dummy.tag;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import tinkers.dummy.TinkersReborn;

public interface ModItemTags {
    TagKey<Item> IRON_TOOL_MATERIALS = register("iron_tool_materials");
    TagKey<Item> GOLD_TOOL_MATERIALS = register("gold_tool_materials");
    TagKey<Item> DIAMOND_TOOL_MATERIALS = register("diamond_tool_materials");
    TagKey<Item> NETHERITE_TOOL_MATERIALS = register("netherite_tool_materials");

    private static TagKey<Item> register(String name) {
        return TagKey.create(Registries.ITEM, TinkersReborn.id(name));
    }
}
