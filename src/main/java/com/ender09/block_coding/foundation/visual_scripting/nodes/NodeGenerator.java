package com.ender09.block_coding.foundation.visual_scripting.nodes;

import net.minecraftforge.registries.ForgeRegistry;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public abstract class NodeGenerator {//extends ForgeRegistryEntry<NodeGenerator> {
    public static Map<String, NodeGenerator> GENERATORS_MAP = new HashMap<>();

    public Node generateNode() {
        String uuid = UUID.randomUUID().toString();
        return generateNode(uuid);
    }

    public abstract Node generateNode(String uuid);
}
