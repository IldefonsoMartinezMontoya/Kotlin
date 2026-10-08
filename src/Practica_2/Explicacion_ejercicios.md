# Explicación de los ejercicios Kotlin

## 4. Biblioteca

El programa pregunta si se quiere crear un libro o un ebook. Según la opción, solicita título y autor; para un ebook también pide el tamaño en MB. `Libro` muestra la información básica y `Ebook` sobrescribe `info()` para incluir el tamaño. La extensión `resumen()` devuelve título, autor y número de caras; si no se indica ese número, utiliza 1.

```kotlin
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
```

## 5. Cuentas bancarias

El programa pide el tipo de cuenta, el IBAN y el saldo inicial. Después solicita una cantidad para ingresar y otra para retirar, aplica la actualización mensual y muestra el saldo final en euros. `Cuenta` comprueba que el saldo y las cantidades sean válidos; `CuentaCorriente` resta la comisión y `CuentaAhorro` añade el interés porcentual. La extensión `euros()` presenta el importe con dos decimales.

```kotlin
package Practica_2.Ejercicio_5

import java.util.Locale

abstract class Cuenta(val iban: String, saldo: Double) {
    init {
        require(saldo >= 0) { "El saldo no puede ser negativo" }
    }

    var saldo: Double = saldo
        protected set(value) {
            require(value >= 0) { "El saldo no puede ser negativo" }
            field = value
        }

    protected fun ingresar(cantidad: Double) {
        require(cantidad >= 0) { "La cantidad no puede ser negativa" }
        saldo += cantidad
    }

    protected fun retirar(cantidad: Double) {
        require(cantidad >= 0) { "La cantidad no puede ser negativa" }
        require(saldo - cantidad >= 0) { "Saldo insuficiente" }
        saldo -= cantidad
    }

    fun ingresarOperacion(cantidad: Double) = ingresar(cantidad)
    fun retirarOperacion(cantidad: Double) = retirar(cantidad)
    abstract fun actualizarMensual()
}

class CuentaCorriente(iban: String, saldo: Double, val comision: Double) : Cuenta(iban, saldo) {
    override fun actualizarMensual() {
        require(comision >= 0) { "La comisión no puede ser negativa" }
        require(saldo - comision >= 0) { "La comisión supera el saldo" }
        saldo -= comision
    }
}

class CuentaAhorro(iban: String, saldo: Double, val interes: Double) : Cuenta(iban, saldo) {
    override fun actualizarMensual() {
        require(interes >= 0) { "El interés no puede ser negativo" }
        ingresar(saldo * interes / 100)
    }
}

fun Double.euros(): String = String.format(Locale("es", "ES"), "%.2f €", this)

fun main() {
    print("Tipo de cuenta (corriente/ahorro): ")
    val tipo = readln().trim().lowercase()
    print("IBAN: ")
    val iban = readln()
    print("Saldo inicial: ")
    val saldoInicial = readln().toDouble()

    val cuenta: Cuenta = when (tipo) {
        "corriente" -> {
            print("Comisión mensual: ")
            CuentaCorriente(iban, saldoInicial, readln().toDouble())
        }
        "ahorro" -> {
            print("Interés mensual (%): ")
            CuentaAhorro(iban, saldoInicial, readln().toDouble())
        }
        else -> {
            println("Tipo de cuenta no válido")
            return
        }
    }

    print("Cantidad para ingresar: ")
    cuenta.ingresarOperacion(readln().toDouble())
    print("Cantidad para retirar: ")
    cuenta.retirarOperacion(readln().toDouble())
    cuenta.actualizarMensual()
    println("Saldo final de ${cuenta.iban}: ${cuenta.saldo.euros()}")
}
```

## 6. Evaluaciones

El programa permite elegir un examen o un trabajo. Para el examen pide dos notas y calcula su media; para el trabajo pide las notas de sus prácticas y usa la extensión `media()` para calcular el promedio. Ambas clases implementan `Calificable`, que proporciona la nota final, y heredan de `Evaluacion`, cuya representación de texto muestra el nombre.

```kotlin
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
```

## 7. Tienda y descuentos

El programa pregunta si se va a registrar un producto o un servicio y solicita sus datos. Después pide un porcentaje de descuento y lo aplica al precio del producto o al total del servicio. Para los productos, la extensión `conIVA()` calcula además el precio con IVA; para los servicios se muestra el total con descuento.

```kotlin
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
```

## 8. Mensajería

El programa permite crear un mensaje de texto o uno de imagen. Solicita el contenido del texto o la URL y dimensiones de la imagen, construye el objeto y muestra su formato. La extensión `sanitizar()` recorta los extremos y elimina los caracteres que no sean letras o números antes de guardar el texto o la URL.

```kotlin
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
```

## 9. Movimiento en plano

El programa lee la posición inicial del coche y los desplazamientos horizontal y vertical. `mover()` crea una nueva posición sumando esos desplazamientos a las coordenadas actuales. Después muestra la posición y calcula la distancia desde ella hasta el origen mediante la extensión `distanciaA()`.

```kotlin
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
```

## 10. Vehículos eléctricos

El programa pregunta si se quiere crear un coche eléctrico o una moto eléctrica y solicita cuánto cargar la batería. El valor de batería se limita al intervalo de 0 a 100. Luego llama, en orden, a `arrancar()`, `revisarBateria()` y `detener()`. Cada subclase muestra mensajes propios al arrancar y detenerse; la extensión informa si la batería está por debajo del 20 %.

```kotlin
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
```
