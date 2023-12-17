package com.ender09.block_coding.foundation.visual_scripting.node_parameters;

import java.lang.reflect.Type;

public class InfoInputParameter<T> extends NodeParameter {
    T value;
    InfoOutputParameter<T> outputParameter;

    public InfoInputParameter(String label, Type valueType) {
        super(label);
    }

    public void setValue(T value) {
        this.value = value;
    }
    public Object getValue() {
        return outputParameter == null? value : outputParameter.getValue();
    }

    public void setOutputParameter(InfoOutputParameter<T> outputParameter) {
        if(outputParameter.value.getClass() == value.getClass())
            this.outputParameter = outputParameter;
    }
}
