package com.ender09.block_coding.foundation.visual_scripting;

import com.ender09.block_coding.foundation.visual_scripting.node_parameters.NodeParameter;
import com.ender09.block_coding.foundation.visual_scripting.nodes.Node;
import net.minecraft.world.level.block.entity.BlockEntity;

public abstract class NodeFunction {
    public boolean isActive;
    String description;

    protected NodeParameter[] inputs;
    protected NodeParameter[] outputs;

    public NodeFunction(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public NodeParameter[] getInputs() {
        return inputs;
    }
    public NodeParameter[] getOutputs() {
        return outputs;
    }

    public void trigger() {
        if(isActive)
            onTrigger();
    }
    public abstract void onTrigger();

    public void remove() {
        inputs = null;
        outputs = null;
    }

    public void setBlockEntity(BlockEntity newBlockEntity) {
    }
}

