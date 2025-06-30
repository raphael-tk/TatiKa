package com.tralkamy.tatika.data.db.dao

import android.content.ContentValues
import android.content.Context
import com.tralkamy.tatika.data.db.TatiKaSQLiteHelper
import com.tralkamy.tatika.model.Temporada

class TemporadaDao(context: Context) {
    private val dbHelper = TatiKaSQLiteHelper(context)

    fun inserir(temporada: Temporada): Long {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("ano", temporada.ano)
            put("ligaId", temporada.ligaId)
        }
        return db.insert("temporadas", null, values)
    }

    fun listarTodas(): List<Temporada> {
        val db = dbHelper.readableDatabase
        val cursor = db.query("temporadas", null, null, null, null, null, null)
        val temporadas = mutableListOf<Temporada>()
        while (cursor.moveToNext()) {
            val temporada = Temporada(
                id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                ano = cursor.getInt(cursor.getColumnIndexOrThrow("ano")),
                ligaId = cursor.getInt(cursor.getColumnIndexOrThrow("ligaId"))
            )
            temporadas.add(temporada)
        }
        cursor.close()
        return temporadas
    }
}