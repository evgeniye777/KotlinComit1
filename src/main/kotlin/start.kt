const val c: Int = 78
fun main() {
    println("1234")
    var a: Int = 23
    a = 24
    println(a)
    val b: Int = 5
    println(b + c)
    typeDate()
}

fun typeDate() {
    val a: Byte = -10 //+-128
    val b: Short = 45
    val c: Int = -250
    val d: Long = 30000

    val aU: UByte = 10U  ///Положительные
    val bU: UShort = 45U
    val cU: UInt = 250U
    val dU: ULong = 30000U

    //16 ричная
    val num: Int = 0x0A1    // 161
    println(num) // 161

    val height: Double = 1.78
    val pi: Float = 3.14F
    val text: String = """
                        SALT II was a series of talks between United States
                        and Soviet negotiators from 1972 to 1979.
                        It was a continuation of the SALT I talks.
                    """
    println(text)

    var name: Any = "Tom" ///любой тип
}