package com.ender09.block_coding.foundation.registry;

import com.ender09.block_coding.BlockCoding;
import com.ender09.block_coding.compat.vanilla.sculk_sensor.SculkSensorBlockEntityOverride;
import com.ender09.block_coding.content.computer.ComputerBlockEntity;
import com.ender09.block_coding.content.network_transceiver.NetworkTransceiverBlockEntity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntityTypes {
    public static final DeferredRegister<BlockEntityType<?>> BE_TYPES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, BlockCoding.MOD_ID);


    //List of registered Block Entities:
    public static final RegistryObject<BlockEntityType<ComputerBlockEntity>> COMPUTER = BE_TYPES.register("computer",
            () -> BlockEntityType.Builder.of(ComputerBlockEntity::new, ModBlocks.COMPUTER.get()).build(null));
    public static final RegistryObject<BlockEntityType<NetworkTransceiverBlockEntity>> NETWORK_TRANSCEIVER = BE_TYPES.register("network_transceiver",
            () -> BlockEntityType.Builder.of(NetworkTransceiverBlockEntity::new, ModBlocks.NETWORK_TRANSCEIVER.get()).build(null));


    //List of overriding Block Entities:
    public static final RegistryObject<BlockEntityType<SculkSensorBlockEntityOverride>> SCULK_SENSOR_OVERRIDE = BE_TYPES.register("sculk_sensor",
            () -> BlockEntityType.Builder.of(SculkSensorBlockEntityOverride::new, Blocks.SCULK_SENSOR).build(null));

    public static void register(IEventBus eventBus) {
        BE_TYPES.register(eventBus);
    }
}