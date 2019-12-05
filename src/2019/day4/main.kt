package `2019`.day4

import util.readInput

fun main() {
    readInput(year = 2019, day = 4)
            .first()
            .let {
                val range = it.split('-').map(String::toInt)
                range[0]..range[1]
            }
            .run {
                println("Year 2019 Day 4 Part 1: ${part1()}")
                println("Year 2019 Day 4 Part 2: ${part2()}")
            }
}

private fun IntRange.part1(): String {
    var validPasswordCount = 0
    for (possiblePassword in this) {
        val passwordText = possiblePassword.toString()
        if (passwordText.neverDecreases() && passwordText.foundDouble()) validPasswordCount += 1
    }
    return validPasswordCount.toString()
}

private fun IntRange.part2(): String {
    var validPasswordCount = 0
    for (possiblePassword in this) {
        val passwordText = possiblePassword.toString()
        if (passwordText.neverDecreases() && passwordText.foundOnlyDouble()) validPasswordCount += 1
    }
    return validPasswordCount.toString()
}

private fun String.neverDecreases(): Boolean {
    reduce { previousNumber, number -> if (previousNumber > number) return false else number }
    return true
}

private fun String.foundDouble(): Boolean {
    reduce { previousNumber, number -> if (previousNumber == number) return true else number }
    return false
}

private fun String.foundOnlyDouble(): Boolean {
    var repeatingCount = 0
    var repeatingNumber = ' '
    forEach { number ->
        if (repeatingCount == 2) {
            if (number != repeatingNumber) return true
            else repeatingCount += 1
        } else {
            if (number == repeatingNumber) repeatingCount += 1
            else {
                repeatingNumber = number
                repeatingCount = 1
            }
        }
    }
    return repeatingCount == 2
}