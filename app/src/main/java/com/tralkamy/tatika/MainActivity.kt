package com.tralkamy.tatika

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.tralkamy.tatika.data.db.AppDatabase
import com.tralkamy.tatika.data.db.DatabaseBuilder
import com.tralkamy.tatika.data.db.dao.TimeDao
import com.tralkamy.tatika.logic.Simulador
import com.tralkamy.tatika.model.Partida
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = DatabaseBuilder.getInstance(this)


        lifecycleScope.launch {
            testarPuxarTimesETestarSimulador(db.timeDao())
        }
    }

    suspend fun testarPuxarTimesETestarSimulador(timeDao: TimeDao) {
        val todosTimes = timeDao.getAllTimes()
        val primeiros20 = todosTimes.take(20)

        primeiros20.forEach {
            println("Time: ${it.nome} | Ataque: ${it.ataque} | Meio: ${it.meio} | Defesa: ${it.defesa}")
        }

        if (primeiros20.size >= 2) {
            val partida = Partida(
                mandante = primeiros20[0],
                visitante = primeiros20[1]
            )
            val resultado = Simulador.simular(partida)
            println("Resultado: ${resultado.mandante.nome} ${resultado.golsMandante} x ${resultado.golsVisitante} ${resultado.visitante.nome}")
        } else {
            println("Não tem times suficientes na DB pra simular uma partida.")
        }
    }

}
