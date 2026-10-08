package Practica_2.Ejercicio_1
abstract class Persona(var nombre:String, var edad:Int) {
    var Nombre: String = nombre
        get() {
            TODO()
        }
        set(value) {
            if (value.isNotEmpty()) {
                field = value.trim()
            } else {
                throw IllegalArgumentException("No puede estar vacío")
            }
        }
    var Edad: Int = edad
        get() {
            TODO()
        }
        set(value) {
            if (value < 0) {
                throw IllegalArgumentException("Negativo")
            } else {
                field = value
            }
        }
    val String.iniciales: String
        get() = trim().split(" ").filter { it.isNotBlank() }.joinToString("") {it.first().uppercase()}
    abstract fun presentarse()
    abstract fun iniciales()
}
class Estudiante(nombre: String, edad: Int, curso: String) : Persona(nombre, edad) {
    var Curso:String = curso
        get() {
            TODO()
        }
        set(value) {
            if (value.isNotEmpty()) {
                field = value.trim()
            } else {
                throw IllegalArgumentException("No puede estar vacío")
            }
        }

    override fun presentarse() {
        println("Soy $nombre de $edad y curso $Curso")
    }

    override fun iniciales() {
        println(nombre.iniciales)
    }
}
class Profesor(nombre: String, edad: Int, aniosTrabajo: Int) : Persona(nombre, edad) {
    var AniosTrabajo: Int = aniosTrabajo
        get() {
            TODO()
        }
        set(value) {
            if (value < 0) {
                throw IllegalArgumentException("Negativo")
            } else {
                field = value
            }
        }

    override fun presentarse() {
        println("Soy $nombre de $edad y llevo ejerciendo $AniosTrabajo")
    }
    override fun iniciales() {
        println(nombre.iniciales)
    }
}
