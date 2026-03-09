package tinkers.dummy.item;

import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import tinkers.dummy.item.custom.*;

import static tinkers.dummy.TinkersReborn.MODID;


public interface ModItems {
    DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);

    //Create each of the part variants
    DeferredItem<Item> PATTERN = ITEMS.register("pattern",
            () -> new PatternItem(new Item.Properties()));

    DeferredItem<Item> PICKAXE_HEAD = ITEMS.register("pickaxe_head",
            () -> new ToolHeadItem(new Item.Properties()));

    DeferredItem<Item> ROD = ITEMS.register("rod",
            () -> new RodItem(new Item.Properties()));

    DeferredItem<Item> BINDING = ITEMS.register("binding",
            () -> new BindingItem(new Item.Properties()));

    DeferredItem<Item> PICKAXE = ITEMS.register("pickaxe",
            () -> new TinkersPickaxeItem(new Item.Properties()));
}