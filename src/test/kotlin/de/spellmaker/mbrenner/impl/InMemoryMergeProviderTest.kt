package de.spellmaker.mbrenner.impl

import de.spellmaker.mbrenner.BaseMergeTest
import org.junit.Test

/**
 * Tests the in memory merge provider
 */
class InMemoryMergeProviderTest : BaseMergeTest() {

    @Test
    fun testAll() {
        runAllTests(InMemoryMergeProvider())
    }
}