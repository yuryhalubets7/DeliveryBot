package com.yuryhalubets.navigator.factory

import com.yuryhalubets.navigator.converter.CardinalPointsDirectionConverter
import com.yuryhalubets.navigator.converter.DirectionConverter

/**
 * Provides [DirectionConverter] instance
 * */
internal object DirectionConverterFactory {
    /**
     * Create [DirectionConverter]
     * */
    fun create(): DirectionConverter {
        return CardinalPointsDirectionConverter()
    }
}