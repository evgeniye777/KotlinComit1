import java.util.concurrent.CompletableFuture
import java.util.regex.Pattern
import kotlin.reflect.KProperty
val month = "(JAN|FEB|MAR|APR|MAY|JUN|JUL|AUG|SEP|OCT|NOV|DEC)"

fun getPattern(): String = """\d{2} $month \d{4}"""
fun main() {
    val regex = getPattern()

    // Пример строки для поиска
    val text = "The event will be held on 15 MAR 2023 and 01 JAN 2024."

    // Создаём Pattern и Matcher
    val pattern = Pattern.compile(regex)
    val matcher = pattern.matcher(text)

    // Находим все соответствия
    while (matcher.find()) {
        println("vivod date: ${matcher.group()}")
    }
}
fun posled() {
    
    val a = 5
    val b = a in 1..6       // true - число 5 входит в последовательность от 1 до 6

    val c = 4
    val d = c in 11..15     // false - число 4 НЕ входит в последовательность от 11 до 15


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