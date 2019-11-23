package de.spellmaker.mbrenner.cli

import de.spellmaker.mbrenner.Interval
import org.junit.Test
import kotlin.test.assertEquals

/**
 * Contains tests for the CLI parser
 */
class ParserTest {

    /* test parser */
    @Test
    fun testParse() {
        assertEquals(
            parseInput("[-1, 2],[2,3],       [4,      5]"),
            listOf(Interval(-1, 2), Interval(2, 3), Interval(4, 5))
        )
    }
}