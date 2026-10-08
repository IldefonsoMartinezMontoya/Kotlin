package Practica_2.Ejercicio_3

interface Enviable {
    fun coste(envioKm: Int): Double
}
class Carta(pesoGr: Double): Enviable {
    var PesoGr: Double = pesoGr
    get() = field
    private set(value) {
        require(value >= 0) { "No puede ser negativo" }
        field = value
    }

    override fun coste(envioKm: Int): Double {
        var precio: Double
        precio = PesoGr * envioKm
        return precio
    }
}
class Paquete(pesoGr: Double, fragil: Boolean): Enviable {
    var PesoGr: Double = pesoGr
        get() = field
        private set(value) {
            require(value >= 0) { "No puede ser negativo" }
            field = value
        }
    var Fragil: Boolean = false
        get() = field
        set(value) {
            field = value
        }
    override fun coste(envioKm: Int): Double {
        var precio: Double
        if (Fragil) {
            precio = PesoGr * envioKm + 5
        } else {
            precio = PesoGr * envioKm
        }
        return precio
    }
}
fun main() {
    print("¿Qué quieres enviar? ")
    when (readln().trim()) {
        "Carta" -> {
            print("Peso: ")
            val peso = readln().toDouble()
            val Enviable = Carta(peso)
            println("Precio final: ${Enviable.coste(10)}")
        }
        "Paquete" -> {
            print("Peso ")
            val peso = readln().toDouble()
            val fragil = true
            val Enviable = Paquete(peso, fragil)
            println("Precio final: ${Enviable.coste(10)}")
        }
    }
}