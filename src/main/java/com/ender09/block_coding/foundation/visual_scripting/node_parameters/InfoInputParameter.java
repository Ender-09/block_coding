package com.ender09.block_coding.foundation.visual_scripting.node_parameters;

import com.ender09.block_coding.foundation.visual_scripting.NodeFunction;

public class InfoInputParameter<T> extends NodeParameter {
    T value;
    InfoOutputParameter<T> outputParameter;
    NodeFunction function;

    public InfoInputParameter(String label) {
        super(label);
    }
    public InfoInputParameter(String label, NodeFunction function) {
        super(label);
        this.function = function;
    }

    public void setValue(T value) {
        this.value = value;

        if(function != null)
            function.trigger();
    }
    public Object getValue() {
        return outputParameter == null? value : outputParameter.getValue();
    }

    public void setOutputParameter(InfoOutputParameter<T> outputParameter) {
        if(value == null || outputParameter.value.getClass() == value.getClass())
            this.outputParameter = outputParameter;
    }
}
