import java.io.File

fun main() {
    val prod = File("src/main/kotlin/input.txt").readLines().map { it.toInt() }
    val dev = listOf(
        1721,
        979,
        366,
        299,
        675,
        1456
    )
    println(goDoener(prod))
    println(goAlexAgain(prod))
}

fun is2020(x: Int, y: Int): Int? {
    return if (x + y == 2020) {
        println("$x + $y are 2020. mul is ${x * y}")
        x * y
    } else null
}

fun is2020(x: Int, y: Int, z: Int): Int? {
    return if (x + y + z == 2020) {
        println("$x + $y + $z are 2020. mul is ${x * y * z}")
        x * y * z
    } else null
}

private fun goDoener(input: List<Int>): Int { // On*log(n)
    val xs = input.sorted()
    val lastIndex = input.size - 1

    fun f(cl: Int, cr: Int): Int {
        val l1 = xs[cl]
        val r1 = xs[lastIndex - cr]
        val sum = l1 + r1

        return when {
            sum < 2020 -> f(cl + 1, cr)
            sum > 2020 -> f(cl, cr + 1)
            else -> l1 * r1
        }
    }

    return f(0, 0)
}

private fun goAlex(input: List<Int>): Int? { // On^2
    for (i in input)
        for (j in input)
            is2020(i, j)?.let { return it }
    return null
}

private fun goRobert(set: Set<Int>, sum: Int): Int? { // On
    return set.find { set.contains(sum - it) }?.let { (sum - it) * it }
}

private fun goAlexAgain(input: List<Int>): Int? { // On^3
    for (i in input)
        for (j in input)
            for (k in input)
                is2020(i, j, k)?.let { return it }

    return null
}