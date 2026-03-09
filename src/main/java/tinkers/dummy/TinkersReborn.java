package tinkers.dummy;

import com.mojang.logging.LogUtils;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.slf4j.Logger;

import static tinkers.dummy.block.ModBlocks.*;
import static tinkers.dummy.item.ModItems.*;
import static tinkers.dummy.block.ModMenuTypes.*;
import static tinkers.dummy.block.ModBlockEntities.*;
import static tinkers.dummy.item.component.ModDataComponents.*;

@Mod(TinkersReborn.MODID)
public class TinkersReborn {
    public static final String MODID = "tinkersreborn";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }


    // Setup and Create the Creative Mod Tabs
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    // Creative Tab
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> TINKERS_TAB = CREATIVE_MODE_TABS.register("tinkers_reborn", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.tinkersreborn"))
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(TINKERS_STATION_BLOCK::toStack)
            .displayItems((parameters, output) -> {
                output.accept(TINKERS_STATION_BLOCK);
                output.accept(PATTERN);
                output.accept(CRAFTING_STATION_BLOCK);
            }).build());


    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> TINKERS_PARTS_TAB = CREATIVE_MODE_TABS.register("tinkers_parts", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.tinkersrebornparts"))
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(BINDING::toStack)
            .displayItems((parameters, output) -> {
                output.accept(ROD);
                output.accept(BINDING);
                output.accept(PICKAXE_HEAD);
            }).build());




    public TinkersReborn(IEventBus modEventBus, ModContainer modContainer) {
        // Register everything to the Mod Event Bus
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);
        BLOCK_ENTITIES.register(modEventBus);
        MENU_TYPES.register(modEventBus);
        DATA_COMPONENTS.register(modEventBus);

        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    @SubscribeEvent
    static void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(TINKERS_STATION_BLOCK);
        }
    }
}