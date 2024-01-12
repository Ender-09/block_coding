package com.ender09.block_coding.content.visual_scripting.nodes;

import com.ender09.block_coding.content.visual_scripting.nodes.node_functions.CompareFunction;
import com.ender09.block_coding.foundation.visual_scripting.nodes.Node;

public class CompareNodes {
    public static final String NODE_NAME = "Compare";
    public static final String NODE_DESCRIPTION = "Used to compare whether two values are the same or not.";

    public static Node createNode() {
        return Node.builder(NODE_NAME, NODE_DESCRIPTION).addFunction(new CompareFunction<String>()).build();
    }
}
