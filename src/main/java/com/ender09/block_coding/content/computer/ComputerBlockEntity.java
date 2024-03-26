package com.ender09.block_coding.content.computer;

import com.ender09.block_coding.foundation.network.INetworkDeviceBE;
import com.ender09.block_coding.foundation.network.Network;
import com.ender09.block_coding.foundation.visual_scripting.NodeCanvas;
import com.ender09.block_coding.registry.ModBlockEntityTypes;
import com.ender09.block_coding.foundation.visual_scripting.NodeGraph;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class ComputerBlockEntity extends BlockEntity implements INetworkDeviceBE {
    public NodeGraph nodeGraph;
    public NodeCanvas nodeCanvas;
    public Network network;

    public ComputerBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntityTypes.COMPUTER.get(), pPos, pBlockState);
        nodeGraph = new NodeGraph(this);
        nodeCanvas = new NodeCanvas(nodeGraph);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);

        pTag.put("Node Canvas", nodeCanvas.serializeNBT());
        pTag.put("Node Graph" , nodeGraph.serializeNBT());
        pTag.putDouble("NetworkID", network.getId());
    }
    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);

        this.nodeCanvas.deserializeNBT(pTag.getCompound("Node Canvas"));
        this.nodeGraph.deserializeNBT(pTag.getCompound("Node Graph"));
        this.network = Network.getNetwork(pTag.getDouble("NetworkID"));
    }
    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag tag = super.getUpdateTag();
        saveAdditional(tag);
        return tag;
    }

    public void setNetwork(Network network) {
        //Remove old network devices
        if(this.network != null) {
            for (BlockPos devicePos : network.getDevices()) {
                BlockEntity deviceBE = this.level.getBlockEntity(devicePos);
                removeDevice(deviceBE);
            }
        }
        this.network = network;

        //Add new network devices
        for(BlockPos devicePos : network.getDevices()) {
            BlockEntity deviceBE = this.level.getBlockEntity(devicePos);
            addDevice(deviceBE);
        }
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
        if(device instanceof ComputerBlockEntity) return;
        nodeGraph.removeDeviceNode(device);
    }

    public void setNodeGraph(NodeGraph nodeGraph) {
        this.nodeGraph = nodeGraph;
        nodeCanvas = new NodeCanvas(nodeGraph);
    }
    public NodeGraph getNodeGraph() {
        return nodeGraph;
    }

    public void tick() {
        nodeGraph.tick();
    }
}
