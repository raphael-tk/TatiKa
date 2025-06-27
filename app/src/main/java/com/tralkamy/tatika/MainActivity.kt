package com.tralkamy.tatika

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import com.tralkamy.tatika.data.db.DatabaseBuilder
import com.tralkamy.tatika.logic.Simulador
import com.tralkamy.tatika.model.Partida
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            val db = DatabaseBuilder.getInstance(this@MainActivity)
            val todosOsTimes = db.timeDao().getAllTimes()

            // Garante que tem pelo menos 20 times
            if (todosOsTimes.size < 20) {
                Log.d("SIMULADOR", "Menos de 20 times no banco!")
                return@launch
            }

            // Pega só os 20 primeiros
            val times = todosOsTimes.take(20)

            // Simula todos contra todos
            for (i in times.indices) {
                for (j in times.indices) {
                    if (i != j) {
                        val partida = Partida(
                            mandante = times[i],
                            visitante = times[j],
                            golsMandante = 0,
                            golsVisitante = 0
                        )
                        val resultado = Simulador.simular(partida)
                        Log.d(
                            "SIMULADOR",
                            "${resultado.mandante.nome} ${resultado.golsMandante} x ${resultado.golsVisitante} ${resultado.visitante.nome}"
                        )
                    }
                }
            }
        }
    }
}
