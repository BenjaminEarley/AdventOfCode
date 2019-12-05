package `2019`.day3

import util.readInput
import kotlin.math.abs

private typealias BothWireOperations = Pair<WireOperations, WireOperations>
private typealias WireOperations = List<Operation>
private typealias Wire = List<Point>

private val STARTING_POINT = Point(0, 0)

fun main() {
    readInput(year = 2019, day = 3)
            .map { it.split(",") }
            .map { wires ->
                wires.map { wire ->
                    val direction = wire[0].let { char -> Direction.getDirectionByChar(char)!! }
                    val distance = wire.substring(1 until wire.length).toInt()
                    Operation(direction, distance)
                }
            }.let { Pair(it[0], it[1]) }
            .apply {
                println("Year 2019 Day 3 Part 1: ${part1()}")
                println("Year 2019 Day 3 Part 2: ${part2()}")
            }
}

private fun BothWireOperations.part1(): String {
    val firstWire = first.toWire()
    val secondWire = second.toWire()

    val wireIntersections = (firstWire intersect secondWire).drop(1)
    val closestIntersection = wireIntersections.map { abs(it.x) + abs(it.y) }.min()

    return closestIntersection.toString()
}

private fun BothWireOperations.part2(): String {
    val firstWire = first.toWire()
    val secondWire = second.toWire()

    val wireIntersections = (firstWire intersect secondWire).drop(1)

    var shortestPathsLength: Int = Int.MAX_VALUE
    wireIntersections.forEach { intersection ->
        val pathsLength = firstWire.stepsTo(intersection) + secondWire.stepsTo(intersection)
        if (shortestPathsLength > pathsLength) shortestPathsLength = pathsLength
    }
    return shortestPathsLength.toString()
}

private fun WireOperations.toWire(): Wire {
    val wire = mutableListOf(STARTING_POINT)
    forEach { operations ->
        val segment = wire.last().move(operations)
        wire.addAll(segment)
    }
    return wire
}

private fun Point.move(operation: Operation): List<Point> {
    val (direction, distance) = operation
    return when (direction) {
        Direction.RIGHT -> ((x + 1)..(x + distance)).map { copy(x = it) }
        Direction.LEFT -> ((x - 1) downTo (x - distance)).map { copy(x = it) }
        Direction.UP -> ((y + 1)..(y + distance)).map { copy(y = it) }
        Direction.DOWN -> ((y - 1) downTo (y - distance)).map { copy(y = it) }
    }
}

private fun Wire.stepsTo(point: Point) = takeWhile { it != point }.count()


private data class Point(val x: Int, val y: Int)
private data class Operation(val direction: Direction, val distance: Int)
private enum class Direction {
    RIGHT, LEFT, UP, DOWN;

    companion object {
        fun getDirectionByChar(char: Char): Direction? = values().find { it.name[0] == char }
    }
}
