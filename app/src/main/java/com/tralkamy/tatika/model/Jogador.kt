package com.tralkamy.tatika.model

data class Jogador(
    val id: Int = 0,
    val nome: String,
    val posicao: String,
    val folego: Int,
    val chute: Int,
    val drible: Int,
    val defesa: Int,
    val passe: Int,
    val fisico: Int,
    val timeId: Int
) {
    fun calcularOverall(): Int {
        val soma = folego + chute + drible + defesa + passe + fisico
        return (soma / 5.1).toInt()
    }
}