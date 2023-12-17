package com.ender09.block_coding.content.computer;

import com.ender09.block_coding.foundation.Network;
import com.ender09.block_coding.foundation.registry.ModBlockEntityTypes;
import com.ender09.block_coding.foundation.visual_scripting.NodeGraph;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class ComputerBlockEntity extends BlockEntity {
    public NodeGraph nodeGraph;
    public Network network;

    public ComputerBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntityTypes.COMPUTER.get(), pPos, pBlockState);
    }

    public void tick() {
        nodeGraph.tick();
    }
}
