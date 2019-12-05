package `2019`.day2

import util.readInput
import java.lang.UnsupportedOperationException
import `2019`.day2.OpCode.HALT
import `2019`.day2.OpCode.ADD
import `2019`.day2.OpCode.MULTIPLY
import java.lang.Exception

fun main() {
    readInput(year = 2019, day = 2).first().split(',').map { it.toInt() }.apply {
        println("Year 2019 Day 2 Part 1: ${part1()}")
        println("Year 2019 Day 2 Part 2: ${part2()}")
    }
}

private fun List<Int>.part1() = runIntcode(noun = 12, verb = 2)

private fun List<Int>.part2(): Int {
    for (noun in 0..99) {
        for (verb in 0..99) {
            val output = runIntcode(noun, verb)
            if (output == 19690720) {
                return 100 * noun + verb
            }
        }
    }
    throw Exception("Unable To Find Output Matching 19690720")
}

private fun List<Int>.runIntcode(noun: Int, verb: Int): Int {
    val memory = this.toMutableList()
    memory[1] = noun
    memory[2] = verb
    var programPosition = 0
    while (true) {
        when (val instruction = readInstruction(programPosition)) {
            is Operation -> {
                instruction(memory)
                programPosition += Operation.SIZE
            }
            Halt -> return memory[0]
        }
    }
}

private sealed class Instruction
private data class Operation(private val address1: Int, private val address2: Int, private val address3: Int, private val operation: (Int, Int) -> Int) : Instruction() {
    operator fun invoke(input: MutableList<Int>) {
        input[address3] = operation(input[address1], input[address2])
    }

    companion object {
        const val SIZE = 4
    }
}

private object Halt : Instruction()

private fun List<Int>.readInstruction(startPosition: Int): Instruction {
    return when (get(startPosition)) {
        ADD.code -> Operation(get(startPosition + 1), get(startPosition + 2), get(startPosition + 3)) { l, r -> l + r }
        MULTIPLY.code -> Operation(get(startPosition + 1), get(startPosition + 2), get(startPosition + 3)) { l, r -> l * r }
        HALT.code -> Halt
        else -> throw UnsupportedOperationException()
    }
}

private enum class OpCode(val code: Int) {
    ADD(1),
    MULTIPLY(2),
    HALT(99)
}