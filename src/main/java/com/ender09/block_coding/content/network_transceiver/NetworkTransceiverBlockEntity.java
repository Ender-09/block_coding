package com.ender09.block_coding.content.network_transceiver;

import com.ender09.block_coding.foundation.network.INetworkDeviceBE;
import com.ender09.block_coding.foundation.network.Network;
import com.ender09.block_coding.registry.ModBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import static com.ender09.block_coding.content.network_transceiver.NetworkTransceiverBlock.FACING;

public class NetworkTransceiverBlockEntity extends BlockEntity {
    Network network;
    BlockPos neighbourPos;
    BlockEntity deviceOldBlockEntity;

    public NetworkTransceiverBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntityTypes.NETWORK_TRANSCEIVER.get(), pPos, pBlockState);
        neighbourPos = pPos.relative(pBlockState.getValue(FACING).getOpposite());
    }

    public void setNetwork(Network network) {
        //this.network = network;
        this.network = Network.NETWORKS.isEmpty() ? new Network(this.level) : Network.NETWORKS.get(0);    //TEMP
        Network.NETWORKS.put((double) 0, this.network);  //TEMP
        addDeviceToNetwork();
    }
    public Network getNetwork() {
        return network;
    }

    void addDeviceToNetwork() {
        if(this.network == null) return;

        BlockState neighbourState = this.level.getBlockState(neighbourPos);
        if(!neighbourState.hasBlockEntity()) return;

        BlockEntity neighbourBlockEntity = this.level.getBlockEntity(neighbourPos);
        if(neighbourBlockEntity instanceof INetworkDeviceBE) {
            deviceOldBlockEntity = neighbourBlockEntity;
            network.addDevice(neighbourBlockEntity);
        }
    }
    void removeDeviceFromNetwork() {
        if(this.network == null) return;
        if(deviceOldBlockEntity == null) return;
        network.removeDevice(deviceOldBlockEntity);
        deviceOldBlockEntity = null;
    }
}
