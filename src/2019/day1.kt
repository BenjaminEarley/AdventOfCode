package `2019`

import kotlin.math.floor
import util.readInput

fun main() {
    part1()
    part2()
}

fun part1() {
    readInput(year = 2019, day = 1)
            .map { convertToFuel(it.toInt()) }
            .sum()
            .let { println("Year 2019 Day 1 Part 1: $it") }
}

fun convertToFuel(mass: Int): Int = mass / 3 - 2

fun part2() {
    readInput(year = 2019, day = 1)
            .map { depleteFuel(it.toInt()) }
            .sum()
            .let { println("Year 2019 Day 1 Part 2: $it") }
}

fun depleteFuel(mass: Int): Int {
    val initialFuel = convertToFuel(mass)
    fun loop(fuel: Int): Int = if (fuel < 1) 0 else fuel + loop(convertToFuel(fuel))
    return loop(initialFuel)
}
