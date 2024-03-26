package com.ender09.block_coding.foundation.visual_scripting.gui;

import com.ender09.block_coding.BlockCoding;
import com.ender09.block_coding.content.computer.ComputerScreen;
import com.ender09.block_coding.foundation.visual_scripting.NodeCanvas;
import com.ender09.block_coding.foundation.visual_scripting.gui.widgets.DeviceListWidget;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public class NodeCanvasUtils extends AbstractWidget {
    private static final ResourceLocation WIDGET_RESOURCE_LOCATION = new ResourceLocation(BlockCoding.MOD_ID, "textures/gui/visual_scripting/utils_elements.png");

    private NodeCanvas nodeCanvas;
    private DeviceListWidget deviceListWidget;

    public NodeCanvasUtils(int pX, int pY, int pWidth, int pHeight, ComputerScreen screen) {
        super(pX, pY, pWidth, pHeight, Component.empty());
        this.nodeCanvas = screen.getNodeCanvas();

        deviceListWidget = new DeviceListWidget(getX() + 4, getY() + 32, this.width - 8, 100, screen);
        //Save node graph
        //Load node graph
        //Open node menu
        //Device List
        //Variable List
        //Edit node graph name
    }

    @Override
    protected void renderWidget(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        pGuiGraphics.blitSprite(WIDGET_RESOURCE_LOCATION, getX(), getY(), getWidth(), getHeight(), 15, 29, 15, 11, 46);
        //pGuiGraphics.blitNineSliced(WIDGET_RESOURCE_LOCATION,getX(), getY(), getWidth(), getHeight(), 15, 29, 15, 11, 46, 70, 0,0);
        renderName(pGuiGraphics, 0.8F);
    }
    public List<AbstractWidget> getComponentsToRender() {
        List<AbstractWidget> components = new ArrayList<>();

        components.add(deviceListWidget);

        return components;
    }

    void renderName(GuiGraphics guiGraphics, float scale) {
        String name = nodeCanvas.getNodeGraph().getName();
        name = name.length() > 20 ? name.substring(0, 16) + "...." : name;

        guiGraphics.pose().pushPose();
        guiGraphics.pose().scale(scale, scale, 1);
        guiGraphics.drawString(Minecraft.getInstance().font, "§l" + "TTTTTTTTTTTTTTTTTTTT", getX()+ 18/scale , getY()/scale + 19,0xFFFFFF, false);
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
