package com.tralkamy.tatika.data.bootstrap

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.tralkamy.tatika.data.db.TatiKaSQLiteHelper
import kotlin.random.Random

object BootstrapData {
    private val timeSerieA = listOf(
        "SE Palmeiras", "CR Flamengo", "Botafogo FR", "SC Corinthians", "SC Internacional",
        "EC Bahia", "Atlético Mineiro", "São Paulo FC", "Grêmio FBPA", "Cruzeiro EC",
        "Fluminense FC", "CR Vasco da Gama", "RB Bragantino", "Santos FC", "Fortaleza EC",
        "Sport Recife", "EC Vitória", "Ceará SC", "Mirassol FC", "EC Juventude"
    )

    fun preencherBanco(context: Context) {
        val db = TatiKaSQLiteHelper(context).writableDatabase

        val ligaId = inserirLiga(db, "Brasileirão Betano Serie A")

        for (time in timeSerieA) {
            val ataque = randomAttr()
            val meio = randomAttr()
            val defesa = randomAttr()
            val timeId = inserirTime(db, time, ataque, meio, defesa, ligaId)
            inserirJogadores(db, timeId)
            inserirGoleiro(db, timeId)
        }

        db.close()
        Log.d("BOOTSTRAP", "Preenchimento inicial completo!")
    }

    private fun inserirLiga(db: SQLiteDatabase, nome: String): Long {
        db.execSQL("INSERT INTO ligas (nome) VALUES (?)", arrayOf(nome))
        return db.rawQuery("SELECT id FROM ligas WHERE nome = ?", arrayOf(nome)).use {
            it.moveToFirst()
            it.getLong(0)
        }
    }

    private fun inserirTime(db: SQLiteDatabase, nome: String, ataque: Int, meio: Int, defesa: Int, ligaId: Long): Long {
        db.execSQL(
            "INSERT INTO times (nome, ataque, meio, defesa, ligaId) VALUES (?, ?, ?, ?, ?)",
            arrayOf(nome, ataque, meio, defesa, ligaId)
        )
        return db.rawQuery("SELECT id FROM times WHERE nome = ?", arrayOf(nome)).use {
            it.moveToFirst()
            it.getLong(0)
        }
    }

    private fun inserirJogadores(db: SQLiteDatabase, timeId: Long) {
        repeat(10) {
            val nome = "Jogador ${('A'..'Z').random()}${Random.nextInt(100)}"
            val posicao = listOf("ATA", "SA", "PE", "PD", "MEI", "MC", "ME", "MD", "ZAG", "LAT", "VOL").random()
            val folego = randomAttr()
            val chute = randomAttr()
            val drible = randomAttr()
            val defesa = randomAttr()
            val passe = randomAttr()
            val fisico = randomAttr()

            db.execSQL(
                "INSERT INTO jogadores (nome, posicao, folego, chute, drible, defesa, passe, fisico, timeId) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)",
                arrayOf(nome, posicao, folego, chute, drible, defesa, passe, fisico, timeId)
            )
        }
    }

    private fun inserirGoleiro(db: SQLiteDatabase, timeId: Long) {
        val nome = "Goleiro ${('A'..'Z').random()}${Random.nextInt(100)}"
        val posicao = "GOL"
        val elasticidade = randomAttr()
        val reflexo = randomAttr()
        val manejo = randomAttr()
        val posicionamento = randomAttr()
        val chute = randomAttr()
        val velocidade = randomAttr()

        db.execSQL(
            "INSERT INTO goleiros (nome, posicao, elasticidade, reflexo, manejo, posicionamento, chute, velocidade, timeId) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)",
            arrayOf(nome, posicao, elasticidade, reflexo, manejo, posicionamento, chute, velocidade, timeId)
        )
    }
    private fun randomAttr(): Int = Random.nextInt(50, 95)
}
