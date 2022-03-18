package com.yuryhalubets.parser

import com.yuryhalubets.models.Node
import com.yuryhalubets.parser.error.NodesParseException

/**
 * Parses the input string and returns a list of [Node]s
 * */
interface NodesParser {
    /**
     * Get list of [Node]s from input string.
     * @throws NodesParseException for invalid input string
     * */
    @Throws(NodesParseException::class)
    fun parse(input: String) : List<Node>
}