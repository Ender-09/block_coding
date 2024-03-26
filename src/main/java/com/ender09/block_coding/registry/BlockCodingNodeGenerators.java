package com.ender09.block_coding.registry;

import com.ender09.block_coding.BlockCoding;
import com.ender09.block_coding.content.visual_scripting.nodes.ConditionNodeGenerator;
import com.ender09.block_coding.foundation.visual_scripting.nodes.NodeGenerator;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class BlockCodingNodeGenerators {
    //List of Node generators
    public static final RegistryObject<NodeGenerator> CONDITION_NODE = BlockCoding.NODE_GENERATOR.register("condition",
            ConditionNodeGenerator::new);
}
