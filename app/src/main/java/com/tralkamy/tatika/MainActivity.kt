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
import com.tralkamy.tatika.ligas.Brasileirao
import com.tralkamy.tatika.logic.Simulador
import com.tralkamy.tatika.model.Partida
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = DatabaseBuilder.getInstance(this)


        lifecycleScope.launch {
                 Brasileirao.simularCampeonato(db.timeDao(), db.partidaDao())
        }
    }
}
