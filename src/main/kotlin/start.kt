import kotlin.reflect.KProperty

fun main() {

    val tom = Person()
    println(tom.name)   // Tom

    val bob = Person()
    println(bob.name)   // Tom
}
class Person{
    val name: String by LoggerDelegate()
}
class LoggerDelegate {
    operator fun getValue(thisRef: Person, property: KProperty<*>): String {
        println("Запрошено свойство: ${property.name}")
        return "Tom"
    }
}