package de.spellmaker.mbrenner

/**
 * Merges a list of intervals
 * The function is defined as follows:
 * Given a list of intervals [a1, b1], [a2, b2]..., return a list where all overlapping intervals have been merged
 * The order of elements in the resulting list is undefined
 */
typealias IntervalMerge = (input : List<Interval>) -> List<Interval>

/**
 * An interval describes an inclusive range of numbers
 * The first index is always smaller than the second
 */
data class Interval(val first : Long, val second : Long) {

    init {
        if (first > second) {
            throw IllegalArgumentException("Expected first index to be less or equal than the second, got $first and $second");
        }
    }
}