package com.tralkamy.tatika.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tralkamy.tatika.data.entity.*
import com.tralkamy.tatika.data.db.dao.*

@Database(
    entities = [LigaEntity::class, TimeEntity::class, JogadorEntity::class],
    version = 1
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun ligaDao(): LigaDao
    abstract fun timeDao(): TimeDao
    abstract fun jogadorDao(): JogadorDao
}