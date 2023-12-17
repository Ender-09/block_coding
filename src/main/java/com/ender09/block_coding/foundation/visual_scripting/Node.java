package com.ender09.block_coding.foundation.visual_scripting;

import com.ender09.block_coding.foundation.visual_scripting.node_parameters.NodeParameter;

import java.util.ArrayList;
import java.util.List;

public class Node {
    String name;
    String description;
    NodeFunction[] functions;
    NodeParameter[] inputs;

    protected Node(String name, String description, NodeFunction[] functions, NodeParameter[] inputs) {
        this.name = name;
        this.description = description;
        this.functions = functions;
        this.inputs = inputs;
    }

    public static Node.Builder builder(String name, String description) {
        return new Node.Builder(name, description);
    }
    public static class Builder {
        String name;
        String description;
        List<NodeFunction> functions = new ArrayList<>();
        List<NodeParameter> inputs = new ArrayList<>();

        public Builder(String name, String description) {
            this.name = name;
            this.description = description;
        }

        public Node.Builder addFunction(NodeFunction function) {
            functions.add(function);
            return this;
        }
        public Node.Builder addInputParameter(NodeParameter inputParameter) {
            inputs.add(inputParameter);
            return this;
        }

        public Node build() {
            return new Node(name, description, functions.toArray(NodeFunction[]::new), inputs.toArray(NodeParameter[]::new));
        }
    }
}
