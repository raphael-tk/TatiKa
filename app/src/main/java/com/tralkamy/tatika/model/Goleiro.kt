package com.tralkamy.tatika.model

data class Goleiro(
    val id: Int = 0,
    val nome: String,
    val posicao: String, // pode ser fixo ou validado depois
    val elasticidade: Int,
    val reflexo: Int,
    val manejo: Int,
    val posicionamento: Int,
    val chute: Int, // para tiros de meta
    val velocidade: Int,
    val timeId: Int
){
    fun calcularOverall(): Int {
        val soma = elasticidade + reflexo + manejo + posicionamento + chute + velocidade
        return (soma / 5.1).toInt()
    }
}
