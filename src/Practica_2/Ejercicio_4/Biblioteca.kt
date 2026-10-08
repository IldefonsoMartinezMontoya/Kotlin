package Practica_2.Ejercicio_4

open class Libro(val titulo: String, val autor: String) {
    open fun info() {
        println("Libro: $titulo, autor: $autor")
    }
}

class Ebook(titulo: String, autor: String, val tamMB: Double) : Libro(titulo, autor) {
    override fun info() {
        println("Ebook: $titulo, autor: $autor, tamaño: $tamMB MB")
    }
}

fun Libro.resumen(caras: Int = 1): String {
    return "$titulo — $autor ($caras)"
}

fun main() {
    print("¿Qué quieres crear (libro/ebook)? ")
    when (readln().trim().lowercase()) {
        "libro" -> {
            print("Título: ")
            val titulo = readln()
            print("Autor: ")
            val autor = readln()
            val libro = Libro(titulo, autor)
            libro.info()
            println(libro.resumen())
        }
        "ebook" -> {
            print("Título: ")
            val titulo = readln()
            print("Autor: ")
            val autor = readln()
            print("Tamaño en MB: ")
            val tamMB = readln().toDouble()
            val ebook = Ebook(titulo, autor, tamMB)
            ebook.info()
            println(ebook.resumen())
        }
        else -> println("Opción no válida")
    }
}
