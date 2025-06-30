package com.tralkamy.tatika.model

data class Jogador(
    val id: Int = 0,
    val nome: String,
    val posicao: String, // Ex: "GOL", "ZAG", "LAT", "VOL", "MEI", "ATA"
    val timeId: Int,

    // Atributos de linha (não goleiro)
    val chute: Int? = null,       // Finalização, posicionamento, antecipação
    val velocidade: Int? = null,  // Corrida, aceleração, agilidade
    val passe: Int? = null,       // Precisão, visão, criatividade
    val desarme: Int? = null,     // Marcação, roubada de bola
    val folego: Int? = null,      // Resistência, preparo físico

    // Atributos de goleiro (só se posicao == "GOL")
    val reflexo: Int? = null,     // Reação a finalizações
    val alcance: Int? = null,     // Envergadura, posicionamento no gol
    val jogoComOsPes: Int? = null,// Participação na saída de bola
    val penaltis: Int? = null,     // Capacidade de pegar pênaltis
    val agilidade: Int? = null      //Capacidade de chegar nas bolas mais rapidamente
)

