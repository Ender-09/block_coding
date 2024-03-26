/*
package com.ender09.block_coding.compat.vanilla.sculk_sensor.visual_scripting;

import com.ender09.block_coding.foundation.visual_scripting.nodes.DeviceNodeGenerator;
import com.ender09.block_coding.foundation.visual_scripting.nodes.Node;
import com.ender09.block_coding.util.events.EventSource;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.SculkSensorBlockEntity;

public class SculkSensorNode extends DeviceNodeGenerator {
    @Override
    protected String getName() {
        return "Sculk Sensor";
    }
    @Override
    protected String getDescription() {
        return "Used to read vibrations received from a sculk sensor or when it activates.";
    }
    @Override
    protected Class<? extends BlockEntity> getBlockEntityClass() {
        return SculkSensorBlockEntity.class;
    }


    public static Node createNode(BlockEntity be) {
        SculkSensorBlockEntity sensorBlockEntity = (SculkSensorBlockEntity) be;
        SculkSensorNode nodeGenerator = new SculkSensorNode();
        EventSource<String> onReceiveVibrationEvent = ((SculkSensorEventAccessor) sensorBlockEntity).getOnReceiveVibrationEvent();

        return Node.builder(nodeGenerator.getName(), nodeGenerator.getDescription())
                .addFunction(new SculkSensorReceivedVibrationNodeFunction(onReceiveVibrationEvent))
                .addFunction(new SculkSensorSetActiveNodeFunction(sensorBlockEntity))
                .addFunction(new SculkSensorSetPowerNodeFunction(sensorBlockEntity))
                .build();
    }
}
*/
