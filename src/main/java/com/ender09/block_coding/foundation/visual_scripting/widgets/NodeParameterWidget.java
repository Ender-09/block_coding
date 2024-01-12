package com.ender09.block_coding.foundation.visual_scripting.widgets;

import com.ender09.block_coding.BlockCoding;
import com.ender09.block_coding.foundation.visual_scripting.node_parameters.InfoInputParameter;
import com.ender09.block_coding.foundation.visual_scripting.node_parameters.InfoOutputParameter;
import com.ender09.block_coding.foundation.visual_scripting.node_parameters.NodeParameter;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class NodeParameterWidget extends AbstractWidget {
    NodeParameterWidgetType type;
    NodeParameter nodeParameter;

    public NodeParameterWidget(int pX, int pY, NodeParameter nodeParameter) {
        super(pX, pY, 0, 0, Component.empty());
        this.nodeParameter = nodeParameter;
        this.type = getType(nodeParameter);
        this.setWidth(type.width);
        this.setHeight(type.height);
    }
    NodeParameterWidgetType getType(NodeParameter nodeParameter) {
        return (nodeParameter instanceof InfoInputParameter<?> || nodeParameter instanceof InfoOutputParameter<?>) ? NodeParameterWidgetType.INFO : NodeParameterWidgetType.EVENT_FLOW;
    }

    @Override
    protected void renderWidget(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        pGuiGraphics.blit(type.IMAGE_LOCATION, getX(), getY() , type.UVx, type.UVy,type.width, type.height);
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput pNarrationElementOutput) {

    }

    public enum NodeParameterWidgetType {
        EVENT_FLOW(120, 10, 5, 5),
        INFO(120, 15, 5, 5);

        final ResourceLocation IMAGE_LOCATION = new ResourceLocation (BlockCoding.MOD_ID, "textures/gui/visual_scripting/elements.png");
        final int UVx, UVy;
        final int width, height;

        NodeParameterWidgetType(int UVx, int UVy, int width, int height) {
            this.UVx = UVx;
            this.UVy = UVy;
            this.width = width;
            this.height = height;
        }
    }
}
