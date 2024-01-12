package com.ender09.block_coding.foundation.visual_scripting.node_parameters;

import java.util.ArrayList;
import java.util.List;

public class EventFlowOutputParameter extends NodeParameter {
    public static final String DEFAULT_LABEL = "Node Event Order";
    List<EventFlowInputParameter> childParameters = new ArrayList<>();

    public EventFlowOutputParameter(String label) {
        super(label);
    }
    public EventFlowOutputParameter() {
        super(DEFAULT_LABEL);
    }

    public void addChildParameter(EventFlowInputParameter childParameter) {
        childParameters.add(childParameter);
    }
    public void removeChildParameter(EventFlowInputParameter childParameter) {
        childParameters.remove(childParameter);
    }

    public void trigger() {
        for(EventFlowInputParameter childParameter: childParameters) {
            childParameter.trigger();
        }
    }
}
