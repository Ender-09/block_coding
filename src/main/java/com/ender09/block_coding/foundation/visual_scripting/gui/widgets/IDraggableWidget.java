package com.ender09.block_coding.foundation.visual_scripting.gui.widgets;

public interface IDraggableWidget {

    void setX(int x);
    int getX();
    void setY(int y);
    int getY();

    void setCursorToCenterOffsetX(int x);
    int getCursorToCenterOffsetX();
    void setCursorToCenterOffsetY(int y);
    int getCursorToCenterOffsetY();

    default void setCursorToCenterOffset(int mouseX, int mouseY) {
        setCursorToCenterOffsetX(getX() - mouseX);
        setCursorToCenterOffsetY(getY() - mouseY);
    }
    default void moveWidget(int mouseX, int mouseY) {
        setX(mouseX + getCursorToCenterOffsetX());
        setY(mouseY + getCursorToCenterOffsetY());
    }
}
