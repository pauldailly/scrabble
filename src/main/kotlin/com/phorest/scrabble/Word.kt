package com.phorest.scrabble


class Word(val value : String) {

    fun points() : Int = 0

    fun totalScore() : Int {
        return value.map { Tile.valueOf(it.toString().toUpperCase()) }.map{it.points}.sum()
    }
 }