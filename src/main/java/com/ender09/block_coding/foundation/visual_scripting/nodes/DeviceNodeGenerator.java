package com.ender09.block_coding.foundation.visual_scripting.nodes;

import net.minecraft.world.level.block.entity.BlockEntity;

import java.util.UUID;

public abstract class DeviceNodeGenerator {
    protected abstract String getName();
    protected abstract String getDescription();
    protected abstract Class<? extends BlockEntity> getBlockEntityClass();

    public Node generateNode(BlockEntity be) {
        String uuid = UUID.randomUUID().toString();
        return generateNode(be, uuid);
    }

    public abstract Node generateNode(BlockEntity be, String uuid);
}
