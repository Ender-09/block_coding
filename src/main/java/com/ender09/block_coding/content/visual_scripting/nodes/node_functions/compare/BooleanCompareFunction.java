package com.ender09.block_coding.content.visual_scripting.nodes.node_functions.compare;

import com.ender09.block_coding.content.visual_scripting.nodes.node_parameters.BooleanNodeParameter;

public class BooleanCompareFunction extends AbstractCompareFunction {
    public BooleanCompareFunction(String name, String description) {
        super(name, description);
        inputs[0] = new BooleanNodeParameter("Value 1", false);
        inputs[1] = new BooleanNodeParameter("Value 2", false);
        outputs[0] = new BooleanNodeParameter("Result", false);
    }

    @Override
    protected Boolean evaluate() {
        switch (compareType) {
            case EQUALS -> {return getA() == getB(); }
            case NOT -> {return getA() != getB(); }
        }
        return false;
    }
}
