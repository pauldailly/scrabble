package com.phorest.scrabble


interface WordSource {
    fun words(): List<String>
}