package practica_1

fun main() {
    print("Elige una opcion(1-5): ")
    var menu = sc.nextInt()
    val menuDelDia = when (menu) {
        1 -> "Ensalada"
        2 -> "Sopa"
        3 -> "Pasta"
        4 -> "Carne"
        5 -> "Postre"
        else -> "Consulta la carta"
    }
    print("Elegiste ${menuDelDia}")
}