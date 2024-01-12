package com.ender09.block_coding.content.computer;

import com.ender09.block_coding.BlockCoding;
import com.ender09.block_coding.foundation.visual_scripting.NodeCanvasScreen;
import com.ender09.block_coding.foundation.visual_scripting.NodeGraph;
import com.ender09.block_coding.foundation.visual_scripting.widgets.NodeWidget;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

import java.util.List;

public class ComputerScreen extends Screen {
    private static final String COMPONENT_KEY = "gui." + BlockCoding.MOD_ID;
    private static final Component TITLE = Component.translatable(COMPONENT_KEY + ".computer_screen.title");

    private final BlockPos position;
    private ComputerBlockEntity blockEntity;
    private NodeGraph nodeGraph;

    public ComputerScreen(BlockPos pos) {
        super(TITLE);
        this.position = pos;
    }

    @Override
    protected void init() {
        super.init();

        if(!assignBlockEntity()) return;
        nodeGraph = blockEntity.nodeGraph;
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
        NodeCanvasScreen canvas = blockEntity.getNodeGraph().getNodeCanvas();
        List<NodeWidget> nodeWidgets = canvas.getNodeWidgets();

        addRenderableOnly(canvas);
        for(NodeWidget nodeWidget : nodeWidgets) {
            addRenderableWidget(nodeWidget);
            nodeWidget.renderWidgetComponents(this.renderables);
        }

        addWidget(canvas);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
