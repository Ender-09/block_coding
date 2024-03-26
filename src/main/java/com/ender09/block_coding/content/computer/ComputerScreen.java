package com.ender09.block_coding.content.computer;

import com.ender09.block_coding.BlockCoding;
import com.ender09.block_coding.foundation.visual_scripting.NodeCanvas;
import com.ender09.block_coding.foundation.visual_scripting.gui.NodeCanvasWidget;
import com.ender09.block_coding.foundation.visual_scripting.gui.NodeCanvasUtils;
import com.ender09.block_coding.foundation.visual_scripting.gui.NodeDescription;
import com.ender09.block_coding.foundation.visual_scripting.NodeGraph;
import com.ender09.block_coding.foundation.visual_scripting.gui.screen.ComputerScreenStateMachine;
import com.ender09.block_coding.foundation.visual_scripting.gui.widgets.SelectionBoxWidget;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarratableEntry;
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
    private NodeCanvas nodeCanvas;
    private NodeCanvasWidget nodeCanvasWidget;
    private NodeCanvasUtils utilsGUI;
    private NodeDescription descriptionWidget;
    private SelectionBoxWidget selectionBoxWidget;

    private ComputerScreenStateMachine stateMachine;

    public ComputerScreen(BlockPos pos) {
        super(TITLE);
        this.position = pos;
    }

    @Override
    protected void init() {
        super.init();

        if(!assignBlockEntity()) return;
        nodeGraph = blockEntity.nodeGraph;
        nodeCanvas = blockEntity.nodeCanvas;
        createWidgets();

        stateMachine = new ComputerScreenStateMachine(this);
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
        nodeCanvasWidget = nodeCanvas.getNodeCanvasWidget();
        addRenderableOnly(nodeCanvasWidget);
        renderWidgetComponents(nodeCanvasWidget.getComponentsToRender());

        selectionBoxWidget = new SelectionBoxWidget(-this.width,-this.height,0,0,Component.empty());
        addRenderableWidget(selectionBoxWidget);

        utilsGUI = new NodeCanvasUtils(5,5,150,this.height - 10, this);
        addRenderableWidget(utilsGUI);
        renderWidgetComponents(utilsGUI.getComponentsToRender());

        descriptionWidget = new NodeDescription(165, this.height - 21);
        addRenderableWidget(descriptionWidget);

        addWidget(nodeCanvasWidget);
    }
    private void renderWidgetComponents(List<AbstractWidget> components) {
        for(AbstractWidget component : components) {
            addRenderableWidget(component);
        }
    }

    //CHANGE-------------
    public <T extends GuiEventListener & Renderable & NarratableEntry> T addNewWidget(T widget) {
        addRenderableWidget(widget);
        return this.addRenderableWidget(widget);
    }
    public void removeOldWidget(GuiEventListener pListener) {
        if (pListener instanceof Renderable) {
            this.renderables.remove((Renderable)pListener);
        }

        this.children().remove(pListener);
    }
    //------------------


    public NodeGraph getNodeGraph() {
        return nodeGraph;
    }
    public NodeCanvas getNodeCanvas() {
        return this.nodeCanvas;
    }

    public NodeDescription getDescriptionWidget() {
        return descriptionWidget;
    }
    public SelectionBoxWidget getSelectionBoxWidget() {
        return selectionBoxWidget;
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public boolean mouseClicked(double pMouseX, double pMouseY, int pButton) {
        stateMachine.mouseClicked(pMouseX, pMouseY, pButton);

        for(GuiEventListener guieventlistener : this.children()) {
            if (guieventlistener.mouseClicked(pMouseX, pMouseY, pButton)) {
                this.setFocused(guieventlistener);
                if (pButton == 1) {
                    this.setDragging(true);
                }

                return true;
            }
        }

        return false;
    }
    @Override
    public boolean mouseReleased(double pMouseX, double pMouseY, int pButton) {
        stateMachine.mouseReleased(pMouseX, pMouseY, pButton);
        return super.mouseReleased(pMouseX, pMouseY, pButton);
    }
    @Override
    public boolean mouseDragged(double pMouseX, double pMouseY, int pButton, double pDragX, double pDragY) {
        stateMachine.mouseDragged(pMouseX, pMouseY, pButton, pDragX, pDragY);
        return this.getFocused() != null && this.isDragging() && pButton == 1 && this.getFocused().mouseDragged(pMouseX, pMouseY, pButton, pDragX, pDragY);
    }


    @Override
    public boolean mouseScrolled(double pMouseX, double pMouseY, double pScrollX, double pScrollY) {
        stateMachine.mouseScrolled(pMouseX, pMouseY, pScrollX, pScrollY);
        return super.mouseScrolled(pMouseX, pMouseY, pScrollX, pScrollY);
    }

    @Override
    public void mouseMoved(double pMouseX, double pMouseY) {
        stateMachine.mouseMoved(pMouseX, pMouseY);
        super.mouseMoved(pMouseX, pMouseY);
    }

    @Override
    public boolean keyPressed(int pKeyCode, int pScanCode, int pModifiers) {
        stateMachine.keyPressed(pKeyCode, pScanCode, pModifiers);
        return super.keyPressed(pKeyCode, pScanCode, pModifiers);
    }
    @Override
    public boolean keyReleased(int pKeyCode, int pScanCode, int pModifiers) {
        stateMachine.keyReleased(pKeyCode, pScanCode, pModifiers);
        return super.keyReleased(pKeyCode, pScanCode, pModifiers);
    }
}
