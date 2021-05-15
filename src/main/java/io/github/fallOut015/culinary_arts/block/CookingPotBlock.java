package io.github.fallOut015.culinary_arts.block;

import io.github.fallOut015.culinary_arts.tileentity.CookingPotTileEntity;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.ContainerBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class CookingPotBlock extends ContainerBlock {
    public CookingPotBlock(Properties properties) {
        super(properties);
    }

    @Override
    public BlockRenderType getRenderShape(BlockState p_149645_1_) {
        return BlockRenderType.MODEL;
    }
    @Nullable
    @Override
    public TileEntity newBlockEntity(IBlockReader p_196283_1_) {
        return new CookingPotTileEntity();
    }
    @Override
    public ActionResultType use(BlockState state, World level, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult rayTrace) {
        if (level.isClientSide) {
            return ActionResultType.SUCCESS;
        } else {
            this.openContainer(level, pos, player);
            return ActionResultType.CONSUME;
        }
    }

    protected void openContainer(World level, BlockPos pos, PlayerEntity player) {
        TileEntity tileentity = level.getBlockEntity(pos);
        if (tileentity instanceof CookingPotTileEntity) {
            player.openMenu((INamedContainerProvider)tileentity);
            //player.awardStat(StatTypesCulinaryArts.INTERACT_WITH_COOKING_POT.get().getRegistryName());
        }
    }
}