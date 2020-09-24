package com.phorest.scrabble

import java.util.*

class TileBag(val letterIndexGenerator: LetterIndexGenerator) {

    private val tiles : MutableList<Pair<Tile, Int>> = mutableListOf(
        Pair(Tile.A, 9),
        Pair(Tile.B, 2),
        Pair(Tile.C, 2),
        Pair(Tile.D, 4),
        Pair(Tile.E, 4),
        Pair(Tile.F, 2),
        Pair(Tile.G, 3),
        Pair(Tile.H, 2),
        Pair(Tile.I, 9),
        Pair(Tile.J, 1),
        Pair(Tile.K, 1),
        Pair(Tile.L, 4),
        Pair(Tile.M, 2),
        Pair(Tile.N, 6),
        Pair(Tile.O, 8),
        Pair(Tile.P, 2),
        Pair(Tile.Q, 1),
        Pair(Tile.R, 6),
        Pair(Tile.S, 4),
        Pair(Tile.T, 6),
        Pair(Tile.U, 4),
        Pair(Tile.V, 2),
        Pair(Tile.W, 2),
        Pair(Tile.X, 1),
        Pair(Tile.Y, 2),
        Pair(Tile.Z, 1),
    )

    fun randomlySelectedTiles(): List<Tile> {

        val randomTiles : MutableList<Tile> = mutableListOf()

        repeat(7) {
            randomTiles.add(randomTile())
        }

        return randomTiles
    }

    // This needs to be improved if we start having multiple rounds
    private fun randomTile() : Tile {
        while (true) {
            val randomLetter = letterIndexGenerator.index()
            val pair = tiles[randomLetter]
            if(pair.second > 0) {
                tiles[randomLetter] = pair.copy(second = pair.second - 1)
                return pair.first
            }
        }
    }

}



