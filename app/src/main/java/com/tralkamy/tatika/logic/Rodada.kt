package com.tralkamy.tatika.logic

import com.tralkamy.tatika.model.Partida
import com.tralkamy.tatika.model.Time
import com.tralkamy.tatika.model.criarPartidaSimples
import com.tralkamy.tatika.model.criarTimeFolga


object Rodada {

    // Gera rodadas com confrontos
    fun criarRodadas(times: List<Time>): List<List<Partida>> {
        val rodadas = mutableListOf<List<Partida>>()
        val totalRodadas = times.size - 1
        val metade = times.size / 2
        val lista = times.toMutableList()

        // Se número ímpar, adiciona um "bye" (folga)
        var temFolga = false
        if (lista.size % 2 != 0) {
            lista.add(criarTimeFolga())
        }


        for (rodada in 0 until totalRodadas) {
            val jogos = mutableListOf<Partida>()

            for (i in 0 until metade) {
                val mandante = lista[i]
                val visitante = lista[lista.size - 1 - i]

                if (mandante.id != -1 && visitante.id != -1) {
                    jogos.add(criarPartidaSimples(mandante, visitante, rodada = rodada + 1, temporada = 2025))
                }
            }

            rodadas.add(jogos)

            // Rotaciona os times (exceto o primeiro)
            val primeiro = lista.first()
            val resto = lista.drop(1).toMutableList()
            resto.add(0, resto.removeAt(resto.lastIndex))
            lista.clear()
            lista.add(primeiro)
            lista.addAll(resto)
        }

        return rodadas
    }


    // Simula os jogos da rodada
    fun simularRodada(jogos: List<Partida>): List<Partida> {
        return jogos.map { Simulador.simular(it) }
    }
}
