package com.tralkamy.tatika.data.db.dao

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import com.tralkamy.tatika.model.Goleiro

class GoleiroDao(private val db: SQLiteDatabase) {

    fun inserir(goleiro: Goleiro): Long {
        val values = ContentValues().apply {
            put("nome", goleiro.nome)
            put("posicao", goleiro.posicao)
            put("elasticidade", goleiro.elasticidade)
            put("reflexo", goleiro.reflexo)
            put("manejo", goleiro.manejo)
            put("posicionamento", goleiro.posicionamento)
            put("chute", goleiro.chute)
            put("velocidade", goleiro.velocidade)
            put("timeId", goleiro.timeId)
        }
        return db.insert("goleiros", null, values)
    }

    fun listarTodos(): List<Goleiro> {
        val goleiros = mutableListOf<Goleiro>()
        val cursor = db.rawQuery("SELECT * FROM goleiros", null)
        while (cursor.moveToNext()) {
            val goleiro = Goleiro(
                id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                nome = cursor.getString(cursor.getColumnIndexOrThrow("nome")),
                posicao = cursor.getString(cursor.getColumnIndexOrThrow("posicao")),
                elasticidade = cursor.getInt(cursor.getColumnIndexOrThrow("elasticidade")),
                reflexo = cursor.getInt(cursor.getColumnIndexOrThrow("reflexo")),
                manejo = cursor.getInt(cursor.getColumnIndexOrThrow("manejo")),
                posicionamento = cursor.getInt(cursor.getColumnIndexOrThrow("posicionamento")),
                chute = cursor.getInt(cursor.getColumnIndexOrThrow("chute")),
                velocidade = cursor.getInt(cursor.getColumnIndexOrThrow("velocidade")),
                timeId = cursor.getInt(cursor.getColumnIndexOrThrow("timeId"))
            )
            goleiros.add(goleiro)
        }
        cursor.close()
        return goleiros
    }

    fun deletarTodos() {
        db.delete("goleiros", null, null)
    }

    fun atualizar(goleiro: Goleiro): Int {
        val values = ContentValues().apply {
            put("nome", goleiro.nome)
            put("posicao", goleiro.posicao)
            put("elasticidade", goleiro.elasticidade)
            put("reflexo", goleiro.reflexo)
            put("manejo", goleiro.manejo)
            put("posicionamento", goleiro.posicionamento)
            put("chute", goleiro.chute)
            put("velocidade", goleiro.velocidade)
            put("timeId", goleiro.timeId)
        }
        return db.update("goleiros", values, "id = ?", arrayOf(goleiro.id.toString()))
    }
}
