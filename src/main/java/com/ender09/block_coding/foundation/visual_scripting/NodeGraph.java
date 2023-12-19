package com.ender09.block_coding.foundation.visual_scripting;

import com.ender09.block_coding.content.visual_scripting.nodes.CompareNodes;
import com.ender09.block_coding.util.EventSource;

import java.util.ArrayList;
import java.util.List;

public class NodeGraph {
    public List<Node> nodes = new ArrayList<>();
    public List<NodeWidget> nodeWidgets = new ArrayList<>();
    public EventSource<Integer> tickerEvent = new EventSource<>();

    public void addNode(Node node) {
        if(nodes.contains(node)) return;
        nodes.add(node);
        //addNodeWidget
    }
    public void removeNode(Node node) {
        if(!nodes.contains(node)) return;
        nodes.remove(node);
        //removeNodeWidget
    }

    public void tick() {
        tickerEvent.fireEvent();
    }
 }
