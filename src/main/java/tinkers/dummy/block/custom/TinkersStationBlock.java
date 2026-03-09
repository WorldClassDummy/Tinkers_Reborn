package tinkers.dummy.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleMenuProvider;
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
import tinkers.dummy.block.ModBlockEntities;
import tinkers.dummy.block.entity.TinkersStationBlockEntity;
import tinkers.dummy.block.menu.TinkersStationMenu;

public class TinkersStationBlock extends BaseEntityBlock {
    // 1. The Codec (Handles data saving/loading)
    public static final MapCodec<TinkersStationBlock> CODEC = simpleCodec(TinkersStationBlock::new);

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    // 2. Constructor
    public TinkersStationBlock(Properties properties) {
        super(properties);
    }

    // 3. Visibility (Crucial for BaseEntityBlocks)
    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    // 4. Create the "Brain" (Block Entity)
    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new TinkersStationBlockEntity(pos, state);
    }

    // 5. Open the GUI on Right-Click
    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (!level.isClientSide()) {
            BlockEntity entity = level.getBlockEntity(pos);
            if (entity instanceof TinkersStationBlockEntity stationEntity) {
                player.openMenu(new SimpleMenuProvider((id, inv, p) ->
                        new TinkersStationMenu(id, inv, stationEntity), Component.literal("Tinkers Station")), pos);
            }
        }
        return InteractionResult.sidedSuccess(level.isClientSide());
    }

    // Inside TinkersStationBlock.java
    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        if (level.isClientSide()) return null;

        return createTickerHelper(type, ModBlockEntities.TINKERS_STATION_BE.get(),
                (pLevel, pPos, pState, pBlockEntity) -> TinkersStationBlockEntity.tick(pLevel, pPos, pState, pBlockEntity));
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


}