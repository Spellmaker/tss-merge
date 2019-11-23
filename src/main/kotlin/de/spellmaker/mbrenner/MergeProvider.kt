package de.spellmaker.mbrenner

/**
 * Interface for types providing a merge function for intervals
 * The function is defined as follows:
 * Given a list of intervals [a1, b1], [a2, b2]..., return a list where all overlapping intervals have been merged
 */
interface MergeProvider {

    /**
     * Perform the merge algorithm on the provided input
     * The result will be a list of intervals, where all overlapping intervals have been merged
     * The order of the elements in the list is dependent on the actual implementation of the the function
     *
     * @param input A list of intervals to be merged
     * @return A list of non-overlapping intervals, merged from the input
     */
    fun merge(input : List<Interval>) : List<Interval>
}

/**
 * An interval describes an inclusive range of numbers
 * The first index is always smaller than the second
 *
 */
data class Interval(val first : Long, val second : Long) {

    init {
        if (first > second) {
            throw IllegalArgumentException("Expected first index to be less or equal than the second, got $first and $second");
        }
    }
}