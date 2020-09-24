package com.phorest.scrabble

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class WordTest {

    @Test
    fun `should return highest triple letter score for word`() {
        assertEquals(42, Word("quiz").highestTripleLetterScore())
        assertEquals(36, Word("zebra").highestTripleLetterScore())
    }
}


