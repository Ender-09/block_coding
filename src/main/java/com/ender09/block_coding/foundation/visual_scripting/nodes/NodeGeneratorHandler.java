package com.ender09.block_coding.foundation.visual_scripting.nodes;

import com.ender09.block_coding.foundation.visual_scripting.nodes.DeviceNodeGenerator;
import com.ender09.block_coding.util.ClassUtils;
import net.minecraft.world.level.block.entity.BlockEntity;

import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class NodeGeneratorHandler {
    public static Map<Class<? extends BlockEntity>, Class<? extends DeviceNodeGenerator>> DEVICE_NODES = new HashMap<>();

    public static void scanAndRegisterDeviceNodeGenerators() {
        Set<Class<? extends DeviceNodeGenerator>> deviceNodeGenerators = ClassUtils.findSubclasses(DeviceNodeGenerator.class);

        for (Class<? extends DeviceNodeGenerator> nodeGeneratorClass : deviceNodeGenerators) {
            if (!Modifier.isAbstract(nodeGeneratorClass.getModifiers())) {
                try {
                    DeviceNodeGenerator instance = nodeGeneratorClass.getDeclaredConstructor().newInstance();
                    Class<? extends BlockEntity> blockEntityClass = instance.getBlockEntityClass();
                    DEVICE_NODES.put(blockEntityClass, nodeGeneratorClass);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
