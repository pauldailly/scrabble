package com.phorest.scrabble


class Word(val value : String) {

    fun points() : Int = 0

    fun totalScore() : Int {
        return value.map { Tile.valueOf(it.toString().toUpperCase()) }.map{it.points}.sum()
    }

    fun highestTripleLetterScore(): Int {
        val tiles = value.map { Tile.valueOf(it.toString().toUpperCase()) }
        val pointsPerLetter = tiles.map { it.points }
        val singleLetterTotalScore = pointsPerLetter.sum()

        val tripleLetterScores = mutableSetOf<Int>()
        repeat(pointsPerLetter.size) { i ->
            val doubleScore = tiles[i].points * 2
            tripleLetterScores.add(singleLetterTotalScore + doubleScore)
        }
        return tripleLetterScores.sorted().last()
    }
}