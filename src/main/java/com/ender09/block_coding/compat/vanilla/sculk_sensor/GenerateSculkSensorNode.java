package com.ender09.block_coding.compat.vanilla.sculk_sensor;

import com.ender09.block_coding.foundation.visual_scripting.Node;

public class GenerateSculkSensorNode {
    public static final String NODE_NAME = "Sculk Sensor";
    public static final String NODE_DESCRIPTION = "Used to read vibrations received from a sculk sensor or when it activates.";

    public Node createNode(SculkSensorBlockEntityOverride be) {
        return Node.builder(NODE_NAME, NODE_DESCRIPTION).addFunction(new SculkSensorTriggerNodeFunction(be.OnShriekEvent)).build();
    }
}
