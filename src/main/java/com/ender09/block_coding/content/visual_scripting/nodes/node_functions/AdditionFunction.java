package com.ender09.block_coding.content.visual_scripting.nodes.node_functions;

import com.ender09.block_coding.foundation.visual_scripting.NodeFunction;
import com.ender09.block_coding.foundation.visual_scripting.node_parameters.InfoInputParameter;
import com.ender09.block_coding.foundation.visual_scripting.node_parameters.InfoOutputParameter;
import com.ender09.block_coding.foundation.visual_scripting.node_parameters.NodeParameter;

public class AdditionFunction<T extends Number> extends NodeFunction {
    public AdditionFunction(String description) {
        super(description);

        inputs = new NodeParameter[]{
                new InfoInputParameter<T>("A"),
                new InfoInputParameter<T>("B")
        };

        outputs = new NodeParameter[]{
                new InfoOutputParameter<T>("A+B",this)
        };
    }

    @Override
    public void trigger() {
        InfoInputParameter<T> valueA = (InfoInputParameter<T>) inputs[0];
        InfoInputParameter<T> valueB = (InfoInputParameter<T>) inputs[1];
        InfoOutputParameter<T> result = (InfoOutputParameter<T>) outputs[0];
    }
}
