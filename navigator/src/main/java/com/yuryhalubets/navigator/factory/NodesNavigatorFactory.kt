package com.yuryhalubets.navigator.factory

import com.yuryhalubets.navigator.NodesNavigator
import com.yuryhalubets.navigator.NodesNavigatorImpl

/**
 * Provides [NodesNavigator] instance
 * */
object NodesNavigatorFactory {
    /**
     * Create [NodesNavigator]
     * */
    fun create(): NodesNavigator {
        val directionsConverter = DirectionConverterFactory.create()
        return NodesNavigatorImpl(directionsConverter)
    }
}