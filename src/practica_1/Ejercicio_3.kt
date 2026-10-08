package practica_1

fun main() {
    println("Dame un numerico: ")
    var n = sc.nextInt()
    var suma = 0
    var iterador = 1

    if (n <= 0) {
        println("Introduce un número positivo")
    } else {
        println("Suma y Media con For")
        for (i in 1..n) {
            suma += i
        }
        println("Suma: $suma Media: ${suma.toDouble() / n}")
        suma = 0
        println("Suma y Media con While")
        while (iterador <= n) {
            suma += iterador
            iterador++
        }
        println("Suma: $suma Media: ${suma.toDouble() / n}")
    }
}