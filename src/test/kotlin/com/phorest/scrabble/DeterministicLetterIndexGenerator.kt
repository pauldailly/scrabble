package com.phorest.scrabble

import java.util.*

class DeterministicLetterIndexGenerator(private val sequence : Stack<Int>) : LetterIndexGenerator {
    override fun index(): Int = sequence.pop()
}