package com.ender09.block_coding.registry;

import com.ender09.block_coding.BlockCoding;
import com.ender09.block_coding.content.visual_scripting.nodes.ConditionNodeGenerator;
import com.ender09.block_coding.foundation.visual_scripting.nodes.NodeGenerator;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class BlockCodingNodeGenerators {
    public static final DeferredRegister<NodeGenerator> NODE_GENERATORS = DeferredRegister.create(BlockCoding.NODE_GENERATOR_REGISTRY_KEY, BlockCoding.MOD_ID);

    //List of Node generators
    public static final RegistryObject<NodeGenerator> CONDITION_NODE = NODE_GENERATORS.register("condition",
            ConditionNodeGenerator::new);

    public static void register(IEventBus eventBus) {
        eventBus.register(NODE_GENERATORS);
    }
}
