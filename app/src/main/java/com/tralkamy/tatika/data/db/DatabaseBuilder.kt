package com.tralkamy.tatika.data.db

import android.content.Context
import androidx.room.Room

object DatabaseBuilder {
    @Volatile
    private var INSTANCE: AppDatabase? = null

    fun getInstance(context: Context): AppDatabase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "tatika.db"
            )
                //.createFromAsset("tatika.db") // ⚠️ REMOVE ISSO
                .fallbackToDestructiveMigration() // apaga o banco antigo se versão mudar
                .build()
            INSTANCE = instance
            instance
        }
    }
}