package com.tralkamy.tatika.data.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class TatiKaSQLiteHelper(context: Context) : SQLiteOpenHelper(context, "tatika.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("""
            CREATE TABLE ligas (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nome TEXT NOT NULL
            );
        """)
        Log.d("DB_CREATE", "Tabela 'ligas' criada com sucesso.")
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
        Log.d("DB_CREATE", "Tabela 'times' criada com sucesso.")
        db.execSQL("""
    CREATE TABLE jogadores (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        nome TEXT NOT NULL,
        posicao TEXT NOT NULL,
        folego INTEGER,
        chute INTEGER,
        drible INTEGER,
        defesa INTEGER,
        passe INTEGER,
        fisico INTEGER,
        timeId INTEGER,
        FOREIGN KEY(timeId) REFERENCES times(id)
    );
""")
        Log.d("DB_CREATE", "Tabela 'jogadores' criada com sucesso.")
        db.execSQL("""
    CREATE TABLE goleiros (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        nome TEXT NOT NULL,
        posicao TEXT,
        elasticidade INTEGER,
        reflexo INTEGER,
        manejo INTEGER,
        posicionamento INTEGER,
        chute INTEGER,
        velocidade INTEGER,
        timeId INTEGER,
        FOREIGN KEY(timeId) REFERENCES times(id)
    );
""")
        Log.d("DB_CREATE", "Tabela 'goleiros' criada com sucesso.")
        db.execSQL("""
            CREATE TABLE temporadas (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                ano INTEGER NOT NULL,
                ligaId INTEGER,
                FOREIGN KEY(ligaId) REFERENCES ligas(id)
            );
        """)
        Log.d("DB_CREATE", "Tabela 'temporadas' criada com sucesso.")
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
        Log.d("DB_CREATE", "Tabela 'partidas' criada com sucesso.")
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
        Log.d("DB_CREATE", "Tabela 'stats' criada com sucesso.")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS stats")
        db.execSQL("DROP TABLE IF EXISTS partidas")
        db.execSQL("DROP TABLE IF EXISTS temporadas")
        db.execSQL("DROP TABLE IF EXISTS goleiros")
        db.execSQL("DROP TABLE IF EXISTS jogadores")
        db.execSQL("DROP TABLE IF EXISTS times")
        db.execSQL("DROP TABLE IF EXISTS ligas")
        onCreate(db)
    }

    override fun onConfigure(db: SQLiteDatabase) {
        super.onConfigure(db)
        db.setForeignKeyConstraintsEnabled(true)
    }

}
