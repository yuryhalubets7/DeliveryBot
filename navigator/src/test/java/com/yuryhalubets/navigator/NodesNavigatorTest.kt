package com.yuryhalubets.navigator


import com.yuryhalubets.models.Node
import com.yuryhalubets.navigator.converter.DirectionConverter
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
internal class NodesNavigatorTest {

    private val directionConverter: DirectionConverter = mockk() {
        every { convertVerticalDirection(any()) } returns "V"
        every { convertHorizontalDirection(any()) } returns "H"
        every { convertVerticalDirection(0) } returns ""
        every { convertHorizontalDirection(0) } returns ""
    }

    private val navigator: NodesNavigator = NodesNavigatorImpl(directionConverter)

    @Test
    fun `only first node - empty string result`() {
        val nodes = listOf(Node(x = 0, y = 0))
        val path = navigator.createPath(nodes)
        assertTrue(path.isEmpty())
    }

    @Test
    fun `normal nodes list - correct result`() {
        val nodes = listOf(
            Node(x = 0, y = 0),
            Node(x = 0, y = 1),
            Node(x = 3, y = 2),
            Node(x = 3, y = 2),
            Node(x = 4, y = 2),
        )
        val path = navigator.createPath(nodes)
        assertEquals("VDHVDDHD", path)
    }
}