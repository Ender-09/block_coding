package com.ender09.block_coding.content.visual_scripting.nodes;

import com.ender09.block_coding.content.visual_scripting.nodes.node_functions.compare.BooleanCompareFunction;
import com.ender09.block_coding.foundation.visual_scripting.Node;

public class CompareNodes {
    static final String BOOLEAN_NAME = "Boolean Compare";
    static final String BOOLEAN_DESCRIPTION = "Use this node to compare two booleans.";

    public Node createBoolean() {
        return Node.builder(BOOLEAN_NAME, BOOLEAN_DESCRIPTION)
                .addFunction(new BooleanCompareFunction(BOOLEAN_NAME, BOOLEAN_DESCRIPTION))
                .build();
    }
}
