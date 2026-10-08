package Practica_2.Ejercicio_6

interface Calificable {
    fun notaFinal(): Double
}

abstract class Evaluacion(val nombre: String) {
    override fun toString(): String = "Evaluación: $nombre"
}

class Examen(nombre: String, val n1: Double, val n2: Double) : Evaluacion(nombre), Calificable {
    override fun notaFinal(): Double = (n1 + n2) / 2
}

class Trabajo(nombre: String, val practicas: List<Int>) : Evaluacion(nombre), Calificable {
    override fun notaFinal(): Double = practicas.media()
}

fun List<Int>.media(): Double = if (isEmpty()) 0.0 else average()

fun main() {
    print("Tipo de evaluación (examen/trabajo): ")
    when (readln().trim().lowercase()) {
        "examen" -> {
            print("Nombre: ")
            val nombre = readln()
            print("Nota 1: ")
            val n1 = readln().toDouble()
            print("Nota 2: ")
            val n2 = readln().toDouble()
            val evaluacion = Examen(nombre, n1, n2)
            println(evaluacion)
            println("Nota final: ${evaluacion.notaFinal()}")
        }
        "trabajo" -> {
            print("Nombre: ")
            val nombre = readln()
            print("Número de prácticas: ")
            val cantidad = readln().toInt()
            val practicas = mutableListOf<Int>()
            for (i in 1..cantidad) {
                print("Nota de la práctica $i: ")
                practicas.add(readln().toInt())
            }
            val evaluacion = Trabajo(nombre, practicas)
            println(evaluacion)
            println("Nota final: ${evaluacion.notaFinal()}")
        }
        else -> println("Tipo de evaluación no válido")
    }
}
