package com.ender09.block_coding.foundation.visual_scripting;

import java.util.ArrayList;
import java.util.List;

public abstract class NodeFunctionParameter {
    protected String label;
    protected Object value;
    List<NodeFunctionParameter> childNodeParameters = new ArrayList<>();

    public NodeFunctionParameter(String label, Object defaultValue) {
        this.label = label;
        this.value = defaultValue;
    }

    public String getLabel() {
        return label;
    }

    public void setValue(Object value) {
        this.value = value;
        for (NodeFunctionParameter childNodeParameter : childNodeParameters) {
            childNodeParameter.setValue(value);
        }
    }
    public Object getValue() {
        return value;
    }

    public void addChildNodeParameter(NodeFunctionParameter childNodeParameter) {
        childNodeParameters.add(childNodeParameter);
    }
    public void removeChildNodeParameter(NodeFunctionParameter childNodeParameter) {
        childNodeParameters.remove(childNodeParameter);
    }
}
