package com.ender09.block_coding.content.visual_scripting.nodes;

import com.ender09.block_coding.content.visual_scripting.nodes.node_functions.AdditionFunction;
import com.ender09.block_coding.foundation.visual_scripting.Node;

public class MathsNodes {
    public static final String ADDITION_NODE_NAME = "Addition";
    public static final String ADDITION_NODE_DESCRIPTION = "Used to add together two numbers.";

    public Node createAdditionIntegerNode() {
        return Node.builder(ADDITION_NODE_NAME, ADDITION_NODE_DESCRIPTION).addFunction(new AdditionFunction<Integer>(ADDITION_NODE_DESCRIPTION)).build();
    }
    public Node createAdditionFloatNode() {
        return Node.builder(ADDITION_NODE_NAME, ADDITION_NODE_DESCRIPTION).addFunction(new AdditionFunction<Float>(ADDITION_NODE_DESCRIPTION)).build();
    }
}
