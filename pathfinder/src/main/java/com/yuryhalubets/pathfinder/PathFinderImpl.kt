package com.yuryhalubets.pathfinder

import com.yuryhalubets.models.Node
import kotlin.math.absoluteValue

/**
 * [PathFinder] with brute force implementation.
 * Nodes lists that have only 2 nodes will be skipped.
 * */
internal class PathFinderImpl : PathFinder {

    private val minPath = mutableListOf<Node>()
    private val currentPath = mutableListOf<Node>()
    private var minLength = Integer.MAX_VALUE
    private var currentLength = 0
    private var nodesSize = 0

    override fun getShortestPath(nodes: List<Node>): List<Node> {
        if (nodes.size < MIN_NODES_SIZE) {
            return nodes
        }
        resetData(nodes)
        // Start from second element in list. First node (0, 0) should not be moved
        find(1)
        return minPath
    }

    private fun resetData(nodes: List<Node>) {
        minPath.clear()
        currentPath.clear()
        currentPath.addAll(nodes)
        nodesSize = nodes.size
        minLength = Integer.MAX_VALUE
        currentLength = 0
    }

    private fun find(index: Int) {
        if (index == nodesSize) {
            if (currentLength < minLength) {
                minLength = currentLength
                minPath.clear()
                minPath.addAll(currentPath)
            }
            return
        }

        for (i in index until nodesSize) {
            swapNodes(currentPath, i, index)

            val distance = distanceBetween(currentPath[index - 1], currentPath[index])
            currentLength += distance

            if (currentLength <= minLength) {
                find(index + 1)
            }

            currentLength -= distance
            swapNodes(currentPath, i, index)
        }
    }

    private fun distanceBetween(node1: Node, node2: Node): Int {
        return (node1.x - node2.x).absoluteValue + (node1.y - node2.y).absoluteValue
    }

    private fun swapNodes(mutableList: MutableList<Node>, from: Int, to: Int) {
        if (from == to) {
            return
        }
        val temp = mutableList[from]
        mutableList[from] = mutableList[to]
        mutableList[to] = temp
    }

    private companion object {
        const val MIN_NODES_SIZE = 3
    }
}
