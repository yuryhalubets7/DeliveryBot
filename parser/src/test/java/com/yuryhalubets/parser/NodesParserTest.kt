package com.yuryhalubets.parser

import com.yuryhalubets.models.Node
import com.yuryhalubets.parser.error.IncorrectDataException
import com.yuryhalubets.parser.error.InvalidGridSizeException
import com.yuryhalubets.parser.error.NoGridSizeException
import com.yuryhalubets.parser.error.NodesOutOfBoundsException
import junit.framework.TestCase.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
internal class NodesParserTest {

    private val nodesParser: NodesParser = NodesParserImpl()

    @Test
    fun `valid input - correct nodes list`() {
        val input = "5x5 (1, 3) (4, 4)"
        val nodes = nodesParser.parse(input)
        val expectedNodes = listOf(
            Node(x = 1, y = 3),
            Node(x = 4, y = 4),
        )
        assertEquals(expectedNodes, nodes)
    }

    @Test
    fun `trim input before validation`() {
        val input = " 5x5 (1, 3) (4, 4) "
        val nodes = nodesParser.parse(input)
        val expectedNodes = listOf(
            Node(x = 1, y = 3),
            Node(x = 4, y = 4),
        )
        assertEquals(expectedNodes, nodes)
    }

    @Test
    fun `no grid size - NoGridSizeException thrown`() {
        val inputList = listOf(
            "5x (1, 3) (4, 4)",
            "x5 (1, 3) (4, 4)",
            "x (1, 3) (4, 4)",
            "(1, 3) (4, 4)",
        )
        inputList.forEach { input ->
            assertThrows(NoGridSizeException::class.java) {
                nodesParser.parse(input)
            }
        }
    }

    @Test
    fun `invalid grid size - InvalidGridSizeException thrown`() {
        val inputList = listOf(
            "0x5 (1, 3) (4, 4)",
            "5x0 (1, 3) (4, 4)",
        )
        inputList.forEach { input ->
            assertThrows(InvalidGridSizeException::class.java) {
                nodesParser.parse(input)
            }
        }
    }

    @Test
    fun `incorrect data - IncorrectDataException thrown`() {
        val inputList = listOf(
            "5x5 (1, 3",
            "5x5 (1, 3) (",
            "5x5 (a, b)",
            "5x5 (1, 3) (4, 4) (a, b)",
        )
        inputList.forEach { input ->
            assertThrows(IncorrectDataException::class.java) {
                nodesParser.parse(input)
            }
        }
    }

    @Test
    fun `input out of bound nodes - NodesOutOfBoundsException thrown`() {
        val input = "5x5 (1, 3) (4, 5)"
        assertThrows(NodesOutOfBoundsException::class.java) {
            nodesParser.parse(input)
        }
    }
}
