package com.ender09.block_coding.foundation.visual_scripting;

import com.ender09.block_coding.foundation.visual_scripting.gui.NodeCanvasWidget;
import com.ender09.block_coding.foundation.visual_scripting.gui.NodeWidgetData;
import com.ender09.block_coding.foundation.visual_scripting.gui.widgets.node_widgets.NodeWidget;
import com.ender09.block_coding.foundation.visual_scripting.nodes.Node;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.ArrayList;
import java.util.List;

public class NodeCanvas implements INBTSerializable<CompoundTag> {
    protected NodeCanvasWidget nodeCanvasWidget;
    protected List<NodeWidget> nodeWidgets = new ArrayList<>();
    NodeGraph nodeGraph;

    public NodeCanvas(NodeGraph nodeGraph) {
        this.nodeGraph = nodeGraph;
        nodeCanvasWidget = new NodeCanvasWidget(this, 2 * 640,2 * 480);
        //nodeWidgets.add(NodeWidget.builder(ConditionNodeGenerator.generateNode(), this).setPosition(width/2, height/2).build());
    }

    public CompoundTag serializeNBT() {
        return null;
    }

    public void deserializeNBT(CompoundTag compoundTag) {

    }

    public NodeCanvasWidget getNodeCanvasWidget() {
        return nodeCanvasWidget;
    }
    public List<NodeWidget> getNodeWidgets() {
        return nodeWidgets;
    }
    public void addNodeWidget(Node node) {
        //NodeWidget widget = NodeWidget.builder(node, this).build();
        //nodeWidgets.add(widget);
    }
    public void removeNodeWidgets(Node referenceNode) {
        nodeWidgets.removeIf(widget -> widget.getReferenceNode() == referenceNode);
    }

    public NodeGraph getNodeGraph() {
        return nodeGraph;
    }

    //Store widget locations
    //Store widget link locations
    //Store device list
    //Store variable list


    //Moves canvas
    //Stores widgets
    //Stores widget links
}
