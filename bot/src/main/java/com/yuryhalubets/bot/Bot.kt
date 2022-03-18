package com.yuryhalubets.bot

/**
 * Bot should accept input value and return [Result] of processing.
 * */
interface Bot {
    /**
     * Execute bot with provided [input] string
     * */
    fun execute(input: String): Result

    /**
     * Result of [Bot] processing.
     * */
    sealed class Result {

        /**
         * Success result.
         * @param path contain correct path
         * */
        data class Success(val path: String): Result()

        /**
         * Failure result.
         * @param cause store [Throwable] that was thrown
         * */
        data class Failure(val cause: Throwable): Result()
    }
}