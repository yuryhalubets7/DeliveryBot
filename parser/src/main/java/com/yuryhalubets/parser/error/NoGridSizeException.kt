package com.yuryhalubets.parser.error


/**
 * NoGridSizeException will be thrown when input string doesn't have grid size.
 * */
internal class NoGridSizeException : NodesParseException(
    message = "Can't find grid size. Make sure you entered the correct information."
)