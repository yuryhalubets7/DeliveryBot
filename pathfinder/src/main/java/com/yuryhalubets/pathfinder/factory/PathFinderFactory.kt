package com.yuryhalubets.pathfinder.factory

import com.yuryhalubets.pathfinder.PathFinder
import com.yuryhalubets.pathfinder.PathFinderImpl

/**
 * Provides [PathFinder] instance
 * */
object PathFinderFactory {

    /**
     * Create [PathFinder]
     * */
    fun create(): PathFinder {
        return PathFinderImpl()
    }
}