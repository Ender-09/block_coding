package com.ender09.block_coding.foundation.visual_scripting.nodes.node_parameters;

import com.ender09.block_coding.foundation.visual_scripting.nodes.Node;

public class EventFlowInputParameter extends NodeParameter {
    Node node;

    public EventFlowInputParameter(String id, String label, Node node) {
        super(id, label);
        this.node = node;
    }
    public EventFlowInputParameter(String id, Node node) {
        super(id, "");
        this.node = node;
    }

    public void trigger() {
        node.trigger();
    }
}
