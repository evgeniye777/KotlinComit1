const val c: Int = 78
fun main() {
    println("1234")
    var a: Int = 23
    a = 24
    println(a)
    val b: Int = 5
    println(b + c)
    bit()
}

fun bit() {

    val z = 3 shl 2     // z = 11 << 2 = 1100
    println(z)          // z = 12
    val d = 0b11 shl 2
    println(d)          // d = 12

    val zp = 12 shr 2     // z = 1100 >> 2 = 11
    println(z)          // z = 3
    val dp = 0b1100 shr 2
    println(d)          // d = 3

    //ushr and or xor inv
}