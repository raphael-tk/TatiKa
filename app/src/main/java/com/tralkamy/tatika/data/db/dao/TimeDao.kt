package com.tralkamy.tatika.data.db.dao

import android.content.ContentValues
import android.content.Context
import com.tralkamy.tatika.data.db.TatiKaSQLiteHelper
import com.tralkamy.tatika.model.Time

class TimeDao(context: Context) {
    private val dbHelper = TatiKaSQLiteHelper(context)

    fun inserir(time: Time): Long {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("nome", time.nome)
            put("ataque", time.ataque)
            put("meio", time.meio)
            put("defesa", time.defesa)
            put("ligaId", time.ligaId)
        }
        return db.insert("times", null, values)
    }

    fun listarTodos(): List<Time> {
        val db = dbHelper.readableDatabase
        val cursor = db.query("times", null, null, null, null, null, null)
        val times = mutableListOf<Time>()
        while (cursor.moveToNext()) {
            val time = Time(
                id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                nome = cursor.getString(cursor.getColumnIndexOrThrow("nome")),
                ataque = cursor.getInt(cursor.getColumnIndexOrThrow("ataque")),
                meio = cursor.getInt(cursor.getColumnIndexOrThrow("meio")),
                defesa = cursor.getInt(cursor.getColumnIndexOrThrow("defesa")),
                ligaId = cursor.getInt(cursor.getColumnIndexOrThrow("ligaId")),
                jogadores = TODO(),
                goleiro = TODO()
            )
            times.add(time)
        }
        cursor.close()
        return times
    }

    // Adiciona métodos update e delete se quiser
}
