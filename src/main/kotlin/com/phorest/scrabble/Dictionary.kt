package com.phorest.scrabble


class Dictionary {

    private val sortedWords: MutableMap<String, MutableList<String>> = mutableMapOf()

    constructor(wordSource: WordSource) {
        wordSource.words().map { w ->
            // Doesn't matter for now if we overwrite a key (e.g 2 different words could have same combination of letters but in different orders e.g 'slip' and 'lisp')
            // as both are worth the same points
            val key = w.toCharArray().sorted().joinToString("")
            sortedWords.putIfAbsent(key, mutableListOf())
            sortedWords[key]?.add(w)
        }
    }

    fun makeValidWordFrom(letters: String): String? {
        return sortedWords[letters.toCharArray().sorted().joinToString("")]?.get(0)
    }

    fun allValidWordsFrom(letters: String): List<String> {
        return sortedWords.getOrDefault(letters.toCharArray().sorted().joinToString(""), emptyList())
    }
}