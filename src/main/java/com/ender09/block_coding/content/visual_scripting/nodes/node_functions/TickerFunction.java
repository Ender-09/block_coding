package com.ender09.block_coding.content.visual_scripting.nodes.node_functions;

import com.ender09.block_coding.content.visual_scripting.nodes.node_parameters.TickerNodeParameter;
import com.ender09.block_coding.foundation.visual_scripting.NodeFunction;
import com.ender09.block_coding.foundation.visual_scripting.NodeFunctionParameter;

public abstract class TickerFunction extends NodeFunction {
    public TickerFunction(String name, String description) {
        super(name, description);
        outputs = new NodeFunctionParameter[1];
        outputs[0] = new TickerNodeParameter("Tick", null);
    }

    public void tick() {
        trigger();
    }
}
