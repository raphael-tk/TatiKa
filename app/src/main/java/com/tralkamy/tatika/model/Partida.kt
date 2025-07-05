package com.tralkamy.tatika.model

data class Partida(
    val id: Int = 0,
    val temporada: Int,
    val rodada: Int,
    val mandanteId: Int,
    val visitanteId: Int,
    val mandante: Time,
    val visitante: Time,
    var golsMandante: Int = 0,
    var golsVisitante: Int = 0
)

