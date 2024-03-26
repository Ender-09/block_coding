package com.ender09.block_coding.registry;

import com.ender09.block_coding.BlockCoding;
import com.ender09.block_coding.foundation.visual_scripting.nodes.DeviceNodeGenerator;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class BlockCodingDeviceNodeGenerators {
    public static final DeferredRegister<DeviceNodeGenerator> DEVICE_NODE_GENERATORS = DeferredRegister.create(BlockCoding.DEVICE_NODE_GENERATOR_REGISTRY_KEY, BlockCoding.MOD_ID);
    public static Map<Class<? extends BlockEntity>, String> blockEntityKeyMap = new HashMap<>();

    //List of Device Nodes
    /*public static final RegistryObject<DeviceNodeGenerator> SCULK_SENSOR = registerDeviceNode("sculk_sensor",
            SculkSensorBlockEntity.class, SculkSensorBlockDeviceNodeGenerator::new);*/


    //Helper Functions
    public static <T extends DeviceNodeGenerator> RegistryObject<T> registerDeviceNode(String nodeId, Class<? extends BlockEntity> blockEntityClass, Supplier<T> deviceNodeGenerator) {
        RegistryObject<T> registryObject = DEVICE_NODE_GENERATORS.register(nodeId, deviceNodeGenerator);
        blockEntityKeyMap.put(blockEntityClass, nodeId);
        return registryObject;
    }

    public static void register(IEventBus eventBus) {
        eventBus.register(DEVICE_NODE_GENERATORS);
    }
}
