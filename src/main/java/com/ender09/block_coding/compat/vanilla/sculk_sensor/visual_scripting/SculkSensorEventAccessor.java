package com.ender09.block_coding.compat.vanilla.sculk_sensor.visual_scripting;

import com.ender09.block_coding.util.EventSource;

public interface SculkSensorEventAccessor {
    EventSource<String> getOnReceiveVibrationEvent();
    void setActiveStatus(boolean isActive);
    void setPower(int power);
}
