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

public class CRemoveNodePacket {
    private final String nodeUuid;
    private final BlockPos blockPos;

    public CRemoveNodePacket(String nodeUuid, BlockPos blockPos) {
        this.nodeUuid = nodeUuid;
        this.blockPos = blockPos;
    }

    public CRemoveNodePacket(FriendlyByteBuf buffer) {
        this(buffer.readUtf(), buffer.readBlockPos());
    }

    public void encode(FriendlyByteBuf buffer) {
        buffer.writeUtf(nodeUuid);
        buffer.writeBlockPos(blockPos);
    }

    public void handle(CustomPayloadEvent.Context context) {
        Level level = Minecraft.getInstance().level;
        BlockEntity blockEntity = BlockUtils.getBlockEntityFromBlockPos(level, blockPos);

        if(blockEntity instanceof ComputerBlockEntity computerBlockEntity) {
            NodeGraph nodeGraph = computerBlockEntity.getNodeGraph();
            nodeGraph.removeNode(nodeUuid);
        }
    }
}
