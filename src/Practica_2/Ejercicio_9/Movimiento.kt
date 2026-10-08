package Practica_2.Ejercicio_9

import kotlin.math.sqrt

data class Punto(val x: Double, val y: Double)

interface Movible {
    fun mover(dx: Double, dy: Double)
}

class Coche(pos: Punto) : Movible {
    var pos: Punto = pos
        private set

    override fun mover(dx: Double, dy: Double) {
        pos = Punto(pos.x + dx, pos.y + dy)
    }
}

fun Punto.distanciaA(otro: Punto): Double {
    val dx = x - otro.x
    val dy = y - otro.y
    return sqrt(dx * dx + dy * dy)
}

fun main() {
    print("Coordenada x inicial: ")
    val x = readln().toDouble()
    print("Coordenada y inicial: ")
    val y = readln().toDouble()
    val coche = Coche(Punto(x, y))

    print("Desplazamiento dx: ")
    val dx = readln().toDouble()
    print("Desplazamiento dy: ")
    val dy = readln().toDouble()
    coche.mover(dx, dy)

    println("Nueva posición: (${coche.pos.x}, ${coche.pos.y})")
    println("Distancia al origen: ${coche.pos.distanciaA(Punto(0.0, 0.0))}")
}
