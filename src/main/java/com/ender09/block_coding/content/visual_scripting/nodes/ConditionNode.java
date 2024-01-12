package com.ender09.block_coding.content.visual_scripting.nodes;

import com.ender09.block_coding.content.visual_scripting.nodes.node_functions.IfFunction;
import com.ender09.block_coding.foundation.visual_scripting.nodes.Node;

public class ConditionNode {
    public static final String IF_NODE_NAME = "IF";
    public static final String IF_NODE_DESCRIPTION = "Used to fire different event orders depending on the boolean supplied.";

    public static Node createIfNode() {
        return Node.builder(IF_NODE_NAME, IF_NODE_DESCRIPTION).addFunction(new IfFunction(IF_NODE_DESCRIPTION)).build();
    }
}
