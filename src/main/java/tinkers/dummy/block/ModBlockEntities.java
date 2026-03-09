package tinkers.dummy.block;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import tinkers.dummy.TinkersReborn;
import tinkers.dummy.block.entity.CraftingStationBlockEntity;
import tinkers.dummy.block.entity.TinkersStationBlockEntity;

import static tinkers.dummy.block.ModBlocks.CRAFTING_STATION_BLOCK;

public interface ModBlockEntities {

    DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, TinkersReborn.MODID);

    DeferredHolder<BlockEntityType<?>, BlockEntityType<TinkersStationBlockEntity>> TINKERS_STATION_BE = BLOCK_ENTITIES.register(
            "tinkers_station_block_entity",
            () -> BlockEntityType.Builder.of(
                    TinkersStationBlockEntity::new,
                    ModBlocks.TINKERS_STATION_BLOCK.get())
                    .build(null));

    DeferredHolder<BlockEntityType<?>, BlockEntityType<CraftingStationBlockEntity>> CRAFTING_STATION_BE =
            BLOCK_ENTITIES.register("crafting_station_block_entity",
                    () -> BlockEntityType.Builder.of(CraftingStationBlockEntity::new, CRAFTING_STATION_BLOCK.get()).build(null));
}
