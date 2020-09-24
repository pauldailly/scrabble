package com.phorest.scrabble

import org.junit.jupiter.api.Test
import java.util.*
import kotlin.test.assertEquals

internal class TileBagTest {

    @Test
    fun `should return 7 randomly selected tiles`() {

        val tileBag = TileBag(RandomLetterIndexGenerator())

        val tiles = tileBag.randomlySelectedTiles()

        assertEquals(7, tiles.size)
    }

    @Test
    fun `should return 7 tiles each of a different letter`() {
        val sequence = Stack<Int>()
        sequence.push(3)
        sequence.push(8)
        sequence.push(2)
        sequence.push(14)
        sequence.push(23)
        sequence.push(16)
        sequence.push(12)
        val tileBag = TileBag(DeterministicLetterIndexGenerator(sequence))

        val tiles = tileBag.randomlySelectedTiles()

        assertEquals(7, tiles.size)
        assertEquals(Tile.M, tiles[0])
        assertEquals(Tile.Q, tiles[1])
        assertEquals(Tile.X, tiles[2])
        assertEquals(Tile.O, tiles[3])
        assertEquals(Tile.C, tiles[4])
        assertEquals(Tile.I, tiles[5])
        assertEquals(Tile.D, tiles[6])
    }

    @Test
    fun `should be possible to get multiple of the same letter`() {
        val sequence = Stack<Int>()
        sequence.push(3)
        sequence.push(8)
        sequence.push(2)
        sequence.push(14)
        sequence.push(14)
        sequence.push(16)
        sequence.push(12)
        val tileBag = TileBag(DeterministicLetterIndexGenerator(sequence))

        val tiles = tileBag.randomlySelectedTiles()

        assertEquals(7, tiles.size)
        assertEquals(Tile.M, tiles[0])
        assertEquals(Tile.Q, tiles[1])
        assertEquals(Tile.O, tiles[2])
        assertEquals(Tile.O, tiles[3])
        assertEquals(Tile.C, tiles[4])
        assertEquals(Tile.I, tiles[5])
        assertEquals(Tile.D, tiles[6])
    }

    @Test
    fun `should try again if a depleted letter is chosen`() {
        val sequence = Stack<Int>()
        sequence.push(3)
        sequence.push(8)
        sequence.push(2)
        sequence.push(14)
        sequence.push(14)
        sequence.push(25)
        sequence.push(25) // There's only 1 'Z' in the bag
        sequence.push(0) // So try again and choose an 'A' instead
        val tileBag = TileBag(DeterministicLetterIndexGenerator(sequence))

        val tiles = tileBag.randomlySelectedTiles()

        assertEquals(7, tiles.size)
        assertEquals(Tile.A, tiles[0])
        assertEquals(Tile.Z, tiles[1])
        assertEquals(Tile.O, tiles[2])
        assertEquals(Tile.O, tiles[3])
        assertEquals(Tile.C, tiles[4])
        assertEquals(Tile.I, tiles[5])
        assertEquals(Tile.D, tiles[6])
    }

}

