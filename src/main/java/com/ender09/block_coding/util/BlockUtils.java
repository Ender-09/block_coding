package com.ender09.block_coding.util;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class BlockUtils {
    public static BlockEntity getBlockEntityFromBlockPos(Level level, BlockPos blockPos) {
        if(level == null) return null;

        BlockState blockState =  level.getBlockState(blockPos);
        if(!blockState.hasBlockEntity()) return null;
        return level.getBlockEntity(blockPos);
    }


}
