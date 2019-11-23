package de.spellmaker.mbrenner.cli

import de.spellmaker.mbrenner.Interval
import java.util.regex.Pattern

/* cache regex for performance, java platform should always return non-null type */
val parserRegex = Pattern.compile("\\]\\s*,\\s*\\[")!!

/**
 * Simple interval parser
 * Parses a list of intervals from an input string in the form of
 * [a1, b1], [a2, b2]
 * Note that this parser does no actual error handling and is only used to demo the algorithm
 * For productive purposes, a more robust implementation should be developed
 * @param input The input string
 * @return The parsed interval list
 */
fun parseInput(input : String) : List<Interval> =
    /* split at separators between interval bounds */
    input.split(parserRegex)
        /* remove whitespace and interval boundaries */
        .map { it.trim() }
        .map { it.replace("[", "")}
        .map { it.replace("]", "")}
        /* parse long values */
        .map { it.split(",").map { it.trim().toLong() }}
        /* create intervals */
        .map { Interval(it[0], it[1])
}