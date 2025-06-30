package com.tralkamy.tatika.data.dao

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.tralkamy.tatika.model.Jogador
import com.tralkamy.tatika.data.db.TatiKaSQLiteHelper

class JogadorDao(context: Context) {
    private val dbHelper = TatiKaSQLiteHelper(context)

    fun inserir(jogador: Jogador): Long {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("nome", jogador.nome)
            put("posicao", jogador.posicao)
            put("timeId", jogador.timeId)

            // Atributos de linha
            put("chute", jogador.chute)
            put("velocidade", jogador.velocidade)
            put("passe", jogador.passe)
            put("desarme", jogador.desarme)
            put("folego", jogador.folego)

            // Atributos de goleiro
            put("reflexo", jogador.reflexo)
            put("alcance", jogador.alcance)
            put("jogoComOsPes", jogador.jogoComOsPes)
            put("penaltis", jogador.penaltis)
        }
        return db.insert("jogadores", null, values)
    }

    fun listarTodos(): List<Jogador> {
        val db = dbHelper.readableDatabase
        val cursor = db.query("jogadores", null, null, null, null, null, null)
        val lista = mutableListOf<Jogador>()

        if (cursor.moveToFirst()) {
            do {
                lista.add(cursorToJogador(cursor))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return lista
    }

    fun buscarPorId(id: Int): Jogador? {
        val db = dbHelper.readableDatabase
        val cursor = db.query(
            "jogadores",
            null,
            "id = ?",
            arrayOf(id.toString()),
            null,
            null,
            null
        )

        var jogador: Jogador? = null
        if (cursor.moveToFirst()) {
            jogador = cursorToJogador(cursor)
        }
        cursor.close()
        return jogador
    }

    fun atualizar(jogador: Jogador): Int {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("nome", jogador.nome)
            put("posicao", jogador.posicao)
            put("timeId", jogador.timeId)

            // Atributos de linha
            put("chute", jogador.chute)
            put("velocidade", jogador.velocidade)
            put("passe", jogador.passe)
            put("desarme", jogador.desarme)
            put("folego", jogador.folego)

            // Atributos de goleiro
            put("reflexo", jogador.reflexo)
            put("alcance", jogador.alcance)
            put("jogoComOsPes", jogador.jogoComOsPes)
            put("penaltis", jogador.penaltis)
        }

        return db.update(
            "jogadores",
            values,
            "id = ?",
            arrayOf(jogador.id.toString())
        )
    }

    fun deletar(id: Int): Int {
        val db = dbHelper.writableDatabase
        return db.delete("jogadores", "id = ?", arrayOf(id.toString()))
    }

    private fun cursorToJogador(cursor: Cursor): Jogador {
        return Jogador(
            id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
            nome = cursor.getString(cursor.getColumnIndexOrThrow("nome")),
            posicao = cursor.getString(cursor.getColumnIndexOrThrow("posicao")),
            timeId = cursor.getInt(cursor.getColumnIndexOrThrow("timeId")),

            // Linha
            chute = cursor.getIntOrNull("chute"),
            velocidade = cursor.getIntOrNull("velocidade"),
            passe = cursor.getIntOrNull("passe"),
            desarme = cursor.getIntOrNull("desarme"),
            folego = cursor.getIntOrNull("folego"),

            // Goleiro
            reflexo = cursor.getIntOrNull("reflexo"),
            alcance = cursor.getIntOrNull("alcance"),
            jogoComOsPes = cursor.getIntOrNull("jogoComOsPes"),
            penaltis = cursor.getIntOrNull("penaltis"),
        )
    }

    // Extensão opcional pra pegar valores int nulos
    private fun Cursor.getIntOrNull(columnName: String): Int? {
        val index = getColumnIndexOrThrow(columnName)
        return if (isNull(index)) null else getInt(index)
    }
}
