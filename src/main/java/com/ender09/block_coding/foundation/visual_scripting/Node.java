package com.ender09.block_coding.foundation.visual_scripting;

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

    public void trigger() {
        for (NodeFunction function : functions) {
            function.trigger();
        }
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
