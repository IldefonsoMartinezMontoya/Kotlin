package practica_1_EjerciciosLenguajeKotlin

import java.util.Scanner

val sc = Scanner(System.`in`)
fun main(args: Array<String>) {
    var aforo: Int
    var asistentes: Int
    do {
        print("Dime el aforo: ")
        aforo = sc.nextInt()
        if (aforo < 0) {
            println("Número inválido")
        }
    } while (aforo < 0)

    do {
        print("Dime el número de asistentes: ")
        asistentes = sc.nextInt()
        if (asistentes < 0) {
            println("Número inválido")
        }
    } while (asistentes < 0)

    if (asistentes > aforo) {
        println("Aforo completo")
    } else {
        println("Quedan ${aforo - asistentes} plazas")
    }
}
