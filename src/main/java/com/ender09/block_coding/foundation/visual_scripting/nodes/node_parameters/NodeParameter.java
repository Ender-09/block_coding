package com.ender09.block_coding.foundation.visual_scripting.nodes.node_parameters;

public class NodeParameter {
    String parameterId;
    String label;

    public NodeParameter(String parameterId, String label) {
        this.parameterId = parameterId;
        this.label = label;
    }

    public String getId() {
        return parameterId;
    }
    public String getLabel() {
        return label;
    }
}
