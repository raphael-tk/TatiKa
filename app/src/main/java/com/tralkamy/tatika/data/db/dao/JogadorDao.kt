package com.tralkamy.tatika.data.dao

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.tralkamy.tatika.model.Jogador

class JogadorDao(private val db: SQLiteDatabase) {

    fun inserir(jogador: Jogador): Long {
        val values = ContentValues().apply {
            put("nome", jogador.nome)
            put("posicao", jogador.posicao)
            put("folego", jogador.folego)
            put("chute", jogador.chute)
            put("drible", jogador.drible)
            put("defesa", jogador.defesa)
            put("passe", jogador.passe)
            put("fisico", jogador.fisico)
            put("timeId", jogador.timeId)
        }
        return db.insert("jogadores", null, values)
    }

    fun listar(): List<Jogador> {
        val lista = mutableListOf<Jogador>()
        val cursor: Cursor = db.rawQuery("SELECT * FROM jogadores", null)
        if (cursor.moveToFirst()) {
            do {
                val jogador = Jogador(
                    id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                    nome = cursor.getString(cursor.getColumnIndexOrThrow("nome")),
                    posicao = cursor.getString(cursor.getColumnIndexOrThrow("posicao")),
                    folego = cursor.getInt(cursor.getColumnIndexOrThrow("folego")),
                    chute = cursor.getInt(cursor.getColumnIndexOrThrow("chute")),
                    drible = cursor.getInt(cursor.getColumnIndexOrThrow("drible")),
                    defesa = cursor.getInt(cursor.getColumnIndexOrThrow("defesa")),
                    passe = cursor.getInt(cursor.getColumnIndexOrThrow("passe")),
                    fisico = cursor.getInt(cursor.getColumnIndexOrThrow("fisico")),
                    timeId = cursor.getInt(cursor.getColumnIndexOrThrow("timeId"))
                )
                lista.add(jogador)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return lista
    }

    fun excluirTodos() {
        db.delete("jogadores", null, null)
    }
}