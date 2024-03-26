package com.ender09.block_coding.foundation.visual_scripting.gui.widgets;

import com.ender09.block_coding.BlockCoding;
import com.ender09.block_coding.foundation.visual_scripting.nodes.Node;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.resources.ResourceLocation;

public class CreateDeviceButton extends ImageButton {
    private static final ResourceLocation WIDGET_RESOURCE_LOCATION = new ResourceLocation(BlockCoding.MOD_ID, "textures/gui/visual_scripting/utils_elements.png");
    public Node node;

    public CreateDeviceButton(int pX, int pY, Node node , OnPress pOnPress) {
        super(pX, pY, 80, 20, new WidgetSprites(WIDGET_RESOURCE_LOCATION, WIDGET_RESOURCE_LOCATION), pOnPress);
        this.node = node;
    }

    @Override
    public void renderWidget(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        super.renderWidget(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
    }
}
