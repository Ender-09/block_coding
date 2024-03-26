package com.ender09.block_coding.foundation.network;

import com.ender09.block_coding.content.computer.ComputerBlockEntity;
import com.ender09.block_coding.foundation.network.block_connectivity.Branch;
import com.ender09.block_coding.foundation.network.block_connectivity.Node;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

import java.util.*;

public class Network {
    public static Map<Double, Network> NETWORKS = new HashMap<>();
    public static Network getNetwork(double id) {
        return NETWORKS.get(id);
    }

    public double networkId;
    Level level;

    List<BlockPos> devices = new ArrayList<>();
    List<BlockPos> computers = new ArrayList<>();

    List<Branch> branches = new ArrayList<>();
    List<Node> nodes = new ArrayList<>();


    public Network(Level level) {
        this.level = level;
        this.networkId = 0;
        NETWORKS.put(networkId, this);
    }

    public double getId() {
        return networkId;
    }

    public List<BlockPos> getDevices() {
        return devices;
    }
    public List<BlockPos> getComputers() {
        return computers;
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
        removeDeviceFromComputers(device);


        if(device instanceof ComputerBlockEntity computerBlockEntity) {
            computers.remove(devicePos);
            computerBlockEntity.removeNetwork();
        }
    }
    void removeDeviceFromComputers(BlockEntity device) {
        for(BlockPos computerPos : computers) {
            if(!this.level.getBlockState(computerPos).hasBlockEntity()) continue;
            ComputerBlockEntity computerBlockEntity = (ComputerBlockEntity) this.level.getBlockEntity(computerPos);
            computerBlockEntity.removeDevice(device);
        }
    }
    private boolean deviceStillConnected(BlockPos devicePos) {
        return false;
    }
}
