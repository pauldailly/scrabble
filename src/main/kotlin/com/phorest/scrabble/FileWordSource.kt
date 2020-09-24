package com.phorest.scrabble

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader


class FileWordSource(val filename: String) : WordSource {
    override fun words(): List<String> {
        val inputStream = FileWordSource::class.java.getResourceAsStream(filename)
        return readFromInputStream(inputStream)
    }

    @Throws(IOException::class)
    private fun readFromInputStream(inputStream: InputStream): List<String> {
        val lines = mutableListOf<String>()
        BufferedReader(InputStreamReader(inputStream)).use { br ->
            var line: String?
            while (br.readLine().also { line = it } != null) {
                lines.add(line!!)
            }
        }
        return ArrayList(lines)
    }
}