package com.ender09.block_coding.foundation.visual_scripting.gui.widgets;

import com.ender09.block_coding.content.computer.ComputerScreen;
import com.ender09.block_coding.foundation.visual_scripting.nodes.Node;
import com.ender09.block_coding.util.events.EventListener;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeviceListWidget extends AbstractWidget implements EventListener<Node> {
    private ComputerScreen screen;
    Map<Node, CreateDeviceButton> nodeButtonMap = new HashMap<>();

    public DeviceListWidget(int pX, int pY, int pWidth, int pHeight, ComputerScreen screen) {
        super(pX, pY, pWidth, pHeight, Component.empty());
        this.screen = screen;
        screen.getNodeGraph().addDeviceNodeEvent.addListener(this);
        screen.getNodeGraph().removeDeviceNodeEvent.addListener(this);

        addButtonsForDeviceList(screen.getNodeGraph().deviceNodes.values().stream().toList());
    }
    private void addButtonsForDeviceList(List<Node> deviceList) {
        for(Node node : deviceList) {
            addDeviceButton(node);
        }
    }

    @Override
    protected void renderWidget(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {

    }
    public void addDeviceButton(Node node) {
        CreateDeviceButton deviceButton = new CreateDeviceButton(getX() + 4, getY() + 4, node, this::createNodeWidget);
        screen.addNewWidget(deviceButton);
        nodeButtonMap.put(node, deviceButton);
    }
    private void createNodeWidget(Button button) {
        CreateDeviceButton createDeviceButton = (CreateDeviceButton) button;
        screen.getNodeCanvas().addNodeWidget(createDeviceButton.node);
    }

    public void removeDeviceButton(Node node) {
        CreateDeviceButton deviceButton = nodeButtonMap.get(node);
        screen.removeOldWidget(deviceButton);
        nodeButtonMap.remove(node);
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput pNarrationElementOutput) {

    }

    @Override
    public void onEventFire(String eventType, Node[] args) {
        Node node = args[0];
        OnDeviceNodeAdded(eventType, node);
        OnDeviceNodeRemoved(eventType, node);
    }
    private void OnDeviceNodeAdded(String eventType, Node node) {
        if(eventType == "NodeGraphAddDeviceNode") {
            addDeviceButton(node);
        }
    }
    private void OnDeviceNodeRemoved(String eventType, Node node) {
        if(eventType == "NodeGraphRemoveDeviceNode") {
            removeDeviceButton(node);
        }
    }
}
