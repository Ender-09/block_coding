package com.ender09.block_coding.mixins;

import com.ender09.block_coding.compat.vanilla.sculk_sensor.visual_scripting.SculkSensorEventAccessor;
import com.ender09.block_coding.foundation.network.INetworkDeviceBE;
import com.ender09.block_coding.util.EventSource;
import net.minecraft.world.level.block.CalibratedSculkSensorBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(CalibratedSculkSensorBlock.class)
public class CalibratedSculkSensorBlockMixin implements SculkSensorEventAccessor, INetworkDeviceBE {
    @Unique
    public EventSource<String> blockCoding$OnReceiveVibrationEvent = new EventSource<String>();
    @Unique
    public EventSource<String> getOnReceiveVibrationEvent() {
        return blockCoding$OnReceiveVibrationEvent;
    }

    @Override
    public void setActiveStatus(boolean isActive) {

    }

    @Override
    public void setPower(int power) {

    }
}
