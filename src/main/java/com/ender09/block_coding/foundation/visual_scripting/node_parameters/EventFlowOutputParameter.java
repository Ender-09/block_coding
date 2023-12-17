package com.ender09.block_coding.foundation.visual_scripting.node_parameters;

public class EventFlowOutputParameter extends NodeParameter {
    EventFlowOutputParameter childParameter;

    public EventFlowOutputParameter(String label) {
        super(label);
    }

    public void setChildParameter(EventFlowOutputParameter childParameter) {
        this.childParameter = childParameter;
    }
    public void trigger() {
        childParameter.trigger();
    }
}
