package com.ender09.block_coding.foundation.visual_scripting;

import com.ender09.block_coding.foundation.visual_scripting.node_parameters.NodeParameter;

public abstract class NodeFunction {
    String description;

    protected NodeParameter[] outputs;

    public NodeFunction(String name, String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public abstract void trigger();
}

