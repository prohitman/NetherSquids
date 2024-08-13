package com.prohitman.nethersquids.common.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.HitResult;

public class FiredObsidianBlock extends Block {
    public static final IntegerProperty AGE = BlockStateProperties.AGE_3;

    public FiredObsidianBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, Integer.valueOf(0)));

    }

    /**
     * Performs a random tick on a block.
     */
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        this.tick(pState, pLevel, pPos, pRandom);
    }

    public void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        if ((pRandom.nextInt(3) == 0 || this.fewerNeigboursThan(pLevel, pPos, 4)) && pLevel.getMaxLocalRawBrightness(pPos) > 11 - pState.getValue(AGE) - pState.getLightBlock(pLevel, pPos) && this.slightlyMelt(pState, pLevel, pPos)) {
            BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

            for(Direction direction : Direction.values()) {
                blockpos$mutableblockpos.setWithOffset(pPos, direction);
                BlockState blockstate = pLevel.getBlockState(blockpos$mutableblockpos);
                if (blockstate.is(this) && !this.slightlyMelt(blockstate, pLevel, blockpos$mutableblockpos)) {
                    pLevel.scheduleTick(blockpos$mutableblockpos, this, Mth.nextInt(pRandom, 20, 40));
                }
            }

        } else {
            pLevel.scheduleTick(pPos, this, Mth.nextInt(pRandom, 20, 40));
        }
    }

    private boolean slightlyMelt(BlockState pState, Level pLevel, BlockPos pPos) {
        int i = pState.getValue(AGE);
        if (i < 3) {
            pLevel.setBlock(pPos, pState.setValue(AGE, Integer.valueOf(i + 1)), 2);
            return false;
        } else {
            this.melt(pState, pLevel, pPos);
            return true;
        }
    }

    public void neighborChanged(BlockState pState, Level pLevel, BlockPos pPos, Block pBlock, BlockPos pFromPos, boolean pIsMoving) {
        if (pBlock.defaultBlockState().is(this) && this.fewerNeigboursThan(pLevel, pPos, 2)) {
            this.melt(pState, pLevel, pPos);
        }

        super.neighborChanged(pState, pLevel, pPos, pBlock, pFromPos, pIsMoving);
    }

    private boolean fewerNeigboursThan(BlockGetter pLevel, BlockPos pPos, int pNeighborsRequired) {
        int i = 0;
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

        for(Direction direction : Direction.values()) {
            blockpos$mutableblockpos.setWithOffset(pPos, direction);
            if (pLevel.getBlockState(blockpos$mutableblockpos).is(this)) {
                ++i;
                if (i >= pNeighborsRequired) {
                    return false;
                }
            }
        }

        return true;
    }

    protected void melt(BlockState pState, Level pLevel, BlockPos pPos) {
        pLevel.setBlockAndUpdate(pPos, meltsInto());
        pLevel.neighborChanged(pPos, meltsInto().getBlock(), pPos);
    }

    public static BlockState meltsInto() {
        return Blocks.LAVA.defaultBlockState();
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(AGE);
    }

    @Override
    public ItemStack getCloneItemStack(BlockState state, HitResult target, LevelReader level, BlockPos pos, Player player) {
        return ItemStack.EMPTY;
    }
}
