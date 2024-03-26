package com.ender09.block_coding.foundation.visual_scripting.nodes.node_parameters;

import com.ender09.block_coding.foundation.visual_scripting.nodes.Node;

public class InfoInputParameter<T> extends NodeParameter {
    T value;
    InfoOutputParameter<T> outputParameter;
    Node node;

    public InfoInputParameter(String id, String label) {
        super(id, label);
    }
    public InfoInputParameter(String id, String label, Node node) {
        super(id, label);
        this.node = node;
    }

    public void setValue(T value) {
        this.value = value;

        if(node != null)
            node.trigger();
    }
    public Object getValue() {
        return outputParameter == null? value : outputParameter.getValue();
    }

    public void setOutputParameter(InfoOutputParameter<T> outputParameter) {
        if(value == null || outputParameter.value.getClass() == value.getClass())
            this.outputParameter = outputParameter;
    }
}
