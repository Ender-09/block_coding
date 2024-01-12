package com.ender09.block_coding.util.client;

import com.ender09.block_coding.content.computer.ComputerScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;

public class ClientHooks {
    public static void openComputerScreen(BlockPos pPos) {
        Minecraft.getInstance().setScreen(new ComputerScreen(pPos));
    }
}
