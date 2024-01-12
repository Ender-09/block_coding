package com.ender09.block_coding.foundation.visual_scripting.widgets;

import com.ender09.block_coding.foundation.visual_scripting.node_parameters.NodeParameter;
import net.minecraft.client.gui.GuiGraphics;

public class InfoInputNodeParameterWidget extends NodeParameterWidget {
    public InfoInputNodeParameterWidget(int pX, int pY, NodeParameter nodeParameter) {
        super(pX, pY, nodeParameter);
    }

    @Override
    protected void renderWidget(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        super.renderWidget(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        //Render input box >> get type and show respective input box
    }
}
