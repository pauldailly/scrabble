package com.phorest.scrabble

import java.util.*


class RandomLetterIndexGenerator : LetterIndexGenerator {

    override fun index(): Int {
        val r = Random()
        val low = 0
        val high = 25
        return r.nextInt(high - low) + low
    }
}