package de.spellmaker.mbrenner.impl

import de.spellmaker.mbrenner.Interval
import kotlin.math.max

/**
 * In memory implementation of {@link merge}
 */
fun mergeInMemory(input: List<Interval>): List<Interval> {
    if (input.isEmpty()) {
        return input
    }
    /* sort input by lower bound */
    val sortedInput = input.sortedBy { it.first }

    /* reduce elements via fold */
    val state : Pair<MutableList<Interval>, Interval> =
        sortedInput.fold(Pair(mutableListOf(), sortedInput.first()), ::mergeNext)

    /* do not forget final element */
    state.first.add(state.second)
    return state.first
}

/**
 * Helper function processing exactly one element
 * @param state The current state with the first element containing the result and the second the current interval
 * @param next The next interval to be considered
 * @return The updated state
 */
private fun mergeNext(state : Pair<MutableList<Interval>, Interval>, next : Interval) =
    if (state.second.second >= next.first) {
        /* intervals overlap, merge intervals and update state*/
        Pair(state.first, Interval(state.second.first, max(state.second.second, next.second)))
    } else {
        /* intervals do not overlap, add result and update state */
        state.first.add(state.second)
        Pair(state.first, next)
    }