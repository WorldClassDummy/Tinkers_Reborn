package tinkers.dummy.block;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import tinkers.dummy.TinkersReborn;
import tinkers.dummy.block.menu.CraftingStationMenu;
import tinkers.dummy.block.menu.TinkersStationMenu;

public interface ModMenuTypes {

    DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(Registries.MENU, TinkersReborn.MODID);

    DeferredHolder<MenuType<?>, MenuType<TinkersStationMenu>> TINKERS_STATION_MENU =
            MENU_TYPES.register("tinkers_station_menu",
                    () -> IMenuTypeExtension.create(TinkersStationMenu::new));

    DeferredHolder<MenuType<?>, MenuType<CraftingStationMenu>> PATTERN_STATION_MENU =
            MENU_TYPES.register("pattern_station_menu",
                    () -> IMenuTypeExtension.create(CraftingStationMenu::new));
}
