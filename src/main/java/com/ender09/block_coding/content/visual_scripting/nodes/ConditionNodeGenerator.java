package com.ender09.block_coding.content.visual_scripting.nodes;

import com.ender09.block_coding.content.visual_scripting.nodes.node_functions.IfFunction;
import com.ender09.block_coding.foundation.visual_scripting.nodes.NodeFunction;
import com.ender09.block_coding.foundation.visual_scripting.nodes.NodeGenerator;
import com.ender09.block_coding.foundation.visual_scripting.nodes.Node;

import java.util.UUID;

public class ConditionNodeGenerator extends NodeGenerator {
    public static final String IF_NODE_NAME = "IF";
    public static final String IF_NODE_DESCRIPTION = "Used to fire different event orders depending on the boolean supplied.";

    public Node generateNode(String uuid) {
        Node.Builder nodeBuilder = Node.builder(uuid, "condition", IF_NODE_NAME, IF_NODE_DESCRIPTION);
        return nodeBuilder
                .addFunction(new IfFunction(nodeBuilder.getNode()))
                .build();
    }
}
