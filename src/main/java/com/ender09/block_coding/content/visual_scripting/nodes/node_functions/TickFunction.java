package com.ender09.block_coding.content.visual_scripting.nodes.node_functions;

import com.ender09.block_coding.foundation.visual_scripting.NodeFunction;
import com.ender09.block_coding.foundation.visual_scripting.node_parameters.EventFlowOutputParameter;
import com.ender09.block_coding.foundation.visual_scripting.node_parameters.NodeParameter;
import com.ender09.block_coding.util.EventListener;
import com.ender09.block_coding.util.EventSource;

public class TickFunction extends NodeFunction implements EventListener<Integer> {
    public static final String DESCRIPTION = "Returns a boolean on whether two values are the same or not.";

    public TickFunction(EventSource<Integer> OnTickEvent) {
        super(DESCRIPTION);
        outputs = new NodeParameter[]{
                new EventFlowOutputParameter("OnGameTick")
        };

        OnTickEvent.addListener(this);
    }

    @Override
    public void onEventFire(Integer[] args) {
        trigger();
    }
    @Override
    public void onTrigger() {
        EventFlowOutputParameter eventFlowParameter = (EventFlowOutputParameter) outputs[0];
        eventFlowParameter.trigger();
    }
}
