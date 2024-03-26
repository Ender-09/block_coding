//package com.ender09.block_coding.compat.vanilla.sculk_sensor.visual_scripting;
//
//import com.ender09.block_coding.foundation.visual_scripting.nodes.Node;
//import com.ender09.block_coding.foundation.visual_scripting.nodes.NodeFunction;
//import com.ender09.block_coding.foundation.visual_scripting.nodes.node_parameters.EventFlowOutputParameter;
//import com.ender09.block_coding.foundation.visual_scripting.nodes.node_parameters.InfoOutputParameter;
//import com.ender09.block_coding.foundation.visual_scripting.nodes.node_parameters.NodeParameter;
//import com.ender09.block_coding.util.events.EventListener;
//import com.ender09.block_coding.util.events.EventSource;
//
//public class SculkSensorReceivedVibrationNodeFunction extends NodeFunction implements EventListener<String> {
//    public static final String FUNCTION_DESCRIPTION = "Called when a sculk sensor is activated.";
//    public static final String VIBRATION_PARAMETER_LABEL = "Vibration";
//
//    public SculkSensorReceivedVibrationNodeFunction(Node node, EventSource<String> sensorBlockEntity) {
//        super(node, FUNCTION_DESCRIPTION);
//        node.addOutput(new EventFlowOutputParameter("event_flow_output", ""));
//        node.addOutput(new InfoOutputParameter<String>("vibration_output", VIBRATION_PARAMETER_LABEL, node));
//
//        sensorBlockEntity.addListener(this);
//    }
//
//    @Override
//    public void onEventFire(String eventType, String[] args) {
//        if(eventType != "SculkSensorOnReceiveVibration") return;
//        ((InfoOutputParameter<String>) node.getOutput("vibration_output")).setValue(args[0]);
//        trigger();
//    }
//
//    @Override
//    public void onTrigger() {
//        ((EventFlowOutputParameter) node.getOutput("event_flow_output")).trigger();
//    }
//}
