package com.phorest.scrabble

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class DictionaryTest {

    @Test
    fun `should return false if word is invalid`() {
        val dictionary = Dictionary(
            InMemoryWordSource(
                listOf(
                    "apple",
                    "banana",
                    "orange"
                )
            )
        )

        assertNull(dictionary.makeValidWordFrom("pear"))
    }

    @Test
    fun `should return true if word is valid`() {
        val dictionary = Dictionary(
            InMemoryWordSource(
                listOf(
                    "apple",
                    "banana",
                    "orange"
                )
            )
        )

        assertEquals("apple", dictionary.makeValidWordFrom("apple"))
    }

    @Test
    fun `should return true if word is valid regardless of letter order`() {
        val dictionary = Dictionary(
            InMemoryWordSource(
                listOf(
                    "apple",
                    "banana",
                    "orange"
                )
            )
        )

        assertEquals("apple", dictionary.makeValidWordFrom("lppae"))
    }

    @Test
    fun `should return all valid words for letter combination`() {
        val dictionary = Dictionary(
            InMemoryWordSource(
                listOf(
                    "adopts",
                    "diotas",
                    "patios",
                    "tables",
                    "patois",
                    "atop",
                    "aids",
                    "adds"
                )
            )
        )

        assertEquals(listOf("patios", "patois"), dictionary.allValidWordsFrom("apotis"))
    }
}