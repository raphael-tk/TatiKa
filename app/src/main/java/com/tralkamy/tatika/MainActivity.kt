package com.tralkamy.tatika

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import com.tralkamy.tatika.data.db.TatiKaSQLiteHelper
import com.tralkamy.tatika.data.bootstrap.BootstrapData

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dbHelper = TatiKaSQLiteHelper(this)
        dbHelper.writableDatabase // força criação

        // preencher com dados reais e atributos random
        BootstrapData.preencherBanco(this)
    }

}

