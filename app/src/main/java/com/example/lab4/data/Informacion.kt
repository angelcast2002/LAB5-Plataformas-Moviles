package com.example.lab4.data

import java.io.Serializable

data class Informacion(
    val nombre: String,
    val direccion: String,
    val horario: String
) : Serializable