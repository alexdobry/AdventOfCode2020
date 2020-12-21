import java.io.File

data class PasswordPolicy(val min: Int, val max: Int, val char: String, val password: String)

fun main() {
    /*val pws = listOf(
    "1-3 a: abcde",
    "1-3 b: cdefg",
    "2-9 c: ccccccccc"
    )*/

    File("src/main/kotlin/input2.txt")
        .readLines()
        .map(::parse)
        .count(::validate2)
        .let(::println)
}

fun parse(string: String): PasswordPolicy {
    val s = string.split("-")
    val ss = s[1].split(" ")

    val min = s[0].toInt()
    val max = ss[0].toInt()
    val char = ss[1].dropLast(1)
    val pw = ss[2]

    return PasswordPolicy(min, max, char, pw)
}

fun validate(policy: PasswordPolicy): Boolean {
    val min = policy.min
    val max = policy.max
    val char = policy.char
    val matches = policy.password.count { it.toString() == char }

    return matches in min..max
}

fun validate2(policy: PasswordPolicy): Boolean {
    val pos1 = policy.min - 1
    val pos2 = policy.max - 1
    val char = policy.char
    val match1 = policy.password[pos1].toString() == char
    val match2 = policy.password[pos2].toString() == char

    return (match1 && !match2) || (!match1 && match2)
}