package com.ender09.block_coding.foundation.visual_scripting.nodes.node_parameters;

import com.ender09.block_coding.foundation.visual_scripting.nodes.Node;
import com.ender09.block_coding.foundation.visual_scripting.nodes.NodeFunction;

public class InfoOutputParameter<T> extends NodeParameter {
    T value;
    Node node;

    public InfoOutputParameter(String id, String label, Node node) {
        super(id, label);
        this.node = node;
    }

    public void setValue(T value) {
        this.value = value;
    }
    public T getValue() {
        if(value == null)
            node.trigger();

        return value;
    }
}
