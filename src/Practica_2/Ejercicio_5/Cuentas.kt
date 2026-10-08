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
