package com.ender09.block_coding.mixins;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.gameevent.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(targets = "net.minecraft.world.level.block.entity.SculkSensorBlockEntity.VibrationUser")
public class VibrationUserMixins {
    @Inject(method = "onReceiveVibration", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/entity/SculkSensorBlockEntity;setLastVibrationFrequency(I)V"))
    public void onReceiveVibration(ServerLevel pLevel, BlockPos pPos, GameEvent pGameEvent, Entity pEntity, Entity pPlayerEntity, float pDistance, CallbackInfo cir) {

    }
}
