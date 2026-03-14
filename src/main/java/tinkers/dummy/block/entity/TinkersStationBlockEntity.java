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
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tinkers.dummy.block.ModBlockEntities;
import tinkers.dummy.block.menu.TinkersStationMenu;
import tinkers.dummy.item.ModItems;
import tinkers.dummy.item.attribute.*;
import tinkers.dummy.item.component.ModDataComponents;
import tinkers.dummy.item.custom.BindingItem;
import tinkers.dummy.item.custom.RodItem;
import tinkers.dummy.item.custom.ToolHeadItem;

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

    public static void tick(Level level, BlockPos pos, BlockState state, TinkersStationBlockEntity entity) {
        if (level.isClientSide) return;

        ItemStack currentOutput = entity.inventory.getStackInSlot(6);
        ItemStack result = getResult(entity);

        if (currentOutput.isEmpty() && !entity.lastSnapshot.isEmpty()) {
            entity.inventory.extractItem(0, 1, false);
            entity.inventory.extractItem(1, 1, false);
            entity.inventory.extractItem(2, 1, false);
            entity.setChanged();
        }

        if (!result.isEmpty()) {
            if (currentOutput.isEmpty()) {
                entity.inventory.setStackInSlot(6, result);
            }
        } else {
            if (!currentOutput.isEmpty()) {
                entity.inventory.setStackInSlot(6, ItemStack.EMPTY);
            }
        }

        entity.lastSnapshot = entity.inventory.getStackInSlot(6).copy();
    }

    private static ItemStack getResult(TinkersStationBlockEntity entity) {
        ItemStack head = entity.inventory.getStackInSlot(0);
        ItemStack rod = entity.inventory.getStackInSlot(1);
        ItemStack binding = entity.inventory.getStackInSlot(2);

        PartMaterial headMaterial = ToolMaterials.getIngredientMaterial(head);
        PartMaterial rodMaterial = ToolMaterials.getIngredientMaterial(rod);
        PartMaterial bindingMaterial = ToolMaterials.getIngredientMaterial(binding);


        if ((head.getItem() instanceof ToolHeadItem) &&
                binding.getItem() instanceof BindingItem &&
                rod.getItem() instanceof RodItem) {
            ItemStack stack = new ItemStack(ModItems.PICKAXE.get());
            stack.set(ModDataComponents.HEAD_PART_COMPONENT.value(), new TinkersHeadComponent(ToolParts.PICKAXE_HEAD, headMaterial));
            stack.set(ModDataComponents.BINDING_PART_COMPONENT.value(), new TinkersBindingComponent(ToolParts.BINDING, bindingMaterial));
            stack.set(ModDataComponents.ROD_PART_COMPONENT.value(), new TinkersRodComponent(ToolParts.ROD, rodMaterial));
            return stack;
        }

        return ItemStack.EMPTY;
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.tinkersreborn.tinkers_station_block");
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