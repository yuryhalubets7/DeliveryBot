package com.yuryhalubets.parser.error

/**
 * InvalidGridSizeException will be thrown in case if provided grid size is less or equals 0.
 * */
internal class InvalidGridSizeException : NodesParseException(
    "Grid size is incorrect. Please use only positive values."
)