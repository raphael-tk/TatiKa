package com.tralkamy.tatika

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.tralkamy.tatika.data.db.AppDatabase
import com.tralkamy.tatika.data.db.DatabaseBuilder
import com.tralkamy.tatika.data.db.dao.TimeDao
import com.tralkamy.tatika.data.db.dao.PartidaDao
import com.tralkamy.tatika.data.entity.TimeEntity
import com.tralkamy.tatika.ligas.Brasileirao
import com.tralkamy.tatika.logic.Simulador
import com.tralkamy.tatika.model.Partida
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = DatabaseBuilder.getInstance(this)

        // Força criação da tabela e inserção de teste
        lifecycleScope.launch {

            val lista = db.timeDao().getAllTimes()
            Log.d("BANCO", "Times no banco: ${lista.map { it.nome }}")
        }
        db.query("PRAGMA wal_checkpoint(FULL)", null)
    }
}

