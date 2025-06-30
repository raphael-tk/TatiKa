package com.tralkamy.tatika.data.db.dao

import android.content.ContentValues
import android.content.Context
import com.tralkamy.tatika.data.db.TatiKaSQLiteHelper
import com.tralkamy.tatika.model.Stat

class StatDao(context: Context) {
    private val dbHelper = TatiKaSQLiteHelper(context)

    fun inserir(stat: Stat): Long {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("jogadorId", stat.jogadorId)
            put("partidaId", stat.partidaId)
            put("gols", stat.gols)
            put("assistencias", stat.assistencias)
            put("nota", stat.nota)
        }
        return db.insert("stats", null, values)
    }

    fun listarTodos(): List<Stat> {
        val db = dbHelper.readableDatabase
        val cursor = db.query("stats", null, null, null, null, null, null)
        val stats = mutableListOf<Stat>()
        while (cursor.moveToNext()) {
            val stat = Stat(
                id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                jogadorId = cursor.getInt(cursor.getColumnIndexOrThrow("jogadorId")),
                partidaId = cursor.getInt(cursor.getColumnIndexOrThrow("partidaId")),
                gols = cursor.getInt(cursor.getColumnIndexOrThrow("gols")),
                assistencias = cursor.getInt(cursor.getColumnIndexOrThrow("assistencias")),
                nota = cursor.getFloat(cursor.getColumnIndexOrThrow("nota"))
            )
            stats.add(stat)
        }
        cursor.close()
        return stats
    }
}