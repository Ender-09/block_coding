package com.ender09.block_coding.mixins;

import com.ender09.block_coding.compat.vanilla.sculk_sensor.visual_scripting.SculkSensorEventAccessor;
import com.ender09.block_coding.foundation.network.INetworkDeviceBE;
import com.ender09.block_coding.util.EventSource;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SculkSensorBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.SculkSensorPhase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import static net.minecraft.world.level.block.SculkSensorBlock.PHASE;
import static net.minecraft.world.level.block.SculkSensorBlock.POWER;


@Mixin(SculkSensorBlockEntity.class)
public abstract class SculkSensorBlockEntityMixins extends BlockEntity implements SculkSensorEventAccessor, INetworkDeviceBE {
    public SculkSensorBlockEntityMixins(BlockEntityType<?> pType, BlockPos pPos, BlockState pBlockState) {
        super(pType, pPos, pBlockState);
    }

    @Unique
    protected EventSource<String> blockCoding$OnReceiveVibrationEvent = new EventSource<String>();
    @Unique
    public EventSource<String> getOnReceiveVibrationEvent() {
        return blockCoding$OnReceiveVibrationEvent;
    }

    @Unique
    public void setActiveStatus(boolean isActive) {
        BlockState blockState = this.level.getBlockState(this.worldPosition);
        SculkSensorPhase phaseValue = isActive? SculkSensorPhase.ACTIVE : SculkSensorPhase.INACTIVE;
        blockState.setValue(PHASE, phaseValue);
    }

    @Unique
    public void setPower(int power) {
        BlockState blockState = this.level.getBlockState(this.worldPosition);

        power = Math.max(0, Math.min(15, power));
        blockState.setValue(POWER, power);
    }
}
