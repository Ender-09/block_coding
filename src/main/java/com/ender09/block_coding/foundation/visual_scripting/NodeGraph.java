package com.ender09.block_coding.foundation.visual_scripting;

import com.ender09.block_coding.content.visual_scripting.nodes.node_functions.TickerFunction;

import java.util.ArrayList;
import java.util.List;

public class NodeGraph {
    public List<Node> nodes = new ArrayList<>();
    public List<NodeWidget> nodeWidgets = new ArrayList<>();
    private List<TickerFunction> tickers = new ArrayList<>();

    public void addNode(Node node) {
        if(nodes.contains(node)) return;

        nodes.add(node);
        NodeWidget widget = createNodeWidget(node);
        nodeWidgets.add(widget);

        /*if(node instanceof TickerNode tickerNode) {
            tickers.add(tickerNode);
        }*/
    }
    public void removeNode(Node node) {
        if(!nodes.contains(node)) return;
        nodes.remove(node);
        //removeNodeWidget

        /*if(node instanceof TickerNode tickerNode) {
            tickers.remove(tickerNode);
        }*/
    }

    NodeWidget createNodeWidget(Node node) {
        return null;
    }

    public void tick() {
        /*for(TickerNode node : tickers) {
            node.tick();
        }*/
    }
 }
