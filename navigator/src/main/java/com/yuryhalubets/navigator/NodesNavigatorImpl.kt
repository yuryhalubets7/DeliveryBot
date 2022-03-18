package com.yuryhalubets.navigator

import com.yuryhalubets.models.Node
import com.yuryhalubets.navigator.converter.DirectionConverter

/**
 * [NodesNavigator] implementation which navigates horizontally first and then vertically.
 * Nodes lists without or with only one element will be skipped.
 * */
internal class NodesNavigatorImpl(
    private val directionConverter: DirectionConverter
) : NodesNavigator {

    override fun createPath(nodes: List<Node>): String {
        if (nodes.size <= 1) {
            return ""
        }
        return nodes
            .windowed(size = WINDOW_SIZE) { (node1, node2) ->
                val horizontalDiff = node2.x - node1.x
                val verticalDiff = node2.y - node1.y
                buildString {
                    append(directionConverter.convertHorizontalDirection(horizontalDiff))
                    append(directionConverter.convertVerticalDirection(verticalDiff))
                    append(DROP_PIZZA)
                }
            }
            .joinToString(separator = "")
    }

    private companion object {
        const val WINDOW_SIZE = 2
        const val DROP_PIZZA = "D"
    }
}