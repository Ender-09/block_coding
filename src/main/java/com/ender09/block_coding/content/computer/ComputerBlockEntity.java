package com.ender09.block_coding.content.computer;

import com.ender09.block_coding.foundation.network.INetworkDeviceBE;
import com.ender09.block_coding.foundation.network.Network;
import com.ender09.block_coding.registry.ModBlockEntityTypes;
import com.ender09.block_coding.foundation.visual_scripting.NodeGraph;
import com.ender09.block_coding.util.EventListener;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.lang.reflect.Method;
import java.util.Map;

public class ComputerBlockEntity extends BlockEntity implements INetworkDeviceBE {
    public NodeGraph nodeGraph = new NodeGraph();
    public Network network;

    public ComputerBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntityTypes.COMPUTER.get(), pPos, pBlockState);
    }

    public void setNetwork(Network network) {
        this.network = network;
    }
    public void removeNetwork() {
        if(network == null) return;
        this.network = null;
    }
    public Network getNetwork() {
        return network;
    }

    public void addDevice(BlockEntity device) {
        if(device instanceof ComputerBlockEntity) return;
        nodeGraph.addDeviceNode(device);
    }
    public void removeDevice(BlockEntity device) {
        nodeGraph.removeDeviceNode(device);
    }

    public void setNodeGraph(NodeGraph nodeGraph) {
        this.nodeGraph = nodeGraph;
    }
    public NodeGraph getNodeGraph() {
        return nodeGraph;
    }

    public void tick() {
        nodeGraph.tick();
    }
}
