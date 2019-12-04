package util

import java.io.File

fun readInput(year: Int, day: Int, part: Int = 0) = File("data/$year/day$day${if (part != 0) "part$part" else ""}.txt").readLines()

