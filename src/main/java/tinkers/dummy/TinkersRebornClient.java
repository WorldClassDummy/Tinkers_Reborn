package tinkers.dummy;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import tinkers.dummy.blocks.menu.PatternStationScreen;
import tinkers.dummy.client.gui.TinkersStationScreen;
/**
 * This class handles client-only setup.
 * The Bus.MOD parameter ensures this only listens to registration events.
 * The Dist.CLIENT parameter ensures it never runs on a dedicated server.
 */
@EventBusSubscriber(modid = TinkersReborn.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class TinkersRebornClient {

    @SubscribeEvent
    public static void registerScreens(RegisterMenuScreensEvent event) {
        // Explicitly link Menu A to Screen A
        event.register(TinkersReborn.TINKERSSTATION_MENU.get(), TinkersStationScreen::new);

        // Explicitly link Menu B to Screen B
        event.register(TinkersReborn.PATTERN_STATION_MENU.get(), PatternStationScreen::new);
    }


    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            // This tells the engine to respect the alpha channel (transparency) in your PNG
            ItemBlockRenderTypes.setRenderLayer(TinkersReborn.CRAFTING_STATION_BLOCK.get(), RenderType.cutout());
        });
    }

}