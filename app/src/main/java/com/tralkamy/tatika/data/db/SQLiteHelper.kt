package com.tralkamy.tatika.data.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class TatiKaSQLiteHelper(context: Context) : SQLiteOpenHelper(context, "tatika.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("""
            CREATE TABLE ligas (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nome TEXT NOT NULL
            );
        """)
        db.execSQL("""
            CREATE TABLE times (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nome TEXT NOT NULL,
                ataque INTEGER,
                meio INTEGER,
                defesa INTEGER,
                ligaId INTEGER,
                FOREIGN KEY(ligaId) REFERENCES ligas(id)
            );
        """)
        db.execSQL("""
    CREATE TABLE jogadores (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        nome TEXT NOT NULL,
        posicao TEXT NOT NULL,
        timeId INTEGER NOT NULL,

        -- Atributos de linha
        chute INTEGER,
        velocidade INTEGER,
        passe INTEGER,
        desarme INTEGER,
        folego INTEGER,

        -- Atributos de goleiro
        reflexo INTEGER,
        alcance INTEGER,
        jogoComOsPes INTEGER,
        penaltis INTEGER,

        FOREIGN KEY(timeId) REFERENCES times(id)
    );
""")

        db.execSQL("""
            CREATE TABLE temporadas (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                ano INTEGER NOT NULL,
                ligaId INTEGER,
                FOREIGN KEY(ligaId) REFERENCES ligas(id)
            );
        """)
        db.execSQL("""
            CREATE TABLE partidas (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                temporada INTEGER,
                rodada INTEGER,
                mandanteId INTEGER,
                visitanteId INTEGER,
                golsMandante INTEGER,
                golsVisitante INTEGER,
                FOREIGN KEY(mandanteId) REFERENCES times(id),
                FOREIGN KEY(visitanteId) REFERENCES times(id),
                FOREIGN KEY(temporada) REFERENCES temporadas(id)
            );
        """)
        db.execSQL("""
            CREATE TABLE stats (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                jogadorId INTEGER,
                partidaId INTEGER,
                gols INTEGER,
                assistencias INTEGER,
                nota REAL,
                FOREIGN KEY(jogadorId) REFERENCES jogadores(id),
                FOREIGN KEY(partidaId) REFERENCES partidas(id)
            );
        """)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS stats")
        db.execSQL("DROP TABLE IF EXISTS partidas")
        db.execSQL("DROP TABLE IF EXISTS temporadas")
        db.execSQL("DROP TABLE IF EXISTS jogadores")
        db.execSQL("DROP TABLE IF EXISTS times")
        db.execSQL("DROP TABLE IF EXISTS ligas")
        onCreate(db)
    }
}
