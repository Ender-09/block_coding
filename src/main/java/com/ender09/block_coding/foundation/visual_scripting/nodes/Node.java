package com.ender09.block_coding.foundation.visual_scripting.nodes;

import com.ender09.block_coding.foundation.visual_scripting.NodeFunction;

import java.util.ArrayList;
import java.util.List;

public class Node {
    String name;
    String description;
    NodeFunction[] functions;

    protected Node(String name, String description, NodeFunction[] functions) {
        this.name = name;
        this.description = description;
        this.functions = functions;
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

    public NodeFunction[] getNodeFunctions(){
        return functions;
    }
    public NodeFunction getNodeFunction(int index) {
        return functions[index];
    }

    public void remove() {
        for(NodeFunction function : functions) {
            function.remove();
        }
        functions = null;
    }

    public static Node.Builder builder(String name, String description) {
        return new Node.Builder(name, description);
    }
    public static class Builder {
        String name;
        String description;
        List<NodeFunction> functions = new ArrayList<>();

        public Builder(String name, String description) {
            this.name = name;
            this.description = description;
        }

        public Node.Builder addFunction(NodeFunction function) {
            functions.add(function);
            return this;
        }

        public Node build() {
            return new Node(name, description, functions.toArray(NodeFunction[]::new));
        }
    }
}
