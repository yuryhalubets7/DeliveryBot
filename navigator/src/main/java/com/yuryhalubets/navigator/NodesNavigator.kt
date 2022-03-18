package com.yuryhalubets.navigator

import com.yuryhalubets.models.Node

/**
 * Helps to navigate between nodes
 * */
interface NodesNavigator {
    /**
     * Create path for provided nodes.
     * */
    fun createPath(nodes: List<Node>): String
}