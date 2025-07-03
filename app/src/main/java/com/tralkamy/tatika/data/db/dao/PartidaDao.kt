package com.tralkamy.tatika.data.db.dao

import android.content.ContentValues
import android.content.Context
import com.tralkamy.tatika.data.db.TatiKaSQLiteHelper
import com.tralkamy.tatika.model.Partida

class PartidaDao(context: Context) {
    private val dbHelper = TatiKaSQLiteHelper(context)

    fun inserir(partida: Partida): Long {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("temporada", partida.temporada)
            put("rodada", partida.rodada)
            put("mandanteId", partida.mandanteId)
            put("visitanteId", partida.visitanteId)
            put("golsMandante", partida.golsMandante)
            put("golsVisitante", partida.golsVisitante)
        }
        return db.insert("partidas", null, values)
    }

    fun listarTodas(): List<Partida> {
        val db = dbHelper.readableDatabase
        val cursor = db.query("partidas", null, null, null, null, null, null)
        val partidas = mutableListOf<Partida>()
        while (cursor.moveToNext()) {
            val partida = Partida(
                id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                temporada = cursor.getInt(cursor.getColumnIndexOrThrow("temporada")),
                rodada = cursor.getInt(cursor.getColumnIndexOrThrow("rodada")),
                mandanteId = cursor.getInt(cursor.getColumnIndexOrThrow("mandanteId")),
                visitanteId = cursor.getInt(cursor.getColumnIndexOrThrow("visitanteId")),
                golsMandante = cursor.getInt(cursor.getColumnIndexOrThrow("golsMandante")),
                golsVisitante = cursor.getInt(cursor.getColumnIndexOrThrow("golsVisitante")),
                mandante = TODO(),
                visitante = TODO()
            )
            partidas.add(partida)
        }
        cursor.close()
        return partidas
    }
}