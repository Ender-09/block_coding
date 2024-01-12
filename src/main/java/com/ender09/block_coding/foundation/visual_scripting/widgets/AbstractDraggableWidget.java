package com.ender09.block_coding.foundation.visual_scripting.widgets;

import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.network.chat.Component;

public abstract class AbstractDraggableWidget extends AbstractWidget {
    protected int cursorToCenterOffsetX, cursorToCenterOffsetY;

    public AbstractDraggableWidget(int pX, int pY, int pWidth, int pHeight, Component pMessage) {
        super(pX, pY, pWidth, pHeight, pMessage);
    }

    @Override
    public boolean mouseClicked(double pMouseX, double pMouseY, int pButton) {
        if(pButton == 0 && isHovered()) {
            setCursorToOriginOffset((int) pMouseX, (int) pMouseY);
            return true;
        }
        return false;
    }
    protected void setCursorToOriginOffset(int mouseX, int mouseY) {
        cursorToCenterOffsetX = this.getX() - mouseX ;
        cursorToCenterOffsetY = this.getY() - mouseY;
    }

    @Override
    public boolean mouseDragged(double pMouseX, double pMouseY, int pButton, double pDragX, double pDragY) {
        moveWidget((int) pMouseX, (int) pMouseY);
        return true;
    }
    protected void moveWidget(int mouseX, int mouseY) {
        this.setX(mouseX + cursorToCenterOffsetX);
        this.setY(mouseY + cursorToCenterOffsetY);
    }
}
