package com.phorest.scrabble


class InMemoryWordSource(val words : List<String>) : WordSource {
    override fun words(): List<String> = this.words

}