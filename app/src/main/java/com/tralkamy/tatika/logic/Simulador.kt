package com.tralkamy.tatika.logic

import com.tralkamy.tatika.model.Partida
import kotlin.math.exp
import kotlin.math.pow
import kotlin.random.Random


private const val BASE_DIVISOR = 50.0

object Simulador {

    private fun gerarGolsPoisson(lambda: Double): Int {
        val L = exp(-lambda)
        var p = 1.0
        var k = 0

        do {
            k++
            p *= Random.nextDouble()
        } while (p > L)
        return  k - 1
    }
    fun simular(partida:Partida):Partida{
        val fatorMandante = 1.1


        val mediaMandante = (partida.mandante.forca * fatorMandante) / BASE_DIVISOR
        val mediaVisitante = partida.visitante.forca / BASE_DIVISOR

        partida.golsMandante = gerarGolsPoisson(mediaMandante)
        partida.golsVisitante = gerarGolsPoisson(mediaVisitante)

        return partida
    }
}