package com.ender09.block_coding.content.visual_scripting.nodes.node_functions.compare;

import com.ender09.block_coding.foundation.visual_scripting.NodeFunction;
import com.ender09.block_coding.foundation.visual_scripting.NodeFunctionParameter;

public abstract class AbstractCompareFunction extends NodeFunction {
    protected CompareType compareType;

    public AbstractCompareFunction(String name, String description) {
        super(name, description);
        inputs = new NodeFunctionParameter[2];
        outputs = new NodeFunctionParameter[1];
    }

    public void trigger() {
        outputs[0].setValue(evaluate());
    }

    protected abstract Boolean evaluate();

    public void setA(Object a) {
        inputs[0].setValue(a);
    }
    public Object getA() {
        return inputs[0].getValue();
    }

    public void setB(Object b) {
        inputs[1].setValue(b);
    }
    public Object getB() {
        return inputs[1].getValue();
    }

    public Boolean getOutput() {
        return (Boolean) outputs[0].getValue();
    }

    public void setCompareType(CompareType compareType) {
        this.compareType = compareType;
    }
    public CompareType getCompareType() {
        return compareType;
    }
}
