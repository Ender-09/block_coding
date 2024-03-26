package com.ender09.block_coding.foundation.visual_scripting.gui.widgets.node_widgets;

import com.ender09.block_coding.BlockCoding;
import com.ender09.block_coding.foundation.visual_scripting.gui.widgets.AbstractHierarchyWidget;
import com.ender09.block_coding.foundation.visual_scripting.nodes.node_parameters.InfoInputParameter;
import com.ender09.block_coding.foundation.visual_scripting.nodes.node_parameters.InfoOutputParameter;
import com.ender09.block_coding.foundation.visual_scripting.nodes.node_parameters.NodeParameter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class NodeParameterWidget extends AbstractHierarchyWidget {
    public enum NodeParameterWidgetType {
        EVENT_FLOW(0, 46, 12, 14),
        INFO(0, 60, 12, 12);

        final ResourceLocation IMAGE_LOCATION = new ResourceLocation (BlockCoding.MOD_ID, "textures/gui/visual_scripting/widget_elements.png");
        final int UVx, UVy;
        final int width, height;

        NodeParameterWidgetType(int UVx, int UVy, int width, int height) {
            this.UVx = UVx;
            this.UVy = UVy;
            this.width = width;
            this.height = height;
        }
    }
    NodeParameterWidgetType type;
    NodeParameter nodeParameter;
    boolean isInputParameter;

    public NodeParameterWidget(int pX, int pY, NodeParameter nodeParameter, boolean isInputParameter, NodeWidget nodeWidget) {
        super(pX, pY, 10, 10, Component.empty(), nodeWidget);
        this.type = getType(nodeParameter);
        this.nodeParameter = nodeParameter;
        this.isInputParameter = isInputParameter;

        this.setWidth(type.width);
        this.setHeight(type.height);
    }
    NodeParameterWidgetType getType(NodeParameter nodeParameter) {
        return (nodeParameter instanceof InfoInputParameter<?> || nodeParameter instanceof InfoOutputParameter<?>) ? NodeParameterWidgetType.INFO : NodeParameterWidgetType.EVENT_FLOW;
    }

    public NodeParameter getNodeParameter() {
        return nodeParameter;
    }

    @Override
    protected void renderWidget(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        renderIcon(pGuiGraphics, 0.55F);
        renderName(pGuiGraphics, 0.8F);
    }
    void renderIcon(GuiGraphics guiGraphics, float scale) {
        guiGraphics.pose().pushPose();
        guiGraphics.pose().scale(scale, scale, 1);
        guiGraphics.blit(type.IMAGE_LOCATION, (int) (getX()/ scale), (int) (getY() / scale), type.UVx, type.UVy,type.width, type.height);
        guiGraphics.pose().popPose();
    }
    void renderName(GuiGraphics guiGraphics, float scale) {
        int x = (int)(getX()/scale) + (isInputParameter ? 18 : - (int) (Minecraft.getInstance().font.width(nodeParameter.getLabel())/ scale) - 2);
        guiGraphics.pose().pushPose();
        guiGraphics.pose().scale(scale, scale, 1);
        guiGraphics.drawString(Minecraft.getInstance().font, nodeParameter.getLabel(), x, getY()/scale + 1,0xFFFFFF, false);
        guiGraphics.pose().popPose();
    }
    @Override
    protected void updateWidgetNarration(NarrationElementOutput pNarrationElementOutput) {

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
}
