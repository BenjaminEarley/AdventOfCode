package `2017`

fun main() {
    println(
        d2p1(
"""5 1 9 5
7 5 3
2 4 6 8"""
        )
    )

    println(
        d2p2(
"""5 9 2 8
9 4 7 3
3 8 6 5"""
        )
    )
}

fun d2p1(checksum: String) =
    checksum
        .split("\n")
        .map {
            val list = it.split(" ").map(String::toInt)
            val high = list.max() ?: 0
            val low = list.min() ?: 0
            high - low
        }
        .sum()

fun d2p2(checksum: String) =
    checksum
        .split("\n")
        .map {
            val list = it.split(" ").map(String::toDouble)

            for (x in 0 until list.size) {
                for(y in x + 1 until list.size) {
                    val a = list[x]
                    val b = list[y]
                    if (a > b) {
                        if (a % b == 0.0) return@map a / b
                    } else {
                        if (b % a == 0.0) return@map b / a
                    }
                }
            }
            0.0
        }
        .map(Double::toInt)
        .sum()