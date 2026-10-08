package Practica_2.Ejercicio_2

import kotlin.math.pow

abstract class Figura() {
    abstract fun area(): Double
}

class circulo(radio: Double) : Figura() {
    var Radio: Double = radio
        get() = field
        private set(value) {
            require(value >= 0) { "No puede ser negativo" }
            field = value
        }

    override fun area(): Double {
        var area: Double
        area = Radio.pow(2.00) * Math.PI
        return area
    }
}
class rectangulo(base: Double, altura: Double) : Figura() {
    var Base: Double = base
        get() = field
        private set(value) {
            require(value >= 0) { "No puede ser negativo" }
            field = value
        }
    var Altura: Double = altura
        get() = field
        private set(value) {
            require(value >= 0) { "No puede ser negativo" }
            field = value
        }

    override fun area(): Double {
        var area: Double
        area = Base * Altura
        return area
    }
}
fun main() {
    print("¿Qué figura quieres creer?(minúsculas) ")
    when (readln().trim().lowercase()) {
        "rectangulo" -> {
            print("Ancho: ")
            val ancho = readln().toDouble()
            print("Alto: ")
            val alto = readln().toDouble()
            val Figura = rectangulo(ancho, alto)
            println("Área: ${Figura.area()}")
        }
        "circulo" -> {
            print("Radio: ")
            val rad = readln().toDouble()
            val Figura = circulo(rad)
            println("Área: ${Figura.area()}")
        }
    }
}