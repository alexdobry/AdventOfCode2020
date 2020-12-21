import java.io.File

data class Slope(val xStep: Int, val yStep: Int)

fun main() {
    val tree = File("src/main/kotlin/input3.txt").readLines()
    val slopes = listOf(
        Slope(1, 1),
        Slope(3, 1),
        Slope(5, 1),
        Slope(7, 1),
        Slope(1, 2)
    )

    /*println(count(tree, Slope(3, 1)))*/
    println(slopes.map(count(tree)).reduce(::mul))
}

fun mul(a: Long, b: Long) = a * b

fun count(tree: List<String>): (Slope) -> Long = { slope ->
    var finished = false
    var i = 0
    var j = 0
    var count = 0L
    val size = tree[0].length

    while (!finished) {
        i += slope.xStep
        j += slope.yStep
        if (j >= tree.size) {
            finished = true
        } else {
            if (tree[j][i % size] == '#')
                count += 1
        }
    }

    count
}