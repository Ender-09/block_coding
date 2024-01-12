package com.ender09.block_coding.compat.vanilla.sculk_sensor.visual_scripting;

import com.ender09.block_coding.foundation.visual_scripting.NodeFunction;
import com.ender09.block_coding.foundation.visual_scripting.node_parameters.InfoInputParameter;
import com.ender09.block_coding.foundation.visual_scripting.node_parameters.NodeParameter;
import com.ender09.block_coding.foundation.visual_scripting.nodes.Node;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.SculkSensorBlockEntity;

public class SculkSensorSetActiveNodeFunction extends NodeFunction {
    public static final String DESCRIPTION = "Used to set whether the sculk sensor can receive vibrations or not.";
    SculkSensorBlockEntity sensorBlockEntity;

    public SculkSensorSetActiveNodeFunction(SculkSensorBlockEntity sensorBlockEntity) {
        super(DESCRIPTION);
        this.sensorBlockEntity = sensorBlockEntity;

        inputs = new NodeParameter[]{
                new InfoInputParameter<Boolean>("is active",this)
        };
    }

    public void setBlockEntity(BlockEntity blockEntity) {
        this.sensorBlockEntity = (SculkSensorBlockEntity) blockEntity;
    }

    @Override
    public void onTrigger() {
        Boolean isActive = (Boolean) ((InfoInputParameter<Boolean>) inputs[0]).getValue();
        ((SculkSensorEventAccessor) sensorBlockEntity).setActiveStatus(isActive);
    }
}
