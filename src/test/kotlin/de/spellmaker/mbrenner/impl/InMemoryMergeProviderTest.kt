package de.spellmaker.mbrenner.impl

import de.spellmaker.mbrenner.BaseMergeTest

/**
 * Tests the in memory merge provider
 */
class InMemoryMergeProviderTest : BaseMergeTest(::mergeInMemory)