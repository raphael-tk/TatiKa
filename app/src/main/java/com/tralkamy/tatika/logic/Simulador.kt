package com.tralkamy.tatika.logic

import android.util.Log
import com.tralkamy.tatika.data.entity.TimeEntity
import com.tralkamy.tatika.model.Partida
import com.tralkamy.tatika.model.Time
import kotlin.math.exp
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
       //pesos
        val pesoAtaque = 0.5
        val pesoMeio = 0.3
        val pesoDefesaAdversaria = 0.2
        val fatorMandante = 1.1

        fun calcularXG(time: TimeEntity, adversario: TimeEntity, isMandante: Boolean): Double{
            val ataque = time.ataque * pesoAtaque
            val meio = time.meio * pesoMeio
            val defesaAdversaria = adversario.defesa * pesoDefesaAdversaria

            var xG = (ataque + meio - defesaAdversaria) / 100.0

            if (isMandante) xG *= fatorMandante

            return maxOf(0.2, xG)
        }

        val xgMandante = calcularXG(partida.mandante, partida.visitante, true)
        val xgVisitante = calcularXG(partida.visitante, partida.mandante, false)

        Log.d("SIMULADOR", "xG Mandante: $xgMandante | xG Visistante: $xgVisitante")

        partida.golsMandante = gerarGolsPoisson(xgMandante)
        partida.golsVisitante = gerarGolsPoisson(xgVisitante)

        return partida
    }
}