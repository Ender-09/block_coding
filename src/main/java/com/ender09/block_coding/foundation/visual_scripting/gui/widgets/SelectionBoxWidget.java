package com.ender09.block_coding.foundation.visual_scripting.gui.widgets;

import com.ender09.block_coding.BlockCoding;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class SelectionBoxWidget extends AbstractWidget implements IDraggableWidget {
    protected int cursorToCenterOffsetX, cursorToCenterOffsetY;

    private static final ResourceLocation WIDGET_RESOURCE_LOCATION = new ResourceLocation(BlockCoding.MOD_ID, "textures/gui/visual_scripting/utils_elements.png");

    public SelectionBoxWidget(int pX, int pY, int pWidth, int pHeight, Component pMessage) {
        super(pX, pY, pWidth, pHeight, pMessage);
    }

    @Override
    protected void renderWidget(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        //pGuiGraphics.blitNineSliced(WIDGET_RESOURCE_LOCATION, getX(), getY(), Math.max(this.width, 4), Math.max(this.height, 4), 1, 1, 1, 1, 4, 4, 62, 0);
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
}
