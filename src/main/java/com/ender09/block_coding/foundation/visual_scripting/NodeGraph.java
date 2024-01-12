package com.ender09.block_coding.foundation.visual_scripting;

import com.ender09.block_coding.compat.vanilla.sculk_sensor.visual_scripting.SculkSensorNode;
import com.ender09.block_coding.foundation.visual_scripting.nodes.DeviceNodeGenerator;
import com.ender09.block_coding.foundation.visual_scripting.nodes.Node;
import com.ender09.block_coding.foundation.visual_scripting.nodes.NodeGeneratorHandler;
import com.ender09.block_coding.util.EventSource;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.SculkSensorBlockEntity;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class NodeGraph {

    public List<Node> nodes = new ArrayList<>();
    public Map<BlockPos, Node> deviceNodes = new HashMap<>();
    public Map<Node, Class<? extends BlockEntity>> unusedDeviceNodes = new HashMap<>();

    public NodeCanvasScreen nodeCanvas = new NodeCanvasScreen(1280,960);

    public EventSource<Integer> tickerEvent = new EventSource<>();

    public NodeGraph() {
        NodeGeneratorHandler.DEVICE_NODES.put(SculkSensorBlockEntity.class, SculkSensorNode.class);

        /*Node sensorNode = SculkSensorNode.createNode()
        nodes.add(sensorNode);
        InfoOutputParameter<String> vibration = (InfoOutputParameter<String>) sensorNode.getFunction(0).outputs[1];
        vibration.setValue("Walk");

        /*
        Node compareNode = CompareNodes.createNode();
        nodes.add(compareNode);
        InfoInputParameter<String> inputA = (InfoInputParameter<String>) compareNode.getFunction(0).inputs[1];
        InfoInputParameter<String> inputB = (InfoInputParameter<String>) compareNode.getFunction(0).inputs[2];
        inputA.setValue("Foo");
        inputA.setOutputParameter(vibration);
        inputB.setValue("Walk");

        EventFlowInputParameter compareEvent = (EventFlowInputParameter) compareNode.getFunction(0).inputs[0];
        EventFlowOutputParameter sensorEvent = (EventFlowOutputParameter) sensorNode.getFunction(0).outputs[0];
        sensorEvent.addChildParameter(compareEvent);*/
    }

    public NodeCanvasScreen getNodeCanvas() {
        return nodeCanvas;
    }

    public List<Node> getNodes() {
        return nodes;
    }
    public Node getNode(int index) {
        return nodes.get(index);
    }
    public void addNode(Node node) {
        if(nodes.contains(node)) return;
        nodes.add(node);
    }
    public void removeNode(Node node) {
        if(!nodes.contains(node)) return;
        nodes.remove(node);
        node.remove();
    }

    public void addDeviceNode(BlockEntity deviceBE) {
        Node node = getNodeByBlockEntityClass(deviceBE.getClass());
        if(node != null) {
            changeDeviceNodeBlockEntity(node, deviceBE);
            node.setActive(true);
            unusedDeviceNodes.remove(node);
        }
        else {
            node = generateDeviceNode(deviceBE);
        }

        deviceNodes.put(deviceBE.getBlockPos(), node);
    }
    private Node getNodeByBlockEntityClass(Class<? extends BlockEntity> blockEntityClass) {
        for (Map.Entry<Node, Class<? extends BlockEntity>> entry : unusedDeviceNodes.entrySet()) {
            if (Objects.equals(blockEntityClass, entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }
    private void changeDeviceNodeBlockEntity(Node node, BlockEntity newBlockEntity) {
        for(NodeFunction nodeFunction : node.getNodeFunctions()) {
            nodeFunction.setBlockEntity(newBlockEntity);
        }
    }
    private Node generateDeviceNode(BlockEntity deviceBE) {
        Class<? extends DeviceNodeGenerator> nodeGenerator = NodeGeneratorHandler.DEVICE_NODES.get(deviceBE.getClass());

        if(nodeGenerator == null) return null;
        try {
            return (Node) nodeGenerator.getMethod("createNode", BlockEntity.class).invoke(null, deviceBE);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void removeDeviceNode(BlockEntity deviceBE) {
        Node node = deviceNodes.get(deviceBE.getBlockPos());
        node.setActive(false);
        deviceNodes.remove(deviceBE.getBlockPos());
        unusedDeviceNodes.put(node, deviceBE.getClass());
    }

    public void tick() {
        tickerEvent.fireEvent();
    }
 }
