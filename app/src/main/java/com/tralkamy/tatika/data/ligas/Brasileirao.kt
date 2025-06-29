package com.tralkamy.tatika.ligas

import android.util.Log
import com.tralkamy.tatika.data.db.dao.PartidaDao
import com.tralkamy.tatika.data.db.dao.TimeDao
import com.tralkamy.tatika.data.entity.PartidaEntity
import com.tralkamy.tatika.data.entity.TimeEntity
import com.tralkamy.tatika.logic.Simulador
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object Brasileirao {

    suspend fun simularCampeonato(
        timeDao: TimeDao,
        partidaDao: PartidaDao
    ) = withContext(Dispatchers.IO) {

        var times = timeDao.getAllTimes()

        if (times.isEmpty()) {
            val clubesBrasileiros = listOf(
                TimeEntity(nome = "CR Flamengo", ligaId = 1, ataque = 85, meio = 82, defesa = 78),
                TimeEntity(nome = "SE Palmeiras", ligaId = 1, ataque = 80, meio = 80, defesa = 82),
                TimeEntity(nome = "São Paulo FC", ligaId = 1, ataque = 77, meio = 75, defesa = 76),
                TimeEntity(nome = "Atlético Mineiro", ligaId = 1, ataque = 79, meio = 78, defesa = 75),
                TimeEntity(nome = "Fluminense FC", ligaId = 1, ataque = 76, meio = 79, defesa = 74),
                TimeEntity(nome = "Botafogo FR", ligaId = 1, ataque = 74, meio = 72, defesa = 70),
                TimeEntity(nome = "Grêmio FBPA", ligaId = 1, ataque = 78, meio = 74, defesa = 71),
                TimeEntity(nome = "SC Internacional", ligaId = 1, ataque = 75, meio = 76, defesa = 73),
                TimeEntity(nome = "SC Corinthians", ligaId = 1, ataque = 70, meio = 72, defesa = 69),
                TimeEntity(nome = "EC Bahia", ligaId = 1, ataque = 68, meio = 66, defesa = 67)
            )

            clubesBrasileiros.forEach { timeDao.insert(it) }
            times = timeDao.getAllTimes()
        }

        val partidas = mutableListOf<PartidaEntity>()
        var rodadaAtual = 1

        for (i in times.indices) {
            for (j in times.indices) {
                if (i != j) {
                    val mandante = times[i]
                    val visitante = times[j]

                    val partida = Simulador.simular(
                        com.tralkamy.tatika.model.Partida(
                            mandante = mandante,
                            visitante = visitante
                        )
                    )

                    Log.d("RESULTADO RODADA $rodadaAtual", "${partida.mandante.nome} ${partida.golsMandante} x ${partida.golsVisitante} ${partida.visitante.nome}")

                    partidas.add(
                        PartidaEntity(
                            mandanteId = mandante.id,
                            visitanteId = visitante.id,
                            golsMandante = partida.golsMandante,
                            golsVisitante = partida.golsVisitante,
                            rodada = rodadaAtual,
                            temporada = 1
                        )
                    )

                    rodadaAtual++
                }
            }
        }

        partidaDao.insertAll(partidas)
    }
}
