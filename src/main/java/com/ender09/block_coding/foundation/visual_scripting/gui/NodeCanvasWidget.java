package com.ender09.block_coding.foundation.visual_scripting.gui;

import com.ender09.block_coding.BlockCoding;
import com.ender09.block_coding.content.visual_scripting.nodes.ConditionNodeGenerator;
import com.ender09.block_coding.foundation.visual_scripting.NodeCanvas;
import com.ender09.block_coding.foundation.visual_scripting.NodeGraph;
import com.ender09.block_coding.foundation.visual_scripting.gui.widgets.IDraggableWidget;
import com.ender09.block_coding.foundation.visual_scripting.nodes.Node;
import com.ender09.block_coding.foundation.visual_scripting.gui.widgets.node_widgets.NodeWidget;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public class NodeCanvasWidget extends AbstractWidget implements IDraggableWidget {
    NodeCanvas nodeCanvas;
    protected int cursorToCenterOffsetX, cursorToCenterOffsetY;
    private static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation(BlockCoding.MOD_ID, "textures/gui/visual_scripting/background.png");

    public NodeCanvasWidget(NodeCanvas nodeCanvas, int width, int height) {
        super(-width / 2, -height / 2, width, height, Component.empty());
        this.nodeCanvas = nodeCanvas;
    }

    @Override
    protected void renderWidget(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        pGuiGraphics.setColor(1,1,1,0.7F);
        pGuiGraphics.blit(BACKGROUND_TEXTURE, getX(), getY(), 0, 0.0F, 0.0F, this.width, this.height, 8, 8);
    }
    public List<AbstractWidget> getComponentsToRender() {
        List<AbstractWidget> components = new ArrayList<>();

        for(NodeWidget nodeWidget : nodeCanvas.getNodeWidgets()) {
            components.add(nodeWidget);
            components.addAll(nodeWidget.getComponentsToRender());
        }

        return components;
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput pNarrationElementOutput) {

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
        return pButton == 0 || pButton == 1;
    }

    @Override
    public void moveWidget(int mouseX, int mouseY) {
        int x = Math.min(0, mouseX + cursorToCenterOffsetX);
        x = Math.max(-this.width / 2, x);

        int y = Math.min(0, mouseY + cursorToCenterOffsetY);
        y = Math.max(-this.height / 2, y);

        this.setX(x);
        this.setY(y);
    }
}
