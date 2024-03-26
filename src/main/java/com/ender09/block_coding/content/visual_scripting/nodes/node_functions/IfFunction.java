package com.ender09.block_coding.content.visual_scripting.nodes.node_functions;

import com.ender09.block_coding.foundation.visual_scripting.nodes.Node;
import com.ender09.block_coding.foundation.visual_scripting.nodes.NodeFunction;
import com.ender09.block_coding.foundation.visual_scripting.nodes.node_parameters.EventFlowInputParameter;
import com.ender09.block_coding.foundation.visual_scripting.nodes.node_parameters.EventFlowOutputParameter;
import com.ender09.block_coding.foundation.visual_scripting.nodes.node_parameters.InfoInputParameter;
import com.ender09.block_coding.foundation.visual_scripting.nodes.node_parameters.NodeParameter;

public class IfFunction extends NodeFunction {
    public static final String DESCRIPTION = "Used to fire different event orders depending on the boolean supplied.";

    public IfFunction(Node node) {
        super(node, DESCRIPTION);

        node.addInput(new EventFlowInputParameter("event_flow_input", node));
        node.addInput(new InfoInputParameter<Boolean>("boolean_input", "Value"));

        node.addOutput(new EventFlowOutputParameter("result_true_output", "True"));
        node.addOutput(new EventFlowOutputParameter("result_false_output", "False"));
    }

    @Override
    public void onTrigger() {
        InfoInputParameter<Boolean> condition = (InfoInputParameter<Boolean>) node.getInput("boolean_input");
        EventFlowOutputParameter outputTrue = (EventFlowOutputParameter) node.getOutput("result_true_output");
        EventFlowOutputParameter outputFalse = (EventFlowOutputParameter) node.getOutput("result_false_output");

        EventFlowOutputParameter output = (Boolean) condition.getValue()? outputTrue : outputFalse;
        output.trigger();
    }
}
