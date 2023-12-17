package com.ender09.block_coding.content.visual_scripting.nodes.node_functions.compare;

public enum CompareType {
    EQUALS("=="),
    NOT("!="),
    GREATER(">"),
    GREATER_EQUALS(">="),
    LESS("<"),
    LESS_EQUALS("<=");

    private final String name;
    CompareType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
