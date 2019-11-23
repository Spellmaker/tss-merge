package de.spellmaker.mbrenner

import org.junit.Test
import kotlin.test.assertEquals

/**
 * This class provides testcases for {@link MergeProvider} functions, independent of the actual implementation
 */
abstract class BaseMergeTest(val sut : IntervalMerge) {

    /**
     * Test correctness of the provided example
     */
    @Test fun providedTest() {
        baseTest(
            listOf(Interval(25, 30), Interval(2, 19), Interval(14, 23)),
            listOf(Interval(2,23), Interval(25, 30))
        )
    }

    /**
     * Tests correctness for empty input
     */
    @Test fun emptyInput() {
        baseTest(
            listOf(),
            listOf()
        )
    }

    /**
     * Tests all non-overlapping input
     */
    @Test fun nonOverlappingInput() {
        baseTest(
            listOf(Interval(1, 10), Interval(25, 28), Interval(100, 101)),
            listOf(Interval(1, 10), Interval(25, 28), Interval(100, 101))
        )
    }

    /**
     * Test negative intervals
     */
    @Test fun negativeInterval() {
        baseTest(
            listOf(Interval(-22, -11), Interval(-15, 2), Interval(1, 22)),
            listOf(Interval(-22, 22))
        )
    }

    /**
     * Test single intervals, i.e. containing only one element
     */
    @Test fun singleIntervals() {
        baseTest(
            listOf(Interval(1, 1), Interval(2, 2), Interval(3, 3)),
            listOf(Interval(1, 1), Interval(2, 2), Interval(3, 3))
        )
    }

    /**
     * Test occurrences of the same interval
     */
    @Test fun sameInterval() {
        baseTest(
            listOf(Interval(1, 1), Interval(2, 5), Interval(5, 5)),
            listOf(Interval(1, 1), Interval(2, 5))
        )
    }

    /**
     * Test merging of more intervals
     */
    @Test fun longMerge() {
        val input =
            /* intervals (5, 10), (10, 15), ... (50, 55) */
            (1L..10L).map { Interval(it * 5, (it + 1) * 5) }.
            /* single interval */
            plus(Interval(100, 150)).
            /* intervals (505, 510), (510, 515), ... (550, 555) */
            plus((1L..10L).map { Interval(it * 5 + 500, (it + 1) * 5 + 500)})
        val output = listOf(Interval(5, 55), Interval(100, 150), Interval(505, 555))
        baseTest(input, output)
    }

    /**
     * Test a complex merge involving a large spanning interval
     */
    @Test fun allContained() {
        baseTest(
            listOf(Interval(20, 75), Interval(10, 25), Interval(90, 1337), Interval(1, 100), Interval(80, 99), Interval(3, 50)),
            listOf(Interval(1, 1337))
            )
    }

    /**
     * Test the provided constellation
     * Note that internally, the order of the elements is not tested
     * @param input The test input
     * @param expected The expected output
     */
    private fun baseTest(input : List<Interval>, expected : List<Interval>) {
        assertEquals(expected.toSet(), sut(input).toSet(), "actual and expected result differ");
    }
}