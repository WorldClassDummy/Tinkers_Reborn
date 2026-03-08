package tinkers.dummy.items;

import net.minecraft.world.item.BlockItem;
import net.neoforged.neoforge.registries.DeferredItem;

import static tinkers.dummy.blocks.CustomBlocks.*;
import static tinkers.dummy.items.Patterns.*;


public class BlockItems {



    public static final DeferredItem<BlockItem> TINKERSSTATION_BLOCK_ITEM = ITEMS.registerSimpleBlockItem("tinkersstation_block", TINKERSSTATION_BLOCK);


    public static final DeferredItem<BlockItem> CRAFTING_STATION_ITEM = ITEMS.registerSimpleBlockItem("crafting_station_block", CRAFTING_STATION_BLOCK);



}
