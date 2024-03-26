package com.ender09.block_coding.foundation.visual_scripting.gui.widgets;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.resources.ResourceLocation;

public class ScalableImageButton extends ImageButton {
    float scale;

    public ScalableImageButton(int pX, int pY, int pWidth, int pHeight, WidgetSprites pSprites, OnPress pOnPress, float scale) {
        super(pX, pY, pWidth, pHeight, pSprites, pOnPress);
        this.scale = scale;
    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        pGuiGraphics.pose().pushPose();
        pGuiGraphics.pose().scale(scale, scale, 1);
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        pGuiGraphics.pose().popPose();
    }

    @Override
    public int getX() {
        return (int) (super.getX()/ scale);
    }
    @Override
    public int getY() {
        return (int) (super.getY()/ scale);
    }

    public void setScale(float scale) {
        this.scale = scale;
    }
    public float getScale() {
        return scale;
    }
}
