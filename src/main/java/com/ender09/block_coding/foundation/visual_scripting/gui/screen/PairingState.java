package com.ender09.block_coding.foundation.visual_scripting.gui.screen;

import com.ender09.block_coding.content.computer.ComputerScreen;
import com.ender09.block_coding.foundation.visual_scripting.gui.widgets.node_widgets.NodeParameterWidget;

public class PairingState extends ComputerScreenState {
    NodeParameterWidget inputParameter;
    NodeParameterWidget outputParameter;

    public PairingState(ComputerScreenStateMachine stateMachine, ComputerScreen screen) {
        super(stateMachine, screen);
    }

    @Override
    public void enterState() {
    }

    @Override
    public void exitState() {

    }

    @Override
    public void mouseReleased(double pMouseX, double pMouseY, int pButton) {
        pairNodeParameters();
        stateMachine.changeState(ComputerScreenStateMachine.ComputerScreenStateKey.EDIT);
    }
    private void pairNodeParameters() {
        //Check if hovering over another parameter
        //Check if parameter is from same node >> cancel
        //Check if parameter is of same type >> cancel
        //Check if parameter is of opposite type >> Check if parameters match value types>> Pair
        //OR Check if parameter is on canvas >> open node select menu
    }
}
