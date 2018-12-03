package `2018`

fun main() {
    println(d1p1("+1, +1, +1"))
    println(d1p1("+1, +1, -2"))
    println(d1p1("-1, -2, -3"))

    println(d1p2("+1, -1"))
    println(d1p2("+3, +3, +4, -2, -4"))
    println(d1p2("-6, +3, +8, +5, -6"))
    println(d1p2("+7, +7, -2, -7 ,-4"))
}

fun d1p1(frequency: String) =
    frequency
        .split(Deliminator)
        .asSequence()
        .map { it.trim() }
        .map { Pair(it.first(), it.drop(1).toInt()) }
        .fold(0) { total, (operator, number) ->
            operators[operator]!!(total, number)
        }

fun d1p2(frequency: String): Int {
    val seenFrequency: MutableSet<Int> = mutableSetOf()
    return frequency
        .split(Deliminator)
        .asSequence()
        .cycle()
        .map { it.trim() }
        .map { Pair(it.first(), it.drop(1).toInt()) }
        .fold(0) { total, (operator, number) ->
            if (seenFrequency.contains(total)) return total
            else seenFrequency.add(total)
            val next = operators[operator]!!(total, number)
            next
        }
}

const val Deliminator = ","

val operators: Map<Char, (Int, Int) -> Int> =
    mapOf(
        '+' to { left, right -> left + right },
        '-' to { left, right -> left - right }
    )

fun <T> Sequence<T>.cycle(): Sequence<T> =
    generateSequence { this }.flatten()

