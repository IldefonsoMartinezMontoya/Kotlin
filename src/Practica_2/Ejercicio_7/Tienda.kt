package Practica_2.Ejercicio_7

interface Descontable {
    fun aplicarDescuento(porc: Int)
}

class Producto(val nombre: String, precio: Double) : Descontable {
    var precio: Double = precio
        private set

    override fun aplicarDescuento(porc: Int) {
        require(porc in 0..100) { "Descuento no válido" }
        precio *= (100 - porc) / 100.0
    }
}

class Servicio(val nombre: String, val tarifaHora: Double, val horas: Double) : Descontable {
    var total: Double = tarifaHora * horas
        private set

    override fun aplicarDescuento(porc: Int) {
        require(porc in 0..100) { "Descuento no válido" }
        total *= (100 - porc) / 100.0
    }
}

fun Producto.conIVA(porc: Int = 21): Double {
    require(porc >= 0) { "IVA no válido" }
    return precio * (1 + porc / 100.0)
}

fun main() {
    print("Tipo (producto/servicio): ")
    when (readln().trim().lowercase()) {
        "producto" -> {
            print("Nombre: ")
            val nombre = readln()
            print("Precio: ")
            val producto = Producto(nombre, readln().toDouble())
            print("Descuento (%): ")
            producto.aplicarDescuento(readln().toInt())
            println("Total de $nombre con IVA: ${producto.conIVA()}")
        }
        "servicio" -> {
            print("Nombre: ")
            val nombre = readln()
            print("Tarifa por hora: ")
            val tarifa = readln().toDouble()
            print("Horas: ")
            val servicio = Servicio(nombre, tarifa, readln().toDouble())
            print("Descuento (%): ")
            servicio.aplicarDescuento(readln().toInt())
            println("Total de $nombre: ${servicio.total}")
        }
        else -> println("Tipo no válido")
    }
}
