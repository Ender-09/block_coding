package com.ender09.block_coding.content.network_cable;

import net.minecraft.util.StringRepresentable;

public enum NetworkCableSide implements StringRepresentable {
    UP("up"),
    SIDE("side"),
    NONE("none");

    private final String name;

    NetworkCableSide(String name) {
        this.name = name;
    }

    public String toString() {
        return this.getSerializedName();
    }

    public String getSerializedName() {
        return this.name;
    }
}
