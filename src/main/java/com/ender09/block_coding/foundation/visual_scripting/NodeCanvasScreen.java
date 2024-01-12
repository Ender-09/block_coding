package com.ender09.block_coding.foundation.visual_scripting;

import com.ender09.block_coding.BlockCoding;
import com.ender09.block_coding.content.visual_scripting.nodes.ConditionNode;
import com.ender09.block_coding.foundation.visual_scripting.nodes.Node;
import com.ender09.block_coding.foundation.visual_scripting.widgets.AbstractDraggableWidget;
import com.ender09.block_coding.foundation.visual_scripting.widgets.NodeWidget;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public class NodeCanvasScreen extends AbstractDraggableWidget {
    protected List<NodeWidget> nodeWidgets = new ArrayList<>();
    NodeGraph nodeGraph;

    private static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation(BlockCoding.MOD_ID, "textures/gui/visual_scripting/background.png");

    public NodeCanvasScreen(int width, int height) {
        super(-width / 2, -height / 2, width, height, Component.empty());
        //nodeWidgets.add(NodeWidget.builder(NodeWidget.builder(CompareNodes.createNode(), this).build());
        nodeWidgets.add(NodeWidget.builder(ConditionNode.createIfNode(), this).setPosition(width/2, height/2).build());
    }

    public List<NodeWidget> getNodeWidgets() {
        return nodeWidgets;
    }
    public void addNodeWidget(Node node) {
        NodeWidget widget = NodeWidget.builder(node, this).build(); //create node
        nodeWidgets.add(widget);
    }
    public void removeNodeWidgets(Node referenceNode) {
        nodeWidgets.removeIf(widget -> widget.getReferenceNode() == referenceNode);
    }

    public NodeGraph getNodeGraph() {
        return nodeGraph;
    }


    @Override
    protected void renderWidget(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        pGuiGraphics.setColor(1,1,1,0.7F);
        pGuiGraphics.blit(BACKGROUND_TEXTURE, getX(), getY(), 0, 0.0F, 0.0F, this.width, this.height, 8, 8);
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput pNarrationElementOutput) {

    }

    @Override
    protected void moveWidget(int mouseX, int mouseY) {
        int x = Math.min(0, mouseX + cursorToCenterOffsetX);
        x = Math.max(-this.width / 2, x);

        int y = Math.min(0, mouseY + cursorToCenterOffsetY);
        y = Math.max(-this.height / 2, y);

        this.setX(x);
        this.setY(y);
    }
}
