package com.tralkamy.tatika.logic

import com.tralkamy.tatika.model.Partida
import com.tralkamy.tatika.model.Time

object Rodada {
    fun criarJogos(times: List<Time>): List<Partida> {
        val jogos = mutableListOf<Partida>()
        for (i in times.indices step 2) {
            if (i + 1 < times.size) {
                jogos.add(Partida(times[i], times[i + 1]))
            }
        }
        return jogos
    }

    fun simularRodada(jogos: List<Partida>) {
        jogos.forEach { partida ->
            Simulador.simular(partida)
        }
    }
}