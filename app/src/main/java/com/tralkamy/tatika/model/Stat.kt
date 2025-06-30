package com.tralkamy.tatika.model

data class Stat(
    val id: Int = 0,
    val jogadorId: Int,
    val partidaId: Int,
    val gols: Int,
    val assistencias: Int,
    val nota: Float
)
