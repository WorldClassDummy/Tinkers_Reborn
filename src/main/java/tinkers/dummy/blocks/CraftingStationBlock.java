package tinkers.dummy.blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;
import tinkers.dummy.TinkersReborn;
import tinkers.dummy.blocks.entity.CraftingStationBlockEntity;

public class CraftingStationBlock extends BaseEntityBlock {
    // 1. Define the Codec
    public static final MapCodec<CraftingStationBlock> CODEC = simpleCodec(CraftingStationBlock::new);

    // 2. Override the codec method to return it
    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    public CraftingStationBlock(Properties properties) {
        super(properties);
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new CraftingStationBlockEntity(pos, state);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (!level.isClientSide) {
            BlockEntity entity = level.getBlockEntity(pos);
            if (entity instanceof CraftingStationBlockEntity patternEntity) {
                player.openMenu(patternEntity, pos);
            } else {
                throw new IllegalStateException("Our Container provider is missing!");
            }
        }
        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        if (level.isClientSide()) return null;

        return createTickerHelper(type, TinkersReborn.CRAFTING_STATION_BE.get(),
                (pLevel, pPos, pState, pBlockEntity) -> CraftingStationBlockEntity.tick(pLevel, pPos, pState, pBlockEntity));
    }


    @Override
    public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
        return true; // Allows light to pass through the transparent parts
    }

    @Override
    public float getShadeBrightness(BlockState state, BlockGetter world, BlockPos pos) {
        return 1.0f; // Prevents the inside of the block from being pitch black
    }

    @Override
    public boolean isOcclusionShapeFullBlock(BlockState state, BlockGetter world, BlockPos pos) {
        return false; // Tells neighboring blocks NOT to hide their faces
    }



    @Override
    public boolean useShapeForLightOcclusion(BlockState state) {
        return true;
    }


    protected boolean isViewBlocking(BlockState state, BlockGetter level, BlockPos pos) {
        return false;
    }
}