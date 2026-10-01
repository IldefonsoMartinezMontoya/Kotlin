package practica_1_EjerciciosLenguajeKotlin

fun main(args: Array<String>) {
    var bateria: Int
    var kilometros: Int
    do {
        print("¿Cuánta bateria tenemos? ")
        bateria = sc.nextInt()
        if (bateria !in 0..100) {
            println("Porcentaje inválido")
        }
    } while (bateria !in 0..<100)

    do {
        print("¿Cuántos kilometros hasta el siguiente cargador? ")
        kilometros = sc.nextInt()
        if (kilometros < 0) {
            println("Número inválido")
        }
    } while (kilometros < 0)

    if (bateria >= 30 && kilometros <= 20) {
        println("Puedes continuar el viaje")
    } else if (bateria < 30 && kilometros <= 5) {
        println("Busca cargador cercano")
    } else {
        println("Detente y carga")
    }
}