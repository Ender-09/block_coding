package com.ender09.block_coding.foundation.visual_scripting.gui.widgets.node_widgets;

import com.ender09.block_coding.BlockCoding;
import com.ender09.block_coding.foundation.visual_scripting.gui.NodeWidgetData;
import com.ender09.block_coding.foundation.visual_scripting.gui.widgets.AbstractHierarchyWidget;
import com.ender09.block_coding.foundation.visual_scripting.gui.widgets.IDescriptionWidget;
import com.ender09.block_coding.foundation.visual_scripting.gui.widgets.IDraggableWidget;
import com.ender09.block_coding.foundation.visual_scripting.gui.widgets.ScalableImageButton;
import com.ender09.block_coding.foundation.visual_scripting.nodes.NodeFunction;
import com.ender09.block_coding.foundation.visual_scripting.nodes.Node;
import com.ender09.block_coding.foundation.visual_scripting.gui.NodeCanvasWidget;
import com.ender09.block_coding.foundation.visual_scripting.nodes.node_parameters.NodeParameter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public class NodeWidget extends AbstractHierarchyWidget implements IDraggableWidget, IDescriptionWidget {
    private String id;
    NodeCanvasWidget nodeCanvasWidget;
    NodeWidgetData nodeWidgetData;

    ScalableImageButton deleteButton;
    ScalableImageButton editButton;
    NodeParameterWidget[] nodeParameterWidgets;

    protected int cursorToCenterOffsetX, cursorToCenterOffsetY;

    private static final ResourceLocation WIDGET_RESOURCE_LOCATION = new ResourceLocation(BlockCoding.MOD_ID, "textures/gui/visual_scripting/widget_elements.png");

    protected NodeWidget(int pX, int pY, int pWidth, int pHeight, NodeWidgetData nodeWidgetData, NodeCanvasWidget nodeCanvasWidget) {
        super(pX, pY, pWidth, pHeight, Component.literal(nodeWidgetData.getNode().getName()), nodeCanvasWidget);
        this.nodeWidgetData = nodeWidgetData;
        this.nodeCanvasWidget = nodeCanvasWidget;

        /*deleteButton = new ScalableImageButton(this.getX() + this.width - 22, this.getY() + 4, 32, 32, 224, 0, WIDGET_RESOURCE_LOCATION, this::deleteNode);
        deleteButton.setScale(0.3F);

        editButton = new ScalableImageButton(this.getX() + this.width - 38, this.getY() + 4, 32, 32, 192, 0, WIDGET_RESOURCE_LOCATION, this::editNode);
        editButton.setScale(0.3F);*/
    }

    public Node getReferenceNode() {
        return nodeWidgetData.getNode();
    }

    @Override
    protected void renderWidget(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        pGuiGraphics.setColor(0.255F,0.4314F,0.749F,1);
        //pGuiGraphics.blitSprite(WIDGET_RESOURCE_LOCATION, getX(), getY(), this.width, this.height, 4,4,4, 4, 96 , 46,0, 0);
        pGuiGraphics.blitSprite(WIDGET_RESOURCE_LOCATION, getX(), getY(), this.width, this.height);
        pGuiGraphics.setColor(1,1,1,1);
        //pGuiGraphics.blitSprite(WIDGET_RESOURCE_LOCATION, getX() + 2, getY() + 2, this.width - 4, this.height - 4, 4,24,4, 4, 92 , 42,2, 2);
        pGuiGraphics.blitSprite(WIDGET_RESOURCE_LOCATION, getX() + 2, getY() + 2, this.width - 4, this.height - 4);

        renderName(pGuiGraphics, 1F);
        deleteButton.setX(getX() + width - 13); deleteButton.setY(getY() + 4);
        editButton.setX(getX() + width - 24); editButton.setY(getY() + 4);
    }
    public List<AbstractWidget> getComponentsToRender() {
        List<AbstractWidget> components = new ArrayList<>();

        //Buttons
        components.add(deleteButton);
        components.add(editButton);

        //Functions
        for(NodeParameterWidget nodeParameterWidget : nodeParameterWidgets) {
            components.add(nodeParameterWidget);
            //components.addAll(nodeParameterWidget.getComponentsToRender());
        }

        return components;
    }
    void renderName(GuiGraphics guiGraphics, float scale) {
        guiGraphics.pose().pushPose();
        guiGraphics.pose().scale(scale, scale, 1);
        guiGraphics.drawString(Minecraft.getInstance().font, "§l" + nodeWidgetData.getNode().getName(), getX()/scale + 8, getY()/scale + 6,0xFFFFFF, false);
        guiGraphics.pose().popPose();
    }
    void renderButtons(GuiGraphics guiGraphics, float scale) {
        guiGraphics.pose().pushPose();
        guiGraphics.pose().scale(scale, scale, 1);

        guiGraphics.pose().popPose();
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput pNarrationElementOutput) {

    }

    @Override
    public void setX(int pX) {
        nodeWidgetData.setX(pX);
    }
    @Override
    public int getX() {
        return this.parent.getX() + nodeWidgetData.getX();
    }

    @Override
    public void setY(int pY) {
        nodeWidgetData.setY(pY);
    }
    @Override
    public int getY() {
        return this.parent.getY() + nodeWidgetData.getY();
    }


    @Override
    public boolean mouseClicked(double pMouseX, double pMouseY, int pButton) {
        if (this.active && this.visible) {
            if (this.isValidClickButton(pButton)) {
                boolean flag = this.clicked(pMouseX, pMouseY);
                if (flag) {
                    this.onClick(pMouseX, pMouseY);
                    return true;
                }
            }

            return false;
        } else {
            return false;
        }
    }

    @Override
    protected boolean isValidClickButton(int pButton) {
        return pButton == 1;
    }

    @Override
    public void setCursorToCenterOffsetX(int x) {
        this.cursorToCenterOffsetX = x;
    }

    @Override
    public int getCursorToCenterOffsetX() {
        return cursorToCenterOffsetX;
    }

    @Override
    public void setCursorToCenterOffsetY(int y) {
        this.cursorToCenterOffsetY = y;
    }

    @Override
    public int getCursorToCenterOffsetY() {
        return cursorToCenterOffsetY;
    }

    @Override
    public void setCursorToCenterOffset(int mouseX, int mouseY) {
        this.setCursorToCenterOffsetX(getX() - mouseX - parent.getX());
        this.setCursorToCenterOffsetY(getY() - mouseY - parent.getY());
    }

    public void deleteNode(Button button) {
        /*nodeCanvas.getNodeGraph().removeNode(node);
        nodeCanvas.removeNodeWidgets(node);
        node = null;*/
    }
    public void editNode(Button button) {

    }
    @Override
    public String getDescription() {
        return nodeWidgetData.getNode().getDescription();
    }

    public static Builder builder(NodeWidgetData nodeWidgetData, NodeCanvasWidget nodeCanvasWidget) {
        return new Builder(nodeWidgetData, nodeCanvasWidget);
    }
    public static class Builder {
        NodeWidgetData nodeWidgetData;
        NodeCanvasWidget nodeCanvasWidget;
        int x, y;

        public Builder(NodeWidgetData nodeWidgetData, NodeCanvasWidget nodeCanvasWidget) {
            this.nodeWidgetData = nodeWidgetData;
            this.nodeCanvasWidget = nodeCanvasWidget;
        }

        public Builder setPosition(int x, int y) {
            this.x = x;
            this.y = y;
            return this;
        }

        public NodeWidget build() {
            return new NodeWidget(x, y, calculateWidth(nodeWidgetData.getNode()), calculateHeight(nodeWidgetData.getNode()), nodeWidgetData, nodeCanvasWidget);
        }
        private int calculateWidth(Node node) {
            int leftWidth = Minecraft.getInstance().font.width(node.getName());
            int rightWidth = 44;

            for (NodeParameter inputParameter : node.getInputs()) {
                leftWidth = Math.max(leftWidth, Minecraft.getInstance().font.width(inputParameter.getLabel()));
            }

            for(NodeParameter outputParameter : node.getOutputs()) {
                rightWidth = Math.max(rightWidth, Minecraft.getInstance().font.width(outputParameter.getLabel()));
            }
            return leftWidth + rightWidth + 4;
        }
        private int calculateHeight(Node node) {
            int height = 23;

            int largerSize = 0;
            if(node.getInputs() != null) {
                largerSize = node.getInputs().length;
            }
            if(node.getOutputs() != null) {
                largerSize = node.getOutputs().length;
            }
            if(node.getInputs() != null && node.getOutputs() != null) {
                largerSize = Math.max(node.getInputs().length, node.getOutputs().length);
            }
                
            height += 12 * largerSize;
            return height;
        }
    }
}
