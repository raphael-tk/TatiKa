package com.tralkamy.tatika.model

data class Time(
    val id: Int = 0,
    val nome: String,
    val ataque: Int,
    val meio: Int,
    val defesa: Int,
    val ligaId: Int,
    val jogadores: List<Jogador>,
    val goleiro: Goleiro
)
