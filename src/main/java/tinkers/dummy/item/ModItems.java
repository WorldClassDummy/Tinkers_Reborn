package tinkers.dummy.item;

import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import static tinkers.dummy.TinkersReborn.MODID;


public interface ModItems {
    DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);

    //Create each of the part variants
    DeferredItem<Item> PATTERN = ITEMS.register("pattern",
            () -> new Item(new Item.Properties()));

    DeferredItem<Item> STONE_PICKAXE_HEAD = ITEMS.register("stone_pickaxe_head",
            () -> new Item(new Item.Properties()));

    DeferredItem<Item> STONE_STICK = ITEMS.register("stone_stick",
            () -> new Item(new Item.Properties()));

    DeferredItem<Item> STONE_BINDING = ITEMS.register("stone_binding",
            () -> new Item(new Item.Properties()));

}
