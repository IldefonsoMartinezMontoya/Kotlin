package Practica_2.Ejercicio_10

interface Vehiculo {
    fun arrancar()
    fun detener()
}

abstract class VehiculoElectrico(var bateria: Int) : Vehiculo {
    init {
        require(bateria in 0..100) { "La batería debe estar entre 0 y 100" }
    }

    fun cargar(valor: Int) {
        require(valor >= 0) { "La carga no puede ser negativa" }
        bateria = (bateria + valor).coerceAtMost(100)
    }
}

class CocheElectrico(bateria: Int) : VehiculoElectrico(bateria) {
    override fun arrancar() = println("El coche eléctrico arranca")
    override fun detener() = println("El coche eléctrico se detiene")
}

class MotoElectrica(bateria: Int) : VehiculoElectrico(bateria) {
    override fun arrancar() = println("La moto eléctrica arranca")
    override fun detener() = println("La moto eléctrica se detiene")
}

fun Vehiculo.revisarBateria() {
    if (this is VehiculoElectrico && bateria < 20) {
        println("Batería baja: $bateria%")
    } else if (this is VehiculoElectrico) {
        println("Batería suficiente")
    }
}

fun main() {
    print("¿Qué quieres crear (coche/moto)? ")
    val vehiculo: Vehiculo = when (readln().trim().lowercase()) {
        "coche" -> CocheElectrico(0)
        "moto" -> MotoElectrica(0)
        else -> {
            println("Tipo de vehículo no válido")
            return
        }
    }

    print("Porcentaje que quieres cargar: ")
    (vehiculo as VehiculoElectrico).cargar(readln().toInt())
    vehiculo.arrancar()
    vehiculo.revisarBateria()
    vehiculo.detener()
}
