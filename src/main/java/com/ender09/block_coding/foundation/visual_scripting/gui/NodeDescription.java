package com.ender09.block_coding.foundation.visual_scripting.gui;

import com.ender09.block_coding.BlockCoding;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarratedElementType;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class NodeDescription extends AbstractWidget {
    private static final ResourceLocation WIDGET_RESOURCE_LOCATION = new ResourceLocation(BlockCoding.MOD_ID, "textures/gui/visual_scripting/utils_elements.png");
    private static final String DEFAULT_DESCRIPTION = "Hover over a node or it's parameter to show how it works!";
    protected String description;

    public NodeDescription(int pX, int pY) {
        super(pX, pY, 319, 16, Component.empty());
        description = DEFAULT_DESCRIPTION;
    }

    @Override
    protected void renderWidget(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        float textScale = 0.8F;
        int descriptionLength = (int)(Minecraft.getInstance().font.width(description) * textScale);
        this.width = Math.max(20 + descriptionLength, 319);

        pGuiGraphics.blitSprite(WIDGET_RESOURCE_LOCATION, getX(), getY(), this.width, 16, 2, 2, 2, 2,16);
        //pGuiGraphics.blitNineSliced(WIDGET_RESOURCE_LOCATION, getX(), getY(), this.width, 16, 2, 2, 2, 2,16,16,46,0);
        renderDescription(pGuiGraphics, textScale);
    }
    private void renderDescription(GuiGraphics guiGraphics, float scale) {
        guiGraphics.pose().pushPose();
        guiGraphics.pose().scale(scale, scale, 1);
        guiGraphics.drawString(Minecraft.getInstance().font, description, getX() / scale + 20, (getY() + 4)/ scale, 0x606060, false);
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


    public void setDescription(String description) {
        this.description = description;
    }
    public void setDefaultDescription() {
        this.description = DEFAULT_DESCRIPTION;
    }
}
