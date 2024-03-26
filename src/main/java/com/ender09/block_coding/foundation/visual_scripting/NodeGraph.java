package com.ender09.block_coding.foundation.visual_scripting;

import com.ender09.block_coding.BlockCoding;
import com.ender09.block_coding.content.computer.ComputerBlockEntity;
import com.ender09.block_coding.foundation.visual_scripting.nodes.*;
import com.ender09.block_coding.network.PacketHandler;
import com.ender09.block_coding.network.visual_scripting.CAddDeviceNodePacket;
import com.ender09.block_coding.network.visual_scripting.CAddNodePacket;
import com.ender09.block_coding.network.visual_scripting.CRemoveDeviceNodePacket;
import com.ender09.block_coding.network.visual_scripting.CRemoveNodePacket;
import com.ender09.block_coding.registry.BlockCodingDeviceNodeGenerators;
import com.ender09.block_coding.util.BlockUtils;
import com.ender09.block_coding.util.events.EventSource;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.*;

public class NodeGraph implements INBTSerializable<CompoundTag> {
    ComputerBlockEntity computerBlockEntity;
    protected String name = "New Node Graph";

    public Map<String, Node> nodes = new HashMap<>();
    public Map<BlockPos, Node> deviceNodes = new HashMap<>();
    public Map<Node, Class<? extends BlockEntity>> unusedDeviceNodes = new HashMap<>();

    public EventSource<Integer> tickerEvent = new EventSource<>("NodeGraphTicker");
    public EventSource<Node> addDeviceNodeEvent = new EventSource<>("NodeGraphAddDeviceNode");
    public EventSource<Node> removeDeviceNodeEvent = new EventSource<>("NodeGraphRemoveDeviceNode");

    public NodeGraph(ComputerBlockEntity computerBlockEntity) {
        this.computerBlockEntity = computerBlockEntity;
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag compoundTag = new CompoundTag();

        compoundTag.putString("Name", name);

        //Save nodes: uuid/ nodeIds
        ListTag nodesListTag = new ListTag();
        addNodesToNBT(nodesListTag, nodes.values().stream().toList());
        compoundTag.put("Node List", nodesListTag);

        //Save device nodes:
        ListTag deviceNodesListTag = new ListTag();
        addDeviceNodesToNBT(deviceNodesListTag, deviceNodes);
        compoundTag.put("Device Node List", deviceNodesListTag);

        //Save unused device nodes:
        /*ListTag unusedDeviceNodesListTag = new ListTag();

        compoundTag.put("Device Nodes List", unusedDeviceNodesListTag);*/

        return compoundTag;
    }
    private void addNodesToNBT(ListTag listTag, List<Node> nodes) {
        for(Node node : nodes) {
            CompoundTag entryTag = new CompoundTag();

            entryTag.putString("Uuid", node.getUUID());
            entryTag.putString("NodeId", node.getId());

            listTag.add(entryTag);
        }
    }
    private void addDeviceNodesToNBT(ListTag listTag, Map<BlockPos, Node> deviceNodes) {
        for(Map.Entry<BlockPos, Node> entry : deviceNodes.entrySet()) {
            CompoundTag entryTag = new CompoundTag();

            CompoundTag blockPosTag = new CompoundTag();
            blockPosTag.putInt("X", entry.getKey().getX());
            blockPosTag.putInt("Y", entry.getKey().getY());
            blockPosTag.putInt("Z", entry.getKey().getZ());
            entryTag.put("BlockPos", blockPosTag);

            entryTag.putString("Uuid", entry.getValue().getUUID());
            entryTag.putString("NodeId", entry.getValue().getId());

            listTag.add(entryTag);
        }
    }

    @Override
    public void deserializeNBT(CompoundTag compoundTag) {
        name = compoundTag.getString("Name");

        ListTag nodesListTag = compoundTag.getList("Node List", 9);
        nodes = compileNodeMap(nodesListTag);

        ListTag deviceNodeListTag = compoundTag.getList("Device Node List", 9);
        deviceNodes = compileDeviceNodeMap(deviceNodeListTag);
    }
    private Map<String, Node> compileNodeMap(ListTag nodeListTag) {
        Map<String, Node> nodeMap = new HashMap<>();

        for(int i = 0; i < nodeListTag.size(); i++) {
            CompoundTag entryTag = nodeListTag.getCompound(i);
            String uuid = entryTag.getString("Uuid");
            String nodeId = entryTag.getString("NodeId");

            NodeGenerator nodeGenerator = BlockCoding.NODE_GENERATOR_REGISTRY.get().getValue(ResourceLocation.tryParse(nodeId));
            if(nodeGenerator == null) continue;

            Node node = nodeGenerator.generateNode(uuid);
            nodeMap.put(uuid, node);
        }

        return nodeMap;
    }
    private Map<BlockPos, Node> compileDeviceNodeMap(ListTag deviceNodeListTag) {
        Map<BlockPos, Node> deviceNodeMap = new HashMap<>();

        for(int i = 0; i < deviceNodeListTag.size(); i++) {
            CompoundTag entryTag = deviceNodeListTag.getCompound(i);
            CompoundTag blockPosTag = entryTag.getCompound("BlockPos");

            BlockPos blockPos = new BlockPos(blockPosTag.getInt("X"), blockPosTag.getInt("Y"), blockPosTag.getInt("Z"));
            BlockEntity blockEntity = BlockUtils.getBlockEntityFromBlockPos(computerBlockEntity.getLevel(), blockPos);

            String uuid = entryTag.getString("Uuid");
            String nodeId = entryTag.getString("NodeId");

            DeviceNodeGenerator deviceNodeGenerator = BlockCoding.DEVICE_NODE_GENERATOR_REGISTRY.get().getValue(ResourceLocation.tryParse(nodeId));
            if(deviceNodeGenerator == null) continue;

            Node node = deviceNodeGenerator.generateNode(blockEntity, uuid);
            deviceNodeMap.put(blockPos, node);
        }

        return  deviceNodeMap;
    }


    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public List<Node> getNodes() {
        return nodes.values().stream().toList();
    }
    public Node getNode(String nodeId) {
        return nodes.get(nodeId);
    }
    public void addNode(Node node) {
        String nodeUuid = node.getUUID();
        String nodeID = node.getId();
        if(nodes.containsKey(nodeID)) return;
        nodes.put(nodeUuid, node);

        if(!computerBlockEntity.getLevel().isClientSide)
            PacketHandler.sendToAllClients(new CAddNodePacket(nodeUuid, nodeID, computerBlockEntity.getBlockPos()));
    }
    public void removeNode(Node node) {
        String nodeUuid = node.getUUID();
        String nodeID = node.getId();
        if(!nodes.containsKey(nodeUuid)) return;
        nodes.remove(nodeUuid);
        node.remove();

        if(!computerBlockEntity.getLevel().isClientSide)
            PacketHandler.sendToAllClients(new CRemoveNodePacket(nodeUuid, computerBlockEntity.getBlockPos()));
    }
    public void removeNode(String nodeUuid) {
        Node node = nodes.get(nodeUuid);
        removeNode(node);
    }

    public void addDeviceNode(BlockEntity deviceBE) {
        Node node = getDeviceNodeByBlockEntityClass(deviceBE.getClass());
        if(node != null) {
            node.setActive(true);
            unusedDeviceNodes.remove(node);
        }
        else {
            node = generateDeviceNode(deviceBE);
        }
        deviceNodes.put(deviceBE.getBlockPos(), node);
        addDeviceNodeEvent.fireEvent(node);

        if(!computerBlockEntity.getLevel().isClientSide)
            PacketHandler.sendToAllClients(new CAddDeviceNodePacket(deviceBE.getBlockPos(), computerBlockEntity.getBlockPos()));
    }
    private Node getDeviceNodeByBlockEntityClass(Class<? extends BlockEntity> blockEntityClass) {
        for (Map.Entry<Node, Class<? extends BlockEntity>> entry : unusedDeviceNodes.entrySet()) {
            if (Objects.equals(blockEntityClass, entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }
    private Node generateDeviceNode(BlockEntity deviceBE) {
        String nodeId = BlockCoding.MOD_ID + ":" + BlockCodingDeviceNodeGenerators.blockEntityKeyMap.get(deviceBE.getClass());
        DeviceNodeGenerator deviceNodeGenerator = BlockCoding.DEVICE_NODE_GENERATOR_REGISTRY.get().getValue(ResourceLocation.tryParse(nodeId));
        Node deviceNode = deviceNodeGenerator.generateNode(deviceBE);
        return deviceNode;
    }

    public void removeDeviceNode(BlockEntity deviceBE) {
        Node node = deviceNodes.get(deviceBE.getBlockPos());
        node.setActive(false);
        deviceNodes.remove(deviceBE.getBlockPos());
        unusedDeviceNodes.put(node, deviceBE.getClass());
        removeDeviceNodeEvent.fireEvent(node);

        if(!computerBlockEntity.getLevel().isClientSide)
            PacketHandler.sendToAllClients(new CRemoveDeviceNodePacket(deviceBE.getBlockPos(), computerBlockEntity.getBlockPos()));
    }

    public void tick() {
        tickerEvent.fireEvent();
    }
}
