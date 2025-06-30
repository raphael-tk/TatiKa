package com.tralkamy.tatika.data.db.dao

import android.content.ContentValues
import android.content.Context
import com.tralkamy.tatika.data.db.TatiKaSQLiteHelper
import com.tralkamy.tatika.model.Liga

class LigaDao(context: Context) {
    private val dbHelper = TatiKaSQLiteHelper(context)

    fun inserir(liga: Liga): Long {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("nome", liga.nome)
        }
        return db.insert("ligas", null, values)
    }

    fun listarTodas(): List<Liga> {
        val db = dbHelper.readableDatabase
        val cursor = db.query("ligas", null, null, null, null, null, null)
        val ligas = mutableListOf<Liga>()
        while (cursor.moveToNext()) {
            val liga = Liga(
                id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                nome = cursor.getString(cursor.getColumnIndexOrThrow("nome"))
            )
            ligas.add(liga)
        }
        cursor.close()
        return ligas
    }
}