package `2017`

fun main() {
    println(d1p1("1122"))
    println(d1p1("1111"))
    println(d1p1("1234"))
    println(d1p1("91212129"))

    println(d1p2("1212"))
    println(d1p2("1221"))
    println(d1p2("123425"))
    println(d1p2("123123"))
    println(d1p2("12131415"))
}

fun d1p1(captcha: String) =
    captcha
        .let { it + it[0] }
        .fold(Pair(0, 0)) { (total, prev), value ->
            val intValue = value.toNumber()
            if (prev == 0 || prev != intValue) Pair(total, intValue)
            else Pair(total + intValue, intValue)
        }
        .first

fun d1p2(captcha: String): Int {
    val halfCaptchaLength = captcha.length/2
    val fullCaptcha = captcha.let { it + it.slice(0..halfCaptchaLength) }
    var total = 0
    for (i in 0 until captcha.length) {
        if (fullCaptcha[i].toNumber() == fullCaptcha[i + halfCaptchaLength].toNumber())
            total += Character.getNumericValue(fullCaptcha[i])
    }
    return total
}

fun Char.toNumber() = Character.getNumericValue(this)
