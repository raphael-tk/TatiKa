package com.tralkamy.tatika

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import com.tralkamy.tatika.data.db.TatiKaSQLiteHelper

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            val dbHelper = TatiKaSQLiteHelper(this@MainActivity)
            dbHelper.writableDatabase // força criação do banco
            Log.d("MAIN", "Banco inicializado com sucesso!")
        }
    }

}

