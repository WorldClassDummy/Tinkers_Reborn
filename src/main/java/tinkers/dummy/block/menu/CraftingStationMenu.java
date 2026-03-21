package tinkers.dummy.block.menu;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.neoforged.neoforge.items.SlotItemHandler;
import tinkers.dummy.TinkersReborn;
import tinkers.dummy.block.ModBlocks;
import tinkers.dummy.block.ModMenuTypes;
import tinkers.dummy.block.entity.CraftingStationBlockEntity;

public class CraftingStationMenu extends AbstractContainerMenu {
    public final CraftingStationBlockEntity blockEntity;
    private final Level level;

    private static final int TE_INVENTORY_SLOT_COUNT = 7;

    public CraftingStationMenu(int containerId, Inventory inv, FriendlyByteBuf extraData) {
        this(containerId, inv, inv.player.level().getBlockEntity(extraData.readBlockPos()));
    }

    public CraftingStationMenu(int containerId, Inventory inv, BlockEntity entity) {
        super(ModMenuTypes.PATTERN_STATION_MENU.get(), containerId);

        // Safety check
        if (entity instanceof CraftingStationBlockEntity stationEntity) {
            this.blockEntity = stationEntity;
        } else {
            throw new IllegalStateException("Block Entity is not a PatternStationBlockEntity!");
        }

        this.level = inv.player.level();
        IItemHandler dataInventory = stationEntity.inventory;

        // --- SLOTS (Re-using your perfect coordinates) ---
        this.addSlot(new SlotItemHandler(dataInventory, 0, 41, 7));  // Pattern
        this.addSlot(new SlotItemHandler(dataInventory, 1, 15, 25));  // Material
        this.addSlot(new SlotItemHandler(dataInventory, 2, 41, 33));
        this.addSlot(new SlotItemHandler(dataInventory, 3, 68, 24));
        this.addSlot(new SlotItemHandler(dataInventory, 4, 23, 57));
        this.addSlot(new SlotItemHandler(dataInventory, 5, 59, 57));

        // Output Slot (Using our custom Result Slot to prevent putting items in)
        this.addSlot(new ModResultSlot(dataInventory, 6, 127, 32));

        layoutPlayerInventory(inv);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        Slot sourceSlot = slots.get(index);
        if (sourceSlot == null || !sourceSlot.hasItem()) return ItemStack.EMPTY;

        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyStack = sourceStack.copy();

        if (index < TE_INVENTORY_SLOT_COUNT) {
            if (!moveItemStackTo(sourceStack, TE_INVENTORY_SLOT_COUNT, 36 + TE_INVENTORY_SLOT_COUNT, true)) {
                return ItemStack.EMPTY;
            }
        } else {
            if (!moveItemStackTo(sourceStack, 0, TE_INVENTORY_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }
        }

        if (sourceStack.isEmpty()) sourceSlot.set(ItemStack.EMPTY);
        else sourceSlot.setChanged();

        return copyStack;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()), player, ModBlocks.CRAFTING_STATION_BLOCK.get());
    }

    private void layoutPlayerInventory(Inventory inventory) {
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        for (int k = 0; k < 9; ++k) {
            this.addSlot(new Slot(inventory, k, 8 + k * 18, 142));
        }
    }
}