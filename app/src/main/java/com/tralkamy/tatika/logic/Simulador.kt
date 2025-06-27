package com.tralkamy.tatika.logic

import android.util.Log
import com.tralkamy.tatika.model.Partida
import kotlin.math.exp
import kotlin.math.pow
import kotlin.random.Random


private const val xG = 2.4

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
        val fatorMandante = 1.05
        val forcaMandante = partida.mandante.ataque + partida.mandante.meio + partida.mandante.defesa
        val forcaVisitante = partida.visitante.ataque + partida.visitante.meio + partida.visitante.defesa

        val mediaMandante = (forcaMandante / 300.0) * xG * fatorMandante
        val mediaVisitante = (forcaVisitante / 300.0) * xG

        Log.d("SIMULADOR", "Média Mandante: $mediaMandante | Média Visitante: $mediaVisitante")
        partida.golsMandante = gerarGolsPoisson(mediaMandante)
        partida.golsVisitante = gerarGolsPoisson(mediaVisitante)

        return partida
    }
}