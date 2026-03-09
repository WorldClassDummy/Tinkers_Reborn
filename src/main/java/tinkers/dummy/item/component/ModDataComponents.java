package tinkers.dummy.item.component;

import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import tinkers.dummy.TinkersReborn;
import tinkers.dummy.item.attribute.TinkersPartComponent;
import tinkers.dummy.item.attribute.TinkersToolComponent;

public class ModDataComponents {
    public static final DeferredRegister.DataComponents DATA_COMPONENTS = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, TinkersReborn.MODID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<TinkersToolComponent>> TOOL_PART_COMPONENT =
            DATA_COMPONENTS.registerComponentType(
                    "tool_part_component",
                    builder -> builder
                            .persistent(TinkersToolComponent.CODEC)
                            .networkSynchronized(TinkersToolComponent.STREAM_CODEC)
            );

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<TinkersPartComponent>> PART_MATERIAL_COMPONENT =
            DATA_COMPONENTS.registerComponentType(
                    "part_material_component",
                    builder -> builder
                            .persistent(TinkersPartComponent.CODEC)
                            .networkSynchronized(TinkersPartComponent.STREAM_CODEC)
            );
}
