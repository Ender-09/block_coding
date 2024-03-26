package com.ender09.block_coding.foundation.visual_scripting.gui.screen;

import com.ender09.block_coding.content.computer.ComputerScreen;
import net.minecraft.client.gui.components.events.GuiEventListener;

public abstract class ComputerScreenState {
    protected final ComputerScreenStateMachine stateMachine;
    protected final ComputerScreen screen;

    public ComputerScreenState(ComputerScreenStateMachine stateMachine, ComputerScreen screen) {
        this.stateMachine = stateMachine;
        this.screen = screen;
    }

    public abstract void enterState();
    public abstract void exitState();

    public void mouseClicked(double pMouseX, double pMouseY, int pButton) {
    }
    public void mouseReleased(double pMouseX, double pMouseY, int pButton) {
    }
    public void mouseDragged(double pMouseX, double pMouseY, int pButton, double pDragX, double pDragY) {
    }
    public void mouseScrolled(double pMouseX, double pMouseY, double pScrollX, double pScrollY) {
    }
    public void mouseMoved(double pMouseX, double pMouseY) {
    }

    public void keyPressed(int pKeyCode, int pScanCode, int pModifiers) {
    }
    public void keyReleased(int pKeyCode, int pScanCode, int pModifiers) {
    }
}
