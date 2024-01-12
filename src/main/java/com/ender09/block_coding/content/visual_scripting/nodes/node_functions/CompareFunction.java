package com.ender09.block_coding.content.visual_scripting.nodes.node_functions;

import com.ender09.block_coding.foundation.visual_scripting.NodeFunction;
import com.ender09.block_coding.foundation.visual_scripting.node_parameters.*;

public class CompareFunction<T> extends NodeFunction {
    public static final String DESCRIPTION = "Returns a boolean on whether two values are the same or not.";

    public CompareFunction() {
        super(DESCRIPTION);
        inputs = new NodeParameter[]{
                new EventFlowInputParameter(this),
                new InfoInputParameter<T>("A"),
                new InfoInputParameter<T>("B")
        };
        outputs = new NodeParameter[]{
                new EventFlowOutputParameter(),
                new InfoOutputParameter<Boolean>("Result", this)
        };
    }

    @Override
    public void onTrigger() {
        InfoInputParameter<T> valueA = (InfoInputParameter<T>) inputs[1];
        InfoInputParameter<T> valueB = (InfoInputParameter<T>) inputs[2];

        EventFlowOutputParameter eventOutput = (EventFlowOutputParameter) outputs[0];
        InfoOutputParameter<Boolean> result = (InfoOutputParameter<Boolean>) outputs[1];

        result.setValue(valueA.getValue() == valueB.getValue());
        eventOutput.trigger();
    }
}
