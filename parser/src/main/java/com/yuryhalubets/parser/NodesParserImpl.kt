package com.yuryhalubets.parser

import com.yuryhalubets.models.Node
import com.yuryhalubets.parser.model.GridSize
import com.yuryhalubets.parser.error.*
import com.yuryhalubets.parser.error.IncorrectDataException
import com.yuryhalubets.parser.error.InvalidGridSizeException
import com.yuryhalubets.parser.error.NoGridSizeException
import com.yuryhalubets.parser.error.NodesOutOfBoundsException

/**
 * [NodesParser] implementation that accepts following pattern: "5x5 (1, 3) (4, 4)".
 * */
internal class NodesParserImpl : NodesParser {

    @Throws(NodesParseException::class)
    override fun parse(input: String): List<Node> {
        val trimmedInput = input.trim()
        val matches = inputRegex.find(trimmedInput)
        if (matches == null) {
            throw IncorrectDataException()
        } else {
            val gridSizeGroup = matches.groups[1]?.value ?: throw NoGridSizeException()

            val foundMatch = matches.groups[0]?.value.orEmpty()
            if (foundMatch.length != trimmedInput.length) {
                throw IncorrectDataException()
            }

            val nodesGroup = matches.groups[2]?.value ?: throw IncorrectDataException()

            val gridSize = getGridSize(gridSizeGroup)
            val nodes = getNodes(nodesGroup)
            validateNodes(gridSize, nodes)
            return nodes
        }
    }

    @Throws(NodesParseException::class)
    private fun getGridSize(gridSizeGroup: String): GridSize {
        val (width, height) = gridSizeGroup.split("x")
            .take(2)
            .map { it.toInt() }
            .toList()
        if (width <= 0 || height <= 0) {
            throw InvalidGridSizeException()
        }
        return GridSize(width, height)
    }

    private fun getNodes(nodesGroup: String): List<Node> {
        return Regex("[0-9]+")
            .findAll(nodesGroup)
            .map { it.value.toInt() }
            .windowed(size = 2, step = 2)
            .map { (x, y) -> Node(x = x, y = y) }
            .toList()
    }

    @Throws(NodesParseException::class)
    private fun validateNodes(gridSize: GridSize, nodes: List<Node>) {
        val horizontalAxisRange = 0 until gridSize.width
        val verticalAxisRange = 0 until gridSize.height
        val outOfBoundsNodes = nodes.filter { node ->
            node.x !in horizontalAxisRange || node.y !in verticalAxisRange
        }
        if (outOfBoundsNodes.isNotEmpty()) {
            throw NodesOutOfBoundsException(outOfBoundsNodes)
        }
    }

    private companion object {
        val inputRegex = "([0-9]+x[0-9]+)?\\s((?:\\([0-9]+,\\s[0-9]+\\)\\s?)*)".toRegex()
    }
}
