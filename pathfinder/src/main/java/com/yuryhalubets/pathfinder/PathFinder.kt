package com.yuryhalubets.pathfinder

import com.yuryhalubets.models.Node

/**
 * Finds the shortest path between provided nodes.
 * */
interface PathFinder {
    /**
     * Get shortest path for provided nodes.
     * */
    fun getShortestPath(nodes: List<Node>): List<Node>
}