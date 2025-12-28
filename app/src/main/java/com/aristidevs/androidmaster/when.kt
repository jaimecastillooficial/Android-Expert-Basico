fun main(){

println(getmonth(7))
}

fun getmonth(month:Int){
    when(month){
        in 1..8-> println("Enero")


        9 -> println("Septiembre")
        10 -> println("Octubre")
        11 -> println("Noviembre")
        12 -> println("Diciembre")
        else -> println("No es un mes valido")
    }

}