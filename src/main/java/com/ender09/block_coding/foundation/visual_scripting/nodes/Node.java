package com.ender09.block_coding.foundation.visual_scripting.nodes;

import com.ender09.block_coding.foundation.visual_scripting.nodes.node_parameters.NodeParameter;

import java.util.*;

public class Node {
    protected String nodeUUID;
    protected String nodeId;
    String name;
    String description;
    NodeFunction[] functions;
    protected Map<String, NodeParameter> inputParameters = new HashMap<>();
    protected Map<String, NodeParameter> outputParameters = new HashMap<>();

    public Node(String uuid, String nodeId, String name, String description) {
        this.nodeUUID = uuid;
        this.nodeId = nodeId;
        this.name = name;
        this.description = description;
    }

    public String getUUID() {
        return nodeUUID;
    }
    public String getId() {
        return nodeId;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setActive(boolean isActive) {
        for(NodeFunction nodeFunction : functions) {
            nodeFunction.isActive = isActive;
        }
    }

    protected void setNodeFunctions(NodeFunction[] nodeFunctions) {
        this.functions = nodeFunctions;
    }
    public NodeFunction[] getNodeFunctions(){
        return functions;
    }
    public NodeFunction getNodeFunction(int index) {
        return functions[index];
    }

    public void addInput(NodeParameter input) {
        if(inputParameters.containsKey(input.getId())) return;
        inputParameters.put(input.getId(), input);
    }
    public NodeParameter getInput(String id) {
        return inputParameters.get(id);
    }
    public NodeParameter[] getInputs() {
        return inputParameters.values().toArray(NodeParameter[]::new);
    }

    public void addOutput(NodeParameter output) {
        if(outputParameters.containsKey(output.getId())) return;
        outputParameters.put(output.getId(), output);
    }
    public NodeParameter getOutput(String id) {
        return outputParameters.get(id);
    }
    public NodeParameter[] getOutputs() {
        return outputParameters.values().toArray(NodeParameter[]::new);
    }


    public void trigger() {
        for(NodeFunction function : functions) {
            function.trigger();
        }
    }

    public void remove() {
        for(NodeFunction function : functions) {
            function.remove();
        }
        functions = null;
    }

    public static Node.Builder builder(String uuid, String nodeId, String name, String description) {
        return new Node.Builder(uuid, nodeId, name, description);
    }
    public static class Builder {
        Node node;
        List<NodeFunction> functions = new ArrayList<>();

        public Builder(String uuid, String nodeId, String name, String description) {
            node = new Node(uuid, nodeId, name, description);
        }

        public Node getNode() {
            return node;
        }

        public Node.Builder addFunction(NodeFunction function) {
            functions.add(function);
            return this;
        }

        public Node build() {
            node.setNodeFunctions(functions.toArray(NodeFunction[]::new));
            return node;
        }
    }
}
