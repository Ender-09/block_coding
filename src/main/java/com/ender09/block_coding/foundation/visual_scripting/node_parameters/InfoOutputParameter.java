package com.ender09.block_coding.foundation.visual_scripting.node_parameters;

import com.ender09.block_coding.foundation.visual_scripting.NodeFunction;

public class InfoOutputParameter<T> extends NodeParameter {
    T value;
    NodeFunction function;

    public InfoOutputParameter(String label, NodeFunction function) {
        super(label);
        this.function = function;
    }

    public void setValue(T value) {
        this.value = value;
    }
    public T getValue() {
        if(value == null)
            function.trigger();

        return value;
    }
}
