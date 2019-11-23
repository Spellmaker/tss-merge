package de.spellmaker.mbrenner

import kotlin.test.assertEquals

/**
 * This class provides testcases for {@link MergeProvider} functions, independent of the actual implementation
 */
class BaseMergeTest {

    /**
     * Test correctness of the provided example
     * @param sut An implementation of the algorithm
     */
    fun providedTest(sut : MergeProvider) {
        baseTest(sut,
            listOf(Interval(25, 30), Interval(2, 19), Interval(14, 23)),
            listOf(Interval(2,23), Interval(25, 30))
        )
    }

    /**
     * Tests correctness for empty input
     * @param sut An implementation of the algorithm
     */
    fun emptyInput(sut : MergeProvider) {
        baseTest(sut,
            listOf(),
            listOf()
        )
    }

    /**
     * Tests all non-overlapping input
     * @param sut An implementation of the algorithm
     */
    fun nonOverlappingInput(sut : MergeProvider) {
        baseTest(sut,
            listOf(Interval(1, 10), Interval(25, 28), Interval(100, 101)),
            listOf(Interval(1, 10), Interval(25, 28), Interval(100, 101))
        )
    }

    /**
     * Test negative intervals
     * @param sut An implementation of the algorithm
     */
    fun negativeInterval(sut : MergeProvider) {
        baseTest(sut,
            listOf(Interval(-22, -11), Interval(-15, 2), Interval(1, 22)),
            listOf(Interval(-22, 22))
        )
    }

    /**
     * Test single intervals, i.e. containing only one element
     * @param sut An implementation of the algorithm
     */
    fun singleIntervals(sut : MergeProvider) {
        baseTest(sut,
            listOf(Interval(1, 1), Interval(2, 2), Interval(3, 3)),
            listOf(Interval(1, 1), Interval(2, 2), Interval(3, 3))
        )
    }

    /**
     * Test occurrences of the same interval
     * @param sut An implementation of the algorithm
     */
    fun sameInterval(sut : MergeProvider) {
        baseTest(sut,
            listOf(Interval(1, 1), Interval(2, 5), Interval(5, 5)),
            listOf(Interval(1, 1), Interval(2, 5))
        )
    }

    /**
     * Test merging of more intervals
     * @param sut An implementation of the algorithm
     */
    fun longMerge(sut : MergeProvider) {
        val input = (1L..10L).map { Interval(it * 5, (it + 1) * 5) }.
            plus(Interval(100, 150)).
            plus((1L..10L).map { Interval(it * 5 + 500, (it + 1) * 500)})
        val output = listOf(Interval(5, 55), Interval(100, 150), Interval(505, 555))
        baseTest(sut, input, output)
    }

    /**
     * Test a complex merge involving a large spanning interval
     * @param sut An implementation of the algorithm
     */
    fun allContained(sut : MergeProvider) {
        baseTest(sut,
            listOf(Interval(20, 75), Interval(10, 25), Interval(90, 1337), Interval(1, 100), Interval(80, 99), Interval(3, 50)),
            listOf(Interval(1, 1337))
            )
    }

    /**
     * Test the provided constellation
     * Note that internally, the order of the elements is not tested
     * @param sut The provider to test
     * @param input The test input
     * @param expected The expected output
     */
    private fun baseTest(sut : MergeProvider, input : List<Interval>, expected : List<Interval>) {
        assertEquals(sut.merge(input).toSet(), expected.toSet(), "actual and expected result differ");
    }
}