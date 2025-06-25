package com.tralkamy.tatika.model

data class Partida(
    val mandante: Time,
    val visitante: Time,
    var golsMandante: Int = 0,
    var golsVisitante: Int = 0
)