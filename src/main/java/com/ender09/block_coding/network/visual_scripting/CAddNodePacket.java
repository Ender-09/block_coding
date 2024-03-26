package com.ender09.block_coding.network.visual_scripting;

import com.ender09.block_coding.BlockCoding;
import com.ender09.block_coding.content.computer.ComputerBlockEntity;
import com.ender09.block_coding.foundation.visual_scripting.NodeGraph;
import com.ender09.block_coding.foundation.visual_scripting.nodes.Node;
import com.ender09.block_coding.foundation.visual_scripting.nodes.NodeGenerator;
import com.ender09.block_coding.util.BlockUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.event.network.CustomPayloadEvent;

public class CAddNodePacket {
    private final String nodeUuid;
    private final String nodeId;
    private final BlockPos blockPos;

    public CAddNodePacket(String nodeUuid, String nodeId, BlockPos blockPos) {
        this.nodeUuid = nodeUuid;
        this.nodeId = nodeId;
        this.blockPos = blockPos;
    }

    public CAddNodePacket(FriendlyByteBuf buffer) {
        this(buffer.readUtf(), buffer.readUtf(), buffer.readBlockPos());
    }

    public void encode(FriendlyByteBuf buffer) {
        buffer.writeUtf(nodeUuid);
        buffer.writeUtf(nodeId);
        buffer.writeBlockPos(blockPos);
    }

    public void handle(CustomPayloadEvent.Context context) {
        Level level = Minecraft.getInstance().level;
        BlockEntity blockEntity = BlockUtils.getBlockEntityFromBlockPos(level, blockPos);

        if(blockEntity instanceof ComputerBlockEntity computerBlockEntity) {
            NodeGraph nodeGraph = computerBlockEntity.getNodeGraph();
            NodeGenerator nodeGenerator = BlockCoding.NODE_GENERATOR_REGISTRY.get().getValue(ResourceLocation.tryParse(nodeId));
            Node node = nodeGenerator.generateNode(nodeUuid);
            nodeGraph.addNode(node);
        }
    }
}
