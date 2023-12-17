package com.ender09.block_coding.foundation.visual_scripting;

public abstract class NodeFunction {
    String name;
    String description;

    protected NodeFunctionParameter[] inputs;
    protected NodeFunctionParameter[] outputs;

    public NodeFunction(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }

    protected abstract void trigger();
}

