package io.github.fallOut015.culinary_arts.block;

import io.github.fallOut015.culinary_arts.item.ItemsCulinaryArts;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IGrowable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ForgeHooks;

import javax.annotation.Nullable;
import java.util.Random;

public class TomatoTopBlock extends Block implements IGrowable {
    private static final IntegerProperty AGE_3 = BlockStateProperties.AGE_3;

    protected TomatoTopBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE_3, Integer.valueOf(0)));
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(AGE_3);
    }
    @Override
    public boolean isValidBonemealTarget(IBlockReader level, BlockPos pos, BlockState state, boolean p_176473_4_) {
        return state.getValue(AGE_3).intValue() != 3 || this.canGrowInto(level.getBlockState(pos.above()));
    }
    @Override
    public boolean isBonemealSuccess(World level, Random random, BlockPos pos, BlockState state) {
        return true;
    }
    @Override
    public void performBonemeal(ServerWorld level, Random random, BlockPos pos, BlockState state) {
        if(state.getValue(AGE_3).intValue() == 3) {
            level.setBlock(pos, this.getBodyBlock().defaultBlockState().setValue(AGE_3, state.getValue(AGE_3)), 2);
            level.setBlock(pos.above(), state.setValue(AGE_3, 0), 2);
        } else {
            level.setBlock(pos, state.setValue(AGE_3, state.getValue(AGE_3) + 1), 2);
        }
    }
    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext p_196258_1_) {
        BlockState blockstate = p_196258_1_.getLevel().getBlockState(p_196258_1_.getClickedPos().relative(Direction.UP));
        return !blockstate.is(this.getHeadBlock()) && !blockstate.is(this.getBodyBlock()) ? this.getStateForPlacement(p_196258_1_.getLevel()) : this.getBodyBlock().defaultBlockState();
    }
    @Override
    public boolean canSurvive(BlockState p_196260_1_, IWorldReader p_196260_2_, BlockPos p_196260_3_) {
        BlockPos blockpos = p_196260_3_.relative(Direction.UP.getOpposite());
        BlockState blockstate = p_196260_2_.getBlockState(blockpos);
        Block block = blockstate.getBlock();
        if (!this.canAttachToBlock(block)) {
            return false;
        } else {
            return block == this.getHeadBlock() || block == this.getBodyBlock() || blockstate.isFaceSturdy(p_196260_2_, blockpos, Direction.UP);
        }
    }
    @Override
    public void tick(BlockState state, ServerWorld level, BlockPos pos, Random random) {
        if (!state.canSurvive(level, pos)) {
            level.destroyBlock(pos, true);
        }
    }
    @Override
    public void randomTick(BlockState state, ServerWorld level, BlockPos pos, Random random) {
        if(state.getValue(AGE_3).intValue() == 3) {
            if (ForgeHooks.onCropsGrowPre(level, pos.relative(Direction.UP), level.getBlockState(pos.relative(Direction.UP)),random.nextDouble() < 0.1d)) {
                BlockPos blockpos = pos.relative(Direction.UP);
                if (this.canGrowInto(level.getBlockState(blockpos))) {
                    level.setBlockAndUpdate(blockpos, state.setValue(AGE_3, 0));
                    ForgeHooks.onCropsGrowPost(level, blockpos, level.getBlockState(blockpos));
                }
            }
        } else {
            if(random.nextInt(10) == 0) {
                level.setBlockAndUpdate(pos, state.setValue(AGE_3, state.getValue(AGE_3) + 1));
            }
        }
    }
    @Override
    public BlockState updateShape(BlockState state1, Direction direction, BlockState state2, IWorld level, BlockPos pos1, BlockPos pos2) {
        if (direction == Direction.UP.getOpposite() && !state1.canSurvive(level, pos1)) {
            level.getBlockTicks().scheduleTick(pos1, this, 1);
        }

        if (direction != Direction.UP || !state2.is(this) && !state2.is(this.getBodyBlock())) {
            return super.updateShape(state1, direction, state2, level, pos1, pos2);
        } else {
            return this.getBodyBlock().defaultBlockState().setValue(AGE_3, state1.getValue(AGE_3));
        }
    }
    @Override
    public void entityInside(BlockState state, World level, BlockPos pos, Entity entity) {
        if (entity instanceof LivingEntity && entity.getType() != EntityType.FOX && entity.getType() != EntityType.BEE) {
            entity.makeStuckInBlock(state, new Vector3d((double)0.8F, 0.75D, (double)0.8F));
        }
    }
    @Override
    public ActionResultType use(BlockState state, World level, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult rayTraceResult) {
        int i = state.getValue(AGE_3);
        if (player.getItemInHand(hand).getItem() == Items.BONE_MEAL) {
            return ActionResultType.PASS;
        } else if (i > 0) {
            popResource(level, pos, new ItemStack(ItemsCulinaryArts.TOMATO.get()));
            level.playSound((PlayerEntity)null, pos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundCategory.BLOCKS, 1.0F, 0.8F + level.random.nextFloat() * 0.4F);
            level.setBlock(pos, state.setValue(AGE_3, state.getValue(AGE_3).intValue() - 1), 2);
            return ActionResultType.sidedSuccess(level.isClientSide);
        } else {
            return super.use(state, level, pos, player, hand, rayTraceResult);
        }
    }

    protected TomatoTopBlock getHeadBlock() {
        return (TomatoTopBlock) BlocksCulinaryArts.TOMATO.get();
    }
    protected TomatoBlock getBodyBlock() {
        return (TomatoBlock) BlocksCulinaryArts.TOMATO_PLANT.get();
    }
    public BlockState getStateForPlacement(IWorld level) {
        return this.defaultBlockState();
    }
    protected boolean canAttachToBlock(Block block) {
        return true;
    }
    protected int getBlocksToGrowWhenBonemealed(Random random) {
        return 1;
    }
    protected boolean canGrowInto(BlockState state) {
        return true;
    }
}