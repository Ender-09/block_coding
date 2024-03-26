//package com.ender09.block_coding.foundation.visual_scripting.gui.widgets.node_widgets;
//
//import com.ender09.block_coding.foundation.visual_scripting.gui.widgets.AbstractHierarchyWidget;
//import com.ender09.block_coding.foundation.visual_scripting.gui.widgets.IDescriptionWidget;
//import com.ender09.block_coding.foundation.visual_scripting.nodes.NodeFunction;
//import com.ender09.block_coding.foundation.visual_scripting.nodes.node_parameters.NodeParameter;
//import net.minecraft.client.gui.GuiGraphics;
//import net.minecraft.client.gui.components.AbstractWidget;
//import net.minecraft.client.gui.narration.NarrationElementOutput;
//import net.minecraft.network.chat.Component;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//public class NodeFunctionWidget extends AbstractHierarchyWidget implements IDescriptionWidget {
//    NodeWidget nodeWidget;
//    NodeFunction nodeFunction;
//    NodeParameterWidget[] inputParameters;
//    NodeParameterWidget[] outputParameters;
//
//    public NodeFunctionWidget(int pX, int pY, int pWidth, int pHeight, NodeWidget nodeWidget, NodeFunction nodeFunction) {
//        super(pX, pY, pWidth, pHeight, Component.empty(), nodeWidget);
//        this.nodeWidget = nodeWidget;
//        this.nodeFunction = nodeFunction;
//
//        //inputParameters = nodeFunction.getInputs() == null ? null : createParameterWidgets(nodeFunction.getInputs(), true);
//        //outputParameters = nodeFunction.getOutputs() == null ? null : createParameterWidgets(nodeFunction.getOutputs(), false);
//    }
//    private NodeParameterWidget[] createParameterWidgets(NodeParameter[] parameters, boolean isInputParameter) {
//        NodeParameterWidget[] nodeParameterWidgets = new NodeParameterWidget[parameters.length];
//        for(int i = 0; i < parameters.length; i++) {
//            int x = isInputParameter ? -8 : this.width + 2;
//            nodeParameterWidgets[i] = new NodeParameterWidget(x,12 * i, this, parameters[i], isInputParameter);
//        }
//        return nodeParameterWidgets;
//    }
//
//    @Override
//    protected void renderWidget(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
//
//    }
//    public List<AbstractWidget> getComponentsToRender() {
//        List<AbstractWidget> components = new ArrayList<>();
//        if(inputParameters != null)
//            components.addAll(Arrays.asList(inputParameters));
//        if(outputParameters != null)
//            components.addAll(Arrays.asList(outputParameters));
//
//        return components;
//    }
//
//
//    @Override
//    protected void updateWidgetNarration(NarrationElementOutput pNarrationElementOutput) {
//
//    }
//
//    @Override
//    public boolean mouseClicked(double pMouseX, double pMouseY, int pButton) {
//        if (this.active && this.visible) {
//            if (this.isValidClickButton(pButton)) {
//                boolean flag = this.clicked(pMouseX, pMouseY);
//                if (flag) {
//                    this.onClick(pMouseX, pMouseY);
//                    return true;
//                }
//            }
//
//            return false;
//        } else {
//            return false;
//        }
//    }
//
//    @Override
//    public String getDescription() {
//        return nodeFunction.getDescription();
//    }
//}
