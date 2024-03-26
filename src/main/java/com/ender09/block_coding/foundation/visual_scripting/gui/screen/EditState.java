package com.ender09.block_coding.foundation.visual_scripting.gui.screen;

import com.ender09.block_coding.content.computer.ComputerScreen;
import com.ender09.block_coding.foundation.visual_scripting.gui.NodeCanvasWidget;
import com.ender09.block_coding.foundation.visual_scripting.gui.widgets.IDescriptionWidget;
import com.ender09.block_coding.foundation.visual_scripting.gui.widgets.IDraggableWidget;
import com.ender09.block_coding.foundation.visual_scripting.gui.widgets.node_widgets.NodeParameterWidget;
import net.minecraft.client.gui.components.events.GuiEventListener;

import java.util.ArrayList;
import java.util.List;

public class EditState extends ComputerScreenState {
    protected List<IDraggableWidget> widgetSelection = new ArrayList<>();
    private boolean canClearSelection = false;

    public EditState(ComputerScreenStateMachine stateMachine, ComputerScreen screen) {
        super(stateMachine, screen);
    }

    @Override
    public void enterState() {

    }
    @Override
    public void exitState() {
        screen.getDescriptionWidget().setDescription("");
    }

    public void setWidgetSelection(List<IDraggableWidget> widgetSelection) {
        if(widgetSelection.size() > 1)
            this.widgetSelection = widgetSelection;
    }

    @Override
    public void mouseClicked(double pMouseX, double pMouseY, int pButton) {
        canClearSelection = true;

        for(GuiEventListener guieventlistener : screen.children()) {
            if (guieventlistener.mouseClicked(pMouseX, pMouseY, pButton)) {
               checkToClearSelection(guieventlistener);

                if(pButton == 0) {
                    pairParameters(guieventlistener);
                    createWidgetSelection(guieventlistener, pMouseX, pMouseY, pButton);
                }

                if(pButton == 1) {
                    setDraggableWidgetsOffset(guieventlistener, pMouseX, pMouseY);
                }
            }
        }
    }
    void checkToClearSelection(GuiEventListener guiEventListener) {
        if(guiEventListener instanceof IDraggableWidget) {
            if (widgetSelection.contains((IDraggableWidget) guiEventListener))
                canClearSelection = false;
        }
    }
    void pairParameters(GuiEventListener guieventlistener) {
        if (guieventlistener instanceof NodeParameterWidget) {
            stateMachine.changeState(ComputerScreenStateMachine.ComputerScreenStateKey.PAIRING);
        }
    }
    void createWidgetSelection(GuiEventListener guieventlistener, double mouseX, double mouseY, int button) {
        if(guieventlistener instanceof NodeCanvasWidget) {
            widgetSelection.clear();
            stateMachine.changeState(ComputerScreenStateMachine.ComputerScreenStateKey.SELECTION);
            stateMachine.getState(ComputerScreenStateMachine.ComputerScreenStateKey.SELECTION).mouseClicked(mouseX, mouseY, button);
        }
    }
    void setDraggableWidgetsOffset(GuiEventListener guieventlistener, double mouseX, double mouseY) {
        if(canClearSelection)
            widgetSelection.clear();

        //Multiple Selection
        if(!widgetSelection.isEmpty()) {
            for(IDraggableWidget draggableWidget : widgetSelection) {
                draggableWidget.setCursorToCenterOffset((int) mouseX, (int) mouseY);
            }
            return;
        }

        //Single Selection
        if (guieventlistener instanceof IDraggableWidget draggableWidget) {
            draggableWidget.setCursorToCenterOffset((int) mouseX, (int) mouseY);
        }
    }

    @Override
    public void mouseDragged(double pMouseX, double pMouseY, int pButton, double pDragX, double pDragY) {
        //Multiple Selection
        if(!widgetSelection.isEmpty()) {
            for(IDraggableWidget draggableWidget : widgetSelection) {
                draggableWidget.moveWidget((int) pMouseX, (int) pMouseY);
            }
            return;
        }

        //Single Selection
        if(screen.getFocused() != null && screen.isDragging() && pButton == 1 && (screen.getFocused() instanceof IDraggableWidget draggableWidget)) {
            if(screen.getFocused() instanceof IDraggableWidget) {
                draggableWidget.moveWidget((int) pMouseX, (int) pMouseY);
            }
        }
    }

    @Override
    public void mouseMoved(double pMouseX, double pMouseY) {
        for(GuiEventListener guiEventListener : screen.children()) {
            if(guiEventListener.isMouseOver(pMouseX, pMouseY) && guiEventListener instanceof IDescriptionWidget descriptionWidget) {
                screen.getDescriptionWidget().setDescription(descriptionWidget.getDescription());
                return;
            }
            screen.getDescriptionWidget().setDefaultDescription();
        }
    }
}
