package com.tralkamy.tatika


import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import com.tralkamy.tatika.data.listaDeTimes
import com.tralkamy.tatika.logic.Rodada
import com.tralkamy.tatika.model.Partida
import androidx.lifecycle.lifecycleScope
import com.tralkamy.tatika.data.db.DatabaseBuilder
import com.tralkamy.tatika.data.entity.LigaEntity
import com.tralkamy.tatika.data.entity.TimeEntity
import com.tralkamy.tatika.logic.Simulador
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = DatabaseBuilder.getInstance(applicationContext)

        lifecycleScope.launch {
            val ligaId = db.ligaDao().insert(LigaEntity(nome = "Brasileirão Betano")).toInt()

            val flamengoId = db.timeDao().insert(
                TimeEntity(
                    nome = "Flamengo",
                    ligaId = ligaId,
                    ataque = 88,
                    meio = 90,
                    defesa = 87
                )
            ).toInt()

            val vascoId = db.timeDao().insert(
                TimeEntity(
                    nome = "Vasco da Gama",
                    ligaId = ligaId,
                    ataque = 82,
                    meio = 80,
                    defesa = 77
                )
            ).toInt()

            val times = db.timeDao().getTimesByLiga(ligaId)

            if (times.size >= 2) {
                val partida = Partida(
                    mandante = com.tralkamy.tatika.model.Time(
                        nome = times[0].nome,
                        forca = (times[0].ataque + times[0].meio + times[0].defesa) / 3
                    ),
                    visitante = com.tralkamy.tatika.model.Time(
                        nome = times[1].nome,
                        forca = (times[1].ataque + times[1].meio + times[1].defesa) / 3
                    )
                )

                Simulador.simular(partida)

                Log.d(
                    "SimulacaoRodada",
                    "${partida.mandante.nome} ${partida.golsMandante} x ${partida.golsVisitante} ${partida.visitante.nome}"
                )
            }
        }
    }
}
