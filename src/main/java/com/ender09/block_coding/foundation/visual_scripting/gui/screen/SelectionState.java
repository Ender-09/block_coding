package com.ender09.block_coding.foundation.visual_scripting.gui.screen;

import com.ender09.block_coding.content.computer.ComputerScreen;
import com.ender09.block_coding.foundation.visual_scripting.gui.widgets.IDraggableWidget;
import com.ender09.block_coding.foundation.visual_scripting.gui.widgets.SelectionBoxWidget;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.events.GuiEventListener;

import java.util.ArrayList;
import java.util.List;

public class SelectionState extends ComputerScreenState {
    private List<IDraggableWidget> widgetSelection = new ArrayList<>();
    protected SelectionBoxWidget selectionBoxWidget;
    private int anchorX, anchorY;

    public SelectionState(ComputerScreenStateMachine stateMachine, ComputerScreen screen) {
        super(stateMachine, screen);
        selectionBoxWidget = screen.getSelectionBoxWidget();
    }

    @Override
    public void enterState() {
        resetSelectionBox();
    }

    @Override
    public void exitState() {
        if(widgetSelection.isEmpty())
            resetSelectionBox();
    }

    @Override
    public void mouseClicked(double pMouseX, double pMouseY, int pButton) {
        anchorX = (int) pMouseX;
        anchorY = (int) pMouseY;
    }

    @Override
    public void mouseReleased(double pMouseX, double pMouseY, int pButton) {
        EditState editState = ((EditState) stateMachine.getState(ComputerScreenStateMachine.ComputerScreenStateKey.EDIT));
        editState.setWidgetSelection(widgetSelection);

        stateMachine.changeState(ComputerScreenStateMachine.ComputerScreenStateKey.EDIT);
    }
    private void resetSelectionBox() {
        selectionBoxWidget.setX(-screen.width);
        selectionBoxWidget.setY(-screen.height);
        selectionBoxWidget.setWidth(4);
        selectionBoxWidget.setHeight(4);
    }

    @Override
    public void mouseDragged(double pMouseX, double pMouseY, int pButton, double pDragX, double pDragY) {
        setSelectionSize(pMouseX, pMouseY);

        for(GuiEventListener guiEventListener : screen.children()) {
            addWidgetToSelectionList(guiEventListener);
        }
    }
    private void setSelectionSize(double mouseX, double mouseY) {
        int width = Math.abs((int) mouseX - anchorX);
        int height = Math.abs((int) mouseY - anchorY);

        selectionBoxWidget.setX((int) Math.min(anchorX, mouseX));
        selectionBoxWidget.setY((int) Math.min(anchorY, mouseY));

        selectionBoxWidget.setWidth(width);
        selectionBoxWidget.setHeight(height);
    }
    private void addWidgetToSelectionList(GuiEventListener guiEventListener) {
        if(guiEventListener instanceof IDraggableWidget) {
            if(guiEventListener instanceof SelectionBoxWidget) return;

            if (!checkIfWidgetIsSelected(guiEventListener)) {
                widgetSelection.remove((IDraggableWidget) guiEventListener);
                return;
            }

            if (!widgetSelection.contains((IDraggableWidget) guiEventListener))
                widgetSelection.add((IDraggableWidget) guiEventListener);
        }
    }
    private boolean checkIfWidgetIsSelected(GuiEventListener guiEventListener) {
        AbstractWidget widget = (AbstractWidget) guiEventListener;

        if(widget.getX() < selectionBoxWidget.getX()) return false;
        if(widget.getX() + widget.getWidth() > selectionBoxWidget.getX() + selectionBoxWidget.getWidth()) return false;
        if(widget.getY() < selectionBoxWidget.getY()) return false;
        if(widget.getY() + widget.getHeight() > selectionBoxWidget.getY() + selectionBoxWidget.getHeight()) return false;

        return true;
    }

}
