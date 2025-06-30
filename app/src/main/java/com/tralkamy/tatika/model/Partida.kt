package com.tralkamy.tatika.model

data class Partida(
    val id: Int = 0,
    val temporada: Int,
    val rodada: Int,
    val mandanteId: Int,
    val visitanteId: Int,
    val golsMandante: Int,
    val golsVisitante: Int
)
