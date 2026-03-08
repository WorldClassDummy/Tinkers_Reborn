package tinkers.dummy;

import com.mojang.logging.LogUtils;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.slf4j.Logger;
import tinkers.dummy.blocks.entity.CraftingStationBlockEntity;
import tinkers.dummy.blocks.entity.TinkersStationBlockEntity;
import tinkers.dummy.blocks.menu.TinkersStationMenu;
import tinkers.dummy.blocks.menu.CraftingStationMenu;

import static tinkers.dummy.blocks.CustomBlocks.*;
import static tinkers.dummy.items.BlockItems.*;
import static tinkers.dummy.items.Patterns.*;

@Mod(TinkersReborn.MODID)
public class TinkersReborn {
    public static final String MODID = "tinkersreborn";
    public static final Logger LOGGER = LogUtils.getLogger();


    // Setup and Create the Creative Mod Tabs
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);








    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<CraftingStationBlockEntity>> CRAFTING_STATION_BE =
            BLOCK_ENTITIES.register("crafting_station_block_entity",
                    () -> BlockEntityType.Builder.of(CraftingStationBlockEntity::new, CRAFTING_STATION_BLOCK.get()).build(null));


    public static final DeferredHolder<MenuType<?>, MenuType<CraftingStationMenu>> PATTERN_STATION_MENU =
            MENU_TYPES.register("pattern_station_menu",
                    () -> IMenuTypeExtension.create(CraftingStationMenu::new));

    // Creative Tab
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> tinkersrebornTAB = CREATIVE_MODE_TABS.register("tinkersreborntab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.tinkersreborn"))
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> TINKERSSTATION_BLOCK_ITEM.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(TINKERSSTATION_BLOCK_ITEM.get());
                output.accept(PATTERN.get());
                output.accept(CRAFTING_STATION_ITEM.get());
            }).build());


    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> tinkersrebornpartsTAB = CREATIVE_MODE_TABS.register("tinkersrebornpartstab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.tinkersrebornparts"))
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> STONE_BINDING.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(STONE_STICK.get());
                output.accept(STONE_BINDING.get());
                output.accept(STONE_PICKAXE_HEAD.get());
            }).build());




    public TinkersReborn(IEventBus modEventBus, ModContainer modContainer) {
        // Register everything to the Mod Event Bus
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);
        BLOCK_ENTITIES.register(modEventBus);
        MENU_TYPES.register(modEventBus);

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::addCreative);

        // Gameplay events (like ServerStarting) go on the NeoForge Bus
        NeoForge.EVENT_BUS.register(this);

        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        LOGGER.info("HELLO FROM COMMON SETUP");
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(TINKERSSTATION_BLOCK_ITEM);
        }
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("HELLO from server starting");
    }
}