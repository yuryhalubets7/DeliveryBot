package com.yuryhalubets.parser.error

/**
 * Incorrect data exception can be thrown in case of wrong input.
 * */
internal class IncorrectDataException : NodesParseException(
    "Incorrect information. Make sure you entered the correct information."
)