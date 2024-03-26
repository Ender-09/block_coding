package com.ender09.block_coding.foundation.visual_scripting.gui.widgets;

import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.network.chat.Component;

public abstract class AbstractHierarchyWidget extends AbstractWidget {
    protected AbstractWidget parent;
    protected float scale = 0.8F;
    protected int offsetX, offsetY;

    public AbstractHierarchyWidget(int pX, int pY, int pWidth, int pHeight, Component pMessage, AbstractWidget parent) {
        super(parent.getX() + pX, parent.getX() + pY, pWidth, pHeight, pMessage);
        this.parent = parent;
        this.offsetX = pX;
        this.offsetY = pY;
    }

    @Override
    public int getX() {
        return parent.getX() + offsetX;
    }
    @Override
    public void setX(int pX) {
        this.offsetX = pX;
    }

    @Override
    public int getY() {
        return parent.getY() + offsetY;
    }
    public void setY(int pY) {
        this.offsetY = pY;
    }

    public float getScale() {
        return this.scale;
    }
    public void setScale(float scale) {
        this.scale = scale;
    }
}
