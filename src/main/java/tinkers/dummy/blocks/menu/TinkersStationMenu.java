package tinkers.dummy.blocks.menu;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.items.SlotItemHandler;
import tinkers.dummy.TinkersReborn;
import tinkers.dummy.blocks.entity.TinkersStationBlockEntity;

public class TinkersStationMenu extends AbstractContainerMenu {
    public final TinkersStationBlockEntity blockEntity;
    private final Level level;

    private static final int TE_INVENTORY_SLOT_COUNT = 7;

    public TinkersStationMenu(int containerId, Inventory inv, FriendlyByteBuf extraData) {
        this(containerId, inv, inv.player.level().getBlockEntity(extraData.readBlockPos()));
    }

    public TinkersStationMenu(int containerId, Inventory inv, BlockEntity entity) {
        super(TinkersReborn.TINKERSSTATION_MENU.get(), containerId);
        this.blockEntity = (TinkersStationBlockEntity) entity;
        this.level = inv.player.level();



        this.addSlot(new SlotItemHandler(blockEntity.inventory, 0, 80, 20));  // Top
        this.addSlot(new SlotItemHandler(blockEntity.inventory, 1, 53, 47));  // Left
        this.addSlot(new SlotItemHandler(blockEntity.inventory, 2, 80, 47));  // Center
        this.addSlot(new SlotItemHandler(blockEntity.inventory, 3, 107, 47));  // Right
        this.addSlot(new SlotItemHandler(blockEntity.inventory, 4, 62, 74));  // Bottom Left
        this.addSlot(new SlotItemHandler(blockEntity.inventory, 5, 98, 74));  // Bottom Right

        // Output Slot (Moved up from 35 -> 31)
        this.addSlot(new ModResultSlot(blockEntity.inventory, 6, 145, 47));

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
        } else if (index < 36 + TE_INVENTORY_SLOT_COUNT) {
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
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()), player, TinkersReborn.TINKERSSTATION_BLOCK.get());
    }

    private void layoutPlayerInventory(Inventory inventory) {
        // Shifted to start at Y=84 to match your image's bottom section
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