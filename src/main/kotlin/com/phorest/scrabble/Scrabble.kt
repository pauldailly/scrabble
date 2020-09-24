package com.phorest.scrabble


class Scrabble {

    fun play() {

        val tileBag = TileBag(RandomLetterIndexGenerator())

        val tileRack =
            TileRack(
                tileBag.randomlySelectedTiles(),
                Dictionary(
                    FileWordSource("/dictionary.txt")
                )
            )

        val longestValidWord = tileRack.longestValidWord()

        if (longestValidWord == null) {
            println("No words could be made from the available tiles ${tileRack.asString()}")
        } else {
            val highestScoringWord = tileRack.highestScoringWord()
            println("Tiles chosen: ${tileRack.asString()}")
            println("Longest valid word: ${longestValidWord.value}")
            println("Highest scoring word: ${highestScoringWord?.value} is worth ${highestScoringWord?.totalScore()}")
        }
    }

}

fun main() {

    Scrabble().play()
}
