package com.ender09.block_coding.foundation.visual_scripting.gui;

import com.ender09.block_coding.foundation.visual_scripting.NodeGraph;
import com.ender09.block_coding.foundation.visual_scripting.nodes.Node;
import com.ender09.block_coding.network.PacketHandler;

public class NodeWidgetData {
    protected NodeGraph nodeGraph;
    protected String nodeId;
    protected int x;
    protected int y;

    public NodeWidgetData(NodeGraph nodeGraph, String nodeId) {
        this.nodeGraph = nodeGraph;
        this.nodeId = nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }
    public String getNodeId() {
        return nodeId;
    }

    public Node getNode() {
        return nodeGraph.getNode(nodeId);
    }

    public void setX(int x) {
        this.x = x;
        //PacketHandler.sendToAllClients(new CUpdateWidgetPosition(nodeId, x, y));
    }
    public int getX() {
        return x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }
}
