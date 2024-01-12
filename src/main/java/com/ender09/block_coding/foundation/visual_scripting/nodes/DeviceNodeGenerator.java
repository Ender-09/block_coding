package com.ender09.block_coding.foundation.visual_scripting.nodes;

import net.minecraft.world.level.block.entity.BlockEntity;

public abstract class DeviceNodeGenerator {
    protected abstract String getName();
    protected abstract String getDescription();
    protected abstract Class<? extends BlockEntity> getBlockEntityClass();

    public static Node createNode(BlockEntity be) {
        return null;
    }
}
