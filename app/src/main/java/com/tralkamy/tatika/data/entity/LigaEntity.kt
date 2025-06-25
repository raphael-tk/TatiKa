package com.tralkamy.tatika.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ligas")
data class LigaEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nome: String
)