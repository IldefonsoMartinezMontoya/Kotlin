package Practica_2.Ejercicio_8

interface Formateable {
    fun formatear(): String
}

fun String.sanitizar(): String = trim().filter { it.isLetterOrDigit() }

class MensajeTexto(contenido: String) : Formateable {
    val contenido: String = contenido.sanitizar()
    override fun formatear(): String = "Texto: $contenido"
}

class MensajeImagen(url: String, val ancho: Int, val alto: Int) : Formateable {
    val url: String = url.sanitizar()
    override fun formatear(): String = "Imagen: $url (${ancho}x$alto)"
}

fun main() {
    print("Tipo de mensaje (texto/imagen): ")
    when (readln().trim().lowercase()) {
        "texto" -> {
            print("Contenido: ")
            val mensaje = MensajeTexto(readln())
            println(mensaje.formatear())
        }
        "imagen" -> {
            print("URL: ")
            val url = readln()
            print("Ancho: ")
            val ancho = readln().toInt()
            print("Alto: ")
            val alto = readln().toInt()
            val mensaje = MensajeImagen(url, ancho, alto)
            println(mensaje.formatear())
        }
        else -> println("Tipo de mensaje no válido")
    }
}
