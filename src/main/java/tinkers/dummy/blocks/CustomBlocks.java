package tinkers.dummy.blocks;

import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import tinkers.dummy.blocks.entity.TinkersStationBlockEntity;
import tinkers.dummy.blocks.menu.TinkersStationMenu;

import static tinkers.dummy.TinkersReborn.MODID;

public class CustomBlocks {




    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);


    // Custom Blocks






    // Tinkers Staiton

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<TinkersStationBlockEntity>> TINKERSSTATION_BE =
            BLOCK_ENTITIES.register("tinkersstation_block_entity",
                    () -> BlockEntityType.Builder.of(TinkersStationBlockEntity::new, TINKERSSTATION_BLOCK.get()).build(null));


    public static final DeferredBlock<Block> TINKERSSTATION_BLOCK = BLOCKS.register("tinkersstation_block",
            () -> new TinkersStationBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE)
                    .strength(2.5f)
                    .noOcclusion()
            ));



    // Crafting Staiton


     public static final DeferredHolder<MenuType<?>, MenuType<TinkersStationMenu>> TINKERSSTATION_MENU =
                MENU_TYPES.register("tinkersstation_menu",
                        () -> IMenuTypeExtension.create(TinkersStationMenu::new));
    public static final DeferredBlock<Block> CRAFTING_STATION_BLOCK = BLOCKS.register("crafting_station_block",
            () -> new CraftingStationBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .strength(2.0f)
                    .noOcclusion() // <--- THIS IS KEY
                    .isValidSpawn((state, level, pos, type) -> false) // Stops mobs spawning on top
            ));





}
