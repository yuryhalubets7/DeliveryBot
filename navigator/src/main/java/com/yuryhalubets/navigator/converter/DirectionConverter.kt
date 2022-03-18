package com.yuryhalubets.navigator.converter

/**
 * Converts integer difference between nodes into string representation
 * */
internal interface DirectionConverter {
    fun convertVerticalDirection(diff: Int): String
    fun convertHorizontalDirection(diff: Int): String
}