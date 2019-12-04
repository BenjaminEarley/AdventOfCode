package `2019`

import util.readInput

fun main() {
    readInput(year = 2019, day = 1).apply {
        part1()
        part2()
    }
}

private fun List<String>.part1() =
        map { convertToFuel(it.toInt()) }
                .sum()
                .let { println("Year 2019 Day 1 Part 1: $it") }

private fun List<String>.part2() =
        map { depleteFuel(it.toInt()) }
                .sum()
                .let { println("Year 2019 Day 1 Part 2: $it") }

private fun convertToFuel(mass: Int): Int = mass / 3 - 2

private fun depleteFuel(mass: Int): Int {
    val initialFuel = convertToFuel(mass)
    fun loop(fuel: Int): Int = if (fuel < 1) 0 else fuel + loop(convertToFuel(fuel))
    return loop(initialFuel)
}
