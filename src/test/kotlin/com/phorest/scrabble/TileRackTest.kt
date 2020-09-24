package com.phorest.scrabble

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class TileRackTest {

    @Test
    fun `should return 7 letter word`() {
        val tileRack = TileRack(
            listOf(
                Tile.A,
                Tile.B,
                Tile.S,
                Tile.E,
                Tile.N,
                Tile.C,
                Tile.E
            ),
            Dictionary(
                InMemoryWordSource(
                    listOf("absence", "address", "achieve")
                )
            )
        )

        assertEquals("absence", tileRack.longestValidWord()?.value)
    }

    @Test
    fun `should return 7 letter word regardless of the order the letters are submitted in`() {
        val tileRack = TileRack(
            listOf(
                Tile.B,
                Tile.A,
                Tile.S,
                Tile.E,
                Tile.C,
                Tile.N,
                Tile.E
            ),
            Dictionary(
                InMemoryWordSource(
                    listOf("absence", "address", "achieve")
                )
            )
        )

        assertEquals("absence", tileRack.longestValidWord()?.value)
    }

    @Test
    fun `should return valid word even if not all tiles are used`() {
        val tileRack = TileRack(
            listOf(
                Tile.A,
                Tile.C,
                Tile.E,
                Tile.T,
                Tile.C,
                Tile.P,
                Tile.Z
            ),
            Dictionary(
                InMemoryWordSource(
                    listOf("absence", "address", "achieve", "pet")
                )
            )
        )

        assertEquals("pet", tileRack.longestValidWord()?.value)
    }

    @Test
    fun `should return highest scoring word`() {

        val tileRack = TileRack(
            listOf(
                Tile.P,
                Tile.A,
                Tile.D,
                Tile.T,
                Tile.O,
                Tile.I,
                Tile.S
            ),
            Dictionary(
                InMemoryWordSource(
                    listOf("diotas", "adopts", "patios") // "diotas" is a 7 point word, "adopts" is 9 points, "patios" is 8 points
                )
            )
        )

        val highestScoringWord = tileRack.highestScoringWord()
        assertEquals("adopts", highestScoringWord?.value)
        assertEquals(9, highestScoringWord?.totalScore())
    }

    @Test
    fun `should consider that highest scoring word isn't always the longest valid word`() {
        val tileRack = TileRack(
            listOf(
                Tile.Z,
                Tile.I,
                Tile.N,
                Tile.C,
                Tile.K,
                Tile.E,
                Tile.L
            ),
            Dictionary(
                InMemoryWordSource(
                    listOf("nickel", "zinc", "other")
                )
            )
        )

        assertEquals("nickel", tileRack.longestValidWord()?.value)
        assertEquals("zinc", tileRack.highestScoringWord()?.value)

    }

    @Test
    fun `should not allow same letter to be used twice`() {
        val tileRack = TileRack(
            listOf(
                Tile.N,
                Tile.M,
                Tile.V,
                Tile.A,
                Tile.P,
                Tile.P,
                Tile.F
            ),
            Dictionary(
                InMemoryWordSource(
                    listOf("panama", "mama") // Both are valid if some tiles are used twice
                )
            )
        )

        assertNull(tileRack.longestValidWord())
    }
}