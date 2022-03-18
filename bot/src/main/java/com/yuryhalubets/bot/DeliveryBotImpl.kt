package com.yuryhalubets.bot

import com.yuryhalubets.models.Node
import com.yuryhalubets.navigator.NodesNavigator
import com.yuryhalubets.parser.NodesParser
import com.yuryhalubets.parser.error.NodesParseException
import com.yuryhalubets.pathfinder.PathFinder


/**
 * Bot implementation that add start node to
 * */
internal class DeliveryBotImpl(
    private val parser: NodesParser,
    private val pathFinder: PathFinder,
    private val navigator: NodesNavigator,
) : Bot {

    override fun execute(input: String): Bot.Result {
        val nodes = try {
            parser.parse(input)
        } catch (e: NodesParseException) {
            return Bot.Result.Failure(cause = e)
        }

        val startNode = Node(x = 0, y = 0)
        val fullNodesList = mutableListOf(startNode).apply { addAll(nodes) }

        val foundPath = pathFinder.getShortestPath(fullNodesList)
        return Bot.Result.Success(navigator.createPath(foundPath))
    }
}
