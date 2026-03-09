package tinkers.dummy.block;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import tinkers.dummy.block.custom.CraftingStationBlock;
import tinkers.dummy.block.custom.TinkersStationBlock;
import tinkers.dummy.item.ModItems;

import java.util.function.Supplier;

import static tinkers.dummy.TinkersReborn.MODID;

public interface ModBlocks {
    DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);

    // Custom Blocks
    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    // Tinkers Station
    DeferredBlock<Block> TINKERS_STATION_BLOCK = registerBlock("tinkers_station_block",
            () -> new TinkersStationBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE)
                    .strength(2.5f)
                    .noOcclusion()
            ));

    // Crafting Station
    DeferredBlock<Block> CRAFTING_STATION_BLOCK = registerBlock("crafting_station_block",
            () -> new CraftingStationBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .strength(2.0f)
                    .noOcclusion() // <--- THIS IS KEY
                    .isValidSpawn((state, level, pos, type) -> false) // Stops mobs spawning on top
            ));
}
