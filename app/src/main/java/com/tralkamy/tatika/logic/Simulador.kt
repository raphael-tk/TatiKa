//Simulador.kt

package com.tralkamy.tatika.logic

import android.util.Log
import com.tralkamy.tatika.model.*
import kotlin.random.Random

object Simulador {

    fun simular(partida: Partida): Partida {
        val eventos = mutableListOf<String>()

        partida.golsMandante = 0
        partida.golsVisitante = 0

        val timeMandante = partida.mandante
        val timeVisitante = partida.visitante

        val folegoMandante = timeMandante.jogadores.associateWith { 100.0 }.toMutableMap()
        val folegoVisitante = timeVisitante.jogadores.associateWith { 100.0 }.toMutableMap()

        for (minuto in 1..90) {
            val atacante = if (Random.nextBoolean()) timeMandante else timeVisitante
            val defensor = if (atacante == timeMandante) timeVisitante else timeMandante
            val folegoA = if (atacante == timeMandante) folegoMandante else folegoVisitante
            val folegoD = if (defensor == timeMandante) folegoMandante else folegoVisitante

            val evento = sorteiaEvento()

            when (evento) {
                "falta" -> {
                    if (Random.nextDouble() < 0.1) {
                        eventos.add("[$minuto'] Falta perigosa para ${atacante.nome}")
                        if (chanceDeGol(atacante, defensor, folegoA, folegoD)) {
                            registrarGol(partida, atacante, minuto, eventos)
                        }
                    }
                }
                "escanteio" -> {
                    eventos.add("[$minuto'] Escanteio para ${atacante.nome}")
                    if (Random.nextDouble() < 0.15 && chanceDeGol(atacante, defensor, folegoA, folegoD)) {
                        registrarGol(partida, atacante, minuto, eventos)
                    }
                }
                "chute" -> {
                    eventos.add("[$minuto'] Finalização de ${atacante.nome}")
                    if (chanceDeGol(atacante, defensor, folegoA, folegoD)) {
                        registrarGol(partida, atacante, minuto, eventos)
                    }
                }
                "cartao" -> {
                    if (Random.nextDouble() < 0.1) {
                        eventos.add("[$minuto'] Cartão para jogador do ${defensor.nome}")
                    }
                }
                "nada" -> { /* passa o tempo */ }
            }

            folegoMandante.keys.forEach { f -> folegoMandante[f] = (folegoMandante[f]!! - 0.4).coerceAtLeast(30.0) }
            folegoVisitante.keys.forEach { f -> folegoVisitante[f] = (folegoVisitante[f]!! - 0.4).coerceAtLeast(30.0) }
        }

        Log.d("EVENTOS", eventos.joinToString("\n"))
        return partida
    }

    private fun sorteiaEvento(): String {
        val prob = Random.nextDouble()
        return when {
            prob < 0.10 -> "falta"
            prob < 0.20 -> "escanteio"
            prob < 0.50 -> "chute"
            prob < 0.60 -> "cartao"
            else -> "nada"
        }
    }

    private fun chanceDeGol(
        ataque: Time,
        defesa: Time,
        folegoA: Map<Jogador, Double>,
        folegoD: Map<Jogador, Double>
    ): Boolean {
        val mediaAtaque = ataque.jogadores.map {
            val fator = folegoA[it]!! / 100.0
            ((it.chute * 0.4 + it.drible * 0.2 + it.passe * 0.2 + it.fisico * 0.2) * fator)
        }.average()

        val mediaDefesa = defesa.jogadores.map {
            val fator = folegoD[it]!! / 100.0
            ((it.defesa * 0.5 + it.fisico * 0.3 + it.folego * 0.2) * fator)
        }.average()

        val gk = defesa.goleiro
        val gkValor = (gk.elasticidade + gk.reflexo + gk.posicionamento + gk.manejo) / 4.0

        val fatorFinal = mediaAtaque - (mediaDefesa * 0.5 + gkValor * 0.5)
        val chance = when {
            fatorFinal > 10 -> 0.35
            fatorFinal > 5 -> 0.25
            fatorFinal > 0 -> 0.15
            fatorFinal > -5 -> 0.08
            else -> 0.03
        }
        return Random.nextDouble() < chance
    }

    private fun registrarGol(partida: Partida, time: Time, minuto: Int, eventos: MutableList<String>) {
        if (time == partida.mandante) partida.golsMandante++ else partida.golsVisitante++
        eventos.add("[$minuto'] GOL DO ${time.nome}!")
    }
}
