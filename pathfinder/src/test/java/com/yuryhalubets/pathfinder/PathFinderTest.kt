package com.yuryhalubets.pathfinder


import com.yuryhalubets.models.Node
import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
internal class PathFinderTest {

    private val pathFinder: PathFinder = PathFinderImpl()

    @Test
    fun `list size is small - same shortest path`() {
        val testNodes = listOf(
            Node(x = 0, y = 0),
            Node(x = 1, y = 4)
        )
        val shortestPath = pathFinder.getShortestPath(testNodes)
        assertEquals(testNodes, shortestPath)
    }

    @Test
    fun `normal list - correct shortest path`() {
        val testNodes = listOf(
            Node(x = 0, y = 0),
            Node(x = 0, y = 0),
            Node(x = 1, y = 3),
            Node(x = 4, y = 4),
            Node(x = 4, y = 2),
            Node(x = 4, y = 2),
            Node(x = 0, y = 1),
            Node(x = 3, y = 2),
            Node(x = 2, y = 3),
            Node(x = 4, y = 1),
        )
        val shortestPath = pathFinder.getShortestPath(testNodes)
        val expectedPath = listOf(
            Node(x = 0, y = 0),
            Node(x = 0, y = 0),
            Node(x = 0, y = 1),
            Node(x = 1, y = 3),
            Node(x = 2, y = 3),
            Node(x = 3, y = 2),
            Node(x = 4, y = 2),
            Node(x = 4, y = 2),
            Node(x = 4, y = 1),
            Node(x = 4, y = 4),
        )
        assertEquals(expectedPath, shortestPath)
    }
}
