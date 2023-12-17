package com.ender09.block_coding.content.network_transceiver;

import com.ender09.block_coding.foundation.registry.ModBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class NetworkTransceiverBlockEntity extends BlockEntity {
    public NetworkTransceiverBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntityTypes.NETWORK_TRANSCEIVER.get(), pPos, pBlockState);
    }
}
