package com.yuryhalubets.bot

import com.yuryhalubets.models.Node
import com.yuryhalubets.navigator.NodesNavigator
import com.yuryhalubets.parser.NodesParser
import com.yuryhalubets.parser.error.NodesParseException
import com.yuryhalubets.pathfinder.PathFinder
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
internal class BotTest {

    private val parser: NodesParser = mockk()
    private val pathFinder: PathFinder = mockk()
    private val navigator: NodesNavigator = mockk()

    private val bot: Bot = DeliveryBotImpl(
        parser = parser,
        pathFinder = pathFinder,
        navigator = navigator,
    )

    @Test
    fun `parser thrown exception - failure result received`() {
        every { parser.parse(any()) } throws NodesParseException("Invalid input")
        assertTrue(bot.execute("invalid input") is Bot.Result.Failure)
    }

    @Test
    fun `parser succeed - success result received`() {
        every { parser.parse(any()) } returns listOf(mockk())
        every { pathFinder.getShortestPath(any()) } returns mockk()
        every { navigator.createPath(any()) } returns "D"

        assertTrue(bot.execute("valid input") is Bot.Result.Success)
    }

    @Test
    fun `add start node before finding path`() {
        val mockedNode = mockk<Node>()

        every { parser.parse(any()) } returns listOf(mockedNode)
        every { pathFinder.getShortestPath(any()) } returns mockk()
        every { navigator.createPath(any()) } returns "D"

        bot.execute("input")

        verify { pathFinder.getShortestPath(listOf(Node(0, 0), mockedNode)) }
    }

    @Test
    fun `don't change found path for navigator`() {
        val foundPath = listOf<Node>(mockk(), mockk())

        every { parser.parse(any()) } returns listOf(mockk())
        every { pathFinder.getShortestPath(any()) } returns foundPath
        every { navigator.createPath(any()) } returns "NDD"

        bot.execute("input")

        verify { navigator.createPath(foundPath) }
    }

    @Test
    fun `don't change navigator result`() {
        val path = "ENNNDEEEND"
        every { parser.parse(any()) } returns listOf(mockk())
        every { pathFinder.getShortestPath(any()) } returns mockk()
        every { navigator.createPath(any()) } returns path

        val result = bot.execute("input")
        assertEquals(Bot.Result.Success(path), result)
    }
}
