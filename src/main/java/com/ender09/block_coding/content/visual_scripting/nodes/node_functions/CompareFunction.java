package com.ender09.block_coding.content.visual_scripting.nodes.node_functions;

import com.ender09.block_coding.foundation.visual_scripting.nodes.Node;
import com.ender09.block_coding.foundation.visual_scripting.nodes.NodeFunction;
import com.ender09.block_coding.foundation.visual_scripting.nodes.node_parameters.*;

public class CompareFunction<T> extends NodeFunction {
    public static final String DESCRIPTION = "Returns a boolean on whether two values are the same or not.";

    public CompareFunction(Node node) {
        super(node, DESCRIPTION);

        node.addInput(new EventFlowInputParameter("event_flow_input", node));
        node.addInput(new InfoInputParameter<T>("value_A_input", "A"));
        node.addInput(new InfoInputParameter<T>("value_B_input", "B"));


        node.addOutput(new EventFlowOutputParameter("event_flow_output"));
        node.addOutput(new InfoOutputParameter<Boolean>("result_output", "Result", node));
    }

    @Override
    public void onTrigger() {
        InfoInputParameter<T> valueA = (InfoInputParameter<T>) node.getInput("value_A_input");
        InfoInputParameter<T> valueB = (InfoInputParameter<T>) node.getInput("value_B_input");

        EventFlowOutputParameter eventOutput = (EventFlowOutputParameter) node.getOutput("event_flow_output");
        InfoOutputParameter<Boolean> result = (InfoOutputParameter<Boolean>) node.getOutput("result_output");

        result.setValue(valueA.getValue() == valueB.getValue());
        eventOutput.trigger();
    }
}
