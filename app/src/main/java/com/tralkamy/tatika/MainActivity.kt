package com.tralkamy.tatika


import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import com.tralkamy.tatika.data.listaDeTimes
import com.tralkamy.tatika.logic.Rodada
import com.tralkamy.tatika.model.Partida

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val jogosRodada: List<Partida> = Rodada.criarJogos(listaDeTimes)
        Rodada.simularRodada(jogosRodada)

        jogosRodada.forEach { partida ->
            Log.d("SimulacaoRodada", "${partida.mandante.nome} ${partida.golsMandante} x ${partida.golsVisitante} ${partida.visitante.nome}")
        }
    }
}
