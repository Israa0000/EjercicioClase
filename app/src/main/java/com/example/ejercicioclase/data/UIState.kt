package com.example.ejercicioclase.data

data class UIState(
    val email : String = "",
    val nombre : String = "",
    val apellido : String = "",
    val contraseña : String = "",
    val cumpleanios : String = "",
    val recibirCorreos : Boolean = false,
    val aceptarTerminos : Boolean = false
)
