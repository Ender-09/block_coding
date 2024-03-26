package com.ender09.block_coding.foundation.visual_scripting.nodes.node_parameters;

import java.util.ArrayList;
import java.util.List;

public class EventFlowOutputParameter extends NodeParameter {
    List<EventFlowInputParameter> childParameters = new ArrayList<>();

    public EventFlowOutputParameter(String id, String label) {
        super(id, label);
    }
    public EventFlowOutputParameter(String id) {
        super(id, "");
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
