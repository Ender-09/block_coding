package com.ender09.block_coding.content.computer;

import com.ender09.block_coding.BlockCoding;
import com.ender09.block_coding.foundation.visual_scripting.NodeWidget;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

import java.util.List;

public class ComputerScreen extends Screen {
    private static final String COMPONENT_KEY = "gui." + BlockCoding.MOD_ID;
    private static final Component TITLE = Component.translatable(COMPONENT_KEY + ".computer_screen.title");

    private static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation(BlockCoding.MOD_ID, "textures/gui/visual_scripting/background.png");

    private BlockPos position;
    private ComputerBlockEntity blockEntity;

    private List<NodeWidget> nodeWidgets;
 
    public ComputerScreen(BlockPos pos) {
        super(TITLE);
        this.position = pos;
    }

    @Override
    protected void init() {
        super.init();

        if(!assignBlockEntity()) return;
        createWidgets();
    }
    Boolean assignBlockEntity() {
        if (this.minecraft == null) return false;
        Level level = this.minecraft.level;
        if (level == null) return false;

        BlockEntity be = level.getBlockEntity(position);
        if (be instanceof ComputerBlockEntity cbe) {
            blockEntity = cbe;
            return true;
        } else {
            System.err.printf("BlockEntity at %s is not example of ComputerBlockEntity!\n", this.position);
            return false;
        }
    }
    void createWidgets() {

    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        pGuiGraphics.setColor(1,1,1,0.7F);
        pGuiGraphics.blit(BACKGROUND_TEXTURE, 0, 0, 0, 0.0F, 0.0F, this.width, this.height, 8, 8);
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
