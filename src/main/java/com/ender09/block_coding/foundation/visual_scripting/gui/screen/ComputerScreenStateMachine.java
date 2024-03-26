package com.ender09.block_coding.foundation.visual_scripting.gui.screen;

import com.ender09.block_coding.content.computer.ComputerScreen;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class ComputerScreenStateMachine {
    public enum ComputerScreenStateKey {
        INACTIVE(InactiveState.class),
        SELECTION(SelectionState.class),
        EDIT(EditState.class),
        PAIRING(PairingState.class);

        final Class<? extends ComputerScreenState> stateClass;

        ComputerScreenStateKey(Class<? extends ComputerScreenState> stateClass) {
            this.stateClass = stateClass;
        }

        public ComputerScreenState createStateInstance(ComputerScreenStateMachine stateMachine, ComputerScreen screen) {
            try {
                return this.stateClass.getDeclaredConstructor(ComputerScreenStateMachine.class, ComputerScreen.class).newInstance(stateMachine, screen);
            } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    Map<ComputerScreenStateKey, ComputerScreenState> stateMap = new HashMap<>();
    protected ComputerScreenState currentState;

    public ComputerScreenStateMachine(ComputerScreen screen) {
        for(ComputerScreenStateKey key : ComputerScreenStateKey.values()) {
            stateMap.put(key, key.createStateInstance(this, screen));
        }
        this.currentState = stateMap.get(ComputerScreenStateKey.EDIT);
        currentState.enterState();
    }

    public ComputerScreenState getState(ComputerScreenStateKey stateKey) {
        return stateMap.get(stateKey);
    }
    public void changeState(ComputerScreenStateKey newStateKey) {
        currentState.exitState();
        currentState = stateMap.get(newStateKey);
        currentState.enterState();
    }

    public void mouseClicked(double pMouseX, double pMouseY, int pButton) {
        currentState.mouseClicked(pMouseX, pMouseY, pButton);
    }
    public void mouseReleased(double pMouseX, double pMouseY, int pButton) {
        currentState.mouseReleased(pMouseX, pMouseY, pButton);
    }
    public void mouseDragged(double pMouseX, double pMouseY, int pButton, double pDragX, double pDragY) {
        currentState.mouseDragged(pMouseX, pMouseY, pButton, pDragX, pDragY);
    }
    public void mouseScrolled(double pMouseX, double pMouseY, double pScrollX, double pScrollY) {
        currentState.mouseScrolled(pMouseX, pMouseY, pScrollX, pScrollY);
    }
    public void mouseMoved(double pMouseX, double pMouseY) {
        currentState.mouseMoved(pMouseX, pMouseY);
    }

    public void keyPressed(int pKeyCode, int pScanCode, int pModifiers) {
        currentState.keyPressed(pKeyCode, pScanCode, pModifiers);
    }
    public void keyReleased(int pKeyCode, int pScanCode, int pModifiers) {
        currentState.keyReleased(pKeyCode, pScanCode, pModifiers);
    }
}
