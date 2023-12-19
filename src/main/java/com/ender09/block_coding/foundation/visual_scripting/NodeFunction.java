package com.ender09.block_coding.foundation.visual_scripting;

import com.ender09.block_coding.foundation.visual_scripting.node_parameters.NodeParameter;

public abstract class NodeFunction {
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

    public abstract void trigger();
}

