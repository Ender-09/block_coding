package com.ender09.block_coding.content.visual_scripting.nodes.node_functions;

import com.ender09.block_coding.foundation.visual_scripting.NodeFunction;
import com.ender09.block_coding.foundation.visual_scripting.node_parameters.EventFlowInputParameter;
import com.ender09.block_coding.foundation.visual_scripting.node_parameters.EventFlowOutputParameter;
import com.ender09.block_coding.foundation.visual_scripting.node_parameters.InfoInputParameter;
import com.ender09.block_coding.foundation.visual_scripting.node_parameters.NodeParameter;

public class IfFunction extends NodeFunction {
    public IfFunction(String description) {
        super(description);
        inputs = new NodeParameter[]{
                new EventFlowInputParameter(this),
                new InfoInputParameter<Boolean>("Value")
        };

        outputs = new NodeParameter[]{
                new EventFlowOutputParameter("True"),
                new EventFlowOutputParameter("False")
        };
    }

    @Override
    public void onTrigger() {
        InfoInputParameter<Boolean> condition = (InfoInputParameter<Boolean>) inputs[1];
        EventFlowOutputParameter output = (Boolean) condition.getValue()? (EventFlowOutputParameter) outputs[0] : (EventFlowOutputParameter) outputs[1];
        output.trigger();
    }
}
