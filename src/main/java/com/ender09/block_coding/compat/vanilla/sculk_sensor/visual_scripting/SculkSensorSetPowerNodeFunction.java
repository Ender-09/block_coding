//package com.ender09.block_coding.compat.vanilla.sculk_sensor.visual_scripting;
//
//import com.ender09.block_coding.foundation.visual_scripting.nodes.NodeFunction;
//import com.ender09.block_coding.foundation.visual_scripting.nodes.node_parameters.InfoInputParameter;
//import com.ender09.block_coding.foundation.visual_scripting.nodes.node_parameters.NodeParameter;
//import net.minecraft.world.level.block.entity.BlockEntity;
//import net.minecraft.world.level.block.entity.SculkSensorBlockEntity;
//
//public class SculkSensorSetPowerNodeFunction extends NodeFunction {
//    public static final String DESCRIPTION = "Used to set the redstone frequency of the sculk sensor.";
//    SculkSensorBlockEntity sensorBlockEntity;
//
//    public SculkSensorSetPowerNodeFunction(SculkSensorBlockEntity sensorBlockEntity) {
//        super(DESCRIPTION);
//        this.sensorBlockEntity = sensorBlockEntity;
//
//        inputs = new NodeParameter[]{
//                new InfoInputParameter<Integer>("set power", this)
//        };
//    }
//
//    public void setBlockEntity(BlockEntity blockEntity) {
//        this.sensorBlockEntity = (SculkSensorBlockEntity) blockEntity;
//    }
//
//    @Override
//    public void onTrigger() {
//        Integer power = (Integer) ((InfoInputParameter<Integer>) inputs[0]).getValue();
//        ((SculkSensorEventAccessor) sensorBlockEntity).setPower(power);
//    }
//}
