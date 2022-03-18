package com.yuryhalubets.navigator.converter

import kotlin.math.absoluteValue

/**
 * [DirectionConverter] implementation that converts vertical/horizontal difference
 * into four main compass directions: north, east, south, and west.
 * Every direction will be repeated 'diff' times
 * */
internal class CardinalPointsDirectionConverter : DirectionConverter {

    override fun convertHorizontalDirection(diff: Int): String {
        return when (diff) {
            0 -> ""
            else -> (if (diff < 0) WEST else EAST).repeat(diff.absoluteValue)
        }
    }

    override fun convertVerticalDirection(diff: Int): String {
        return when (diff) {
            0 -> ""
            else -> (if (diff < 0) SOUTH else NORTH).repeat(diff.absoluteValue)
        }
    }

    private companion object {
        const val WEST = "W"
        const val EAST = "E"
        const val NORTH = "N"
        const val SOUTH = "S"
    }
}