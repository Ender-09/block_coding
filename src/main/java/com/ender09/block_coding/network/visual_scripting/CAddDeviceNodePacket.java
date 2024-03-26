package com.ender09.block_coding.network.visual_scripting;

import com.ender09.block_coding.content.computer.ComputerBlockEntity;
import com.ender09.block_coding.foundation.visual_scripting.NodeGraph;
import com.ender09.block_coding.util.BlockUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.event.network.CustomPayloadEvent;

public class CAddDeviceNodePacket {
    private final BlockPos deviceBlockPos;
    private final BlockPos computerBlockPos;

    public CAddDeviceNodePacket(BlockPos deviceBlockPos, BlockPos computerBlockPos) {
        this.deviceBlockPos = deviceBlockPos;
        this.computerBlockPos = computerBlockPos;
    }

    public CAddDeviceNodePacket(FriendlyByteBuf buffer) {
        this(buffer.readBlockPos(), buffer.readBlockPos());
    }

    public void encode(FriendlyByteBuf buffer) {
        buffer.writeBlockPos(deviceBlockPos);
        buffer.writeBlockPos(computerBlockPos);
    }

    public void handle(CustomPayloadEvent.Context context) {
        Level level = Minecraft.getInstance().level;
        BlockEntity blockEntity = BlockUtils.getBlockEntityFromBlockPos(level, computerBlockPos);
        BlockEntity deviceBlockEntity = BlockUtils.getBlockEntityFromBlockPos(level, deviceBlockPos);

        if(blockEntity instanceof ComputerBlockEntity computerBlockEntity) {
            NodeGraph nodeGraph = computerBlockEntity.getNodeGraph();
            nodeGraph.addDeviceNode(deviceBlockEntity);
        }
    }
}
