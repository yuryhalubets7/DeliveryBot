package com.yuryhalubets.bot.factory

import com.yuryhalubets.bot.Bot
import com.yuryhalubets.bot.DeliveryBotImpl
import com.yuryhalubets.navigator.factory.NodesNavigatorFactory
import com.yuryhalubets.parser.factory.NodesParserFactory
import com.yuryhalubets.pathfinder.factory.PathFinderFactory

/**
 * Provides [Bot] instance
 * */
object BotFactory {

    /**
     * Create [Bot]
     * */
    fun create(): Bot {
        return DeliveryBotImpl(
            parser = NodesParserFactory.create(),
            pathFinder = PathFinderFactory.create(),
            navigator = NodesNavigatorFactory.create(),
        )
    }
}