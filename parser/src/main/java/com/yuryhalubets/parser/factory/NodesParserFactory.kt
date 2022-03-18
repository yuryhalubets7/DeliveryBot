package com.yuryhalubets.parser.factory

import com.yuryhalubets.parser.NodesParser
import com.yuryhalubets.parser.NodesParserImpl

/**
 * Provides [NodesParser] instance
 * */
object NodesParserFactory {

    /**
     * Create [NodesParser]
     * */
    fun create(): NodesParser {
        return NodesParserImpl()
    }
}