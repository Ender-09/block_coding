package com.ender09.block_coding.foundation.visual_scripting.node_parameters;

import com.ender09.block_coding.foundation.visual_scripting.NodeFunction;

public class EventFlowInputParameter extends NodeParameter {
    NodeFunction function;

    public EventFlowInputParameter(String label, NodeFunction function) {
        super(label);
        this.function = function;
    }

    public void trigger() {
        function.trigger();
    }
}
