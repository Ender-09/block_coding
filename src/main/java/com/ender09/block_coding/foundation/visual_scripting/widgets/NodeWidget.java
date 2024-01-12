package com.ender09.block_coding.foundation.visual_scripting.widgets;

import com.ender09.block_coding.BlockCoding;
import com.ender09.block_coding.foundation.visual_scripting.nodes.Node;
import com.ender09.block_coding.foundation.visual_scripting.NodeCanvasScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public class NodeWidget extends AbstractDraggableWidget {
    NodeCanvasScreen nodeCanvas;
    Node node;
    List<NodeParameterWidget> inputParameters = new ArrayList<>();
    List<NodeParameterWidget> outputParameters = new ArrayList<>();

    ImageButton deleteButton;
    ImageButton editButton;

    private static final ResourceLocation WIDGET_RESOURCE_LOCATION = new ResourceLocation(BlockCoding.MOD_ID, "textures/gui/visual_scripting/elements.png");

    protected NodeWidget(int pX, int pY, int pWidth, int pHeight, Node node, NodeCanvasScreen nodeCanvas) {
        super(pX, pY, pWidth, pHeight, Component.literal(node.getName()));
        this.node = node;
        this.nodeCanvas = nodeCanvas;

        deleteButton = new ImageButton(nodeCanvas.getX() + getX() + width - 7, nodeCanvas.getY() + getY() + 2, 5, 5, 120, 0, WIDGET_RESOURCE_LOCATION, this::deleteNode);
        editButton = new ImageButton(nodeCanvas.getX()+ getX() + width - 14, nodeCanvas.getY() + getY() + 2, 5, 5, 120, 5, WIDGET_RESOURCE_LOCATION, this::editNode);
    }

    public Node getReferenceNode() {
        return node;
    }

    @Override
    protected void renderWidget(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        pGuiGraphics.setColor(1,1,1,1);
        pGuiGraphics.blitNineSliced(WIDGET_RESOURCE_LOCATION, nodeCanvas.getX() + getX(), nodeCanvas.getY() + getY(), 15, 15, 5, 80 , 0,40, 20);

        renderName(pGuiGraphics, 0.8F);

        deleteButton.setX(nodeCanvas.getX() + getX() + width - 7); deleteButton.setY(nodeCanvas.getY() + getY() + 2);
        editButton.setX(nodeCanvas.getX() + getX() + width - 14); editButton.setY(nodeCanvas.getY() + getY() + 2);
    }
    public void renderWidgetComponents(List<Renderable> renderable) {
        //Buttons
        renderable.add(deleteButton);
        renderable.add(editButton);

        //Parameters
        renderable.addAll(inputParameters);
        renderable.addAll(outputParameters);
    }
    void renderName(GuiGraphics guiGraphics, float scale) {
        guiGraphics.pose().popPose();
        guiGraphics.pose().pushPose();
        guiGraphics.pose().scale(scale,scale, 1);
        guiGraphics.drawString(Minecraft.getInstance().font, node.getName(), nodeCanvas.getX()/4 + getX()/scale + 2, nodeCanvas.getY()/4 + getY()/scale + 2,0xFFFFFF, false);
        guiGraphics.pose().popPose();
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput pNarrationElementOutput) {

    }

    @Override
    public boolean isHovered() {
        showDescription();
        return super.isHovered();
    }
    void showDescription() {
        //Show when hovering over
    }

    public void deleteNode(Button button) {
        nodeCanvas.getNodeGraph().removeNode(node);
        node = null;
    }
    public void editNode(Button button) {

    }

    public static NodeWidget.Builder builder(Node node, NodeCanvasScreen nodeCanvas) {
        return new NodeWidget.Builder(node, nodeCanvas);
    }
    public static class Builder {
        Node node;
        NodeCanvasScreen nodeCanvas;
        int x, y;
        int width = 40, height = 20;

        public Builder(Node node, NodeCanvasScreen nodeCanvas) {
            this.node = node;
            this.nodeCanvas = nodeCanvas;
        }

        public NodeWidget.Builder setSize(int width, int height) {
            this.width = width;
            this.height = height;
            return this;
        }

        public NodeWidget.Builder setPosition(int x, int y) {
            this.x = x;
            this.y = y;
            return this;
        }

        public NodeWidget build() {
            return new NodeWidget(x, y, width, height, node, nodeCanvas);
        }
    }
}
