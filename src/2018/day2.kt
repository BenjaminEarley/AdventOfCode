package `2018`

fun main() {
    println(
        d2p1(
"""abcdef
bababc
abbcde
abcccd
aabcdd
abcdee
ababab"""
        )
    )

    println(
        d2p2(
"""abcde
fghij
klmno
pqrst
fguij
axcye
wvxyz"""
        )
    )
}

fun d2p1(input: String): Int =
    input
        .split("\n")
        .asSequence()
        .fold(Pair(0, 0)) { (exactlyTwoCount, exactlyThreeCount), x ->
            val hasExactlyTwo = hasExactCount(x, 2)
            val hasExactlyThree = hasExactCount(x, 3)
            Pair(exactlyTwoCount + hasExactlyTwo, exactlyThreeCount + hasExactlyThree)
        }
        .let { it.first * it.second }

fun hasExactCount(input: String, count: Int) =
    input.groupBy { it }.filter { it.value.count() == count }.isNotEmpty().let { if (it) 1 else 0 }

fun d2p2(input: String): String {
    fun difference(a: String, b: String): Set<Int> = a.findIndices { index, n -> n != b[index] }
    val inputs = input.split("\n")
    for (x in 0 until inputs.count()) {
        for (y in x + 1 until inputs.count()) {
            val d = difference(inputs[x], inputs[y])
            if (d.count() == 1) return inputs[x].removeRange(d.first()..d.first())
        }
    }
    return ""
}

fun String.findIndices(predicate: (Int, Char) -> Boolean): Set<Int> {
    val x = mutableSetOf<Int>()
    forEachIndexed { index, char ->
        if (predicate(index, char)) x.add(index)
    }
    return x
}
