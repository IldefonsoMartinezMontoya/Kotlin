package Practica_2.Ejercicio_1

import java.util.Scanner

abstract class Persona(nombre: String, edad: Int) {
    var Nombre: String = nombre
        get() = field
        set(value) {
            require(value.isNotBlank()) { "No puede estar vacío" }
            field = value.trim()
        }

    var Edad: Int = edad
        get() = field
        set(value) {
            require(value >= 0) { "No puede ser negativo" }
            field = value
        }
    val String.iniciales: String
        get() = trim().split(" ").filter { it.isNotBlank() }.joinToString("") {it.first().uppercase()}
    abstract fun presentarse()
    abstract fun iniciales()
}
class Estudiante(nombre: String, edad: Int, curso: String) : Persona(nombre, edad) {
    var Curso:String = curso
        private set (value) {
            if (value.isNotEmpty()) {
                field = value.trim()
            } else {
                throw IllegalArgumentException("No puede estar vacío")
            }
        }

    override fun presentarse() {
        println("Soy $Nombre de $Edad y curso $Curso")
    }

    override fun iniciales() {
        println(Nombre.iniciales)
    }
}
class Profesor(nombre: String, edad: Int, aniosTrabajo: Int) : Persona(nombre, edad) {
    var AniosTrabajo: Int = aniosTrabajo
        private set(value) {
            if (value < 0) {
                throw IllegalArgumentException("Negativo")
            } else {
                field = value
            }
        }

    override fun presentarse() {
        println("Soy $Nombre de $Edad y llevo ejerciendo $AniosTrabajo")
    }
    override fun iniciales() {
        println(Nombre.iniciales)
    }
}
fun main() {
    val sc = Scanner(System.`in`)
    var nombre: String = sc.nextLine()
    var edad: Int = sc.nextInt()
    var curso: String = sc.nextLine()
        val p1 = Estudiante(nombre, edad, curso)
        p1.presentarse()
        p1.iniciales()
    }
