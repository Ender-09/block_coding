package com.ender09.block_coding.compat.vanilla.sculk_sensor.visual_scripting;

import com.ender09.block_coding.foundation.visual_scripting.NodeFunction;
import com.ender09.block_coding.foundation.visual_scripting.node_parameters.EventFlowOutputParameter;
import com.ender09.block_coding.foundation.visual_scripting.node_parameters.InfoOutputParameter;
import com.ender09.block_coding.foundation.visual_scripting.node_parameters.NodeParameter;
import com.ender09.block_coding.util.EventListener;
import com.ender09.block_coding.util.EventSource;

public class SculkSensorReceivedVibrationNodeFunction extends NodeFunction implements EventListener<String> {
    public static final String FUNCTION_DESCRIPTION = "Called when a sculk sensor is activated.";
    public static final String VIBRATION_PARAMETER_LABEL = "Vibration";

    public SculkSensorReceivedVibrationNodeFunction(EventSource<String> sensorBlockEntity) {
        super(FUNCTION_DESCRIPTION);
        outputs = new NodeParameter[]{
                new EventFlowOutputParameter(),
                new InfoOutputParameter<String>(VIBRATION_PARAMETER_LABEL, this)
        };

        sensorBlockEntity.addListener(this);
    }

    @Override
    public void onEventFire(String[] args) {
        ((InfoOutputParameter<String>) outputs[1]).setValue(args[0]);
        trigger();
    }

    @Override
    public void onTrigger() {
        ((EventFlowOutputParameter) outputs[0]).trigger();
    }
}
