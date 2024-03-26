package com.ender09.block_coding.foundation.visual_scripting.nodes;

import com.ender09.block_coding.foundation.visual_scripting.nodes.node_parameters.NodeParameter;
import net.minecraft.world.level.block.entity.BlockEntity;

public abstract class NodeFunction {
    protected Node node;
    public boolean isActive;
    String description;

    public NodeFunction(Node node, String description) {
        this.node = node;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void trigger() {
        if(isActive)
            onTrigger();
    }
    public abstract void onTrigger();

    public void remove() {
    }
}

