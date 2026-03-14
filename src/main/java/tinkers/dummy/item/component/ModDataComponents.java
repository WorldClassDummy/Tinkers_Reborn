package tinkers.dummy.item.component;

import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import tinkers.dummy.TinkersReborn;
import tinkers.dummy.item.attribute.TinkersBindingComponent;
import tinkers.dummy.item.attribute.TinkersHeadComponent;
import tinkers.dummy.item.attribute.TinkersMaterialComponent;
import tinkers.dummy.item.attribute.TinkersRodComponent;

public class ModDataComponents {
    public static final DeferredRegister.DataComponents DATA_COMPONENTS = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, TinkersReborn.MODID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<TinkersHeadComponent>> HEAD_PART_COMPONENT =
            DATA_COMPONENTS.registerComponentType(
                    "head_part_component",
                    builder -> builder
                            .persistent(TinkersHeadComponent.CODEC)
                            .networkSynchronized(TinkersHeadComponent.STREAM_CODEC)
            );
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<TinkersBindingComponent>> BINDING_PART_COMPONENT =
            DATA_COMPONENTS.registerComponentType(
                    "binding_part_component",
                    builder -> builder
                            .persistent(TinkersBindingComponent.CODEC)
                            .networkSynchronized(TinkersBindingComponent.STREAM_CODEC)
            );
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<TinkersRodComponent>> ROD_PART_COMPONENT =
            DATA_COMPONENTS.registerComponentType(
                    "rod_part_component",
                    builder -> builder
                            .persistent(TinkersRodComponent.CODEC)
                            .networkSynchronized(TinkersRodComponent.STREAM_CODEC)
            );

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<TinkersMaterialComponent>> PART_MATERIAL_COMPONENT =
            DATA_COMPONENTS.registerComponentType(
                    "part_material_component",
                    builder -> builder
                            .persistent(TinkersMaterialComponent.CODEC)
                            .networkSynchronized(TinkersMaterialComponent.STREAM_CODEC)
            );
}
