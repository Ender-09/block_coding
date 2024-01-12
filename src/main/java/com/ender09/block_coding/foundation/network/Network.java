package com.ender09.block_coding.foundation.network;

import com.ender09.block_coding.content.computer.ComputerBlockEntity;
import com.ender09.block_coding.foundation.network.block_connectivity.Branch;
import com.ender09.block_coding.foundation.network.block_connectivity.Node;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

import java.util.ArrayList;
import java.util.List;

public class Network {
    public static List<Network> NETWORKS = new ArrayList<>();

    public double networkId;
    Level level;

    List<BlockPos> devices = new ArrayList<>();
    List<BlockPos> computers = new ArrayList<>();

    List<Branch> branches = new ArrayList<>();
    List<Node> nodes = new ArrayList<>();


    public Network(Level level) {
        this.level = level;
        NETWORKS.add(this);
    }
    public void delete() {
        NETWORKS.remove(this);
    }

    public void addDevice(BlockEntity device) {
        BlockPos devicePos = device.getBlockPos();
        if(devices.contains(devicePos)) return;
        devices.add(devicePos);
        addDeviceToComputers(device);

        if(device instanceof ComputerBlockEntity computerBlockEntity) {
            computers.add(devicePos);
            computerBlockEntity.setNetwork(this);
        }
    }
    void addDeviceToComputers(BlockEntity device) {
        for(BlockPos computerPos : computers) {
            ComputerBlockEntity computerBlockEntity = (ComputerBlockEntity) this.level.getBlockEntity(computerPos);
            computerBlockEntity.addDevice(device);
        }
    }

    public void removeDevice(BlockEntity device) {
        BlockPos devicePos = device.getBlockPos();
        if(deviceStillConnected(devicePos)) return;
        if(!devices.contains(devicePos)) return;
        devices.remove(devicePos);
        removeDeviceToComputers(device);


        if(device instanceof ComputerBlockEntity computerBlockEntity) {
            computers.remove(devicePos);
            computerBlockEntity.removeNetwork();
        }
    }
    void removeDeviceToComputers(BlockEntity device) {
        for(BlockPos computerPos : computers) {
            ComputerBlockEntity computerBlockEntity = (ComputerBlockEntity) this.level.getBlockEntity(computerPos);
            computerBlockEntity.removeDevice(device);
        }
    }
    private boolean deviceStillConnected(BlockPos devicePos) {
        return false;
    }
}
