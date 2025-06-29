package com.tralkamy.tatika.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tralkamy.tatika.data.db.dao.*
import com.tralkamy.tatika.data.entity.*

@Database(
    entities = [
        TimeEntity::class,
        JogadorEntity::class,
        PartidaEntity::class,
        TemporadaEntity::class,
        EstatisticaJogadorEntity::class
    ],
    version = 1, // se mudar aqui, tem que lidar com migração
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun timeDao(): TimeDao
    abstract fun jogadorDao(): JogadorDao
    abstract fun partidaDao(): PartidaDao
    abstract fun temporadaDao(): TemporadaDao
    abstract fun estatisticaJogadorDao(): EstatisticaJogadorDao
}
