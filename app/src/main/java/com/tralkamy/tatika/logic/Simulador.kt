package com.tralkamy.tatika.logic

import android.util.Log
import com.tralkamy.tatika.model.Jogador
import com.tralkamy.tatika.model.Goleiro
import com.tralkamy.tatika.model.Partida
import com.tralkamy.tatika.model.Time
import kotlin.math.exp
import kotlin.random.Random

object Simulador {

    private fun gerarGolsPoisson(xg: Double): Int {
        val lambda = when {
            xg < 0.5 -> xg * 1.5
            xg < 0.8 -> xg * 1.3
            xg < 1.2 -> xg * 1.1
            else     -> xg
        }.coerceAtLeast(0.4)

        val L = exp(-lambda)
        var p = 1.0
        var k = 0

        do {
            k++
            p *= Random.nextDouble()
        } while (p > L)

        return k - 1
    }

    fun simular(partida: Partida): Partida {
        val xgMandante = calcularXG(partida.mandante, partida.visitante, true)
        val xgVisitante = calcularXG(partida.visitante, partida.mandante, false)

        Log.d("SIMULADOR", "xG Mandante: $xgMandante | xG Visitante: $xgVisitante")

        partida.golsMandante = gerarGolsPoisson(xgMandante)
        partida.golsVisitante = gerarGolsPoisson(xgVisitante)

        return partida
    }

    private fun calcularXG(time: Time, adversario: Time, isMandante: Boolean): Double {
        val pesoChute = 0.4
        val pesoPasse = 0.2
        val pesoDrible = 0.2
        val pesoFisicoFolego = 0.1

        val pesoDefesa = 0.5
        val pesoGoleiro = 0.5
        val fatorMandante = 1.1

        // ATAQUE
        val atacantes = time.jogadores

        val ataque = atacantes.map {
            val variacao = Random.nextDouble(0.95, 1.05)
            (it.chute * pesoChute +
                    it.passe * pesoPasse +
                    it.drible * pesoDrible +
                    ((it.fisico + it.folego) / 2.0) * pesoFisicoFolego) * variacao
        }.average()


        // DEFESA ADVERSÁRIA
        val defensores = adversario.jogadores

        val defesa = defensores.map {
            it.defesa * pesoDefesa +
                    ((it.fisico + it.folego) / 2.0) * (1 - pesoDefesa)
        }.average()

        // GOLEIRO
        val gk = adversario.goleiro
        val qualidadeGoleiro =
            (gk.elasticidade + gk.reflexo + gk.posicionamento + gk.manejo) / 4.0

        val impactoDefensivoTotal = (defesa * pesoDefesa + qualidadeGoleiro * pesoGoleiro)

        // CALCULA XG FINAL
        var xG = (ataque - impactoDefensivoTotal) / 10.0

        if (isMandante) xG *= fatorMandante

        xG *= Random.nextDouble(0.9, 1.1)
        return xG.coerceIn(0.3, 3.0)
    }
}
