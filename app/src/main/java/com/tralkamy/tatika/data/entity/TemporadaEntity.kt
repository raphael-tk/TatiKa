package com.tralkamy.tatika.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "temporadas")
data class TemporadaEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val ano: Int,
    val descricao: String
)