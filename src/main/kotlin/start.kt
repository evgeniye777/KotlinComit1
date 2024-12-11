const val c: Int = 78
fun main() {
    outerloop@ for(i in 1..3){
        for(j in 1..3){
            if(j == 3) break@outerloop; //выход именно из внешнего цикла который помечен
            println("Hello")
        }
    }
    bit()
}

fun bit() {
    for(i in 1..9){
        for(j in 1..9){
            print("${i * j} \t")
        }
        println()
    }
}