package com.phorest.scrabble

import java.util.*


class TileRack(private val tiles: List<Tile>, private val dictionary: Dictionary) {

    fun longestValidWord(): Word? {

        var wordLength = 8
        var result: String? = null
        while (result == null && wordLength > 0) {
            val wordAttempts = combinations(
                tiles
                    .map { it.char }.joinToString(""), --wordLength
            )

            var attemptCount = 0
            while (attemptCount < wordAttempts.size && result == null) {
                result = dictionary.makeValidWordFrom(wordAttempts[attemptCount])
                ++attemptCount
            }

        }

        return if (result != null) Word(result) else null

    }

    fun highestScoringWord(): Word? {
        val validWords = validWords()
        return validWords.sortedByDescending { it.totalScore() }.first()
    }

    fun highestTripleLetterScoreWord(): Word? {
        val validWords = validWords()
        return validWords.sortedByDescending { it.highestTripleLetterScore() }.first()
    }

    private fun validWords(): MutableSet<Word> {
        val validWords = mutableSetOf<Word>()

        repeat(7) { i ->
            val wordAttempts = combinations(
                tiles
                    .map { it.char }.joinToString(""), i + 1
            )
            wordAttempts.forEach { attempt ->
                validWords.addAll(dictionary.allValidWordsFrom(attempt).map { Word(it) })
            }
        }
        return validWords
    }

    // Stolen from the internet!!!
    private fun combinations(nChars: String, k: Int): List<String> {
        val n = nChars.length
        val combos = ArrayList<String>()
        if (k == 0) {
            combos.add("")
            return combos
        }
        if (n < k || n == 0) return combos
        val last = nChars.substring(n - 1)
        combos.addAll(combinations(nChars.substring(0, n - 1), k))
        for (subCombo in combinations(nChars.substring(0, n - 1), k - 1)) combos.add(subCombo + last)
        return combos
    }

    fun asString(): String = tiles.map { it.char }.joinToString("")
}