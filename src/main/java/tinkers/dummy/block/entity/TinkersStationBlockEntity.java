package tinkers.dummy.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tinkers.dummy.block.ModBlockEntities;
import tinkers.dummy.block.menu.TinkersStationMenu;

public class TinkersStationBlockEntity extends BlockEntity implements MenuProvider {

    public final ItemStackHandler inventory = new ItemStackHandler(7) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };

    private ItemStack lastSnapshot = ItemStack.EMPTY;

    public TinkersStationBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.TINKERS_STATION_BE.get(), pos, state);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, TinkersStationBlockEntity pEntity) {
        if (level.isClientSide) return;

        ItemStack currentOutput = pEntity.inventory.getStackInSlot(6);
        ItemStack result = getResult(pEntity);

        if (currentOutput.isEmpty() && !pEntity.lastSnapshot.isEmpty()) {
            pEntity.inventory.extractItem(0, 1, false);
            pEntity.inventory.extractItem(1, 1, false);
            pEntity.inventory.extractItem(2, 1, false);
            pEntity.setChanged();
        }

        if (!result.isEmpty()) {
            if (pEntity.inventory.getStackInSlot(6).isEmpty()) {
                pEntity.inventory.setStackInSlot(6, result);
            }
        } else {
            if (!pEntity.inventory.getStackInSlot(6).isEmpty()) {
                pEntity.inventory.setStackInSlot(6, ItemStack.EMPTY);
            }
        }

        pEntity.lastSnapshot = pEntity.inventory.getStackInSlot(6).copy();
    }

    private static ItemStack getResult(TinkersStationBlockEntity entity) {
        ItemStack head = entity.inventory.getStackInSlot(0);
        ItemStack binding = entity.inventory.getStackInSlot(1);
        ItemStack stick = entity.inventory.getStackInSlot(2);

        if (!head.isEmpty() &&
                !binding.isEmpty() &&
                !stick.isEmpty()) {
            return new ItemStack(Items.STONE_PICKAXE);
        }

        return ItemStack.EMPTY;
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.tinkersreborn.tinkersstation_block");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return new TinkersStationMenu(containerId, playerInventory, this);
    }

    @Override
    protected void saveAdditional(CompoundTag tag, @NotNull HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.put("inventory", inventory.serializeNBT(registries));
    }

    @Override
    protected void loadAdditional(CompoundTag tag, @NotNull HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        if (tag.contains("inventory")) {
            inventory.deserializeNBT(registries, tag.getCompound("inventory"));
        }
    }
}