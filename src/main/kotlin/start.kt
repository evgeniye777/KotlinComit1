const val c: Int = 78
fun main() {
    println("1234")
    var a: Int = 23
    a = 24
    println(a)
    val b: Int = 5
    println(b + c)
    posled()
}

fun posled() {
    
    val a = 5
    val b = a in 1..6       // true - число 5 входит в последовательность от 1 до 6

    val c = 4
    val d = c in 11..15     // false - число 4 НЕ входит в последовательность от 11 до 15
}