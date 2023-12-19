package com.ender09.block_coding.compat.vanilla.sculk_sensor;

import com.ender09.block_coding.foundation.visual_scripting.NodeFunction;
import com.ender09.block_coding.foundation.visual_scripting.node_parameters.EventFlowOutputParameter;
import com.ender09.block_coding.foundation.visual_scripting.node_parameters.InfoOutputParameter;
import com.ender09.block_coding.foundation.visual_scripting.node_parameters.NodeParameter;
import com.ender09.block_coding.util.EventListener;
import com.ender09.block_coding.util.EventSource;

public class SculkSensorTriggerNodeFunction extends NodeFunction implements EventListener<String> {
    public static final String FUNCTION_DESCRIPTION = "Called when a sculk sensor is activated.";
    public static final String VIBRATION_PARAMETER_LABEL = "Vibration";

    public SculkSensorTriggerNodeFunction(EventSource<String> sensorBlockEntity) {
        super(FUNCTION_DESCRIPTION);
        outputs = new NodeParameter[]{
                new EventFlowOutputParameter(),
                new InfoOutputParameter<String>(VIBRATION_PARAMETER_LABEL, this)
        };

        sensorBlockEntity.addListener(this);
    }

    @Override
    public void onEventFire(String[] args) {
        InfoOutputParameter<String> vibration = (InfoOutputParameter<String>) outputs[1];
        vibration.setValue(args[0]);
        trigger();
    }

    @Override
    public void trigger() {
        EventFlowOutputParameter eventFlowParameter = (EventFlowOutputParameter) outputs[0];
        eventFlowParameter.trigger();
    }
}
