package com.ender09.block_coding.foundation;

import net.minecraft.core.BlockPos;

import java.util.List;

public class Network {
    public static List<Network> NETWORKS;

    public double networkId;
    public List<BlockPos> devices;
    public List<BlockPos> handlers;
}
