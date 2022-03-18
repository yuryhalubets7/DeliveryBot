package com.yuryhalubets.parser.error

import com.yuryhalubets.models.Node


/**
 * NodesOutOfBoundsException will be thrown when [Node]'s position isn't in grid.
 * */
internal class NodesOutOfBoundsException(nodes: List<Node>) : NodesParseException(
    nodes.joinToString(
        prefix = "The following points out of bounds: "
    ) { node ->
        "(${node.x}, ${node.y})"
    }
)